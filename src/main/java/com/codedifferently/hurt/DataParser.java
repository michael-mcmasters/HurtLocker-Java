package com.codedifferently.hurt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {

    int errors = 0;
    int indexErrorThrownAt = 0;

    public void parse(String input) {
        input = input.toLowerCase();
        String[] strArr = input.split("##");
        for (String str : strArr) {
            createData(str);
        }
        System.out.println(errors);
        System.out.println(indexErrorThrownAt);

        DataBuilder.createLogFile();
    }

    private void createData(String str) {
        String result = "";

        Matcher m = Pattern.compile("\\w+.*\\w+").matcher(str);
        while (m.find()) {
            String data = m.group();
            DataBuilder.buildClass(data);
        }
        System.out.println("                  ");
    }

}
