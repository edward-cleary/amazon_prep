
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // starting at startindex, search for a stopcodon
        // if not found, return length of dna string
        // if found, check to see if distance from startindex is mod 3
        // if it is mod 3 return that index + startindex
        // if not mod 3, continue searching from current index + 1 for an occurence of stopcodon until string is empty
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;   
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna){
        int posATG = dna.indexOf("ATG");
        if (posATG == -1) {
            return "";
        }
        
        int firstTAA = findStopCodon(dna, posATG, "TAA");
        int firstTAG = findStopCodon(dna, posATG, "TAG");
        int firstTGA = findStopCodon(dna, posATG, "TGA");
        
        int result = Math.min(firstTAA, (Math.min(firstTAG, firstTGA)));
        
        if (result != dna.length()) {
            return dna.substring(posATG, result + 3);
        } else {
            return "";
        }
    }
    
    public void testFindStopCodon() {
        System.out.println("Testing ATGAAGAAGTAAAAT");
        System.out.println(findStopCodon("ATGAAGAAGTAAAAT", 0, "TAA"));
        System.out.println("Testing AAGATGAAGTAAAAT");
        System.out.println(findStopCodon("AAGATGAAGTAGAAT", 3, "TAG"));
        System.out.println("Testing AAGAATGAAGAATGAAAT");
        System.out.println(findStopCodon("AAGAATGAAGAATGAAAT", 4, "TGA"));
        System.out.println("Testing ATGAAGAATAAAAGATAAAAT");
        System.out.println(findStopCodon("ATGAAGAATAAAAGATAA", 0, "TAA"));
    }
    
    public void testFindGene() {
        System.out.println("Testing ATGAAGAAGTAAAAT");
        System.out.println(findGene("ATGAAGAAGTAAAAT"));
        System.out.println("Testing ATGAAGAAGAAGTAGTAA");
        System.out.println(findGene("ATGAAGAAGAAGTAGTAA"));
        System.out.println("Testing ATGAAGAAGAATAGATAA");
        System.out.println(findGene("ATGAAGAAGAATAGATAA"));
        System.out.println("Testing ");
        System.out.println(findGene(""));
        System.out.println("Testing ATGAAGAAGTAATAGTGA");
        System.out.println(findGene("ATGAAGAAGTAATAGTGA"));
        System.out.println("Testing ATGAAGAAGTAAATGAAGAAGTAATATATAATGAAGAAGTAGAAAATGTAAAAGATGATTATTATTGATGAATGA");
        printAllGenes("ATGAAGAAGTAAATGAAGAAGTAATATATAATGAAGAAGTAGAAAATGTAAAAGATGATTATTATTGATGAATGA");
    }
    
    public void printAllGenes(String dna) {
        int currIndex = dna.indexOf("ATG");
        
        while (currIndex != -1) {
            String currentGene = findGene(dna);
            System.out.println(currentGene);
            int posDelete = dna.indexOf(currentGene) + currentGene.length();
            dna = dna.substring(posDelete);
            currIndex = dna.indexOf("ATG");
        }
    }
}
