package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;

        for(CSVRecord currentRow : parser){
            largestSoFar = getLargestOfTwo(largestSoFar, currentRow);
        }
        return largestSoFar;
    }

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

    private CSVRecord getSmallestOfTwo(CSVRecord smallestSoFar, CSVRecord currentRow){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

            if(currentTemp < largestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;

        for(CSVRecord currentRow : parser){
            coldestSoFar = getSmallestOfTwo(coldestSoFar, currentRow);
        }
        return coldestSoFar;
    }

    public CSVRecord coldestInManyDays(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(smallestSoFar, currentRow);
        }
        return smallestSoFar;
    }

    // Tests
    public void testHottestHourInDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }

    public void testColdestHourInDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
    }

    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
}
