package org.example;
import generated.Flower;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String xmlDocument = "src/main/java/org/example/xml_files/flowers/f1.xml";
        String xmlSchema = "src/main/java/org/example/xml_files/greenhouse.xsd";
        boolean is_valid = XMLValidator.validationXML(xmlDocument,xmlSchema);
        System.out.println(is_valid);
    }
}

//    <Name> Роза </Name>
//    <Soil> Підзолистий </Soil>
//    <Origin> Україна </Origin>
//    <Multiplying> живцями </Multiplying>