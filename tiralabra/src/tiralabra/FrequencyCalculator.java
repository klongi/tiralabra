/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krista
 */
public class FrequencyCalculator {
    private int[] freqTable;
    
    public FrequencyCalculator() {
        freqTable = new int[256];
    }
    
   /**
    * Calculates the frequencies of each 256 characters in a text file
    * 
    * @param filename 
    */
    public void countFrequencies(String filename) {
        File file = new File(filename);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            int input;
            while (fis.available() > 0) {
                input = fis.read();
                freqTable[input] += 1;
            }
            fis.close();
        } catch (Exception ex) {
            System.out.println("Tieodton lukeminen ei onnistunut");
        }

//        for (int i = 0; i < freqTable.length; i++) {
//            System.out.println(i);
//            System.out.println(freqTable[i]);
//        }
    }
    
    public int[] getFrequencyTable() {
        return freqTable;
    }
}
