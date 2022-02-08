import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        
        if (vowels.indexOf(Character.toLowerCase(ch)) != -1 ) {
            return true;
        }
        
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
       // loop through string and check if it is a value
       // if it equates to true, change that letter to char
       // if it equates to false, return that letter as is
       StringBuilder newPhrase = new StringBuilder(phrase);
       for (int i = 0; i < phrase.length(); i++) {
           if (isVowel(phrase.charAt(i))) {
               newPhrase.setCharAt(i, ch);
           }
       }
       
       return newPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++) {
            if (newPhrase.charAt(i) == ch) {
                if (i % 2 == 0) {
                    newPhrase.setCharAt(i, '*');
                } else {
                    newPhrase.setCharAt(i, '+');
                }
            }
        }
        
        return newPhrase.toString();
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }
}
