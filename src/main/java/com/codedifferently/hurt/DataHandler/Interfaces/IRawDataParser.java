package com.codedifferently.hurt.DataHandler.Interfaces;

import com.codedifferently.hurt.DataHandler.Data;

import java.util.List;

public interface IRawDataParser {

    public List<Data> convertJSONToObjects(String rawData);
    public int getErrorCount();

}
