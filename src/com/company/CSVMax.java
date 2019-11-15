package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {

    // Function to return the hottest temperature in a file
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;

        for(CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(largestSoFar, currentRow);
        }
        return largestSoFar;
    }

    // Function to return the hottest temperature from many days
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(largestSoFar, currentRow);
        }
        return largestSoFar;
    }

    // Function to return the hottest temperature of two
    private CSVRecord getLargestOfTwo(CSVRecord largestSoFar, CSVRecord currentRow){
        if(largestSoFar == null){
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

            if(currentTemp > largestTemp){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    // Tests
    public void testHottestHourInDay(){
        FileResource fr = new FileResource("data/2014/weather-2014-08-15.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEDT"));
    }

    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
}