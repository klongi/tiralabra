
package tiralabra;

import java.io.File;
import java.io.FileInputStream;


/**
 * The class calculates the frequencies of all characters in a given text file
 * 
 * @author Krista
 */
public class FrequencyCalculator {
    private int[] freqTable;
    
    /**
     * The constructor creates a table with size 256.
     * 
     * This is because there is 256 different ascii codes.
     */
    public FrequencyCalculator() {
        freqTable = new int[256];
    }
    
   /**
    * Calculates the frequencies of each 256 characters in a text file
    * 
    * @param filename 
    */
    public void countFrequencies(String filename) {
        FileReader reader = new FileReader(filename);
        while (reader.available() > 0) {
            freqTable[reader.read()]++;
        }
        reader.close();
    }
    
    /**
     * Getter for the frequencies
     * 
     * @return table that includes all the frequencies for all characters in the file
     */
    public int[] getFrequencyTable() {
        return freqTable;
    }
}
