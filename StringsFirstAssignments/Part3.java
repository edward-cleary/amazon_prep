
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurences(String stringA, String stringB){
        // find out if stringb is in string a
        // get index of stringb in stringa
        // get length of string a
        // check index of stringb starting at indexof1ststring + length of a
        // if not -1, return true
        if (stringB.indexOf(stringA) != -1) {
            int indexPos1 = stringB.indexOf(stringA) + stringA.length();
            if (stringB.indexOf(stringA, indexPos1) != -1){
                return true;
            }
            return false;
        }
        
        
        return false;
    }
    
    public String lastPart(String stringA, String stringB){
        if (stringB.indexOf(stringA) != -1) {
            return stringB.substring(stringB.indexOf(stringA) + stringA.length());
        }
        return stringB;
    }
    
    public void testing() {
        String test1 = "by";
        String line1 = "A story by Abby Long";
        String test2 = "a";
        String line2 = "banana";
        String test3 = "atg";
        String line3 = "ctgtatgta";
        
        System.out.println("Testing " + test1 + " in " + line1);
        System.out.println(twoOccurences(test1, line1));
        System.out.println("Testing " + test2 + " in " + line2);
        System.out.println(twoOccurences(test2, line2));
        System.out.println("Testing " + test3 + " in " + line3);
        System.out.println(twoOccurences(test3, line3));
        
        String test4 = "an";
        String test5 = "zoo";
        
        System.out.println("The part of the string after " + test4 + " in " + line2 + " is " + lastPart(test4, line2));
        System.out.println("The part of the string after " + test5 + " in forest is " + lastPart(test5, "forest"));
    }
}
