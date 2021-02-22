package com.ditsov.jira.issue.parser.service.file;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ditsov.jira.issue.parser.model.issue.IssueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonFileGeneratorService implements FileGeneratorService {

    /**
     * @see FileGeneratorService#generateFileFrom(List)
     */
    @Override
    public byte[] generateFileFrom(final List<IssueDTO> listOfIssues) throws Exception {
	ObjectMapper mapper = new ObjectMapper();

	return mapper.writeValueAsBytes(listOfIssues);
    }

}
