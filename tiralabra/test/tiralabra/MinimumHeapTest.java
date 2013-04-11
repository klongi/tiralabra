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
public class MinimumHeapTest {
    MinimumHeap minheap;
    
    public MinimumHeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        minheap = new MinimumHeap();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void heapIsEmptyAfterBeingCreated() {
         assertTrue(minheap.isEmpty()); 
     }
     
     @Test
     public void heapNotEmptyWhenNodeAdded() {
         Node node = new Node(120, 120);
         minheap.insert(node);
         assertFalse(minheap.isEmpty());
     }
     
     @Test
     public void heapIsEmptyAfterDeletingAll() {
         minheap.insert(new Node(120, 120));
         minheap.insert(new Node(110, 110));
         minheap.deleteMinimum();
         minheap.deleteMinimum();
         assertTrue(minheap.isEmpty()); 
     }
     
     @Test
     public void deletingReturnsNodewithSmallestFrequency() {
         minheap.insert(new Node(120, 120));
         minheap.insert(new Node(110, 110));
         minheap.insert(new Node(130, 100));
         assertEquals(110, minheap.deleteMinimum().getFrequency());
         assertEquals(120, minheap.deleteMinimum().getFrequency());
     }
     
     @Test
     public void deletingReturnsSmallestWhenAlsoEqualNodesAdded() {
         minheap.insert(new Node(120, 120));
         minheap.insert(new Node(110, 110));
         minheap.insert(new Node(130, 100));
         minheap.insert(new Node(120, 120));
         minheap.insert(new Node(110, 110));
         minheap.insert(new Node(130, 100));
         assertEquals(110, minheap.deleteMinimum().getFrequency());
         assertEquals(110, minheap.deleteMinimum().getFrequency());
     }
}
