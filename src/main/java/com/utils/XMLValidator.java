package com.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.constants.AppConstants;

public class XMLValidator {
	public static boolean validateXML(String xmlFilePath, String xsdFilePath) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdFilePath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlFilePath)));
			System.out.println("XML is valid.");
			return true;
		} catch (SAXException | IOException e) {
			System.out.println("XML Validation Failed: " + e.getMessage());
			return false;
		}
	}

	public static void main(String[] args) {
		String xmlFile = AppConstants.XML_FILE_PATH;
		String xsdFile = AppConstants.XSD_FILE_PATH;
		validateXML(xmlFile, xsdFile);
	}
}
