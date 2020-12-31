package com.codedifferently.hurt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerskSONBuilder {

    int errors = 0;
    int indexErrorThrownAt = 0;

    public void build(String input) {
        input = input.toLowerCase();
        String[] strArr = input.split("##");
        for (String str : strArr) {
            getData(str);
        }
        System.out.println(errors);
        System.out.println(indexErrorThrownAt);
    }

    private void getData(String str) {
        Matcher m = Pattern.compile("\\w+.*\\w+").matcher(str);

        while (m.find()) {
            String data = m.group();
            String[] dataArr = data.split("(;|:|\\^|%|\\*|@|!)");

            for (int i = 0; i < dataArr.length; i += 2) {
                try {
                    System.out.println(dataArr[i] + ": " + dataArr[i + 1]);
                } catch (IndexOutOfBoundsException e) {
                    errors++;
                    indexErrorThrownAt = i;
                    System.out.println("ERROR IS HERE");
                }
            }
        }
        System.out.println("                  ");
    }

}
