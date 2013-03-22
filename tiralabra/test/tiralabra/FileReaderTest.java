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
public class FileReaderTest {
    FileReader reader;
    
    public FileReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        reader = new FileReader("cormen.txt");
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void readsCorrecCharacter() {
        assertEquals(102, reader.read());
    }
    @Test
    public void rightAmounAvailable() {
        assertEquals(100, reader.available());
    }
}
