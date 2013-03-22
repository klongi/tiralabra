/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.LinkedList;
import java.util.Queue;
import tiralabra.FileReader;
import tiralabra.FileWriter;

/**
 *
 * @author Krista
 */
public class Packer {

    private String[] huffmanCodes;
    private FileReader reader;
    private FileWriter writer;
    private Queue<Integer> bitQueue;
    private int trashbits;

    /**
     * Creates a new Packer
     * 
     * @param outputFile Name of the file to be written
     * @param inputFile Name of the file to be red
     * @param codes  Huffman codes
     */
    public Packer(String outputFile, String inputFile, String[] codes) {
        this.reader = new FileReader(inputFile);
        this.writer = new FileWriter(outputFile);
        this.huffmanCodes = codes;
        bitQueue = new LinkedList<Integer>();
    }

    /**
     * Reads the input file character by character, and adds the corresponding 
     * Huffman codes to a queue
     * 
     * If there are more than 8 bits in the queue, one byte is written to a file.
     */
    public void pack() {
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
            if (bitQueue.size() < 8) {
                trashbits = 8 - bitQueue.size();
            }
            writer.write(makeByte());
        }
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
     * Writes the Huffman codes of used characters in the beginning of the file
     */
    private void writeHuffmanCodes() {
        String characters = "" + calculateAmountOfCharacters();
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
     * Makes a String out of the Huffman codes
     * 
     * @return String with all Huffman codes separated by |
     */
    public String makeCodeString() {
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
     * Calculates the amount of different characters in the file
     * 
     * @return amount
     */
    public int calculateAmountOfCharacters() {
        int amount = 0;
        for (String s : huffmanCodes){
            if (s != null) {
                amount++;
            }
        }
        return amount;
    }
}