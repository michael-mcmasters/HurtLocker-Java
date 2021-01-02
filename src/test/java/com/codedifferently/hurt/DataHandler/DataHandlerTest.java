package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.Main;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DataHandlerTest {

    @Test
    public void testConstructor() {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016";
        DataHandler dataHandler = new DataHandler(rawData);
        List<Data> data = dataHandler.getData();
        Assert.assertTrue(data.size() > 0);
    }

    @Test
    public void testDataLoggedToFile() throws Exception {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016";
        DataHandler dataHandler = new DataHandler(rawData);
        boolean actual = dataHandler.printDataToFile();
        Assert.assertTrue(actual);
    }
}