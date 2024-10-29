package org.example.parsers;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.Attribute;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import generated.Flower;

public class StAXFlowerParser {
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
        Flower flower = null;
        Flower.VisualParameters visualParams = null;
        Flower.GrowingTips growingTips = null;

        try {
            // Create an XMLInputFactory instance
            XMLInputFactory factory = XMLInputFactory.newInstance();

            // Create an XMLEventReader for reading XML events
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(filePath));

            // Iterate over XML events
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();

                    // Handle different start elements
                    switch (qName) {
                        case "Flowers":
                            flower = new Flower();
                            Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                            Attribute nameAttr = startElement.getAttributeByName(new QName("Name"));
                            Attribute originAttr = startElement.getAttributeByName(new QName("Origin"));
                            Attribute multiplyingAttr = startElement.getAttributeByName(new QName("Multiplying"));
                            Attribute soilAttr = startElement.getAttributeByName(new QName("Soil"));
                            if (idAttr != null) flower.setId(idAttr.getValue());
                            if (nameAttr != null) flower.setName(nameAttr.getValue());
                            if (originAttr != null) flower.setOrigin(originAttr.getValue());
                            if (multiplyingAttr != null) flower.setMultiplying(multiplyingAttr.getValue());
                            if (soilAttr != null) flower.setSoil(soilAttr.getValue());
                            break;
                        case "Visual_Parameters":
                            visualParams = new Flower.VisualParameters();
                            break;
                        case "Stem_Color":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(visualParams).setStemColor(event.asCharacters().getData().trim());
                            break;
                        case "Leaf_Color":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(visualParams).setLeafColor(event.asCharacters().getData().trim());
                            break;
                        case "Average_Length":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(visualParams).setAverageLength(Double.parseDouble(event.asCharacters().getData().trim()));
                            break;
                        case "Growing_Tips":
                            growingTips = new Flower.GrowingTips();
                            break;
                        case "Temperature":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(growingTips).setTemperature(Float.parseFloat(event.asCharacters().getData().trim()));
                            break;
                        case "Photophilous":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(growingTips).setPhotophilous(Boolean.parseBoolean(event.asCharacters().getData().trim()));
                            break;
                        case "Watering":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(growingTips).setWatering(Double.parseDouble(event.asCharacters().getData().trim()));
                            break;
                        case "Growth_Time":
                            event = eventReader.nextEvent();
                            Objects.requireNonNull(growingTips).setGrowthTime(Integer.parseInt(event.asCharacters().getData().trim()));
                            break;
                    }
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();

                    // Handle end elements
                    if (endElement.getName().getLocalPart().equals("Flowers")) {
                        flowers.add(flower);
                    } else if (endElement.getName().getLocalPart().equals("Visual_Parameters")) {
                        Objects.requireNonNull(flower).setVisualParameters(visualParams);
                    } else if (endElement.getName().getLocalPart().equals("Growing_Tips")) {
                        Objects.requireNonNull(flower).setGrowingTips(growingTips);
                    }
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
