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
public class NodeTest {
    
    Node test;
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        test = new Node(8, 97);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void nodeCreatedCorrectly() {
        assertEquals(8, test.getFrequency());
    }
    
    @Test
    public void rightChildSetCorrectly() {
        Node right = new Node(9, 98);
        test.setRightChild(right);
        assertEquals(9, test.getRightChild().getFrequency());
    }
    
    @Test
    public void leftChildSetCorrectly() {
        Node left = new Node(10, 99);
        test.setLeftChild(left);
        assertEquals(10, test.getLeftChild().getFrequency());
    }
    
    @Test
    public void nodesComparedCorrectly() {
        Node node1 = new Node(10, 99);
        Node node2 = new Node(5, 100);
        assertTrue(node1.compareTo(node2) > 0);
    }
}
