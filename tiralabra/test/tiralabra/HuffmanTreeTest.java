/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.util.PriorityQueue;
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
public class HuffmanTreeTest {
    MinimumHeap pq;
    HuffmanTree ht;
    
    public HuffmanTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /* 
     * Here I have used the example from Thomas H. Cormens book
     * Introduction to Algorithms. Therefore I can know what
     * codes and frequencies to expect.
     */
    @Before
    public void setUp() {
        pq = new MinimumHeap();
        pq.insert(new Node(45, 97));
        pq.insert(new Node(13, 98));
        pq.insert(new Node(12, 99));
        pq.insert(new Node(16, 100));
        pq.insert(new Node(5, 102));
        pq.insert(new Node(9, 101));
        
        ht = new HuffmanTree(pq);
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void rootFrequencyCorrect() {
        assertEquals(100, ht.getRoot().getFrequency());
    }
    @Test
    public void aHasCorrectHuffmanCode() {
        assertEquals("0", ht.getHuffmanCodes()[97]);
    }
    
    @Test
    public void eHasCorrectHuffmanCode() {
        assertEquals("1101", ht.getHuffmanCodes()[101]);
    }
    
    @Test
    public void cHasCorrectHuffmanCode() {
        assertEquals("100", ht.getHuffmanCodes()[99]);
    }
    
    
}
