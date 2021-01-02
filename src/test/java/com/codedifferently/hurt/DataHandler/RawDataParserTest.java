package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Exceptions.NotEnoughDataException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RawDataParserTest {

    @Test
    public void convertJSONToObjectsTest() {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016";
        RawDataParser rawDataParser = new RawDataParser();
        List<Data> data = rawDataParser.convertJSONToObjects(rawData);
        Assert.assertTrue(data.size() > 0);
    }

    @Test
    public void getOneError() {
        String rawData = "naMe:Milk;price:";
        RawDataParser rawDataParser = new RawDataParser();
        List<Data> data = rawDataParser.convertJSONToObjects(rawData);
        int actual = rawDataParser.getErrorCount();
        Assert.assertEquals(1, actual);
    }
}
