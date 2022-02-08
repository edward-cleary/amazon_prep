import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts) {
        //loop through resource by words
        //count the length of each word, but if the first or last letters are not actually letters they are not counted
        //increase the counter for the length in the counts
        for (String word : resource.words()) {
            int count = word.length();
            StringBuilder parseWord = new StringBuilder(word);
            
            if (Character.isLetter(parseWord.charAt(count - 1)) == false) {
                count--;
            }
            
            if (Character.isLetter(parseWord.charAt(0)) == false){
                count--;
            };
            
            if (count > 0 && count < 30) {
                counts[count]++;
            } else if (count >= 30){
                counts[30]++;
            }
        }
        
        for (int i = 1; i < counts.length; i++) {
            System.out.println(counts[i] + " words of length " + i);
        }
        
        int mostWords = indexOfMax(counts);
        
        System.out.println("The word length with the most occurences is length " + mostWords + " with " + counts[mostWords]);
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int [31];
        countWordLengths(fr, counts);
    }
    
    public int indexOfMax(int[] values) {
        int counter = 0;
        int idx = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > counter) {
                counter = values[i];
                idx = i;
            }
        }
        
        return idx;
    }
}
