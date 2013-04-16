/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Krista
 */
public class Packer {

    private HuffmanTree huffmantree;
    private FrequencyCalculator freqCalc;
    private String[] huffmanCodes;
    private FileReader reader;
    private FileWriter writer;
    private Queue<Integer> bitQueue;
    private String inputfile;

    /**
     * Creates a new Packer
     * 
     * @param outputFile Name of the file to be written
     * @param inputFile Name of the file to be red
     * @param codes  Huffman codes
     */
    public Packer(String outputFile, String inputFile) {
        this.inputfile = inputFile;
        this.reader = new FileReader(inputFile);
        this.writer = new FileWriter(outputFile);
        this.freqCalc = new FrequencyCalculator();
        bitQueue = new LinkedList<Integer>();
    }

    /**
     * Does the packing of the input file.
     * 
     * First it calculates the frequencies and then constructs the huffman tree.
     * Then it reads the input file again character by character and writes the corresponding
     * huffman codes into the output file.
     * 
     */
    public void pack() {
        freqCalc.countFrequencies(inputfile);
        this.huffmantree = makeHuffmanTree();
        this.huffmanCodes = huffmantree.getHuffmanCodes();
        writeHuffmanCodes();
        while (reader.available() > 0) {
            String code = huffmanCodes[reader.read()];
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '0') {
                    bitQueue.add(0);
                } else {
                    bitQueue.add(1);
                }
            }
            if (bitQueue.size() > 8) {
                writer.write(makeByte());
            }
        }
        while (bitQueue.size() > 0) {
            writer.write(makeByte());
        }
    }
    
        
    /**
     * Constructs the Huffman tree.
     * 
     * First constructs the minimum heap using the frequencies calculated by 
     * frequencyCalculatorneeded to make the HuffmanTree.
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
        int bit = 0;
        for (int i = 0; i < 8; i++) {
            if (!bitQueue.isEmpty()) {
                bit = bitQueue.poll();
            }
            if (bit == 1) {
                ret += (1 << (7 - i));
            }
        }
        return ret;
    }
    /**
     * Writes the Huffman codes of used characters in the beginning of the file.
     * 
     * First the amount of different characters is written so that we can know
     * how many different codes we need to read when unpacking.
     * Then the amount of different characters is separated with | 
     * from the amount of characters in general. This is needed to handle the
     * extra bits written to the end of file to make full bits when packing.
     * This is again separated with | from all the Huffman codes used.
     * Different huffman codes are also spearated with |.
     */
    private void writeHuffmanCodes() {
        String characters = "" + calculateAmountOfCharacters();
        for (int i = 0; i < characters.length(); i++) {
            writer.write(characters.charAt(i));    
        }
        writer.write('|');
        characters = "" + freqCalc.getCharactersRed();
        for (int i = 0; i < characters.length(); i++) {
            writer.write(characters.charAt(i));    
        }
        writer.write('|');
        String codes = makeCodeString();
        for (int i = 0; i < codes.length(); i++) {
            writer.write(codes.charAt(i));
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