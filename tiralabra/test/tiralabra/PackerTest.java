/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Krista
 */
public class PackerTest {
    Packer packer;
    PriorityQueue<Node> pq;
    HuffmanTree ht;
    
    public PackerTest() {
    }
    
//    @Rule
//    private File output;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pq = new PriorityQueue();
        pq.add(new Node(45, 97));
        pq.add(new Node(13, 98));
        pq.add(new Node(12, 99));
        pq.add(new Node(16, 100));
        pq.add(new Node(5, 102));
        pq.add(new Node(9, 101));
        
        ht = new HuffmanTree(pq);
        packer = new Packer("output", "cormen.txt", ht.getHuffmanCodes());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void rightAmountOfDifferentCharacters() {
        assertEquals(6, packer.calculateAmountOfCharacters());
    }
    
    @Test
    public void codeStringCorrect() {
        assertEquals("a0|b101|c100|d111|e1101|f1100|", packer.makeCodeString());
    }
    
//    @Test
//    public void codesWrittenCorrectly() {
//        packer.pack();
//        FileReader reader = new FileReader(output);
//         System.out.println(reader.read());
//         
//    }
//    @Test
//    public void firstByteWrittenCorrectly() {
//        packer.pack();
//    }
}
