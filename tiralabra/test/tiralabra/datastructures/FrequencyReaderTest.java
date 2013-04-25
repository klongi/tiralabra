/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datastructures;

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
public class FrequencyReaderTest {
     FrequencyReader freqRead;
    
    public FrequencyReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        freqRead = new FrequencyReader();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void rightAMountOfaWhenReading() {
        freqRead.readFrequencies("frequencies_english.txt");
        assertEquals(5263779, freqRead.getFrequencyTable()[97]);
    }
    
    @Test
    public void rightAMountOfHuutomerkkiWhenReading() {
        freqRead.readFrequencies("frequencies_english.txt");
        assertEquals(2178, freqRead.getFrequencyTable()[33]);
    }
}
