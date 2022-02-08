import edu.duke.*;
import java.util.*;
import java.io.File;
import org.apache.commons.csv.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        int totalNames = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            totalNames++;
            
            if (record.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            } else {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        
        System.out.println("total births= " + totalBirths);
        System.out.println("total girls= " + totalGirls);
        System.out.println("total boys= " + totalBoys);
        System.out.println("total girls names= " + totalGirlsNames);
        System.out.println("total boys names= " + totalBoysNames);
        System.out.println("total names= " + totalNames);
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        
        for(CSVRecord record : parser) {
            if (record.get(1).equals(gender)){
                rank++;
            }
            
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                return rank;
            }
        }
        
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rankCounter = 0;
        
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                rankCounter++;
            }
            
            if (rankCounter == rank) {
                return record.get(0);
            }
        }
        
        return "NO NAME";
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        
        int highestRank = -1;
        String yearOfHighestRank = "";
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int nameRankInYear = 0;
            
            for (CSVRecord record : parser) {
                if (record.get(1).equals(gender)) {
                    nameRankInYear++;
             
                    if (record.get(0).equals(name)) {
                        if (highestRank == -1) {
                            highestRank = nameRankInYear;
                        }
                        
                        if (nameRankInYear <= highestRank) {
                            highestRank = nameRankInYear;
                            yearOfHighestRank = f.getName().substring(3, 7);
                        }
                    }
                }
            }
        }
        
        if (yearOfHighestRank.isEmpty()) {
            return -1;
        } else {
            return Integer.parseInt(yearOfHighestRank);
        }
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        
        double nameRankTotal = 0.0;
        int count = 0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            int nameRank = 0;
            
            for (CSVRecord record : parser) {
                if (record.get(1).equals(gender)) {
                    nameRank++;
                    
                    if (record.get(0).equals(name)) {
                        count++;
                        break;
                    }
                }
            }

            nameRankTotal += nameRank;
        }
         
        return nameRankTotal / count;
    }
    
    public int totalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int totalRankedHigher = 0;
        
        for (CSVRecord record : parser) {
            if (record.get(0).equals(name)) {
                return totalRankedHigher;
            }
            
            if (record.get(1).equals(gender)) {
                totalRankedHigher += Integer.parseInt(record.get(2));
            }
        }
        
        return -1;
    }
    
    public void testTotalBirthsRankedHigher() {
        System.out.println(totalBirthsRankedHigher(2012, "Ethan", "M"));
    }
    
    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
        
    }
    
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public void testGetName() {
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int sourceNameRank = getRank(year, name, gender);
        String targetName = getName(newYear, sourceNameRank, gender);

        System.out.print(name + " born in " + year + " would be " + targetName + " if "); 
        if (gender.equals("M")){ 
            System.out.print("he");
        } else {System.out.print("she");
        } 
        System.out.print(" was born in " + newYear + ". \n");
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public void testGetRank() {
        System.out.println(getRank(2012, "Mason", "M"));
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        
        totalBirths(fr);
    }
}
