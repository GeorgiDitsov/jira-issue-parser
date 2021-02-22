package com.ditsov.jira.issue.parser.factory.issue.comment;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.atlassian.jira.rest.client.api.domain.Comment;
import com.ditsov.jira.issue.parser.factory.AbstractFactory;
import com.ditsov.jira.issue.parser.model.issue.comment.IssueCommentDTO;

@Component
public class IssueCommentFactoryImpl implements IssueCommentFactory {

    /**
     * @see AbstractFactory#convertToDTO(Object)
     */
    @Override
    public IssueCommentDTO convertToDTO(final Comment comment) {
	IssueCommentDTO dto = new IssueCommentDTO();

	dto.setText(comment.getBody());

	Optional.ofNullable(comment.getAuthor()).ifPresent(author -> dto.setAuthorName(author.getDisplayName()));

	return dto;
    }

}
