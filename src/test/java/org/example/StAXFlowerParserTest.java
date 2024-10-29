package org.example;

import generated.Flower;
import org.example.parsers.StAXFlowerParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StAXFlowerParserTest {

    @Test
    public void testParseFlowers() throws Exception {
        // Create a temporary XML file
        String xmlContent = """
                <Flower>
                    <Flowers id="1" Name="Rose" Origin="Ukraine" Multiplying="Cloning" Soil="Loam">
                        <Visual_Parameters>
                            <Stem_Color>Green</Stem_Color>
                            <Leaf_Color>Red</Leaf_Color>
                            <Average_Length>50.0</Average_Length>
                        </Visual_Parameters>
                        <Growing_Tips>
                            <Temperature>20.0</Temperature>
                            <Photophilous>true</Photophilous>
                            <Watering>500.0</Watering>
                            <Growth_Time>100</Growth_Time>
                        </Growing_Tips>
                    </Flowers>
                    <Flowers id="2" Name="Tulip" Origin="Netherlands" Multiplying="Seeds" Soil="Sand">
                        <Visual_Parameters>
                            <Stem_Color>Yellow</Stem_Color>
                            <Leaf_Color>Green</Leaf_Color>
                            <Average_Length>60.0</Average_Length>
                        </Visual_Parameters>
                        <Growing_Tips>
                            <Temperature>18.0</Temperature>
                            <Photophilous>false</Photophilous>
                            <Watering>600.0</Watering>
                            <Growth_Time>120</Growth_Time>
                        </Growing_Tips>
                    </Flowers>
                    <Flowers id="3" Name="Sunflower" Origin="USA" Multiplying="Leaves" Soil="Clay">
                        <Visual_Parameters>
                            <Stem_Color>Brown</Stem_Color>
                            <Leaf_Color>Yellow</Leaf_Color>
                            <Average_Length>80.0</Average_Length>
                        </Visual_Parameters>
                        <Growing_Tips>
                            <Temperature>25.0</Temperature>
                            <Photophilous>true</Photophilous>
                            <Watering>700.0</Watering>
                            <Growth_Time>140</Growth_Time>
                        </Growing_Tips>
                    </Flowers>
                </Flower>
                """;

        Path tempFile = Files.createTempFile("test-flowers", ".xml");
        Files.write(tempFile, xmlContent.getBytes());

        // Parse the XML file
        List<Flower> flowers = StAXFlowerParser.parseFlowers(tempFile.toString());

        // Validate the parsed data
        assertEquals(3, flowers.size());

        // Validate Flower 1
        Flower flower1 = flowers.getFirst();
        assertEquals("1", flower1.getId());
        assertEquals("Rose", flower1.getName());
        assertEquals("Ukraine", flower1.getOrigin());
        assertEquals("Cloning", flower1.getMultiplying());
        assertEquals("Loam", flower1.getSoil());
        assertEquals("Green", flower1.getVisualParameters().getStemColor());
        assertEquals("Red", flower1.getVisualParameters().getLeafColor());
        assertEquals(50.0, flower1.getVisualParameters().getAverageLength());
        assertEquals(20.0, flower1.getGrowingTips().getTemperature());
        assertTrue(flower1.getGrowingTips().isPhotophilous());
        assertEquals(500.0, flower1.getGrowingTips().getWatering());
        assertEquals(100, flower1.getGrowingTips().getGrowthTime());

        // Validate Flower 2
        Flower flower2 = flowers.get(1);
        assertEquals("2", flower2.getId());
        assertEquals("Tulip", flower2.getName());
        assertEquals("Netherlands", flower2.getOrigin());
        assertEquals("Seeds", flower2.getMultiplying());
        assertEquals("Sand", flower2.getSoil());
        assertEquals("Yellow", flower2.getVisualParameters().getStemColor());
        assertEquals("Green", flower2.getVisualParameters().getLeafColor());
        assertEquals(60.0, flower2.getVisualParameters().getAverageLength());
        assertEquals(18.0, flower2.getGrowingTips().getTemperature());
        assertFalse(flower2.getGrowingTips().isPhotophilous());
        assertEquals(600.0, flower2.getGrowingTips().getWatering());
        assertEquals(120, flower2.getGrowingTips().getGrowthTime());

        // Validate Flower 3
        Flower flower3 = flowers.get(2);
        assertEquals("3", flower3.getId());
        assertEquals("Sunflower", flower3.getName());
        assertEquals("USA", flower3.getOrigin());
        assertEquals("Leaves", flower3.getMultiplying());
        assertEquals("Clay", flower3.getSoil());
        assertEquals("Brown", flower3.getVisualParameters().getStemColor());
        assertEquals("Yellow", flower3.getVisualParameters().getLeafColor());
        assertEquals(80.0, flower3.getVisualParameters().getAverageLength());
        assertEquals(25.0, flower3.getGrowingTips().getTemperature());
        assertTrue(flower3.getGrowingTips().isPhotophilous());
        assertEquals(700.0, flower3.getGrowingTips().getWatering());
        assertEquals(140, flower3.getGrowingTips().getGrowthTime());

        // Delete the temporary file
        Files.delete(tempFile);
    }
}
