import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public String printYoutubeLinks(String url){
        URLResource ur = new URLResource(url);
        String returnLinks = "";
        
        for (String s : ur.words()) {
            if (s.toLowerCase().indexOf("youtube.com") != -1) {
                int firstQuote = s.toLowerCase().lastIndexOf("\"htt", s.toLowerCase().indexOf("youtube.com"));
                int secondQuote = s.toLowerCase().indexOf("\"", firstQuote + 4) + 1;
                
                returnLinks += s.substring(firstQuote, secondQuote) + "\n";
            }
        }
        return returnLinks;
    }
    
    public void testPrintYoutubeLinks(){
        String testUrl = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        System.out.println(printYoutubeLinks(testUrl));
    }
}
