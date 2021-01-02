package com.codedifferently.hurt.DataHandler;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RawDataParserTest {

    @Test
    public void convertJSONToObjectsTest() {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;";
        RawDataParser rawDataParser = new RawDataParser();
        List<Data> data = rawDataParser.convertJSONToObjects(rawData);
        Assert.assertTrue(data.size() > 0);
    }

}
