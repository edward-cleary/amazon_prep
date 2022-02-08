import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFileNamesMap;
    
    public WordsInFiles() {
        wordFileNamesMap = new HashMap();
    }
    
    private void addWordsFromFile(File f) {
        FileResource parseWords = new FileResource(f);
        for (String word : parseWords.words()) {
            if (wordFileNamesMap.containsKey(word)) {
                if (!wordFileNamesMap.get(word).contains(f.getName())){
                    ArrayList<String> addToList = wordFileNamesMap.get(word);
                    addToList.add(f.getName());
                    wordFileNamesMap.put(word, addToList);
                }
            } else {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(f.getName());
                wordFileNamesMap.put(word, newList);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordFileNamesMap.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    
    }
    
    private int maxNumber() {
        int currMax = 0;
        
        for(String key : wordFileNamesMap.keySet()) {
            int currSize = wordFileNamesMap.get(key).size();
            if (currSize > currMax) {
                currMax = currSize;
            }
        }
        
        return currMax;
    }
    
    private ArrayList<String> wordsInNumFiles(int num) {
        ArrayList<String> wordsArrayList = new ArrayList<String>();
        for(String key : wordFileNamesMap.keySet()) {
            ArrayList<String> currList = wordFileNamesMap.get(key);
            if (currList.size() == num) {
                wordsArrayList.add(key);
            }
        }
        return wordsArrayList;
    }
    
    private void printFilesIn(String word) {
        if (wordFileNamesMap.containsKey(word)) {
            ArrayList<String> currList = wordFileNamesMap.get(word);
            for (String currFile : currList) {
                System.out.print(currFile + " ");
            }
        } else {
            System.out.println(word + " not found");
        }
    }
    
    public void tester() {
        buildWordFileMap();
        
        /*for (String word : wordFileNamesMap.keySet()){
            System.out.println(word + " is found in " + wordFileNamesMap.get(word).size() + " files."); 
        }*/
        
        int maxNum = maxNumber();
        ArrayList<String> wordsInMaxFiles = wordsInNumFiles(maxNum);
        
        
        for (String word : wordsInMaxFiles) {
            System.out.print("\"" + word + "\", ");
        }
        
        System.out.print(" found in " + maxNum + " files.\n");
        
        for (String word : wordsInMaxFiles) {
            System.out.println(word + " is found in files: ");
            printFilesIn(word);
            System.out.println("\r");
        }
        
        for (String key : wordFileNamesMap.keySet()) {
            System.out.println(key + "\t" + wordFileNamesMap.get(key));
        }
    }
}
