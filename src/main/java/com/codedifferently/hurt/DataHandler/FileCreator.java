package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator implements IFileCreator {

    private IDataParser dataParser;

    public FileCreator(IDataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public void createLogFile() {
        File file = new File("output_2.txt");

        try {
            //String output = getData();
            String output = "wow";

            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("FAILED");
        }
    }

}
