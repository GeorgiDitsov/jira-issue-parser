package com.ditsov.jira.issue.parser.factory.issue.comment;

import com.atlassian.jira.rest.client.api.domain.Comment;
import com.ditsov.jira.issue.parser.factory.AbstractFactory;
import com.ditsov.jira.issue.parser.model.issue.comment.IssueCommentDTO;

public interface IssueCommentFactory extends AbstractFactory<Comment, IssueCommentDTO> {

}
