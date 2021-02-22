package com.ditsov.jira.issue.parser.service.request;

import java.util.List;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.ditsov.jira.issue.parser.model.issue.IssueDTO;

public interface RequestService {

    /**
     * Obtains entities of type {@link Issue} by JQL query and converts them into
     * {@link List} of {@link IssueDTO}s.
     *
     * @param query JQL query.
     *
     * @return {@link List} of {@link IssueDTO}s.
     */
    List<IssueDTO> fetchIssuesByQuery(final String query);

}
