/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datastructures;

import tiralabra.datastructures.Node;
import java.util.PriorityQueue;

/**
 * Constructs the Huffman tree and generates the Huffman codes for each character from the tree
 * 
 * @author Krista
 */
public class HuffmanTree { 
    /**
     * root node of the Huffman tree
     */
    private Node root;
    /**
     * Table that includes the Huffman codes for each character in the file
     */
    private String[] huffmanCodes;
 
    /**
     * The constructor generates the Huffman tree and Huffman codes.
     * 
     * @param pq PriorityQueue
     */
    public HuffmanTree(MinimumHeap pq) {
        huffmanCodes = new String[256];
        makeTree(pq);
        makeCodes(root, "");
    }
    
    /**
     * Creates a HuffmanTree with just a root node and does not use priority queue
     * 
     * @param root 
     */
    public HuffmanTree(Node root) {
        this.root = root;
        huffmanCodes = new String[256];
    }
    
    /**
     * Constructs the Huffman tree.
     * 
     * Huffman tree is constructed by taking the two smallest nodes from the priority queue, and making a
     * new node that has the combined frequency of the two smallest nodes. This new node is the parent of the
     * two smaller nodes. New node is put back to the priority queue. This is repeated until there is only one
     * element left in the queue. This is the root of the tree.
     * 
     * @param pq PriorityQueue
     */
    private void makeTree(MinimumHeap pq) {
        while (pq.getSize() > 1) {
            Node smallest1 = pq.deleteMinimum();
            Node smallest2 = pq.deleteMinimum();
            Node newNode = new Node(smallest1.getFrequency() + smallest2.getFrequency(), -1);
            newNode.setLeftChild(smallest1);
            newNode.setRightChild(smallest2);
            pq.insert(newNode);
        }
        root = pq.deleteMinimum();
    }
    
    /**
     * Constructs the Huffman codes.
     * 
     * The method is given the root node as a parameter, and the tree is traversed recursively.
     * When going left, "0" is added to the code, and when going to the right, "1" is added to the code.
     * When we reach a leaf, which is a node that includes a character, the code is saved to a table called
     * huffmanCodes.
     * 
     * @param node root of the Huffman tree
     * @param code empty String
     */
    private void makeCodes(Node node, String code) {
        if (node != null) {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                //System.out.println("koodi: " + code + " freq: " + node.getFrequency() + " merkki: " + node.getCharacter());
                huffmanCodes[node.getCharacter()] = code;
            }
            makeCodes(node.getLeftChild(), code + "0");
            makeCodes(node.getRightChild(), code + "1");
        }
    }
    
    /**
     * Setter for a huffmanCode of a character
     * 
     * @param code Huffman code of the character
     * @param character ascii code of the character
     */
    public void addHuffmanCode(String code, int character) {
        huffmanCodes[character] = code;
    }
    
    /**
     * Getter for the Huffman codes
     * 
     * @return table that includes all the Huffman codes.
     */
    public String[] getHuffmanCodes() {
        return huffmanCodes;
    }
//    
//    /**
//     * Setter for the root of the HuffmanTree
//     * 
//     * @param node new root
//     */
//    public void setRoot(Node node) {
//        root = node;
//    }
    /**
     * Getter for the root of the Huffman tree
     * 
     * @return root
     */
    public Node getRoot() {
        return root;
    }
}
