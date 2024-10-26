package org.example;
// import org.apache.tools.ant.types.resources.selectors.None;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
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
import java.nio.file.Paths;

public class SAXFlowerParser extends DefaultHandler {
    private List<Flower> flowers;
    private Flower currentFlower;
    private Flower.VisualParameters visualParameters;
    private Flower.GrowingTips growingTips;
    private StringBuilder content;

    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startDocument() throws SAXException {
        flowers = new ArrayList<>();
        content = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Flower":
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

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Flower":
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
                growingTips.setGrowthTime((Integer.parseInt(content.toString().trim())));
                break;
        }
        content.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content.append(ch, start, length);
    }

    public static Flower parseFile(String fileName){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXFlowerParser handler = new SAXFlowerParser();
            saxParser.parse(fileName, handler);

            List<Flower> flowers = handler.getFlowers();
            return flowers.getFirst();
        } catch (Exception e) {
            String exMess = e.toString();
            System.out.println(exMess);
            return null;
        }
    }
    public static List<Flower> parseDirectory(String directory){
        List<Flower> flowers = new ArrayList<Flower>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(directory), "*.xml")) {
            for (Path file : stream) {
                flowers.add(parseFile(file.toString()));
            }
        } catch (IOException e) {
            String exMess = e.toString();
            System.out.println(exMess);
        }
        return flowers;
    }

    public static void main(String[] args) {
        List<Flower> f = SAXFlowerParser.parseDirectory("src/main/java/org/example/xml_files/flowers");
        for (Flower fl : f){
            fl.print();
            System.out.println("*********************");
        }

    }
}
