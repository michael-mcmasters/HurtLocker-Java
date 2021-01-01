package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.ArrayList;
import java.util.List;

public class RawDataParser implements IRawDataParser {

    // Converts every line of the corrupted JSON file to a Data object. Returns all objects.
    @Override
    public List<Data> convertJSONToObjects(String rawData) {
        List<Data> dataList = new ArrayList<>();

        rawData = rawData.toLowerCase();
        String[] linesOfData = rawData.split("##");
        for (String line : linesOfData) {
            String[] properties = getProperties(line);
            Data data = new Data(properties);
            dataList.add(data);
        }
        return dataList;
    }

    // Returns an array of properties for the corrupted JSON object.
    // Use list<> instead of an array just in case there are more than 4 items.
    private String[] getProperties(String lineOfData) {
        List<String> properties = new ArrayList<>();

        String[] keyAndPair = lineOfData.split("(;|:|\\^|%|\\*|@|!)");
        for (int i = 0; i < keyAndPair.length; i += 2) {
            try {
                properties.add(keyAndPair[i + 1].trim());      // We only want the pair, so return every odd number.
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
                break;
            }
        }
        // Converting a list to an array in Java. Source: https://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
        return properties.stream().toArray(String[]::new);
    }
}
