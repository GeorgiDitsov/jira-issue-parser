package com.ditsov.jira.issue.parser.controller;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ditsov.jira.issue.parser.model.issue.IssueDTO;
import com.ditsov.jira.issue.parser.service.file.FileGeneratorService;
import com.ditsov.jira.issue.parser.service.request.RequestService;
import com.fasterxml.jackson.core.JsonGenerationException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("rest/api/v1")
@AllArgsConstructor
public class BaseController {

    private static final String INTERNAL_SERVICE_ERROR_MSG = "Some internal service error occured when attempting to generate %s file.";

    private final RequestService requestService;
    private final FileGeneratorService fileGeneratorService;

    @GetMapping(value = "/{query}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getIssueAsFile(@PathVariable final String query) throws Exception {
	List<IssueDTO> listOfIssues = requestService.fetchIssuesByQuery(query);

	return fileGeneratorService.generateFileFrom(listOfIssues);
    }

    @ExceptionHandler({ JAXBException.class, JsonGenerationException.class })
    public ResponseEntity<String> handleInternalServiceError(final Exception exception) {
	String message = null;

	if (exception instanceof JAXBException) {
	    message = String.format(INTERNAL_SERVICE_ERROR_MSG, "xml");
	} else {
	    message = String.format(INTERNAL_SERVICE_ERROR_MSG, "json");
	}

	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

}
