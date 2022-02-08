
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int indexATG = dna.indexOf("ATG");
        if (indexATG == -1) {
            return "";
        }
        int indexTAA = dna.indexOf("TAA", indexATG + 3);
        if (indexTAA == -1) {
            return "";
        }
        if ((indexTAA - indexATG) % 3 == 0) {
            return dna.substring(indexATG, indexTAA + 3);
        }
        
        return "";
    }
    
    public void testSimpleGene() {
        String gene1 = "ATCGCGATCGATCGATCG";
        String gene2 = "ATCATGGTCAGCTAGCTAG";
        String gene3 = "GCGCGCGCGCGC";
        String gene4 = "ATGCGCCGCCGCCGCTAA";
        String gene5 = "ATGACTAA";
        
        System.out.println(gene1);
        System.out.println(findSimpleGene(gene1));
        System.out.println(gene2);
        System.out.println(findSimpleGene(gene2));
        System.out.println(gene3);
        System.out.println(findSimpleGene(gene3));
        System.out.println(gene4);
        System.out.println(findSimpleGene(gene4));
        System.out.println(gene5);
        System.out.println(findSimpleGene(gene5));
    }
}
