package com.codedifferently.hurt.DataHandler.Interfaces;

import com.codedifferently.hurt.DataHandler.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IDataParser {

    public List<Data> getInstancesOfName(String name);
    public Map<String, List<Data>> getInstancesOfEveryName();
    public Map<String, Integer> getNamesAndOccurences();
    public Map<String, Integer> getPricesAndOccurences();
    public Map<String, Integer> getTypesAndOccurences();
    public Map<String, Integer> getExpirationsAndOccurences();
    public Map<String, Integer> getPropertyAndOccurences(List<Data> dataList, Function<Data, String> function);

}
