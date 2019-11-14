package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVAverage {

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidity){
        double averageTemp = 0.0;
        int timeCount = 0;
        DirectoryResource dr = new DirectoryResource();

        for(CSVRecord currentRow : parser){
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if(currentHumidity > humidity){
                averageTemp += Double.parseDouble(currentRow.get("TemperatureF"));
                timeCount++;
            }
        }

        if(averageTemp == 0.0) {
            System.out.println("No temperatures with that humidity");
        }

        return averageTemp / timeCount;
    }

    public double averageTemperatureInFile(CSVParser parser){
        double averageTemp = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(CSVRecord currentRow : parser){
            averageTemp += Double.parseDouble(currentRow.get("TemperatureF"));
        }

        return averageTemp / 24;
    }

    public void testAverageTemperature(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemp);
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average temperature with humidity in file is " + averageTemp);
    }
}
