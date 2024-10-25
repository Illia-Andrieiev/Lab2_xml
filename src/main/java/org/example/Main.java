package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String xmlDocument = "xml_files/greenhouse.xml";
        String xmlSchema = "xml_files/greenhouse.xsd";
        boolean is_valid = XMLValidator.validationXML(xmlDocument,xmlSchema);
        System.out.println(is_valid);
    }
}