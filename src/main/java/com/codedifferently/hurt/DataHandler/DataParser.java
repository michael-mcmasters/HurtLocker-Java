package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Enums.SortOrder;
import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;

import java.util.*;
import java.util.function.Function;

// Holds all Data objects and has helper methods to easily parse information from them.
// (Not to be confused with RawDataParser which is what instantiates the Data objects from the corrupted JSON file.)
public class DataParser implements IDataParser {

    List<Data> dataList;

    public DataParser(List<Data> dataList) {
        this.dataList = dataList;
        dataList.forEach(d -> System.out.println("Name: " + d.name + "\n" + "Price: " + d.price + "\n" + "Type: " + d.type + "\n" + "Expiration: " + d.expiration + "\n" + "FuzzyMatched: " + d.getFuzzyMatched() + "\n"));
    }

    @Override
    public List<Data> getDataList() {
        return dataList;
    }

    // Groups classes together with similar names. Returns all of their instances.
    // Name is the key, a list of instances with that name is the pair.
    @Override
    public Map<String, List<Data>> getInstancesOfEveryName() {
        // Name, every class instance holding that name
        Map<String, List<Data>> instances = new HashMap<>();

        for (Data data : dataList) {
            String name = data.name;
            if (name == "") continue;

            if (!instances.containsKey(name)) {
                instances.put(name, new ArrayList<>());
            }
            List<Data> list = instances.get(name);
            list.add(data);
        }
        return instances;
    }

    // Returns every found value in the given list and how many times it occurred.
    // The type of Value is the property passed as a lambda (name, price, expiration, etc).
    // (Use lambda so code doesn't need to be repeated for every property.)
    @Override
    public Map<String, Integer> getPropertyAndOccurences(List<Data> dataList, Function<Data, String> function) {
        Map<String, Integer> occurences = new HashMap<>();

        for (Data data : dataList) {
            String property = function.apply(data);
            if (property == "") continue;

            if (!occurences.containsKey(property)) {
                occurences.put(property, 1);
            } else {
                int timesAppeared = occurences.get(property);
                occurences.put(property, ++timesAppeared);
            }
        }
        return occurences;
    }

    // Helper methods for if you don't want to pass a lambda.
    @Override
    public Map<String, Integer> getNamesAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.name);
    }

    @Override
    public Map<String, Integer> getPricesAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.price);
    }

    @Override
    public Map<String, Integer> getTypesAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.type);
    }

    @Override
    public Map<String, Integer> getExpirationsAndOccurences(List<Data> dataList) {
        return getPropertyAndOccurences(dataList, data -> data.expiration);
    }

    // Returns keys sorted from lowest to greatest or greatest to lowest in numerical order.
    public String[] sortPricesNumerically(Map<String, Integer> map, SortOrder sortOrder) {
        Float[] floats = convertToFloat(map);

        if (sortOrder == SortOrder.GREATESTTOLOWEST) Arrays.sort(floats, Collections.reverseOrder());
        else if (sortOrder == SortOrder.LOWESTTOGREATEST) Arrays.sort(floats);

        return convertToString(floats);
    }

    @Override
    public int getFuzzyMatchCount() {
        int counter = 0;
        for (Data data : dataList) {
            if (data.getFuzzyMatched()) counter++;
        }
        return counter;
    }

    private Float[] convertToFloat(Map<String, Integer> map) {
        Float[] floats = new Float[map.keySet().size()];
        int index = 0;
        for (String k : map.keySet()) {
            floats[index] = Float.parseFloat(k);
            index++;
        }
        return floats;
    }

    private String[] convertToString(Float[] floats) {
        return Arrays.stream(floats).map(String::valueOf).toArray(String[]::new);
    }
}
