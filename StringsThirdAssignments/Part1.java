import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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
    
    /*public void testFindStopCodon() {
        System.out.println("Testing ATGAAGAAGTAAAAT");
        System.out.println(findStopCodon("ATGAAGAAGTAAAAT", 0, "TAA"));
        System.out.println("Testing AAGATGAAGTAAAAT");
        System.out.println(findStopCodon("AAGATGAAGTAGAAT", 3, "TAG"));
        System.out.println("Testing AAGAATGAAGAATGAAAT");
        System.out.println(findStopCodon("AAGAATGAAGAATGAAAT", 4, "TGA"));
        System.out.println("Testing ATGAAGAATAAAAGATAAAAT");
        System.out.println(findStopCodon("ATGAAGAATAAAAGATAA", 0, "TAA"));
    }*/
    
    /*public void testFindGene() {
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
        getAllGenes("ATGAAGAAGTAAATGAAGAAGTAATATATAATGAAGAAGTAGAAAATGTAAAAGATGATTATTATTGATGAATGA");
    }*/
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        
        int currIndex = dna.indexOf("ATG");
        
        while (currIndex != -1) {
            String currentGene = findGene(dna.substring(currIndex));
            if (!currentGene.isEmpty()) {
                geneList.add(currentGene);
                currIndex = dna.indexOf("ATG", currIndex + currentGene.length());
            } else {
                break;
            }
        }
        return geneList;
        /*int currIndex = dna.indexOf("ATG");

        while (currIndex != -1) {
            String currentGene = findGene(dna);
            /*System.out.println("this is the current gene " + currentGene);
            System.out.println("Its length is " + currentGene.length());
            geneList.add(currentGene);
            currIndex = dna.indexOf("ATG", currIndex + currentGene.length());
        }
        return geneList;*/
    }
    
    public double cgRatio(String dna) {
        double numCs = 0.0;
        double numGs = 0.0;
        int count = dna.indexOf("C");
        while (count != -1) {
            numCs++;
            count = dna.indexOf("C", count  + 1);
        }
        count = dna.indexOf("G");
        while (count != -1) {
            numGs++;
            count = dna.indexOf("G", count + 1);
        }
        return numCs / numGs;
    }
    
    public void processGenes(StorageResource sr){
        System.out.println("These are all greater than 60 characters: ");
        int numGreaterThan60Chars = 0;
        for (String s : sr.data()) {
            if (s.length() > 60) {
                numGreaterThan60Chars++;
                System.out.println(s);
            }
        }
        System.out.println("The number of strings greater than 60 characters is: " + numGreaterThan60Chars);
        
        System.out.println("These strings have a C-G ratio greater than 0.35");
        int numCGRatio = 0;
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                numCGRatio++;
                System.out.println(s);
            }
        }
        System.out.println("The strings with a C-G ratio greater than 0.35 totals to: " + numCGRatio);
        
        int longestGene = 0;
        for (String s : sr.data()) {
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
        }
        System.out.println("The longest gene found was " + longestGene);
    }
    
    public int countCodon(String codon, String dna){
        int pos = dna.indexOf(codon);
        int count = 0;
        while (pos != -1) {
            if ((pos + 1) % 3 == 0) {
                count++;
            }
            pos = dna.indexOf(codon, pos + 1);
        }
        return count;
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource genes = getAllGenes(dna);
        System.out.println("The number of genes found is " + genes.size());
        processGenes(genes);
        System.out.println("There are " + countCodon("ATG", dna) + " ATG codons in this dna strand.");
    }
    
    //public void testOn(String dna){
        //StorageResource genes = getAllGenes(dna);
       // for (String g: genes.data()) { 
           // System.out.println(g);
        //}
    //}
    
    public void test() {
        //testOn("ATGAAGAAGTAAATGAAGAAGTAATATATAATGAAGAAGTAGAAAATGTAAAAGATGATTATTATTGATGAATGA");
        //System.out.println(cgRatio("ACGCGCGC"));
        //testProcessGenes("ATGCGCCGCCGCCGCTAAATGTAAATGTAAATGCGCGCGCGCCGCTAA");
        //testProcessGenes("ATGTAAATGTAAATGCCCTAA");
        //testProcessGenes();
        //System.out.println(countCodon);
    }
}
