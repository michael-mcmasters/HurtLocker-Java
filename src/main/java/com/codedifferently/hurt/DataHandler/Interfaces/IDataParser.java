package com.codedifferently.hurt.DataHandler.Interfaces;

import com.codedifferently.hurt.DataHandler.Data;

import java.util.List;

public interface IDataParser {

    public List<Data> getInstancesOfName(String name);
    public List<Data> getInstancesOfEveryName();

}
