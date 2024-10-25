package org.example;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XMLValidator {
    public static boolean validationXML(String xmlDocument, String xmlSchema ) {
        try {
            // Create schema factory
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Load schema
            Schema schema = factory.newSchema(new File(xmlSchema));

            // Create validator
            Validator validator = schema.newValidator();

            // Validating xml document
            validator.validate(new StreamSource(new File(xmlDocument)));
            return true;

        } catch (SAXException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
