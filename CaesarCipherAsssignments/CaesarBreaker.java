import edu.duke.*;
import java.util.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }

        System.out.println("dkey is " + dkey);

        return cc.encrypt(encrypted, 26 - dkey);
    }

    public String halfOfString(String message, int start) {
        StringBuilder newString = new StringBuilder(message);
        StringBuilder oldString = new StringBuilder(message);
        int deleteCount = 0;
        
        for (int i = 0; i < message.length(); i++) {
            if ((i + 2) % 2 != start) {
                if (Character.isLetter(oldString.charAt(i)) == true) {
                    newString.deleteCharAt(i - deleteCount);
                    deleteCount++;
                }
            }
        }

        return newString.toString();
    }

    public int[] countLetters(String text) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder textToParse = new StringBuilder(text);

        int[] counts = new int[26];

        for (int i = 0; i < textToParse.length(); i++) {
            char currChar = textToParse.charAt(i);

            if (Character.isLetter(currChar) == true) {
                currChar = Character.toLowerCase(currChar);

                if (textToParse.indexOf(Character.toString(currChar)) != -1) {
                    int idx = alpha.indexOf(currChar);
                    counts[idx]++;
                }
            }
        }
        
        return counts;
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
    
    public int getKey(String s) {
        return indexOfMax(countLetters(s));
    }
    
    public int getDKey(int key){
        int dkey = key - 4;
        if (key < 4) {
            dkey = 26 - (4 - key);
        }
        
        return 26 - dkey;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstString = halfOfString(encrypted, 0);
        String secondString = halfOfString(encrypted, 1);
        
        CaesarCipher cc = new CaesarCipher();
         
        int key1 = getKey(firstString);
        int key2 = getKey(secondString);
        
        System.out.println("key1 " + key1 + " key2 " + key2);
        key1 = getDKey(2);
        key2 = getDKey(20);
        
        return cc.encryptTwoKeys(encrypted, key1, key2);
        //return cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", key1, key2);
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encryptedString = fr.asString();
        System.out.println(decryptTwoKeys(encryptedString));
    }
    
    public void testGetKey() {
        FileResource fr = new FileResource();
        String stringFile = fr.asString();
        System.out.println(getKey(stringFile));
    }

    public void testHalfOfString() {
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public void testDecrypt() {
        FileResource fr = new FileResource();
        String stringFile = fr.asString();
        System.out.println(decrypt(stringFile));
    }
}
