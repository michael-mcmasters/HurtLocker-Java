package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Exceptions.NotEnoughDataException;
import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RawDataParser implements IRawDataParser {

    private int errorCount = 0;

    // Converts every line of the corrupted JSON file to a Data object. Returns all objects.
    @Override
    public List<Data> convertJSONToObjects(String rawData) {
        List<Data> dataList = new ArrayList<>();

        rawData = rawData.toLowerCase();
        String[] linesOfData = rawData.split("##");
        for (String line : linesOfData) {
            try {
                String[] parsedProperties = parseProperties(line);
                String[] properties = fuzzyMatchProperties(parsedProperties, dataList);
                boolean fuzzyMatched = fuzzyMatched(properties, parsedProperties);
                dataList.add(new Data(fuzzyMatched, properties));
            } catch (NotEnoughDataException e) {
                errorCount++;
            }
        }
        return dataList;
    }

    public int getErrorCount() {
        return errorCount;
    }

    // Returns an array of properties for the corrupted JSON object.
    // Use list<> instead of an array just in case there are more than 4 items.
    private String[] parseProperties(String lineOfData) throws NotEnoughDataException {
        String[] keysAndPairs = lineOfData.split("(;|:|\\^|%|\\*|@|!)");
        if (keysAndPairs.length < 8) throw new NotEnoughDataException();

        List<String> properties = new ArrayList<>();
        // We only want the pair, so loop through every odd number.
        for (int i = 1; i < keysAndPairs.length; i += 2) {
            String property = keysAndPairs[i].trim();
            if (property.equals("")) throw new NotEnoughDataException();

            properties.add(property);
        }
        // Converting a list to an array in Java. Source: https://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
        return properties.stream().toArray(String[]::new);
    }

    // Renames property if it closely resembles another property from other objects. Returns same properties otherwise.
    private String[] fuzzyMatchProperties(String[] parsedProperties, List<Data> dataList) {
        String[] properties = Arrays.copyOf(parsedProperties, parsedProperties.length);
        for (Data data : dataList) {
            // Only match name and type properties because price and date are too similar and will return false positives.
            if (properties.length > 0) properties[0] = fuzzyMatchProperty(properties[0], data.name);
            if (properties.length > 2) properties[2] = fuzzyMatchProperty(properties[2], data.type);
        }
        return properties;
    }

    private String fuzzyMatchProperty(String newProperty, String existingProperty) {
        // 3 characters is not enough information to fuzzy match. Return.
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

    // If any properties don't match, they were fuzzy matched.
    // (Have to do it this way because Java doesn't have tuples or passing by reference :( )
    private boolean fuzzyMatched(String[] propsA, String[] propsB) {
        if (propsA.length != propsB.length) return true;

        for (int i = 0; i < propsA.length; i++) {
            if (!propsA[i].equals(propsB[i]))
                return true;
        }
        return false;
    }
}
