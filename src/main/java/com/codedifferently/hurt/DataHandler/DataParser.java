package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;

import java.util.ArrayList;
import java.util.List;

public class DataParser implements IDataParser {

    List<Data> data;

    public DataParser(List<Data> data) {
        this.data = data;
        data.forEach(d -> System.out.println(d.name));
        data.forEach(d -> System.out.println(d.price));
        data.forEach(d -> System.out.println(d.type));
        data.forEach(d -> System.out.println(d.expiration));
    }

    @Override
    public List<Data> getInstancesOfName(String name) {
        return null;
    }

    @Override
    public List<Data> getInstancesOfEveryName() {
        return null;
    }
}
