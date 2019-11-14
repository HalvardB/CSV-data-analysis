package com.company;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

    public static void main(String[] args) {
        WhichCountriesExport csvExport = new WhichCountriesExport();
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();

        // To answer Coursera Quiz
        // System.out.println(csvExport.numberOfExporters(parser,"gold"));
        // System.out.println(csvExport.countryInfo(parser, "Nauru"));
        // csvExport.bigExporters(parser, "$999,999,999,999");


        CSVMax csvMax = new CSVMax();
        csvMax.testHottestHourInDay();
        // csvMax.testHottestInManyDays();

        CSVMin csvMin = new CSVMin();
        csvMin.testColdestHourInDay();

        CSVHumidity csvHumdity = new CSVHumidity();
        // csvHumdity.testLowestHudidityInFile();
        // csvHumdity.lowestHumidityInManyDays();

        CSVAverage csvAverage = new CSVAverage();
        // csvAverage.testAverageTemperature();
        csvAverage.testAverageTemperatureWithHighHumidityInFile();



        //CSVRecord coldest = csvMax.coldestInManyDays();
        //System.out.println("The coldest temperature was " + coldest.get("Temperature") + " at " + coldest.get("DateUTC"));
        // csvMin.fileWithColdestTemperature();
        // System.out.println(fileName);

    }
}
