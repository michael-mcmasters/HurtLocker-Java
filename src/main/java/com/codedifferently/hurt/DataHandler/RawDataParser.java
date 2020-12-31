package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IRawDataParser;

import java.util.ArrayList;
import java.util.List;

public class RawDataParser implements IRawDataParser {

    @Override
    public List<Data> formatData(String rawData) {
        rawData = rawData.toLowerCase();
        String[] dataArr = rawData.split("##");
        for (String lineOfData : dataArr) {
            List<String> properties = getProperties(lineOfData);
            instantiateClass(properties);
            System.out.println(properties);
        }
        return null;
    }

    private List<String> getProperties(String lineOfData) {
        List<String> properties = new ArrayList<>();

        String[] dataArr = lineOfData.split("(;|:|\\^|%|\\*|@|!)");
        for (int i = 0; i < dataArr.length; i += 2) {
            try {
                properties.add(dataArr[i + 1]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
                break;
            }
        }
        System.out.println("");
        return properties;
    }

    private void instantiateClass(List<String> properties) {
        //properties.get(0), properties.get(1), properties.get(2), properties.get(3)
        //System.out.println(jsonStr);
    }
}
