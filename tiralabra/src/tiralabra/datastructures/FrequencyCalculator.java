
package tiralabra.datastructures;

import tiralabra.io.FileReader;


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
     * Reads the frequencies from a separate file.
     * 
     * @param filename 
     */
    public void readFrequencies(String filename) {
        FileReader reader = new FileReader(filename);
        while (reader.available() > 0) {
            freqTable[reader.read()] = readAmount(reader);
        }
        reader.close();
        freqTable[10] = 1000000;
    }
    
    /**
     * Reads an integer from the file.
     * 
     * Used to read frequencies of different characters from a file.
     * It is assumed that frequencies are listed in the way that a character and its
     * frequency are separated with a space, and all characters are listed on different lines.
     * 
     * @return found integer
     */
    private int readAmount(FileReader reader) {
        int input = 0;
        String amount = "";
        input = reader.read();
        while (input != 10 && input != -1) { //new line character or end of file character
            amount += (char) input;
            input = reader.read();
        }
        return Integer.parseInt(amount.trim());
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
