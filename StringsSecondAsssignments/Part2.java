
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringA, String stringB) {
        int currIndex = 0;
        int count = 0;
        while (currIndex != -1) {
            currIndex = stringB.indexOf(stringA);
            if (currIndex > -1) {
                count++;
                stringB = stringB.substring(currIndex + stringA.length());
            }
        }
        return count;
    }
    
    public void testHowMany() {
        System.out.println("and in aaaandandandandaaa");
        System.out.println(howMany("and", "aaaandandandandaaa"));
        System.out.println("but in andandand");
        System.out.println(howMany("but", "andandand"));
        System.out.println("but in (emptry string)");
        System.out.println(howMany("but", ""));
    }
}
