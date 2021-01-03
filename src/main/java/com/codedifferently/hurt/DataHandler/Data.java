package com.codedifferently.hurt.DataHandler;

import java.util.ArrayList;
import java.util.List;

// Holds properties for every JSON object.
public class Data {

    private final boolean fuzzyMatched;

    protected String name;
    protected String price;
    protected String type;
    protected String expiration;
    protected List<String> additionalProperties;


    public Data(String[] properties, boolean fuzzyMatched) {
        this.fuzzyMatched = fuzzyMatched;

        this.name = properties[0];
        this.price = properties[1];
        this.type = properties[2];
        this.expiration = properties[3];

        // This never happens for this project but I figured it would be a good edge case to check for in the real world.
        this.additionalProperties = new ArrayList<>();
        if (properties.length > 4) {
            System.out.println("Note! There were more properties than expected. Adding them to additionalProperties.");
            for (int i = 4; i < properties.length; i++) {
                additionalProperties.add(properties[i]);
            }
        }
    }

    public boolean getFuzzyMatched() {
        return fuzzyMatched;
    }
}
