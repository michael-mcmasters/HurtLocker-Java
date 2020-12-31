package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;
import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.List;

public class DataHandler {

    private IRawDataParser rawDataParser;
    private IDataParser dataParser;
    private IFileCreator fileCreator;

    public DataHandler(String rawData) {
        rawDataParser = new RawDataParser();
        dataParser = new DataParser(convertJSONToObjects(rawData));
        fileCreator = new FileCreator(dataParser);
    }

    private List<Data> convertJSONToObjects(String rawData) {
        return rawDataParser.convertJSONToObjects(rawData);
    }

    public void printDataToFile() {
        fileCreator.createLogFile();
    }
}
