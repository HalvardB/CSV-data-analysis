package com.company;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVMin {

    private CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

            if(currentTemp < lowestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;

        for(CSVRecord currentRow : parser){
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }

    public void fileWithColdestTemperature(){

        String filePath = null;
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        //iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            //use method to get smallest in file
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(currentRow, lowestSoFar);
            //if file contains lowest temperature then set the file name to the file path
            if(currentRow == lowestSoFar){
                filePath = fileName;
            }
        }

        System.out.println("File name :" + filePath);
        System.out.println("The coldest day was in file " + filePath);
        System.out.println("The coldest temperature on that day was " + lowestSoFar.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");

        // Print each temperature record from the day with the coldest temperature
        FileResource fr = new FileResource("data/2014/" + filePath);
        for(CSVRecord currentRow : fr.getCSVParser()){
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
    }



    // Tests
    public void testColdestHourInDay(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
    }
}
