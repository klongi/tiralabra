/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

/**
 * Node is a node in the Huffman tree
 * 
 * @author Krista
 */
public class Node implements Comparable<Node> {
    /**
     * frequency is the frequency of the character in the file if node is a leaf node.
     * Otherwise frequency is the sum of the frequencies of its children.
     */
    private int frequency;
    /**
     * character has the code of the character that is saved into the node.
     * If Node is not a leaf node, this field is null
     */
    private int character;
    private Node leftChild;
    private Node rightChild;
    
    /**
     * The constructor is given as a parameter the frequency of a character in a file and the code of the character. The character can be
     * null if node is not a leaf node, and then the frequency is the sum of its children.
     * @param frequency 
     * @param character 
     */
    public Node(int frequency, int character) {
        this.frequency = frequency;
        this.character = character;
        this.leftChild = null;
        this.rightChild = null;
    }
    
    /**.
     * @return The frequency of the Node.
     */
    public int getFrequency() {
        return this.frequency;
    }
    
    /**
     * Sets a left child for the Node.
     * @param leftChild The Node that is set as the left Child
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    
    /**
     * Sets a right child for the Node.
     * @param rightChild The Node that is set as the right child.
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    
    /**
     * Getter for the right child
     * @return the right child of the node
     */
    public Node getRightChild() {
        return this.rightChild;
    }
    
    /**
     * Getter for the left child
     * @return the left child of the node
     */
    public Node getLeftChild() {
        return this.leftChild;
    }

    /**
     * 
     * @param o other Node
     * @return 
     */
    @Override
    public int compareTo(Node o) {
        return this.frequency - o.frequency;
    }

    /**
     * Setter for the character field of the Node
     * 
     * @param character 
     */
    public void setCharacter(int character) {
        this.character = character;
    }
    /**
     * Getter for the character
     * @return character
     */
    public int getCharacter() {
        return this.character;
    }
    
}
