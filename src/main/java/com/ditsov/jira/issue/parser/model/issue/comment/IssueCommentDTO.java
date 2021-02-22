package com.ditsov.jira.issue.parser.model.issue.comment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "comment")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class IssueCommentDTO implements Serializable {

    private static final long serialVersionUID = -4083388247890261701L;

    private String authorName;
    private String text;

}
