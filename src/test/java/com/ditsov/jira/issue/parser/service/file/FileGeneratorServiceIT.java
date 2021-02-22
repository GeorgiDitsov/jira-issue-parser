package com.ditsov.jira.issue.parser.service.file;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ditsov.jira.issue.parser.model.issue.IssueDTO;
import com.ditsov.jira.issue.parser.model.issue.comment.IssueCommentDTO;

@ExtendWith(SpringExtension.class)
public class FileGeneratorServiceIT {

    private List<IssueDTO> issues = null;

    private FileGeneratorService fileGeneratorService;

    @BeforeEach
    public void testInit() {
	List<IssueCommentDTO> commentsOne = List.of(generateMockIssueCommentDTO("someone", "12345"), generateMockIssueCommentDTO("ivan", "54321"));
	List<IssueCommentDTO> coomentsTwo = List.of(generateMockIssueCommentDTO("harry", "555"), generateMockIssueCommentDTO("pam", "Hey!"));
	IssueDTO issueOne = generateMockIssueDTO("123", "456", "789", "Bug", "Low", "Example", "someone", "01.01.2021 00:00:01", commentsOne);
	IssueDTO issueTwo = generateMockIssueDTO("987", "654", "321", "Feature", "High", "Example", "someone",
		"05.05.2021 00:00:01", coomentsTwo);
	issues = List.of(issueOne, issueTwo);
    }

    @Test
    @DisplayName("Verifies that generating the JSON file does not throw any kind of Exception.")
    public void testOne() {
	// Given
	fileGeneratorService = new JsonFileGeneratorService();

	// When
	Executable attemptingToGenerateJsonFile = () -> fileGeneratorService.generateFileFrom(issues);

	// Then
	assertDoesNotThrow(attemptingToGenerateJsonFile);
    }

    @Test
    @DisplayName("Verifies that generating the XML file does not throw any kind of Exception.")
    public void testTwo() {
	// Given
	fileGeneratorService = new XmlFileGeneratorService();

	// When
	Executable attemptingToGenerateXmlFile = () -> fileGeneratorService.generateFileFrom(issues);

	// Then
	assertDoesNotThrow(attemptingToGenerateXmlFile);
    }

    @Test
    @DisplayName("Verifies that generating the JSON file returns byte array which is not null.")
    public void testThree() throws Exception {
	// Given
	fileGeneratorService = new JsonFileGeneratorService();

	// When
	byte[] result = fileGeneratorService.generateFileFrom(issues);

	// Then
	assertNotNull(result);
    }

    @Test
    @DisplayName("Verifies that generating the XML file returns byte array which is not null.")
    public void testFour() throws Exception {
	// Given
	fileGeneratorService = new XmlFileGeneratorService();

	// When
	byte[] result = fileGeneratorService.generateFileFrom(issues);

	// Then
	assertNotNull(result);
    }

    private IssueDTO generateMockIssueDTO(final String key, final String summary, final String url, final String type,
	    final String priority, final String description, final String reporterName, final String createdDate,
	    final List<IssueCommentDTO> comments) {
	IssueDTO issue = new IssueDTO();

	issue.setKey(key);
	issue.setSummary(summary);
	issue.setUrl(url);
	issue.setType(type);
	issue.setPriority(priority);
	issue.setDescription(description);
	issue.setReporterName(reporterName);
	issue.setCreatedDate(createdDate);
	issue.setComments(comments);

	return issue;

    }

    private IssueCommentDTO generateMockIssueCommentDTO(final String authorName, final String text) {
	IssueCommentDTO comment = new IssueCommentDTO();

	comment.setAuthorName(authorName);
	comment.setText(text);

	return comment;
    }

}
