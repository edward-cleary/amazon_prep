import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characters = new ArrayList<String>();
    private ArrayList<Integer> numCharacters = new ArrayList<Integer>();
    
    public void findAllCharacters() {
        characters.clear();
        numCharacters.clear();
        
        FileResource fr = new FileResource();
        
        for (String line : fr.lines()){
            if (line.indexOf(".") != -1) {
                String character = line.substring(0, line.indexOf(".")).trim();
                update(character);
            } else {
                continue;
            }
        }
    }
    
    public void update(String person) {  
        if (numCharacters.size() == 0) {
            numCharacters.add(1);
            characters.add(person);
            return;
        } 
            
        int match = 0;
        for (int i = 0; i < numCharacters.size(); i++) {
            if (characters.get(i).toUpperCase().equals(person)) {
                int value = numCharacters.get(i);
                numCharacters.set(i, value + 1);
                match = 1;
                return;
            }
        }
        
        if (match == 0) {
            characters.add(person.toUpperCase());
            numCharacters.add(1);
            return;
        }
    }
    
    public void charactersWithNumParts(int min, int max) {
        for(int i = 0; i < numCharacters.size(); i++) {
            if (numCharacters.get(i) >= min && numCharacters.get(i) <= max) {
                System.out.println(characters.get(i) + " has " + numCharacters.get(i) + " speaking parts which is between your entry of " + min + " and " + max);
            }
        }
    }
   
    public void tester() {
        for (int i = 0; i < numCharacters.size(); i++) {
            if (numCharacters.get(i) > 6) {
                System.out.println(characters.get(i) + "\t" + numCharacters.get(i));
            }
        }
    }
}
