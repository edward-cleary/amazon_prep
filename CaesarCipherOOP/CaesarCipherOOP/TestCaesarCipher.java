import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    
    public String breakCaesarCipher(String input) {
        CaesarCipher cc = new CaesarCipher(0);
        
        int indexOfE = cc.maxIndex(cc.countLetters(input));
        int dKey = indexOfE - 4;
        if (indexOfE < 4) {
            dKey = 26 - (4 - indexOfE);
        }
        
        CaesarCipher breakCC = new CaesarCipher(dKey);
        
        return breakCC.decrypt(input);  
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String fileString = fr.asString();
        
        //CaesarCipher cc = new CaesarCipher(18);
        
        //String encryptedFR = cc.encrypt(fileString);
        //System.out.println(encryptedFR);
        // String decryptedFR = cc.decrypt(encryptedFR);
        //System.out.println(decryptedFR);
        
        System.out.println(breakCaesarCipher(fileString));
    }
}
