package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileCreator implements IFileCreator {

    private IDataParser dataParser;

    public FileCreator(IDataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public void createLogFile() {
        // Use String instead of StringBuffer to make code more readable.
        String output = "";

        // Name is the key (milk, bread, etc).
        // Pair is a list of instances of every Data object with that name property.
        Map<String, List<Data>> names = dataParser.getInstancesOfEveryName();

        // Every unit is one empty char.
        int columnWidth = 13;
        int widthBetweenColumns = 8;

        String nameColumnTitle = "Name:";
        String seenColumnTitle = "Seen:";
        String priceColumnTitle = "Price:";

        for (String name : names.keySet()) {
            List<Data> list = names.get(name);
            int seenAmount = list.size();
            name = capitalizeFirstLetter(name);

            // Row 1 Column 1 (Name)
            output += nameColumnTitle;
            output += addCharacter(" ", columnWidth - nameColumnTitle.length() - name.length());
            output += name;
            output += addCharacter(" ", widthBetweenColumns);

            // Row 1 Column 2 (Seen)
            output += seenColumnTitle;
            String seenTimesStr = seenAmount + " times";
            output += addCharacter(" ", columnWidth - seenColumnTitle.length() - seenTimesStr.length());
            output += seenTimesStr;
            output += "\n";




            // Row 2 Column 1 (====)
            output += addCharacter("=", columnWidth);
            output += addCharacter(" ", widthBetweenColumns);

            // Row 2 Column 2 (====)
            output += addCharacter("=", columnWidth);
            output += "\n";




            // Row 3 - Print all prices with a dotted line underneath.
            Map<String, Integer> prices = dataParser.getPricesAndOccurences(list);
            String[] sortedPrices = getSortedPrices(prices);

            boolean addDottedLine = true;       // (Dotted line is only added after every other price).
            for (String price: sortedPrices) {
                String seen = prices.get(price).toString();

                // Row 3 Column 1 (Price)
                output += priceColumnTitle;
                output += addCharacter(" ", columnWidth - priceColumnTitle.length() - price.length());
                output += price;
                output += addCharacter(" ", widthBetweenColumns);

                // Row 3 Column 2 (Seen)
                output += seenColumnTitle;
                seenTimesStr = seen + " times";
                output += addCharacter(" ", columnWidth - seenColumnTitle.length() - seenTimesStr.length());
                output += seenTimesStr;
                output += addCharacter(" ", widthBetweenColumns);
                output += "\n";

                // Row 4 Column 1 and Column 2 (----)
                if (addDottedLine) {
                    output += addCharacter("-", columnWidth);
                    output += addCharacter(" ", widthBetweenColumns);
                    output += addCharacter("-", columnWidth);
                    output += "\n";
                }
                addDottedLine = !addDottedLine;
            }
            output += "\n";
        }

        // Error Row, Column 1
        int errors = dataParser.getEmptyPropertyCount();
        output += "Errors";
        output += addCharacter(" ", columnWidth - "Errors".length());
        output += addCharacter(" ", widthBetweenColumns);

        // Error Row, Column 2
        output += seenColumnTitle;
        String seenTimesStr = errors + " times";
        output += addCharacter(" ", columnWidth - seenColumnTitle.length() - seenTimesStr.length());
        output += seenTimesStr;

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

    private String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Returns keys sorted from greatest to lowest in numerical order.
    private String[] getSortedPrices(Map<String, Integer> map) {
        Float[] floats = new Float[map.keySet().size()];
        int index = 0;
        for (String k : map.keySet()) {
            floats[index] = Float.parseFloat(k);
            index++;
        }
        Arrays.sort(floats, Collections.reverseOrder());
        return Arrays.stream(floats).map(String::valueOf).toArray(String[]::new);
    }

    private void printToFile(String output) {
        File file = new File("output_2.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("FAILED");
        }
    }

}
