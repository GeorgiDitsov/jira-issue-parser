package com.ditsov.jira.issue.parser.service.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;
import com.ditsov.jira.issue.parser.factory.issue.IssueFactory;
import com.ditsov.jira.issue.parser.model.issue.IssueDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final JiraRestClient jiraRestClient;
    private final IssueFactory issueFactory;

    /**
     * @see RequestService#fetchIssuesByQuery(String)
     */
    @Override
    public List<IssueDTO> fetchIssuesByQuery(final String query) {
	Promise<SearchResult> searchResultPromise = jiraRestClient.getSearchClient().searchJql(query, 50, 0,
		Set.of("*all"));

	List<IssueDTO> results = new ArrayList<>();

	searchResultPromise.claim().getIssues().forEach(issue -> results.add(issueFactory.convertToDTO(issue)));

	return results;
    }

}
