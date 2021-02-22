package com.ditsov.jira.issue.parser.factory.issue;

import java.util.Optional;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.ditsov.jira.issue.parser.factory.AbstractFactory;
import com.ditsov.jira.issue.parser.factory.issue.comment.IssueCommentFactory;
import com.ditsov.jira.issue.parser.model.issue.IssueDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IssueFactoryImpl implements IssueFactory {

    private final DateTimeFormatter dateTimeFormatter;
    private final IssueCommentFactory commentFactory;

    /**
     * @see AbstractFactory#convertToDTO(Object)
     */
    @Override
    public IssueDTO convertToDTO(final Issue issue) {
	IssueDTO dto = new IssueDTO();

	dto.setKey(issue.getKey());
	dto.setSummary(issue.getSummary());
	dto.setUrl(issue.getSelf().toString());
	dto.setType(issue.getIssueType().getName());
	dto.setDescription(issue.getDescription());
	dto.setCreatedDate(issue.getCreationDate().toString(dateTimeFormatter));

	Optional.ofNullable(issue.getPriority()).ifPresent(priority -> dto.setPriority(priority.getName()));
	Optional.ofNullable(issue.getReporter()).ifPresent(reporter -> dto.setReporterName(reporter.getDisplayName()));

	issue.getComments().forEach(comment -> dto.getComments().add(commentFactory.convertToDTO(comment)));

	return dto;
    }

}
