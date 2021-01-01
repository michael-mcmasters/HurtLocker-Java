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

        int totalCharsInColumn = 15;
        int spacesBetweenColumns = 10;
        String nameColumnTitle = "Name:";
        String seenColumnTitle = "Seen:";
        String priceColumnTitle = "Price:";

        for (String name : names.keySet()) {
            List<Data> list = names.get(name);
            int seenAmount = list.size();
            Map<String, Integer> prices = dataParser.getPricesAndOccurences(list);

            // Row 1 Column 1 (Name)
            output += nameColumnTitle;
            output += addCharacter(" ", totalCharsInColumn - nameColumnTitle.length() - name.length());
            output += name;
            output += addCharacter(" ", spacesBetweenColumns);

            // Row 1 Column 2 (Seen)
            output += seenColumnTitle;
            String seenTimes = seenAmount + " times";
            output += addCharacter(" ", totalCharsInColumn - seenColumnTitle.length() - seenTimes.length());
            output += seenTimes;
            output += "\n";

            // Row 2 Column 1 (====)
            output += addCharacter("=", totalCharsInColumn);
            output += addCharacter(" ", spacesBetweenColumns);

            // Row 2 Column 2 (====)
            output += addCharacter("=", totalCharsInColumn);
            output += "\n";

        }

        printToFile(output);
    }

    // Pass the character you want added, and how many times to add it.
    // Returns String with the result.
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
