package tiralabra.encoding;

import tiralabra.datastructures.FrequencyCalculator;
import tiralabra.datastructures.FrequencyCalculator;
import tiralabra.datastructures.MinimumHeap;
import tiralabra.datastructures.HuffmanTree;
import tiralabra.datastructures.BitQueue;
import tiralabra.datastructures.Node;
import tiralabra.io.FileWriter;
import tiralabra.io.FileReader;


/**
 * Packer packs a file.
 * 
 * @author Krista
 */
public class Packer {

    private HuffmanTree huffmantree;
    private FrequencyCalculator freqCalc;
    private String[] huffmanCodes;
    private FileReader reader;
    private FileWriter writer;
    private BitQueue bitQueue;
    private String inputfile;
    private boolean countedFrequencies;

  /**
   * Creates a new Packer.
   * 
   * @param outputFile
   * @param inputFile 
   */
    public Packer(String outputFile, String inputFile) {
        this.inputfile = inputFile;
        this.reader = new FileReader(inputFile);
        this.writer = new FileWriter(outputFile);
        this.freqCalc = new FrequencyCalculator();
        bitQueue = new BitQueue();
    }

    /**
     * Does the packing of the input file.
     * 
     * Frequencies should be calculated or red before calling this method. Otherwise
     * all characters have frequency of zero.
     * 
     * Method reads the input file and writes the corresponding
     * huffman codes into the output file.
     * 
     */
    public void pack() {
        this.huffmantree = makeHuffmanTree();
        this.huffmanCodes = huffmantree.getHuffmanCodes();
        writeHuffmanCodes();
        readInputAndWritePacked();
    }
    
    /**
     * Counts the frequencies of each different character in the file to be compressed
     */
    public void countFrequencies() {
        freqCalc.countFrequencies(inputfile);
        countedFrequencies = true;
    }
    
    /**
     * Reads the frequencies from a file given as a parameter.
     * 
     * It is assumed that frequencies are listed in the way that a character and its
     * frequency are separated with a space, and all characters are listed on different lines.
     * 
     * Here we can use expected frequencies for each character in the file to be compressed for
     * compressing. This way we do not need to read the file to be compressed twice.
     * 
     * @param filename file that has expected frequencies listed
     */
    public void readFrequencies(String filename) {
        freqCalc.readFrequencies(filename);
        countedFrequencies = false;
    }
    
    /**
     * Reads the input file character by character and writes the corresponding
     * Huffman codes into the output file.
     * 
     * In the end the amount of extra bits used to write full bytes (trash bits) is written.
     * This is needed so that when unpacking it is possible to know how many bits to ignore
     * from the end.
     * 
     */
    private void readInputAndWritePacked() {
        while (reader.available() > 0) {
            String code = huffmanCodes[reader.read()];
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '0') {
                    bitQueue.add(false);
                } else {
                    bitQueue.add(true);
                }
            }
            while (bitQueue.getSize() > 8) {
                writer.write(makeByte());
            }
        }
        int trashbits = 8 - bitQueue.getSize();
        writer.write(makeByte());
        writeStringCaracterByCharacter(""+trashbits);
    }
    
        
    /**
     * Constructs the Huffman tree.
     * 
     * First constructs the minimum heap using the frequencies calculated by 
     * frequencyCalculator needed to make the HuffmanTree.
     * 
     * If frequencies have been calculated from the file to be compressed only characters
     * with frequencies larger than zero are added to the tree. If frequencies are just approximates
     * all characters are added to the tree because we can't be sure what characters the file to be
     * compressed will include.
     * 
     * @return constructed Huffman tree
     */
    private HuffmanTree makeHuffmanTree() {
        MinimumHeap pq = new MinimumHeap();
        int[] freqTable = freqCalc.getFrequencyTable();
        for (int i = 0; i < freqTable.length; i++) {
            if ( countedFrequencies && freqTable[i] != 0) {
                pq.insert(new Node(freqTable[i], i));
            } else if (!countedFrequencies) {
                pq.insert(new Node(freqTable[i], i));
            }
        }
        return new HuffmanTree(pq);
    }

    /**
     * Transforms 8 bits to a byte
     * @return 
     */
    private int makeByte() {
        int ret = 0;
        boolean bit = false;
        for (int i = 0; i < 8; i++) {
            if (!bitQueue.isEmpty()) {
                bit = bitQueue.remove();
            }
            if (bit) {
                ret += (1 << (7 - i));
            }
        }
        return ret;
    }
    /**
     * Writes the Huffman codes of used characters in the beginning of the packed file (The Dictionary).
     * 
     * First the amount of different characters is written so that we can know
     * how many different codes we need to read when unpacking.
     * Then the amount of different characters is separated with | 
     * from all the Huffman codes used.
     * Different Huffman codes are also separated with |.
     */
    private void writeHuffmanCodes() {
        writeStringCaracterByCharacter("" + calculateAmountOfCharacters());
        writer.write('|');
        writeStringCaracterByCharacter(makeCodeString());
    }
    
    /**
     * Writes a String to a file character by character.
     * 
     * @param characters String to be written.
     */
    private void writeStringCaracterByCharacter(String characters) {
        for (int i = 0; i < characters.length(); i++) {
            writer.write(characters.charAt(i));    
        }
    }
    
    /**
     * Makes a String out of the Huffman codes.
     * 
     * Different codes are separated with |
     * 
     * @return String with all Huffman codes separated by |
     */
    private String makeCodeString() {
        String codes = "";
        for (int i = 0; i < huffmanCodes.length; i++) {
            if (huffmanCodes[i] != null) {
                codes += (char)i;
                for (int j = 0; j < huffmanCodes[i].length(); j++) {
                    codes+=huffmanCodes[i].charAt(j);
                }
                codes+='|';
            }
        }
        return codes;
    }

    /**
     * Calculates the amount of different characters in the file.
     * 
     * @return amount
     */
    private int calculateAmountOfCharacters() {
        int amount = 0;
        for (String s : huffmanCodes){
            if (s != null) {
                amount++;
            }
        }
        return amount;
    }
    
}