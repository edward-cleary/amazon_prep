
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    private int dKey1;
    private int dKey2;
    
    public CaesarCipherTwo(int key3, int key4) {
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        key1 = key3;
        key2 = key4;
        dKey1 = getDkey(key1);
        dKey2 = getDkey(key2);
    }
    
    private int getDkey(int key) {
        int dKey = key - 4;
        if (key < 4) {
            dKey = 26 - (4 - key);
        }
        
        return dKey;
    }
    
    public String encrypt(String input) {

        StringBuilder parseInput = new StringBuilder(input);
        
        for (int i = 0; i < parseInput.length(); i++) {
            char currChar = parseInput.charAt(i);
            
            if (!Character.isLetter(currChar)) {
                continue;
            }
            
            boolean isUpperCase = Character.isUpperCase(currChar) ? true : false;
            currChar = Character.toUpperCase(currChar);
            
            int idx = alphabet.indexOf(currChar);
            
            char newChar = ' ';
            
            if (i % 2 == 0) {
                newChar = shiftedAlphabet1.charAt(idx);
            } else {
                newChar = shiftedAlphabet2.charAt(idx);
            }
            
            if (!isUpperCase) {
                newChar = Character.toLowerCase(newChar);
            }
            
            parseInput.setCharAt(i, newChar);
        }
        
        return parseInput.toString();
    }
    
    public String decrypt(String input){
        StringBuilder decrypted = new StringBuilder(input);
        CaesarCipher cc1 = new CaesarCipher(26 - key1);
        CaesarCipher cc2 = new CaesarCipher(26 - key2);
        
        for (int i = 0; i < input.length(); i++) {
            String currChar = Character.toString(decrypted.charAt(i));
            
            if (i % 2 == 0) {
                decrypted.setCharAt(i, cc1.encrypt(currChar).charAt(0));
            } else {
                decrypted.setCharAt(i, cc2.encrypt(currChar).charAt(0));
            }
        }
        
        return decrypted.toString();
    }
}
