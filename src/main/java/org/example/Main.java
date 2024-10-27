package org.example;
import generated.Flower;
import org.example.parsers.SAXFlowerParser;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String xmlDocument = "src/main/java/org/example/xml_files/flowers/flowers.xml";
        String xmlSchema = "src/main/java/org/example/xml_files/greenhouse.xsd";
        String xslTransformation = "src/main/java/org/example/xml_files/transformation.xsl";
        boolean is_valid = XMLValidator.validationXML(xmlDocument,xmlSchema);
        System.out.println(is_valid);
        if(is_valid){
            List<Flower> flowers = SAXFlowerParser.parseFile(xmlDocument);
            if(flowers == null)
                return;
            for (Flower flower : flowers) {
                flower.print();
                System.out.println("*********************");
            }
            System.out.println("////////////////////");
            Flower.sortFlowers(flowers,"photophilous");
            for (Flower flower : flowers) {
                flower.print();
                System.out.println("*********************");
            }
        }
        String resFilename = "src/main/java/org/example/xml_files/result_transformation.xml";
        XSLTTransformer.transformXML(xmlDocument,xslTransformation,resFilename);
    }
}

//    <Name> Роза </Name>
//    <Soil> Підзолистий </Soil>
//    <Origin> Україна </Origin>
//    <Multiplying> живцями </Multiplying>