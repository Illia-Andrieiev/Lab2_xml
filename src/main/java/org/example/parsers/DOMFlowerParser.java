package org.example.parsers;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import generated.Flower;

public class DOMFlowerParser {
    public static void main(String[] args) {
        // Parse the XML file and get a list of Flower objects
        List<Flower> flowers = parseFlowers("src/main/java/org/example/xml_files/flowers/flowers.xml");

        // Print the details of each Flower object
        for (Flower flower : flowers) {
            flower.print();
            System.out.println("**************************************");
        }
    }
    // parse xml document, that validated by greenhouse.xsd schema
    public static List<Flower> parseFlowers(String filePath) {
        List<Flower> flowers = new ArrayList<>();

        try {
            // Load the XML file
            File inputFile = new File(filePath);

            // Create a DocumentBuilderFactory and DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parse the XML file and normalize the document
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Get all the elements with the tag name "Flowers"
            NodeList nodeList = doc.getElementsByTagName("Flowers");

            // Iterate through each element in the nodeList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // Check if the node is an element node
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Create a new Flower object
                    Flower flower = new Flower();

                    // Set the attributes of the Flower object
                    flower.setId(element.getAttribute("id"));
                    flower.setName(element.getAttribute("Name"));
                    flower.setOrigin(element.getAttribute("Origin"));
                    flower.setMultiplying(element.getAttribute("Multiplying"));
                    flower.setSoil(element.getAttribute("Soil"));

                    // Get the Visual_Parameters element and its child elements
                    Element visualParamsElement = (Element) element.getElementsByTagName("Visual_Parameters").item(0);
                    Flower.VisualParameters visualParams = new Flower.VisualParameters();
                    visualParams.setStemColor(visualParamsElement.getElementsByTagName("Stem_Color").item(0).getTextContent().trim());
                    visualParams.setLeafColor(visualParamsElement.getElementsByTagName("Leaf_Color").item(0).getTextContent().trim());
                    visualParams.setAverageLength(Double.parseDouble(visualParamsElement.getElementsByTagName("Average_Length").item(0).getTextContent().trim()));
                    flower.setVisualParameters(visualParams);

                    // Get the Growing_Tips element and its child elements
                    Element growingTipsElement = (Element) element.getElementsByTagName("Growing_Tips").item(0);
                    Flower.GrowingTips growingTips = new Flower.GrowingTips();
                    growingTips.setTemperature(Float.parseFloat(growingTipsElement.getElementsByTagName("Temperature").item(0).getTextContent().trim()));
                    growingTips.setPhotophilous(Boolean.parseBoolean(growingTipsElement.getElementsByTagName("Photophilous").item(0).getTextContent().trim()));
                    growingTips.setWatering(Double.parseDouble(growingTipsElement.getElementsByTagName("Watering").item(0).getTextContent().trim()));
                    growingTips.setGrowthTime(Integer.parseInt(growingTipsElement.getElementsByTagName("Growth_Time").item(0).getTextContent().trim()));
                    flower.setGrowingTips(growingTips);

                    // Add the Flower object to the list
                    flowers.add(flower);
                }
            }
        } catch (Exception e) {
            // Print any exceptions that occur
            String exMess = e.toString();
            System.out.println(exMess);
        }

        // Return the list of Flower objects
        return flowers;
    }
}
