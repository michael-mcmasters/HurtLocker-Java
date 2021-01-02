package com.codedifferently.hurt.DataHandler.Interfaces;

import com.codedifferently.hurt.DataHandler.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IDataParser {

    public List<Data> getInstancesOfName(String name);
    public Map<String, List<Data>> getInstancesOfEveryName();
    public Map<String, Integer> getNamesAndOccurences(List<Data> dataList);
    public Map<String, Integer> getPricesAndOccurences(List<Data> dataList);
    public Map<String, Integer> getTypesAndOccurences(List<Data> dataList);
    public Map<String, Integer> getExpirationsAndOccurences(List<Data> dataList);
    public Map<String, Integer> getPropertyAndOccurences(List<Data> dataList, Function<Data, String> function);
    public int getFuzzyMatchCount();
    public int getEmptyPropertyCount();
}
