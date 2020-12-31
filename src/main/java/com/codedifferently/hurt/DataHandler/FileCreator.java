package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileCreator implements IFileCreator {

    private IDataParser dataParser;

    public FileCreator(IDataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public void createLogFile() {
        Map<String, List<Data>> names = dataParser.getInstancesOfEveryName();
        for (String key : names.keySet()) {
            System.out.println(key);
        }


        printToFile(names.toString());
        //System.out.println(names);
    }

    private void printToFile(String output) {
        File file = new File("output_2.txt");
        try {
            //String output = getData();
            //String output = "wow";

            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("FAILED");
        }
    }

}
