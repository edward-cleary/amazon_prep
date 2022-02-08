import edu.duke.*;
import java.util.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        
        for (String word : fr.words()) {
            word  = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs the most often is \"" + myWords.get(maxIndex) + "\" - Which occured " + myFreqs.get(maxIndex) + " times.");
    }
    
    public int findIndexOfMax() {
        int count = 0;
        int idx = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > count) {
                idx = i;
                count = myFreqs.get(i);
            }
        }
        
        return idx;
    }
}
