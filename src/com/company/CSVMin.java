package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {

    // Function to return the smallest temperature of two
    private CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

            if(currentTemp < lowestTemp && currentTemp != -9999){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    // Function to return the coldest temperature in a file
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;

        for(CSVRecord currentRow : parser){
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    // Function to find the file with the coldest temperature from many files
    public void fileWithColdestTemperature(){
        String filePath = null;
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        // Iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fileName = f.getName();

            // Find the smallest temperature
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(currentRow, lowestSoFar);

            // If file contains lowest temperature then set the file name to the file path
            if(currentRow == lowestSoFar){
                filePath = fileName;
            }
        }

        // Print information about this file
        System.out.println("File name: " + filePath);
        System.out.println("The coldest day was in file " + filePath);
        System.out.println("The coldest temperature on that day was " + lowestSoFar.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day where:");

        // Print each temperature record from the day with the coldest temperature
        FileResource fr = new FileResource("data/2013/" + filePath);
        for(CSVRecord currentRow : fr.getCSVParser()){
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
    }

    // Test
    public void testColdestHourInDay(){
        FileResource fr = new FileResource("data/2014/weather-2014-08-15.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEDT"));
    }
}
