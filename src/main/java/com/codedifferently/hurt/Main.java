package com.codedifferently.hurt;

import com.codedifferently.hurt.DataHandler.DataHandler;
import org.apache.commons.io.IOUtils;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);

        DataHandler dataHandler = new DataHandler(output);
        dataHandler.logDataToFile();
    }
}
