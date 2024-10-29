package generated;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class FlowerTest {
    private List<Flower> flowers;

    @BeforeEach
    public void setUp() {
        flowers = new ArrayList<>();

        // Flower 1
        Flower flower1 = new Flower();
        flower1.setId("1");
        flower1.setName("Rose");
        flower1.setOrigin("Ukraine");
        flower1.setMultiplying("Cloning");
        flower1.setSoil("Loam");
        Flower.VisualParameters visualParams1 = new Flower.VisualParameters();
        visualParams1.setStemColor("Green");
        visualParams1.setLeafColor("Red");
        visualParams1.setAverageLength(50.0);
        flower1.setVisualParameters(visualParams1);
        Flower.GrowingTips growingTips1 = new Flower.GrowingTips();
        growingTips1.setTemperature(20.0f);
        growingTips1.setPhotophilous(true);
        growingTips1.setWatering(500.0);
        growingTips1.setGrowthTime(100);
        flower1.setGrowingTips(growingTips1);


        // Flower 2
        Flower flower2 = new Flower();
        flower2.setId("2");
        flower2.setName("Tulip");
        flower2.setOrigin("Netherlands");
        flower2.setMultiplying("Seeds");
        flower2.setSoil("Sand");
        Flower.VisualParameters visualParams2 = new Flower.VisualParameters();
        visualParams2.setStemColor("Yellow");
        visualParams2.setLeafColor("Green");
        visualParams2.setAverageLength(60.0);
        flower2.setVisualParameters(visualParams2);
        Flower.GrowingTips growingTips2 = new Flower.GrowingTips();
        growingTips2.setTemperature(18.0f);
        growingTips2.setPhotophilous(false);
        growingTips2.setWatering(600.0);
        growingTips2.setGrowthTime(120);
        flower2.setGrowingTips(growingTips2);


        // Flower 3
        Flower flower3 = new Flower();
        flower3.setId("3");
        flower3.setName("Sunflower");
        flower3.setOrigin("USA");
        flower3.setMultiplying("Leaves");
        flower3.setSoil("Clay");
        Flower.VisualParameters visualParams3 = new Flower.VisualParameters();
        visualParams3.setStemColor("Brown");
        visualParams3.setLeafColor("Yellow");
        visualParams3.setAverageLength(80.0);
        flower3.setVisualParameters(visualParams3);
        Flower.GrowingTips growingTips3 = new Flower.GrowingTips();
        growingTips3.setTemperature(25.0f);
        growingTips3.setPhotophilous(true);
        growingTips3.setWatering(700.0);
        growingTips3.setGrowthTime(140);
        flower3.setGrowingTips(growingTips3);
        flowers.add(flower3);
        flowers.add(flower1);
        flowers.add(flower2);
    }

    @Test
    public void testSortByName() {
        Flower.sortFlowers(flowers, "name");
        assertEquals("Rose", flowers.get(0).getName());
        assertEquals("Sunflower", flowers.get(1).getName());
        assertEquals("Tulip", flowers.get(2).getName());
    }

    @Test
    public void testSortByOrigin() {
        Flower.sortFlowers(flowers, "origin");
        assertEquals("USA", flowers.get(1).getOrigin());
        assertEquals("Ukraine", flowers.get(2).getOrigin());
        assertEquals("Netherlands", flowers.get(0).getOrigin());
    }

    @Test
    public void testSortById() {
        Flower.sortFlowers(flowers, "id");
        assertEquals("1", flowers.get(0).getId());
        assertEquals("2", flowers.get(1).getId());
        assertEquals("3", flowers.get(2).getId());
    }

    @Test
    public void testSortByMultiplying() {
        Flower.sortFlowers(flowers, "multiplying");
        assertEquals("Cloning", flowers.get(0).getMultiplying());
        assertEquals("Leaves", flowers.get(1).getMultiplying());
        assertEquals("Seeds", flowers.get(2).getMultiplying());
    }

    @Test
    public void testSortBySoil() {
        Flower.sortFlowers(flowers, "soil");
        assertEquals("Clay", flowers.get(0).getSoil());
        assertEquals("Loam", flowers.get(1).getSoil());
        assertEquals("Sand", flowers.get(2).getSoil());
    }

    @Test
    public void testSortByTemperature() {
        Flower.sortFlowers(flowers, "temperature");
        assertEquals(18.0f, flowers.get(0).getGrowingTips().getTemperature());
        assertEquals(20.0f, flowers.get(1).getGrowingTips().getTemperature());
        assertEquals(25.0f, flowers.get(2).getGrowingTips().getTemperature());
    }

    @Test
    public void testSortByPhotophilous() {
        Flower.sortFlowers(flowers, "photophilous");
        assertFalse(flowers.get(0).getGrowingTips().isPhotophilous());
        assertTrue(flowers.get(1).getGrowingTips().isPhotophilous());
        assertTrue(flowers.get(2).getGrowingTips().isPhotophilous());
    }

    @Test
    public void testSortByWatering() {
        Flower.sortFlowers(flowers, "watering");
        assertEquals(500.0, flowers.get(0).getGrowingTips().getWatering());
        assertEquals(600.0, flowers.get(1).getGrowingTips().getWatering());
        assertEquals(700.0, flowers.get(2).getGrowingTips().getWatering());
    }

    @Test
    public void testSortByGrowthTime() {
        Flower.sortFlowers(flowers, "growth_time");
        assertEquals(100, flowers.get(0).getGrowingTips().getGrowthTime());
        assertEquals(120, flowers.get(1).getGrowingTips().getGrowthTime());
        assertEquals(140, flowers.get(2).getGrowingTips().getGrowthTime());
    }

    @Test
    public void testSortByStemColor() {
        Flower.sortFlowers(flowers, "stem_color");
        assertEquals("Brown", flowers.get(0).getVisualParameters().getStemColor());
        assertEquals("Green", flowers.get(1).getVisualParameters().getStemColor());
        assertEquals("Yellow", flowers.get(2).getVisualParameters().getStemColor());
    }

    @Test
    public void testSortByLeafColor() {
        Flower.sortFlowers(flowers, "leaf_color");
        assertEquals("Green", flowers.get(0).getVisualParameters().getLeafColor());
        assertEquals("Red", flowers.get(1).getVisualParameters().getLeafColor());
        assertEquals("Yellow", flowers.get(2).getVisualParameters().getLeafColor());
    }

    @Test
    public void testSortByAverageLength() {
        Flower.sortFlowers(flowers, "average_length");
        assertEquals(50.0, flowers.get(0).getVisualParameters().getAverageLength());
        assertEquals(60.0, flowers.get(1).getVisualParameters().getAverageLength());
        assertEquals(80.0, flowers.get(2).getVisualParameters().getAverageLength());
    }

}
