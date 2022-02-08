import edu.duke.*;
import java.util.*;
import java.io.File;
import org.apache.commons.csv.*;

/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        double coldestTemperature = 0.0;
        for(CSVRecord record : parser){
            double currentTemperature = Double.parseDouble(record.get("TemperatureF"));
            if(coldestRecord == null){
                coldestRecord = record;
                coldestTemperature = currentTemperature;
            } else {
                if (currentTemperature < coldestTemperature && currentTemperature > -999)  {
                    coldestRecord = record;
                    coldestTemperature = currentTemperature;
                }
            }
        }
        
        return coldestRecord;
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String coldestFile = null;
        double coldestTemperature = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            double currentTemperature = Double.parseDouble(coldestHourInFile(parser).get("TemperatureF"));
            
            if (coldestFile == null || currentTemperature < coldestTemperature) {
                coldestTemperature = currentTemperature;
                coldestFile = f.getName();
            }
        }
        
        return coldestFile;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        int lowestHumidityFound = 99;
        CSVRecord lowestHumidityRecord = null;
        
        for (CSVRecord record : parser) {
            
            if (record.get("Humidity").equals("N/A")) {
                continue;
            } else {
                int currentHumidity = Integer.parseInt(record.get("Humidity"));
                if (currentHumidity < lowestHumidityFound) {
                    lowestHumidityFound = currentHumidity;
                    lowestHumidityRecord = record;
                }
            }
        }
    
        return lowestHumidityRecord;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        int lowestHumidityFound = 99;
        int currentHumidity = 100;
        CSVRecord lowestHumidityRecord = null;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            currentHumidity = Integer.parseInt(lowestHumidityInFile(parser).get("Humidity"));
            CSVParser parserz = fr.getCSVParser();
            if (currentHumidity < lowestHumidityFound) {
                lowestHumidityRecord = lowestHumidityInFile(parserz);
                lowestHumidityFound = currentHumidity;
            }
        }

        return lowestHumidityRecord;
    }
    
    public Double averageTemperatureInFile(CSVParser parser) {
       int count = 0;
       double tempTotal = 0.0;
       
       for (CSVRecord record : parser) {
           tempTotal += Double.parseDouble(record.get("TemperatureF"));
           count++;
       }
       
       double averageTemperatureInFile = tempTotal / count;
       
       return averageTemperatureInFile;
    }
    
    public Double averageTemperatureWithHumidityInFile(CSVParser parser, int value) {
        double totalTemp = 0.0;
        int count = 0;
        
        for (CSVRecord record : parser) {
            double recordHumidity = Double.parseDouble(record.get("Humidity"));
            
            if (recordHumidity >= value) {
                totalTemp += Double.parseDouble(record.get("TemperatureF")); 
                count++;
            }
        }
        
        double averageTemperatureWithHumidity = totalTemp / count;
        
        if (count == 0) {
            return null;
        } else {
            return averageTemperatureWithHumidity;
        }
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        
        System.out.println("The coldest temperature was " + coldestRecord.get("TemperatureF") + " and occured at " + coldestRecord.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();   
        System.out.println("Coldest day was in file " + coldestFile);
        System.out.println("Coldest temperature on that day was " + coldestHourInFile(parser).get("TemperatureF") + " degrees Fahrenheit.");
        FileResource frz = new FileResource(coldestFile);
        CSVParser parserz = frz.getCSVParser();
        System.out.println("All the temperatures on the coldest day were:");
        for (CSVRecord recordz : parserz) {
            System.out.println(recordz.get("DateUTC") + ": " + recordz.get("TemperatureF"));
        }
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityInManyFilesRecord = lowestHumidityInManyFiles();
        
        System.out.println("Lowest Humidity was " + lowestHumidityInManyFilesRecord.get("Humidity") + " at " + lowestHumidityInManyFilesRecord.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        System.out.println("The average temperature in this file is " + averageTemperatureInFile(parser));
    }
    
    public void testAverageTemperatureWithHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        Double averageTemperatureWithHumidityFound = averageTemperatureWithHumidityInFile(parser, 80);
        
        if (averageTemperatureWithHumidityFound == null) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature found " + averageTemperatureWithHumidityFound);
        }
    }
}
