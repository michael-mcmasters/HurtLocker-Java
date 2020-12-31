package com.codedifferently.hurt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    List<Data> dataList = new ArrayList<>();

    public void buildClass(String input) {
        String[] properties = getPair(input);

        Data data = new Data("", "", "", "");
        dataList.add(data);
    }

    private String[] getPair(String input) {
        String[] properties = input.split(": ");
    }

    public void createLogFile(String data) throws IOException {
        File file = new File("../../../../../output.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("our text here");
        writer.flush();
        writer.close();
    }
}
