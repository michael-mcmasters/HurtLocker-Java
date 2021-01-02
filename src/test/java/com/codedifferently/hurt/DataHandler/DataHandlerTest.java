package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DataHandlerTest {

    private String rawData;
    private DataHandler dataHandler;

    @Before
    public void init() {
        rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016";
        dataHandler = new DataHandler(rawData);
    }

    @Test
    public void testConstructor() {
        List<Data> data = dataHandler.getData();
        Assert.assertTrue(data.size() > 0);
    }

    @Test
    public void testDataLoggedToFile() {
        boolean fileCreated = dataHandler.printDataToFile();
        Assert.assertTrue(fileCreated);
    }

    @Test
    public void testGetDataParser() {
        IDataParser dataParser = dataHandler.getDataParser();
        Assert.assertNotNull(dataParser);
    }

    @Test
    public void testGetFileCreator() {
        IFileCreator fileCreator = dataHandler.getFileCreator();
        Assert.assertNotNull(fileCreator);
    }
}