
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public int[] countLetters (String input) {
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
    
    public int maxIndex(int[] input) {
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
    
    public String encrypt(String input) {

        StringBuilder parseInput = new StringBuilder(input);
        
        for (int i = 0; i < parseInput.length(); i++) {
            char currChar = parseInput.charAt(i);
            
            if (Character.isLetter(currChar) != true) {
                continue;
            }
            
            boolean isUpperCase = Character.isUpperCase(currChar) ? true : false;
            currChar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currChar);
            char newLetter = shiftedAlphabet.charAt(idx);
            if (isUpperCase == false) {
              Character.toUpperCase(newLetter);
            }
            
            parseInput.setCharAt(i, newLetter);
        }
        
        return parseInput.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        return cc.encrypt(input);
    }
    
    public void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(2);
        System.out.println(decrypt(cc.encrypt("Encrypt this")));
    }
}
