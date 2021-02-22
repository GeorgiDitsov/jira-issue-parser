package com.ditsov.jira.issue.parser.factory.issue;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.ditsov.jira.issue.parser.factory.AbstractFactory;
import com.ditsov.jira.issue.parser.model.issue.IssueDTO;

public interface IssueFactory extends AbstractFactory<Issue, IssueDTO> {

}
