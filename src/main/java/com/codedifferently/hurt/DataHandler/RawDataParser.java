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

//    // Renames property if it closely resembles another property.
//    private String[] fuzzyMatchProperties(String[] properties, List<Data> dataList) {
//        if (properties[0] == "co0kies") {
//            System.out.println("cookies");
//            return null;
//        }
//
//        fuzzyMatchProperty(properties[0], dataList);
//
//        // If a word is 4 or less characters long it is probably too small to fuzzy match.
//        return properties;
//    }
//
//    private String fuzzyMatchProperty(String a, String b, Function<String, String> function) {
//        String property =
//
//        if (properties[0].length() < 5) return properties;
//
//        for (Data data : dataList) {
//            if (data.name.length() >= 5 && getMatchingCharCount(properties[0], data.name) >= 5) {
//                properties[0] = data.name;
//                System.out.println("YEYEYEYEYEYSYSYS");
//            }
//        }
//    }

    private String[] fuzzyMatchProperties(String[] properties, List<Data> dataList) {
        for (Data data : dataList) {
            properties[0] = fuzzyMatchProperty(properties[0], data.name);
            properties[2] = fuzzyMatchProperty(properties[2], data.type);
        }
        return properties;
    }

    private String fuzzyMatchProperty(String property, String otherProperty) {
        // 3 characters is not enough to fuzzy match.
        if (property.length() < 4) return property;

        int matchingChars = getMatchingCharCount(property, otherProperty);
        if (matchingChars >=4) {
            return otherProperty;
        }
        return property;
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
