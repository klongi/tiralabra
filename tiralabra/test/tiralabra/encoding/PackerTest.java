/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.encoding;

import tiralabra.encoding.Packer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
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
    Scanner reader;
    
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
        packer = new Packer("cormen_packed.txt", "cormen.txt");
        packer.pack();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void hello() {}
    
//    @Test
//    public void rightAmountOfDifferentCharacters() throws FileNotFoundException {
//        File output = new File("cormen_packed.txt");
//        reader = new Scanner(output);
//        String line = reader.nextLine();
//        String[] parts = line.split("|");
//        assertEquals(6, parts[0]);
//    }
//    
//    @Test
//    public void rightAmountOfCharacters() throws FileNotFoundException {
//        File output = new File("cormen_packed.txt");
//        reader = new Scanner(output);
//        String line = reader.nextLine();
//        String[] parts = line.split("|");
//        assertEquals(100, parts[1]);
//    }
    
//    @Test
//    public void codeStringCorrect() {
//        assertEquals("a0|b101|c100|d111|e1101|f1100|", packer.makeCodeString());
//    }
    
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
