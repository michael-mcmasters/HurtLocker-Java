package com.codedifferently.hurt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    List<Data> dataList = new ArrayList<>();

    public void buildClass(String input) {
        //Data data = new Data();
    }

    public void createLogFile(String data) throws IOException {
        File file = new File("../../resources/log.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("our text here");
        writer.flush();
        writer.close();
    }
}
