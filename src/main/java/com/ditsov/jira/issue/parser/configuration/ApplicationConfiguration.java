package com.ditsov.jira.issue.parser.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.AnonymousAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.ditsov.jira.issue.parser.service.file.FileGeneratorService;
import com.ditsov.jira.issue.parser.service.file.JsonFileGeneratorService;
import com.ditsov.jira.issue.parser.service.file.XmlFileGeneratorService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConfigurationProperties(prefix = "jira-issue-parser")
@Getter
@Setter
@Slf4j
public class ApplicationConfiguration {

    private static final String XML_FILE_FORMAT = "xml";

    private String jiraRestApiIssueUri;
    private String fileFormat;
    private String dateFormatPattern;

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.any()).build();
    }

    @Bean
    public JiraRestClient getJiraRestClient() {
	URI jiraUri = null;
	try {
	    jiraUri = new URI(jiraRestApiIssueUri);
	} catch (URISyntaxException e) {
	    log.error(e.getLocalizedMessage());
	}

	return new AsynchronousJiraRestClientFactory().create(jiraUri, new AnonymousAuthenticationHandler());
    }

    @Bean
    public FileGeneratorService fileGeneratorService() {
	return XML_FILE_FORMAT.equals(fileFormat) ? new XmlFileGeneratorService() : new JsonFileGeneratorService();
    }


    @Bean
    public DateTimeFormatter getDateTimeFormat() {
	return new DateTimeFormatterBuilder().appendPattern(dateFormatPattern).toFormatter();
    }

}
