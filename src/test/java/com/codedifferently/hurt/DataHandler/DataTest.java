package com.codedifferently.hurt.DataHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DataTest {

    private DataHandler dataHandler;

    @Before
    public void init() {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016";
        this.dataHandler = new DataHandler(rawData);
    }

    @Test
    public void testConstructor() {
        String[] properties = { "milk", "4.89", "food", "12/11/20" };
        Data data = new Data(properties, false);
        Assert.assertEquals("12/11/20", data.expiration);
    }

    @Test
    public void testFuzzyMatchedFalse() {
        String[] properties = { "milk", "4.89", "food", "12/11/20" };
        Data data = new Data(properties, false);
        Assert.assertFalse(data.getFuzzyMatched());
    }

    @Test
    public void testFuzzyMatchedTrue() {
        String[] properties = { "milk", "4.89", "food", "12/11/20" };
        Data data = new Data(properties, true);
        Assert.assertTrue(data.getFuzzyMatched());
    }

    @Test
    public void testGetAdditionalProperties() {
        String[] properties = { "milk", "4.89", "food", "12/11/20", "shoes", "1738", "ambulence" };
        Data data = new Data(properties, true);
        int expected = 3;
        List<String> additionalProperties = data.additionalProperties;
        int actual = additionalProperties.size();
        Assert.assertEquals(expected, actual);
    }
}
