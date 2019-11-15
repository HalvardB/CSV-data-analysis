package com.company;

public class Main {

    public static void main(String[] args) {

        WhichCountriesExport csvExport = new WhichCountriesExport();
        CSVMax csvMax = new CSVMax();
        CSVMin csvMin = new CSVMin();
        CSVHumidity csvHumdity = new CSVHumidity();
        CSVAverage csvAverage = new CSVAverage();

        // Facts about my birthday
        System.out.println("Temperature statistics on the 15th of August 2014:");
        csvMin.testColdestHourInDay();
        csvMax.testHottestHourInDay();
        csvHumdity.testLowestHudidityInFile();
        csvAverage.testAverageTemperature();

        System.out.println("<----------------->");

        // Export statistics
        System.out.println("These are some export facts:");
        csvExport.tests();
    }
}
