
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int countAllGenes(String dna) {
        int currIndex = dna.indexOf("ATG");
        int count = 0;
        
        while (currIndex != -1) {
            String gene = findGene(dna.substring(currIndex));
            
            if (!gene.isEmpty()) {
                count++;
                int posDelete = dna.indexOf(gene) + gene.length();
                dna = dna.substring(posDelete);
                currIndex = dna.indexOf("ATG");
            } else {
                currIndex = -1;
            }
        }
        return count;
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
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
    
    public void testCountGenes() {
        System.out.print("Number of genes in ATGTAAATGTAG is: ");
        System.out.print(countAllGenes("ATGTAAATGTAG") + "\n");
        System.out.print("Number of genes in (emptry string) is: ");
        System.out.print(countAllGenes("") + "\n");
        System.out.print("Number of genes in ATGCCCTAAATGAATGAAATGAAAATGTAA is: ");
        System.out.print(countAllGenes("ATGCCCTAAATGAATGAAATGAAAATGTAA") + "\n");
    }
}
