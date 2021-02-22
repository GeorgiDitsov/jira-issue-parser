package com.ditsov.jira.issue.parser.service.file;

import java.util.List;

import com.ditsov.jira.issue.parser.model.issue.IssueDTO;

public interface FileGeneratorService {

    /**
     * Generates file as array of bytes from {@link List} of {@link IssueDTO}s.
     *
     * @param listOfIssues list of {@link IssueDTO}s.
     *
     * @return file as array of bytes.
     *
     * @throws Exception
     */
    public byte[] generateFileFrom(final List<IssueDTO> listOfIssues) throws Exception;

}
