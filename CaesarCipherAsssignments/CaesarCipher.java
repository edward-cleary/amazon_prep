import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);  
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            
            boolean isUpper = true;
            if (Character.isLowerCase(currChar) == true) {
                isUpper = false;
            } 
            
            currChar = Character.toUpperCase(currChar);
            
            int idx = alphabet.indexOf(currChar);
            
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                
                if (isUpper == true) {
                    encrypted.setCharAt(i, newChar);
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++) {
            String letter = Character.toString(encrypted.charAt(i));
            
            if (i % 2 == 0) {
                encrypted.setCharAt(i, encrypt(letter, key1).charAt(0));
            } else {
                encrypted.setCharAt(i, encrypt(letter, key2).charAt(0));
            }
        }
        
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }
    
    public void testCaesar() {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(message);
        String encrypted = encrypt(message, key);
        System.out.println("The key is " + key + "\n" + encrypted);
    }
    
    public void testEncrypt() {
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }
}
