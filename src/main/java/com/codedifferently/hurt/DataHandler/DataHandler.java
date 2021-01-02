package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;
import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.List;

// Container class that determines how data is parsed, instantiated, and printed.
public class DataHandler {

    private IRawDataParser rawDataParser;
    private IDataParser dataParser;
    private IFileCreator fileCreator;

    // Automatically instantiates a class for each JSON object.
    public DataHandler(String rawData) {
        this.rawDataParser = new RawDataParser();
        List<Data> dataList = rawDataParser.convertJSONToObjects(rawData);
        this.dataParser = new DataParser(dataList);
        this.fileCreator = new FileCreator(dataParser, rawDataParser);
    }

    public boolean printDataToFile() {
        return fileCreator.createLogFile();
    }

    public List<Data> getDataList() {
        return dataParser.getDataList();
    }

    public IDataParser getDataParser() {
        return dataParser;
    }

    public IFileCreator getFileCreator() {
        return fileCreator;
    }
}
