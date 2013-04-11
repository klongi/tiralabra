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
public class UnPacker {

    private HuffmanTree tree;
    FileReader reader;
    FileWriter writer;
    private Queue<Boolean> bitQueue;

    /**
     * Creates a new UnPacker
     * 
     * @param inputFile name of the file to be unpacked
     * @param outputFile name the unpacked file
     */
    public UnPacker(String inputFile, String outputFile) {
        reader = new FileReader(inputFile);
        writer = new FileWriter(outputFile);
        tree = new HuffmanTree(new Node(-1,-1));
        bitQueue = new LinkedList<Boolean>();
    }
    
    /**
     * Unpacks a packed file
     */
    public void unpack() {
        makeHuffmanTree();
        while (reader.available() > 0) {
            readByte();
            while (bitQueue.size() >= 256) {
                writer.write(findCode());
            }
        }
        while (bitQueue.size() > 0) {
            writer.write(findCode());
        }
    }
    
    /**
     * Reads one byte and adds the bits in to a queue
     */
    public void readByte() {
        int input = reader.read();
        boolean[] bits = byteToBits(input);
        for (int i = 0; i < bits.length; i++) {
            bitQueue.add(bits[i]);
        }
    }
    
    /**
     * Constructs a Huffman tree
     * 
     * It first reads the Huffman codes written in the beginning of a packed file.
     * Then based on the codes the tree is constructed.
     */
    public void makeHuffmanTree() {
        readHuffmanCodes();
        for (int i = 0; i < tree.getHuffmanCodes().length; i++) {
            if (tree.getHuffmanCodes()[i] != null) {
                addNodeToTree(tree.getHuffmanCodes()[i], i, tree.getRoot());
            }  
        }
    }
    
    /**
     * Adds a new node to the Huffman tree
     * 
     * The Huffman code tells the place in the tree that the node must be added
     * to. Every node added is a leaf node, so whenever needed, additional
     * internal nodes are also created.
     * 
     * @param code the Huffman code
     * @param character the value of the character field in a leaf node
     * @param node root node of the tree
     */
    public void addNodeToTree(String code, int character, Node node) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                if (node.getLeftChild() != null) {
                    node = node.getLeftChild();
                } else {
                    node.setLeftChild(new Node(-1, -1));
                    node = node.getLeftChild();
                }
            } else {
                 if (node.getRightChild() != null) {
                    node = node.getRightChild();
                } else {
                    node.setRightChild(new Node(-1, -1));
                    node = node.getRightChild();
                }
            }
        }
        node.setCharacter(character);
    }
    /**
     * Reads the Huffman codes stored in the beginning of the file and
     * stores to codes in to a table in the class HuffmanTree.
     */
    public void readHuffmanCodes() {
        int amount = readAmountOfHuffmanCodes();
        for (int i = 0; i < amount; i++) {
            String code = "";
            int character = reader.read();
            int input = reader.read();
            while (input != 124) {
                code += (char) input;
                input = reader.read();
            }
            tree.addHuffmanCode(code, character);
        }
    }
    
    /**
     * Checks how many different Huffman codes has been used to pack the file
     * 
     * The first number in the beginning of the file tells how many different Huffman
     * codes has been used to pack the file (=how many different characters the file has).
     * This information is needed, because the Huffman codes are also written in the beginning
     * of the file, and the number must be know to read them.
     * 
     * @return amount of different Huffman codes
     */
    public int readAmountOfHuffmanCodes() {
        int input = 0;
        String amountOfCodes = "";
        input = reader.read();
        while (input != 124) {
            amountOfCodes += (char) input;
            input = reader.read();
        }
        return Integer.parseInt(amountOfCodes);
    }
    /**
     * Changes one byte into bits which are stored into a boolean table
     * 
     * @param input byte to be changed
     * @return boolean table that includes the bits
     */
     public boolean[] byteToBits(int input) {
        boolean[] bits = new boolean[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = ((input & (1 << (7 - i))) != 0);
        }
        return bits;
    }
    
     /**
      * Finds the Huffman code
      * 
      * When we read a file bit by bit, the bits can uniquely be changed to Huffman codes
      * by traversing the tree. If the bit red is "0" we travel to the left, otherwise we travel to
      * the right. When we reach a leaf node, we have found the Huffman code.
      * 
      * @return the Huffman code that was found
      */
    public int findCode() {
        Node node = tree.getRoot();
        while (true) {
          if (node.getLeftChild() == null && node.getRightChild() == null) {
              return node.getCharacter();
          }
          boolean bit = bitQueue.poll();
          if (!bit) {
             node = node.getLeftChild();
          } else {
              node = node.getRightChild();
          }
        }
    }

    /**
     * Getter for the HuffmanTree
     * 
     * @return the root node of the tree
     */
    public HuffmanTree getHuffmanTree() {
        return tree;
    }
    
    /**
     * Getter for the bitQueue
     * 
     * @return the bitQueue
     */
    public Queue getBitQueue() {
        return bitQueue;
    }
}
