package com.ditsov.jira.issue.parser.model.issue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "issues")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Issues implements Serializable {

    private static final long serialVersionUID = -7615512975818995565L;

    @XmlElement(name = "issue")
    private List<IssueDTO> listOfIssues = null;

    public static class Builder {

	private List<IssueDTO> listOfIssues = new ArrayList<>();

	public Builder addIssue(final IssueDTO issue) {
	    this.listOfIssues.add(issue);

	    return this;
	}

	public Builder addAllIssues(final List<IssueDTO> listOfIssues) {
	    this.listOfIssues = listOfIssues;

	    return this;
	}

	public Issues build() {
	    Issues issues = new Issues();

	    issues.listOfIssues = this.listOfIssues;

	    return issues;
	}
    }

}
