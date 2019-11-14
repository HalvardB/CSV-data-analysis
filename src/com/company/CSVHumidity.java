package com.company;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

public class CSVHumidity {
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;

        for(CSVRecord currentRow : parser){
            if(currentRow.get("Humidity") != "N/A"){
                if(lowestHumiditySoFar == null){
                    lowestHumiditySoFar = currentRow;
                } else {
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    double lowestTemp = Double.parseDouble(lowestHumiditySoFar.get("TemperatureF"));

                    if(currentTemp > lowestTemp){
                        lowestHumiditySoFar = currentRow;
                    }
                }
            }
        }

        // System.out.println("Lowest humidity was " + lowestHumiditySoFar.get("Humidity") + " at " + lowestHumiditySoFar.get("DateUTC"));
        return lowestHumiditySoFar;
    }

    private CSVRecord getSmallestOfTwo(CSVRecord smallestSoFar, CSVRecord currentRow){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));

            if(currentTemp > smallestTemp){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord lowestHumidityInManyDays(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(lowestSoFar, currentRow);
        }
        System.out.println("Lowest humidity was " + lowestSoFar.get("Humidity") + " at " + lowestSoFar.get("DateUTC"));

        return lowestSoFar;
    }

    // Tests
    public void testLowestHudidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
    }
}
