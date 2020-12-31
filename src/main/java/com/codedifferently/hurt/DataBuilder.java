package com.codedifferently.hurt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class DataBuilder {

    static List<Data> dataList = new ArrayList<>();
    static int errorsCount = 0;

    public static List<Data> getDataList() {
        return dataList;
    }

    public static void buildClass(String dataStr) {
        List<String> properties = getPair(dataStr);

        Data data = new Data(properties.get(0), properties.get(1), properties.get(2), properties.get(3));
        dataList.add(data);

        System.out.println(dataList.size());
    }

    private static List<String> getPair(String data) {
        List<String> properties = new ArrayList<>();

        String[] dataArr = data.split("(;|:|\\^|%|\\*|@|!)");
        for (int i = 0; i < dataArr.length; i += 2) {
            try {
                System.out.println(dataArr[i] + ": " + dataArr[i + 1]);
                properties.add(dataArr[i + 1]);
            } catch (IndexOutOfBoundsException e) {
                errorsCount++;
            }
        }
        return properties;
    }

    public static void createLogFile() {
        File file = new File("output2.txt");

        try {
            String output = getData();

            FileWriter writer = new FileWriter(file);
//            writer.write(output);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("FAILED");
        }
    }

    private static String getData() {
        String output = "";
        Map<String, List<Data>> names = getInstances();
        System.out.println("begin loop");

        for (List<Data> list : names.values()) {
            for (Data data : list) {
                System.out.println(data.getName());
            }
            getProperties(list, d -> d.getPrice());
            System.out.println("_____");
        }
        return output;
    }

    // Names from the raw data. Meaning cookies, milk, bread, etc.
    private static Map<String, List<Data>> getInstances() {
        // Name, every class instance holding that name
        Map<String, List<Data>> map = new HashMap<>();

        for (Data data : dataList) {
            String name = data.getName();
            if (name == "") continue;
            if (!map.containsKey(name))
                map.put(name, new ArrayList<>());

            List<Data> list = map.get(name);
            list.add(data);
        }
        return map;
    }

    private static void test(List<Data> dataList) {
        getProperties(dataList, data -> data.getPrice());
    }


    // Lamda is getBread(), getPrice(), getType(), getExpiration().
    private static Map<String, Integer> getProperties(List<Data> dataList, Function<Data, String> function) {
        Map<String, Integer> map = new HashMap<>();

        for (Data data : dataList) {
            if (function.apply(data) != "") {
                System.out.println("Data is " + function.apply(data));
            }
        }
        return null;
    }
}


























