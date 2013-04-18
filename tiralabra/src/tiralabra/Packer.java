package tiralabra;

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

  /**Creates a new Packer
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
     * First it calculates the frequencies and then constructs the huffman tree.
     * Then it reads the input file again and writes the corresponding
     * huffman codes into the output file.
     * 
     */
    public void pack() {
        freqCalc.countFrequencies(inputfile);
        this.huffmantree = makeHuffmanTree();
        this.huffmanCodes = huffmantree.getHuffmanCodes();
        writeHuffmanCodes();;
        readInputAndWritePacked();
    }
    
    /**
     * Reads the input file again character by character and writes the corresponding
     * Huffman codes into the output file.
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
        while (bitQueue.getSize() > 0) {
            System.out.println(bitQueue.getSize());
            writer.write(makeByte());
        }
    }
    
        
    /**
     * Constructs the Huffman tree.
     * 
     * First constructs the minimum heap using the frequencies calculated by 
     * frequencyCalculator needed to make the HuffmanTree.
     * @return 
     */
    private HuffmanTree makeHuffmanTree() {
        MinimumHeap pq = new MinimumHeap();
        int[] freqTable = freqCalc.getFrequencyTable();
        for (int i = 0; i < freqTable.length; i++) {
            if (freqTable[i] != 0) {
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
     * from the amount of characters in general. This is needed to handle the
     * extra bits written to the end of file to make full bits when packing.
     * This is again separated with | from all the Huffman codes used.
     * Different Huffman codes are also separated with |.
     */
    private void writeHuffmanCodes() {
        writeStringCaracterByCharacter("" + calculateAmountOfCharacters());
        writer.write('|');
        writeStringCaracterByCharacter("" + freqCalc.getCharactersRed());
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