package com.codedifferently.hurt.DataHandler.Interfaces;

import com.codedifferently.hurt.DataHandler.Data;

import java.util.List;
import java.util.Map;

public interface IDataParser {

    public List<Data> getInstancesOfName(String name);
    public Map<String, List<Data>> getInstancesOfEveryName();

}
