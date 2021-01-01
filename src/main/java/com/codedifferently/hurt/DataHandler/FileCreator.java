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

        // Name is the key (milk, bread, etc).
        // Pair is a list of instances of every Data object with that name property.
        Map<String, List<Data>> names = dataParser.getInstancesOfEveryName();

        int charactersInFirstRow = 11;
        int equalSymbols = charactersInFirstRow + 5; // because "seen:" and "name:" are each 5 characters long.
        int spaces = 10;

        for (String name : names.keySet()) {
            List<Data> list = names.get(name);
            int seen = list.size();
            Map<String, Integer> prices = dataParser.getPricesAndOccurences(list);

            // Row 1 Column 1 (Name)
            output += "Name:";
            output += addCharacter(" ", charactersInFirstRow - name.length());
            output += name;
            output += addCharacter(" ", spaces);

            // Row 1 Column 2 (Seen)
            String seenTimes = seen + " times";
            output += "Seen:";
            output += addCharacter(" ", charactersInFirstRow - seenTimes.length());
            output += seenTimes;
            output += "\n";

            // Row 2 Column 1 (====)
            output += addCharacter("=", equalSymbols);
            output += addCharacter(" ", spaces);

            // Row 2 Column 2 (====)
            output += addCharacter("=", equalSymbols);
            output += "\n";

        }

        printToFile(output);
    }

    // Returns a String with the given character added x amounts of times.
    private String addCharacter(String charToAdd, int amount) {
        String characters = "";
        for (int i = 1; i <= amount; i++) {
            characters += charToAdd;
        }
        return characters;
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
