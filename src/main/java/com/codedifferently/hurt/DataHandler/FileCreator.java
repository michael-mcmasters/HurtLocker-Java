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

//        Map<String, List<Data>> names = dataParser.getInstancesOfEveryName();
//        for (String namesKey : names.keySet()) {
//            System.out.println("__"+ namesKey + "__");
//
//            List<Data> list = names.get(namesKey);
//            int seen = list.size();
//
//            System.out.println("Times appeared: " + seen);
//            Map<String, Integer> prices = dataParser.getPricesAndOccurences(list);
//            System.out.println("Prices: " + prices);
//            System.out.println("\n");
//        }


        String output = "";

        Map<String, List<Data>> names = dataParser.getInstancesOfEveryName();

        // Name is the key (milk, bread, etc).
        // Pair is a list of instances of every Data object with that name property.
        for (String name : names.keySet()) {
            List<Data> list = names.get(name);
            int seen = list.size();
            Map<String, Integer> prices = dataParser.getPricesAndOccurences(list);

            output += "Name:    ";
            output += name;

            output += addSpaces(8 - name.length());
            output += "       ";

            output += "Seen: " + seen + "times";

            output += "\n";
            output += "================";

            output += "        ";
            output += "================";
            output += "\n";

            System.out.printf("%-30.30s  %-30.30s%n", "Name:", name);
            System.out.println(String.format("Name: %10s %10s %-10s", name, " ", name));
            System.out.println(String.format("Name: %10s %10s %-10s", name, " ", name));
            System.out.println(String.format("================= %10s =================", " "));
            System.out.println(String.format( "%5s of %-8s", name, name));
            System.out.println("\n\n\n");

        }

        // - symbol pushes things on right away
        System.out.printf("%-22s%-22s%-22s\n","Column 1","Column 2","Column 3");
        System.out.printf("%-22s%-22s%-22s\n","v1", "v222", "v33333");
        System.out.printf("%-22s22%s%-22s%-22s\n","v1", "v3", "v222", "v33333");

//        System.out.printf("%-10s %-10s %-10s\n", "osne", "two", "thredsfe");
//        System.out.printf("%-10s %-10s %-10s\n", "one", "tdsfwo", "thsdfree");
//        System.out.printf("%-10s %-10s %-10s\n", "onsdfe", "twdfo", "three");
//        System.out.printf("%-10s %-10s %-10s\n", "odsfne", "twsdfo", "thdfree");
//        System.out.printf("%-10s %-10s %-10s\n", "osdne", "twdfo", "three");
//        System.out.printf("%-10s %-10s %-10s\n", "odsfne", "tdfwo", "three");

        printToFile(output);
    }

    private String addSpaces(int numOfSpaces) {
        String spaces = "";
        for (int i = 1; i <= numOfSpaces; i++) {
            spaces += " ";
        }
        return spaces;
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
