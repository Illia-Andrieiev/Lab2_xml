package org.example;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorTest {

    String xmlSchema = "src/main/java/org/example/xml_files/greenhouse.xsd";

    @org.junit.jupiter.api.Test
    void validationXMLCorrect() {
        String xmlDocumentCorrect = "src/main/java/org/example/xml_files/flowers/flowers_test_correct.xml";
        assertTrue(XMLValidator.validationXML(xmlDocumentCorrect, xmlSchema), "result of validation must be True");
    }
    @org.junit.jupiter.api.Test
    void validationXMLIncorrect1() {
        String xmlDocumentIncorrect = "src/main/java/org/example/xml_files/flowers/flowers_test_incorrect1.xml";
        assertFalse(XMLValidator.validationXML(xmlDocumentIncorrect, xmlSchema), "attributes are required");
    }
    @org.junit.jupiter.api.Test
    void validationXMLIncorrect2() {
        String xmlDocumentIncorrect = "src/main/java/org/example/xml_files/flowers/flowers_test_incorrect2.xml";
        assertFalse(XMLValidator.validationXML(xmlDocumentIncorrect, xmlSchema), "all sub elements are required");
    }
    @org.junit.jupiter.api.Test
    void validationXMLIncorrect3() {
        String xmlDocumentIncorrect = "src/main/java/org/example/xml_files/flowers/flowers_test_incorrect3.xml";
        assertFalse(XMLValidator.validationXML(xmlDocumentIncorrect, xmlSchema), "root element must be \"Flower\"");

    }
    @org.junit.jupiter.api.Test
    void validationXMLIncorrect4() {
        String xmlDocumentIncorrect = "src/main/java/org/example/xml_files/flowers/flowers_test_incorrect4.xml";
        assertFalse(XMLValidator.validationXML(xmlDocumentIncorrect, xmlSchema), "value must be from enumeration");
    }

}