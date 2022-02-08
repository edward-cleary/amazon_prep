
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int indexStartCodon = dna.toUpperCase().indexOf(startCodon);
        if (indexStartCodon == -1) {
            return "";
        }
        int indexStopCodon = dna.toUpperCase().indexOf(stopCodon, indexStartCodon + 3);
        if (indexStopCodon == -1) {
            return "";
        }
        if ((indexStartCodon - indexStopCodon) % 3 == 0) {
            return dna.substring(indexStartCodon, indexStopCodon + 3);
        }
        
        return "";
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String gene1 = "ATCGCGATCGATCGATCG";
        String gene2 = "ATCATGGTCAGCTAGCTAG";
        String gene3 = "GCGCGCGCGCGC";
        String gene4 = "ATGCGCCGCCGCCGCTAA";
        String gene5 = "ATGACTAA";
        String gene6 = "ATGGGTTAAGTC";
        String gene7 = "gatgctataat";
        
        System.out.println(gene1);
        System.out.println(findSimpleGene(gene1, startCodon, stopCodon));
        System.out.println(gene2);
        System.out.println(findSimpleGene(gene2, startCodon, stopCodon));
        System.out.println(gene3);
        System.out.println(findSimpleGene(gene3, startCodon, stopCodon));
        System.out.println(gene4);
        System.out.println(findSimpleGene(gene4, startCodon, stopCodon));
        System.out.println(gene5);
        System.out.println(findSimpleGene(gene5, startCodon, stopCodon));
        System.out.println(gene6);
        System.out.println(findSimpleGene(gene6, startCodon, stopCodon));
        System.out.println(gene7);
        System.out.println(findSimpleGene(gene7, startCodon, stopCodon));
    }
}
