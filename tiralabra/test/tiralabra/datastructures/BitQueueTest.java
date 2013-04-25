/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datastructures;

import tiralabra.datastructures.BitQueue;
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
public class BitQueueTest {
    BitQueue bitQueue;
    
    public BitQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bitQueue = new BitQueue();
    }
    
    @After
    public void tearDown() {
    }
  
    @Test
    public void queueEmptyWhenCreated() {
        assertTrue(bitQueue.isEmpty());
        assertTrue(bitQueue.getSize() == 0);
    }
    
    @Test
    public void queueSizeCorrectWhenOneAdded() {
        bitQueue.add(true);
        assertEquals(1, bitQueue.getSize());
    }
    
    @Test
    public void queueReturnCorrectValueWhenOneAdded() {
        bitQueue.add(true);
        assertTrue(bitQueue.remove());
    }
    
    @Test
    public void queueSizeCorrectWhenMoreThanOneAdded() {
        bitQueue.add(true);
        bitQueue.add(false);
        bitQueue.add(false);
        assertEquals(3, bitQueue.getSize());
    }
     
    @Test
    public void queueReturnCorrectValueWhenMoreThanOneAdded() {
        bitQueue.add(true);
        bitQueue.add(false);
        bitQueue.add(false);
        assertTrue(bitQueue.remove());
    }
    
      @Test
    public void queueSizeCorrectWhenMoreThanOneAddedandRemoved() {
        bitQueue.add(true);
        bitQueue.add(false);
        bitQueue.add(false);
        bitQueue.remove();
        bitQueue.remove();
        assertEquals(1, bitQueue.getSize());
    }
      
    @Test
    public void queueReturnCorrecLastValueWhenMoreThanOneAdded() {
        bitQueue.add(true);
        bitQueue.add(true);
        bitQueue.add(false);
        assertTrue(!bitQueue.removeLast());
    }
}
