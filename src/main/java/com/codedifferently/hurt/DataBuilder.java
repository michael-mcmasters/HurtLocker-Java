package com.codedifferently.hurt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    static List<Data> dataList = new ArrayList<>();

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
                //errors++;
                //indexErrorThrownAt = i;
                System.out.println("ERROR IS HERE");
            }
        }
        return properties;
    }

    public static void createLogFile(String data) throws IOException {
        File file = new File("../../../../../output.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("our text here");
        writer.flush();
        writer.close();
    }
}
