package com.codedifferently.hurt.DataHandler.Interfaces;

import com.codedifferently.hurt.DataHandler.Data;
import com.codedifferently.hurt.DataHandler.Enums.SortOrder;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface IDataParser {

    public List<Data> getDataList();
    public Map<String, List<Data>> getInstancesOfEveryName();
    public Map<String, Integer> getNamesAndOccurences(List<Data> dataList);
    public Map<String, Integer> getPricesAndOccurences(List<Data> dataList);
    public Map<String, Integer> getTypesAndOccurences(List<Data> dataList);
    public Map<String, Integer> getExpirationsAndOccurences(List<Data> dataList);
    public Map<String, Integer> getPropertyAndOccurences(List<Data> dataList, Function<Data, String> function);
    public String[] sortPricesNumerically(Map<String, Integer> map, SortOrder sortOrder);
    public int getFuzzyMatchCount();
}
