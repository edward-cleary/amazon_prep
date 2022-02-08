import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/
public class CodonCount {
    private HashMap<String, Integer> codonCount;
    
    public CodonCount() {
        codonCount = new HashMap<>();
    }
    
    public void buildCodonMap(int start, String dna) {
        codonCount.clear();
        for(int i = start; i <= dna.length() - 3; i += 3) {
            if (dna.length() > 3) {
                String currCodon = dna.substring(i, i + 3);
                if (Character.isLetter(currCodon.charAt(2))) {
                    if (codonCount.containsKey(currCodon)) {
                        codonCount.put(currCodon, codonCount.get(currCodon) + 1);
                    } else {
                        codonCount.put(currCodon, 1);
                    }
                }
            }
        }
    }
    
    public String getMostCommonCodon() {
        int maxCount = 0;
        String commonCodon = "";
        for (String key : codonCount.keySet()) {
            if (codonCount.get(key) > maxCount) {
                maxCount = codonCount.get(key);
                commonCodon = key;
            }
        }
        return commonCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        System.out.println("These codons between " + start + " and " + end + " are:");
        for (String key : codonCount.keySet()) {
            if (start <= codonCount.get(key) && end >= codonCount.get(key)) {
                System.out.println(key + "\t" + codonCount.get(key));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        
        String contents = fr.asString().toUpperCase().trim();
        
        Integer[] readingFrames = {0,1,2};
        
        for (int frame : readingFrames) {
            buildCodonMap(frame, contents);
            System.out.println("\n\nTotal number of unique codons in frame " + frame + " is " + codonCount.size());
            String commonCodon = getMostCommonCodon();
            System.out.println("The most common codon in frame " + frame + " is " + commonCodon + " with " + codonCount.get(commonCodon) + " occurences.");
            printCodonCounts(2, 10);
        }
        
    }
}
