/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Krista
 */
public class UnPackerTest {
    UnPacker unpacker;
    
    public UnPackerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        unpacker = new UnPacker("cormen_packed.txt", "cormen_unpacked.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void nodeAddedCorrectly() {
        Node root = new Node(-1,-1);
        unpacker.addNodeToTree("10", 97, root);
        assertTrue(root.getRightChild().getLeftChild() != null);
        assertEquals(97, root.getRightChild().getLeftChild().getCharacter());
    }
    
    @Test
    public void rightAmountOfHuffmanCodes() {
        assertEquals(6, unpacker.readAmount());
    }
    
    @Test
    public void rightAmountOfCharacters() {
        unpacker.readAmount();
        assertEquals(100, unpacker.readAmount());
    }
    
    @Test
    public void rightHuffmanCodeFora() {
        unpacker.readHuffmanCodes();
        assertEquals("0", unpacker.getHuffmanTree().getHuffmanCodes()[97]);
    }
    @Test
    public void rightHuffmanCodeForb() {
        unpacker.readHuffmanCodes();
        assertEquals("101", unpacker.getHuffmanTree().getHuffmanCodes()[98]);
    }
    @Test
    public void aHasRightPlaceInTree() {
        unpacker.makeHuffmanTree();
        Node root = unpacker.getHuffmanTree().getRoot();
        assertEquals(97, root.getLeftChild().getCharacter()); 
    }
   
    @Test
    public void byteChangedToBitsCorrectly() {
        boolean[] rightBits = {false,true,true,false,false,false,false,true};
        boolean[] bits = unpacker.byteToBits(97);
        for (int i = 0; i < 8; i++) {
            assertEquals(rightBits[i], bits[i]);
        }
    }
    @Test
    public void findsTheFirstCode() {
        unpacker.makeHuffmanTree();
        unpacker.readByte();
        unpacker.readByte();
        assertEquals(102, unpacker.findCode());
    }
    @Test
    public void huffmanTreeMadeCorrectly() {
        unpacker.makeHuffmanTree();
        assertEquals(97, unpacker.getHuffmanTree().getRoot().getLeftChild().getCharacter());
    }
    
    @Test
    public void bitsAddedToQueue() {
        unpacker.readByte();;
        assertEquals(8, unpacker.getBitQueue().size());
    }
    
}
