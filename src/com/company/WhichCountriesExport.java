package com.company;

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee(){
        FileResource fr = new FileResource("exports_small.csv");
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }

    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String exporter = record.get("Country");
            if(exporter.equals(country)){
                String products = record.get("Exports");
                String value = record.get("Value (dollars)");
                return country + ": " + products + ": " + value;
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }

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

    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String stringValue = record.get("Value (dollars)");
            if(stringValue.length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

    // Test cases created like a constructor so they run automatically
    public void tests(){
        FileResource fr = new FileResource("exports_small.csv");
        CSVParser parser = fr.getCSVParser();

        // CountryInfo test
        String countryTest = countryInfo(parser, "Germany");
        System.out.println(countryTest);

        // listExportersTwoProducts test
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold", "diamonds");

        // numberOfExporters test
        parser = fr.getCSVParser();
        int exporters = numberOfExporters(parser, "gold");
        System.out.println("Numbers of exporters: " + exporters);

        // bigExporters test
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");
    }
}