package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVHumidity {

    // Function to find the time with the lowest humidity from a file
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;

        for(CSVRecord currentRow : parser){
            if(!currentRow.get("Humidity").equals("N/A")){
                if(lowestHumiditySoFar == null){
                    lowestHumiditySoFar = currentRow;
                } else {
                    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestTemp = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));

                    if(currentTemp < lowestTemp && currentTemp != lowestTemp){
                        lowestHumiditySoFar = currentRow;
                    }
                }
            }
        }
        return lowestHumiditySoFar;
    }

    // Function to find the smallest number from two
    private CSVRecord getSmallestOfTwo(CSVRecord smallestSoFar, CSVRecord currentRow){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));

            if(currentTemp < smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    // Function to find the lowest humidity from many files
    public CSVRecord lowestHumidityInManyDays(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        // Iterate over each file
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(lowestSoFar, currentRow);
        }

        System.out.println("Lowest humidity was " + lowestSoFar.get("Humidity") + " at " + lowestSoFar.get("DateUTC"));
        return lowestSoFar;
    }

    // Test
    public void testLowestHudidityInFile(){
        FileResource fr = new FileResource("data/2014/weather-2014-08-15.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("TimeEDT"));
    }
}
