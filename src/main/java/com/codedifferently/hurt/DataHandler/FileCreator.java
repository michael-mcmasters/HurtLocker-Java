package com.codedifferently.hurt.DataHandler;

import com.codedifferently.hurt.DataHandler.Interfaces.IDataParser;
import com.codedifferently.hurt.DataHandler.Interfaces.IFileCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Creates output file and handles formatting.
public class FileCreator implements IFileCreator {

    private IDataParser dataParser;

    public FileCreator(IDataParser dataParser) {
        this.dataParser = dataParser;
    }

    // Generate generic report.
    // Returns true if file was created.
    @Override
    public boolean createLogFile() {
        int columnWidth = 13;               // Every unit is one empty char.
        int widthBetweenColumns = 8;

        String nameColumnTitle = "Name:";
        String seenColumnTitle = "Seen:";
        String priceColumnTitle = "Price:";

        String errorsRowTitle = "Errors";
        String fuzzyMatchesRowTitle = "Fuzzy Matches";

        return createLogFile(columnWidth, widthBetweenColumns, nameColumnTitle, seenColumnTitle, priceColumnTitle, errorsRowTitle, fuzzyMatchesRowTitle);
    }

    // Generate report with the given criteria.
    // Returns true if file was created.
    public boolean createLogFile(int columnWidth, int widthBetweenColumns, String nameColumnTitle, String seenColumnTitle,
                              String priceColumnTitle, String errorsRowTitle, String fuzzyMatchesRowTitle) {

        String output = generateOutput(columnWidth, widthBetweenColumns, nameColumnTitle, seenColumnTitle, priceColumnTitle, errorsRowTitle, fuzzyMatchesRowTitle);
        return printToFile(output);
    }

    // Returns true if file was successfully written.
    private boolean printToFile(String output) {
        File file = new File("output_2.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }



    // Returns the text that will display in the file.
    public String generateOutput(int columnWidth, int widthBetweenColumns, String nameColumnTitle, String seenColumnTitle,
                                 String priceColumnTitle, String errorsRowTitle, String fuzzyMatchesRowTitle) {
        // Use String instead of StringBuffer to make code more readable.
        String output = "";

        // Name is the key (milk, bread, etc).
        // Pair is a list of instances of every Data object with that name property.
        Map<String, List<Data>> names = dataParser.getInstancesOfEveryName();
        String[] sortedNames = getSortedNames(names);

        for (String name : sortedNames) {
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
            String seenTimesStr = seenAmount + getTimesPlurality(seenAmount);
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
                seenTimesStr = seen + getTimesPlurality(seen);
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
        output += "\n";
        int errors = dataParser.getEmptyPropertyCount();
        output += errorsRowTitle;
        output += addCharacter(" ", columnWidth - errorsRowTitle.length());
        output += addCharacter(" ", widthBetweenColumns);

        // Error Row, Column 2
        output += seenColumnTitle;
        String seenTimesStr = errors + getTimesPlurality(errors);
        output += addCharacter(" ", columnWidth - seenColumnTitle.length() - seenTimesStr.length());
        output += seenTimesStr;
        output += "\n";




        // Fuzzy Match Row, Column 1
        int fuzzyMatches = dataParser.getFuzzyMatchCount();
        System.out.println(fuzzyMatches);
        output += fuzzyMatchesRowTitle;
        output += addCharacter(" ", columnWidth - fuzzyMatchesRowTitle.length());
        output += addCharacter(" ", widthBetweenColumns);

        // Fuzzy Match Row, Column 2
        output += seenColumnTitle;
        String seenFuzzyMatchesStr = fuzzyMatches + getTimesPlurality(fuzzyMatches);
        output += addCharacter(" ", columnWidth - seenColumnTitle.length() - seenFuzzyMatchesStr.length());
        output += seenFuzzyMatchesStr;

        return output;
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

    // Arbitrarily sort keys so that they print exactly as they do in the example output.txt file.
    private String[] getSortedNames(Map<String, List<Data>> map) {
        String[] strArr = new String[map.keySet().size()];
        for (String s : map.keySet()) {
            char c = s.charAt(0);
            switch (c) {
                case 'm':
                    strArr[0] = s;
                    break;
                case 'b':
                    strArr[1] = s;
                    break;
                case 'c':
                    strArr[2] = s;
                    break;
                case 'a':
                    strArr[3] = s;
                    break;
                default: strArr[4] = s;
            }
        }
        return strArr;
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

    // Determines if "time" or "times" should be used.
    private String getTimesPlurality(String sizeStr) {
        return getTimesPlurality(Integer.parseInt(sizeStr));
    }

    private String getTimesPlurality(int size) {
        if (size > 1) {
            return " times";
        }
        return " time ";        // Put space at end so "time " and "times" are the same length for formatting.
    }
}
