package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Enums.SortOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DataParserTest {

    private DataParser dataParser;

    @Before
    public void init() {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        DataHandler dataHandler = new DataHandler(rawData);
        this.dataParser = (DataParser) dataHandler.getDataParser();
    }

    @Test
    public void testInstance() {
        Assert.assertTrue(dataParser instanceof DataParser);
    }

    @Test
    public void testGetData() {
        List<Data> dataList = dataParser.getDataList();
        Assert.assertTrue(dataList.size() > 0);
    }

    @Test
    public void testGetInstancesOfEveryName() {
        Map<String, List<Data>> instances = dataParser.getInstancesOfEveryName();

        int expected = 4;
        Assert.assertEquals(expected, instances.size());
    }

    @Test
    public void testMilkNameOccurs6Times() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> occurences = dataParser.getNamesAndOccurences(dataList);

        int expected = 6;
        int actual = occurences.get("milk");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDollarTwentyThreePriceOccurs6Times() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> occurences = dataParser.getPricesAndOccurences(dataList);

        int expected = 7;
        int actual = occurences.get("1.23");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFoodTypeOccurs24Times() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> occurences = dataParser.getTypesAndOccurences(dataList);

        int expected = 24;
        int actual = occurences.get("food");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDateOccurs10Times() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> occurences = dataParser.getExpirationsAndOccurences(dataList);

        int expected = 10;
        int actual = occurences.get("1/25/2016");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testBreadOccurs6TimesWithLambda() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> occurences = dataParser.getPropertyAndOccurences(dataList, d -> d.name);

        int expected = 6;
        int actual = occurences.get("bread");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortPricesNumericallyGreatestToLowest() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> prices = dataParser.getPricesAndOccurences(dataList);

        String[] expected = { "3.23", "2.25", "1.23", "0.25", "0.23" };
        String[] actual = dataParser.sortPricesNumerically(prices, SortOrder.GREATESTTOLOWEST);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortPricesNumericallyLowestToGreatest() {
        List<Data> dataList = dataParser.getDataList();
        Map<String, Integer> prices = dataParser.getPricesAndOccurences(dataList);

        String[] expected = { "0.23", "0.25", "1.23", "2.25", "3.23" };
        String[] actual = dataParser.sortPricesNumerically(prices, SortOrder.LOWESTTOGREATEST);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testFuzzyMatchCountIs1() {
        int expected = 1;
        int actual = dataParser.getFuzzyMatchCount();
        Assert.assertEquals(expected, actual);
    }
}
