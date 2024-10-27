package org.example;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTransformer {
    public static void transformXML(String xmlFilename, String xslFilename, String resultFilename) {
        try {
            // xml file
            StreamSource xmlSource = new StreamSource(new File(xmlFilename));
            // xsl file
            StreamSource xslSource = new StreamSource(new File(xslFilename));
            // result transformation
            StreamResult outputTarget = new StreamResult(new File(resultFilename));

            // create transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslSource);

            // transform xml
            transformer.transform(xmlSource, outputTarget);

        } catch (Exception e) {
            String exMess = e.toString();
            System.out.println(exMess);
        }
    }
}
