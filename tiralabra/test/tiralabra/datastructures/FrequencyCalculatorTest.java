/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datastructures;

import tiralabra.datastructures.FrequencyCalculator;
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
public class FrequencyCalculatorTest {
    
    FrequencyCalculator freqCalc;
    
    public FrequencyCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        freqCalc = new FrequencyCalculator();
    }
    
    @After
    public void tearDown() {
    }
   
    @Test
    public void rightAMountOfa() {
        freqCalc.countFrequencies("cormen.txt");
        assertEquals(45, freqCalc.getFrequencyTable()[97]);
    }
    
    @Test
    public void frequency0IfCharacterNotInFile() {
        freqCalc.countFrequencies("cormen.txt");
        assertEquals(0, freqCalc.getFrequencyTable()[105]);
    }
}
