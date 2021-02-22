package com.ditsov.jira.issue.parser.model.issue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.ditsov.jira.issue.parser.model.issue.comment.IssueCommentDTO;

import lombok.Data;

@XmlRootElement(name = "issue")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class IssueDTO implements Serializable {

    private static final long serialVersionUID = -5812018105914219321L;

    private String key;
    private String summary;
    private String url;
    private String type;
    private String priority;
    private String description;
    private String reporterName;
    private String createdDate;

    @XmlElementWrapper(name = "comments")
    @XmlElement(name = "comment")
    private List<IssueCommentDTO> comments = new ArrayList<>();

}
