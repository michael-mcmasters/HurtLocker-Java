package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.Main;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DataHandlerTest {

    @Test
    public void testConstructor() throws Exception {
        String output = (new Main()).readRawDataToString();
        DataHandler dataHandler = new DataHandler(output);
        List<Data> data = dataHandler.getData();
        Assert.assertTrue(data.size() > 0);
    }

    @Test
    public void testDataLoggedToFile() throws Exception {
        String output = (new Main()).readRawDataToString();
        DataHandler dataHandler = new DataHandler(output);
        boolean actual = dataHandler.printDataToFile();
        Assert.assertTrue(actual);
    }
}