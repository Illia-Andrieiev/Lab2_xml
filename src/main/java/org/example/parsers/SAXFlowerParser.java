package org.example.parsers;
// import org.apache.tools.ant.types.resources.selectors.None;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import generated.Flower;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SAXFlowerParser extends DefaultHandler {
    private List<Flower> flowers;
    private Flower currentFlower;
    private Flower.VisualParameters visualParameters;
    private Flower.GrowingTips growingTips;
    private StringBuilder content;

    // Returns the list of Flower objects
    public List<Flower> getFlowers() {
        return flowers;
    }

    // Called when the document starts
    @Override
    public void startDocument() {
        flowers = new ArrayList<>();
        content = new StringBuilder();
    }

    // Called when an element starts
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "Flowers":
                currentFlower = new Flower();
                currentFlower.setId(attributes.getValue("id"));
                currentFlower.setName(attributes.getValue("Name"));
                currentFlower.setOrigin(attributes.getValue("Origin"));
                currentFlower.setMultiplying(attributes.getValue("Multiplying"));
                currentFlower.setSoil(attributes.getValue("Soil"));
                break;
            case "Visual_Parameters":
                visualParameters = new Flower.VisualParameters();
                break;
            case "Growing_Tips":
                growingTips = new Flower.GrowingTips();
                break;
        }
    }

    // Called when an element ends
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "Flowers":
                flowers.add(currentFlower);
                break;
            case "Visual_Parameters":
                currentFlower.setVisualParameters(visualParameters);
                break;
            case "Growing_Tips":
                currentFlower.setGrowingTips(growingTips);
                break;
            case "Stem_Color":
                visualParameters.setStemColor(content.toString().trim());
                break;
            case "Leaf_Color":
                visualParameters.setLeafColor(content.toString().trim());
                break;
            case "Average_Length":
                visualParameters.setAverageLength(Double.parseDouble(content.toString().trim()));
                break;
            case "Temperature":
                growingTips.setTemperature(Float.parseFloat(content.toString().trim()));
                break;
            case "Photophilous":
                growingTips.setPhotophilous(Boolean.parseBoolean(content.toString().trim()));
                break;
            case "Watering":
                growingTips.setWatering(Double.parseDouble(content.toString().trim()));
                break;
            case "Growth_Time":
                growingTips.setGrowthTime(Integer.parseInt(content.toString().trim()));
                break;
        }
        content.setLength(0); // Clear the content buffer
    }

    // Called when characters are encountered within an element
    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    // Parses an XML file that is validated by the greenhouse.xsd schema
    public static List<Flower> parseFlowers(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXFlowerParser handler = new SAXFlowerParser();
            saxParser.parse(fileName, handler);
            return handler.getFlowers();
        } catch (Exception e) {
            String exMess = e.toString();
            System.out.println(exMess);
            return null;
        }
    }

    // Parses a directory of XML documents that are validated by the greenhouse.xsd schema
    public static List<Flower> parseDirectory(String directory) {
        List<Flower> flowers = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(directory), "*.xml")) {
            for (Path file : stream) {
                flowers.addAll(Objects.requireNonNull(parseFlowers(file.toString())));
            }
        } catch (IOException e) {
            String exMess = e.toString();
            System.out.println(exMess);
        }
        return flowers;
    }

    public static void main(String[] args) {
        // Parse the directory of XML files and print each flower
        List<Flower> f = SAXFlowerParser.parseDirectory("src/main/java/org/example/xml_files/flowers");
        for (Flower fl : f) {
            fl.print();
            System.out.println("*********************");
        }
    }
}
