import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo  {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String halfOfString(String input, boolean even) {
        int shift = 0;
        String newString = "";
        
        if (even == false) {
            shift = 1;
        }
        
        StringBuilder parseInput = new StringBuilder(input);
        
        for (int i = shift; i < parseInput.length(); i += 2) {
            newString += parseInput.charAt(i);
        }
        
        return newString;
    }
    
    private int[] countLetters (String input) {
        StringBuilder parseInput = new StringBuilder(input);
        int[] letters = new int[26];
        
        for (int i = 0; i < parseInput.length(); i++) {
            if (Character.isLetter(parseInput.charAt(i))) {
                char currChar = Character.toUpperCase(parseInput.charAt(i));
                int idx = alphabet.indexOf(currChar);
                letters[idx]++;
            }
        }
        
        return letters;
    }   
    
    private int maxIndex(int[] input) {
        int max = 0;
        int idx = 0;
        
        for(int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
                idx = i;
            }
        }
           
        return idx;
    }
    
    private int getDkey(int key) {
        int dKey = key - 4;
        if (key < 4) {
            dKey = 26 - (4 - key);
        }
        
        return dKey;
    }
    
    public String breakCaesarCipherTwo(String input) {
        int key1 = getDkey(maxIndex(countLetters(halfOfString(input, true))));
        int key2 = getDkey(maxIndex(countLetters(halfOfString(input, false))));
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        
        System.out.println(key1 + " " + key2);
        
        return cc.decrypt(input);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String stringFile = fr.asString();
        
        //CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        //System.out.println(cc.encrypt(stringFile));
        //System.out.println(cc.decrypt(stringFile));
        
        System.out.println(breakCaesarCipherTwo(stringFile));
        System.out.println();
        
        //CaesarCipherTwo cc = new CaesarCipherTwo(2,20);
        //System.out.println(cc.decrypt("Top ncmy qkff vi vguv vbg ycpx"));
        
        //System.out.println(breakCaesarCipherTwo("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
    }
}
