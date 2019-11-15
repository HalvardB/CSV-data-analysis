package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {

    // Function to list exporters of one type og good
    public void listExporters(CSVParser parser, String exportOfInterest){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    // Function that return what goods a country exports
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String exporter = record.get("Country");
            if(exporter.equals(country)){
                return record.get("Exports");
            }
        }
        return "NOT FOUND";
    }

    // Function that returns exporters of two types of goods
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(" - " + record.get("Country"));
            }
        }
    }

    // Function that returns how many countries that export one type of goods
    public int numberOfExporters(CSVParser parser, String exportItem){
        int countCountries = 0;
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                countCountries++;
            }
        }
        return countCountries;
    }

    // Function that lists all countries that exports for more than a value.
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String stringValue = record.get("Value (dollars)");
            if(stringValue.length() > amount.length()){
                System.out.println(" - " + record.get("Country") + ": " + record.get("Value (dollars)"));
            }
        }
    }

    // Tests
    public void tests(){
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();

        // CountryInfo test
        String countryTest = countryInfo(parser, "Norway");
        System.out.println("Norway exports: " + countryTest);

        // listExportersTwoProducts test
        parser = fr.getCSVParser();
        System.out.println("This are the only countries that exports both cotton AND flowers are:");
        listExportersTwoProducts(parser,"cotton", "flowers");

        // numberOfExporters test
        parser = fr.getCSVParser();
        int exporters = numberOfExporters(parser, "cocoa");
        System.out.println(exporters + " countries exports cocoa.");

        // bigExporters test
        parser = fr.getCSVParser();
        System.out.println("Countries whose exports are valued one trillion US dollars or more:");
        bigExporters(parser, "$999,999,999,999");
    }
}