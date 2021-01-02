package com.codedifferently.hurt.DataHandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileCreatorTest {

    FileCreator fileCreator;

    @Before
    public void init() {
        String rawData = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        DataHandler dataHandler = new DataHandler(rawData);
        this.fileCreator = (FileCreator) dataHandler.getFileCreator();
    }

    @Test
    public void testInstance() {
        Assert.assertTrue(fileCreator instanceof FileCreator);
    }

    @Test
    public void testCreateLogFile() {
        boolean fileCreated = fileCreator.createLogFile();
        Assert.assertTrue(fileCreated);
    }

    @Test
    public void testCreateLogFileWithParams() {
        int columnWidth = 13;
        int widthBetweenColumns = 8;
        String nameColumnTitle = "Name:";
        String seenColumnTitle = "Seen:";
        String priceColumnTitle = "Price:";
        String errorsRowTitle = "Errors";
        String fuzzyMatchesRowTitle = "Fuzzy Matches";

        boolean fileCreated = fileCreator.createLogFile(columnWidth, widthBetweenColumns, nameColumnTitle,
                seenColumnTitle, priceColumnTitle, errorsRowTitle, fuzzyMatchesRowTitle);

        Assert.assertTrue(fileCreated);
    }
}
