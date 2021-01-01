package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RawDataParser implements IRawDataParser {

    // Converts every line of the corrupted JSON file to a Data object. Returns all objects.
    @Override
    public List<Data> convertJSONToObjects(String rawData) {
        List<Data> dataList = new ArrayList<>();

        rawData = rawData.toLowerCase();
        String[] linesOfData = rawData.split("##");
        for (String line : linesOfData) {
            String[] properties = getProperties(line);
            properties = fuzzyMatchProperties(properties, dataList);

            dataList.add(new Data(properties));
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




    // Renames property if it closely resembles another property.
    private String[] fuzzyMatchProperties(String[] properties, List<Data> dataList) {
        for (Data data : dataList) {
            // Only match name and type properties because price and date are too similar and will return false positives.
            properties[0] = fuzzyMatchProperty(properties[0], data.name);
            properties[2] = fuzzyMatchProperty(properties[2], data.type);
        }
        return properties;
    }

    private String fuzzyMatchProperty(String newProperty, String existingProperty) {
        // 3 characters is not enough information to fuzzy match.
        if (newProperty.length() < 4) return newProperty;

        if (getMatchingCharCount(newProperty, existingProperty) >= 4) {
            return existingProperty;
        }
        return newProperty;
    }

    private int getMatchingCharCount(String aStr, String bStr) {
        int matchingChars = 0;

        String[] a = aStr.split("");
        String[] b = bStr.split("");
        int smallerWord = getLowerNumber(a.length, b.length);
        for (int i = 0; i < smallerWord; i++) {
            if (a[i].equals(b[i])) matchingChars++;
        }
        return matchingChars;
    }

    private int getLowerNumber(int a, int b) {
        if (a < b)
            return a;
        return b;
    }
}
