package com.codedifferently.hurt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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
            //String output = getData();
            test(dataList);

            FileWriter writer = new FileWriter(file);
//            writer.write(output);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("FAILED");
        }
    }

    private static String getData() {
        // Pair can be bread, milk, cookies etc.
        // And then the price and how many times they appear.
        class Pair {
            String pair;
            Map<String, Integer> prices;
        }
        List<Pair> pairs = new ArrayList<>();

        String output = "";
        Map<String, Integer> names = getAllNames();
        for (String name : names.keySet()) {
            System.out.println(name);
            //Map<String, Integer> prices = getAllPricesForNames(name);
            //Map<String, Integer> prices = getAllPricesForNames(dataList, name, s -> s.getPrice());
//            System.out.println(names);
//            System.out.println(prices);
        }
//        for (Data data : dataList) {
//            if (data.getName() == "") {
//                System.out.println("YES THIS IS BROKEN");
//                break;
//            }
//
//            output += " " + data.getName();
//            output += " " + data.getPrice();
//            output += " " + data.getType();
//            output += " " + data.getExpiration();
//            output += "\n";
//        }
        return output;
    }

    private static Map<String, Integer> getAllNames() {
        // Name, times appeared
        Map<String, Integer> map = new HashMap<>();

        for (Data data : dataList) {
            if (data.getName() == "") continue;

            if (map.containsKey(data.getName())) {
                int timesAppeared = map.get(data.getName());
                map.put(data.getName(), ++timesAppeared);
            } else {
                map.put(data.getName(), 1);
            }
        }
        return map;
    }

    private static Map<String, Integer> getAllPricesForNames(List<Data> dataList, String name, Predicate<Data> p) {
        // Price, times appeared
        Map<String, Integer> map = new HashMap<>();

        for (Data data : dataList) {
//            if (data.getName() != name || (p.))
//                continue;

            if (map.containsKey(data.getPrice())) {
                int timesAppeared = map.get(data.getPrice());
                map.put(data.getPrice(), ++timesAppeared);
            } else {
                map.put(data.getPrice(), 1);
            }
        }
        return map;
    }


    private static void test(List<Data> dataList) {

//        getInfoFor(dataList, "apples", new Function<Data, String>(){
//            public String apply(Data input) {
//                return input.getPrice();
//            }});

        Function<Data, String> fn = data -> data.getPrice();
        getInfoFor(dataList, "milk", fn);

        getInfoFor(dataList, "milk", data -> data.getPrice());
    }


    private static Map<String, Integer> getInfoFor(List<Data> dataList, String name, Function<Data, String> function) {
        Map<String, Integer> map = new HashMap<>();

        System.out.println("getInfoFor CALLED");
        System.out.println("size is " + dataList.size());

        for (Data data : dataList) {
            System.out.println("looping");
            if (function.apply(data) != "") {
                System.out.println("DATA IS " + function.apply(data));
            }
        }
        return null;
    }

//
//    private static void test(List<Data> dataList) {
//        String message = "Hello World";
//        //getInfoFor(dataList, "milk", value -> print(value));
//        getInfoFor(dataList, "milk", data -> data.getPrice());
//    }

//    public static void getInfoFor(List<Data> dataList, String name, Consumer<Data> lambda) {
//        lambda.accept(value);
//
//        System.out.println("getInfoFor CALLED");
//        System.out.println("size is " + dataList.size());
//
//        for (Data data : dataList) {
//            System.out.println("looping");
////            if (function.apply(data) != "") {
////                System.out.println("DATA IS " + function.apply(data));
////            }
//            lambda.accept(data);
//
//            if (lambda.accept(data) != "") {
//                System.out.println("DATA IS " + function.apply(data));
//            }
//        }
//        return null;
//    }
}
