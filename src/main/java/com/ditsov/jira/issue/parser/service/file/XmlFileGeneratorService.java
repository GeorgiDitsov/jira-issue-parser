package com.ditsov.jira.issue.parser.service.file;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Service;

import com.ditsov.jira.issue.parser.model.issue.IssueDTO;
import com.ditsov.jira.issue.parser.model.issue.Issues;

@Service
public class XmlFileGeneratorService implements FileGeneratorService {

    /**
     * @see FileGeneratorService#generateFileFrom(List)
     */
    @Override
    public byte[] generateFileFrom(final List<IssueDTO> listOfIssues) throws JAXBException {
	JAXBContext context = JAXBContext.newInstance(Issues.class);
	Marshaller jaxbMarshaller = context.createMarshaller();
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	jaxbMarshaller.marshal(new Issues.Builder().addAllIssues(listOfIssues).build(), out);

	return out.toByteArray();
    }

}
