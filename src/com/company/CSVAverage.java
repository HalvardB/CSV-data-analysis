package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVAverage {

    // Function to find the average temperature from days with X humidity
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidity){
        double averageTemp = 0.0;
        int timeCount = 0;
        DirectoryResource dr = new DirectoryResource();

        for(CSVRecord currentRow : parser){
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if(currentHumidity >= humidity){
                averageTemp += Double.parseDouble(currentRow.get("TemperatureF"));
                timeCount++;
            }
        }

        if(timeCount == 0) {
            System.out.println("No temperatures with that humidity");
            return 0;
        }

        return averageTemp / timeCount;
    }

    // Function to find average temperature from a file
    public double averageTemperatureInFile(CSVParser parser){
        double averageTemp = 0.0;
        int tempCount = 0; // the count varies form file to file (23 hours in some files)
        DirectoryResource dr = new DirectoryResource();

        // Iterate over each row in the file
        for(CSVRecord currentRow : parser){
            averageTemp += Double.parseDouble(currentRow.get("TemperatureF"));
            tempCount++;
        }

        return averageTemp / tempCount;
    }

    // Tests
    public void testAverageTemperature(){
        FileResource fr = new FileResource("data/2014/weather-2014-08-15.csv");
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature was " + Math.round(averageTemp) + " fahrenheit");
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average temperature with humidity in file is " + averageTemp);
    }
}
