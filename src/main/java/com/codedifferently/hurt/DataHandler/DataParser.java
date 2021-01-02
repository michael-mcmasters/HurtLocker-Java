package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Holds all Data objects instantiated from JSON file.
// Contains helper methods to parse data.
public class DataParser implements IDataParser {

    List<Data> dataList;

    public DataParser(List<Data> dataList) {
        this.dataList = dataList;
        dataList.forEach(d -> System.out.println("Name: " + d.name + "\n" + "Price: " + d.price + "\n" + "Type: " + d.type + "\n" + "Expiration: " + d.expiration + "\n" + "FuzzyMatched: " + d.getFuzzyMatched() + "\n"));
    }

    @Override
    public List<Data> getData() {
        return dataList;
    }

    @Override
    public List<Data> getInstancesOfName(String name) {
        return null;
    }

    // Groups classes together with similar names. Returns all of their instances.
    // Name is the key, a list of instances with that name is the pair.
    @Override
    public Map<String, List<Data>> getInstancesOfEveryName() {
        // Name, every class instance holding that name
        Map<String, List<Data>> map = new HashMap<>();

        for (Data data : dataList) {
            String name = data.name;
            if (name == "") continue;

            if (!map.containsKey(name)) {
                map.put(name, new ArrayList<>());
            }
            List<Data> list = map.get(name);
            list.add(data);
        }
        return map;
    }

    // Helper methods for if you don't want to pass a lambda.
    public Map<String, Integer> getNamesAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.name);
    }

    public Map<String, Integer> getPricesAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.price);
    }

    public Map<String, Integer> getTypesAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.type);
    }

    public Map<String, Integer> getExpirationsAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.expiration);
    }

    // Returns every property of the given type (name, price, etc), and how many times it appeared.
    // (Pass a lambda for the property you want to get. This way we don't need to re-create this function for every property the object has.)
    public Map<String, Integer> getPropertyAndOccurences(List<Data> dataList, Function<Data, String> function) {
        Map<String, Integer> map = new HashMap<>();

        for (Data data : dataList) {
            String property = function.apply(data); // Property is the lambda getter being passed. Could be name, price, type, expiration etc.
            if (property == "") continue;

            if (!map.containsKey(property)) {
                map.put(property, 1);
            } else {
                int timesAppeared = map.get(property);
                map.put(property, ++timesAppeared);
            }
        }
        return map;
    }

    public int getFuzzyMatchCount() {
        int counter = 0;
        for (Data data : dataList) {
            if (data.getFuzzyMatched()) counter++;
        }
        return counter;
    }

    public void getHowManyTimesAppears() {

    }

    // Returns the total number of empty properties for every JSON object.
    public int getEmptyPropertyCount() {
        int counter = 0;
        for (Data data : dataList) {
            if (data.name.length() == 0) counter++;
            if (data.price.length() == 0) counter++;
            if (data.type.length() == 0) counter++;
            if (data.expiration.length() == 0) counter++;
        }
        return counter;
    }
}
