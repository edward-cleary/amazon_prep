
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public String countryInfo(CSVParser parser, String country){
        String exports = "";
        String value = "";
        String stringToReturn = "";
        
        for(CSVRecord record : parser){
            
            if (record.get("Country").contains(country)) {
                exports = record.get("Exports");
                value = record.get("Value (dollars)");
                
                stringToReturn = country + ": " + exports + ": " + value;
            }
        }
        
        if (stringToReturn.isEmpty()){
            System.out.println(stringToReturn);
            return "Not Found";
        } else {
            return stringToReturn;
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        //print out a line for each country that has both export items
        //loop through records to find if record exports contains both export item 1 and export item2
        //if it does contain, get record country name and print it 
        String countryExports = "";
        
        for(CSVRecord record : parser) {
            countryExports = record.get("Exports");
            if (countryExports.contains(exportItem1) && countryExports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
        
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        
        for(CSVRecord record: parser) {
            if (record.get("Exports").contains(exportItem)) {
                count++;
            }
        }
        
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
}
