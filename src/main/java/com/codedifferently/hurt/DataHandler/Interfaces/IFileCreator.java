package com.codedifferently.hurt.DataHandler.Interfaces;

public interface IFileCreator {

    public void createLogFile();
    public void createLogFile(int columnWidth, int widthBetweenColumns, String nameColumnTitle, String seenColumnTitle,
                              String priceColumnTitle, String errorsRowTitle, String fuzzyMatchesRowTitle);

}
