package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;
import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.List;

public class DataHandler {

    private List<Data> data;
    private IRawDataParser rawDataParser;
    private IDataParser dataParser;
    private IFileCreator fileCreator;

    public DataHandler(String rawData) {
        data = formatData(rawData);
    }

    private List<Data> formatData(String rawData) {
        return null;
    }

    public void logDataToFile() {

    }
}
