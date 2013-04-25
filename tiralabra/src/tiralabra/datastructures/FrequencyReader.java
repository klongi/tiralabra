/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datastructures;


import tiralabra.io.FileReader;

/**
 * The class calculates the frequencies of all characters in a given text file
 * 
 * @author Krista
 */
public class FrequencyReader {
    private int[] freqTable;
    
    /**
     * The constructor creates a table with size 256.
     * 
     * This is because there is 256 different ascii codes.
     */
    public FrequencyReader() {
        freqTable = new int[256];
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
     * @return found integer
     */
    private int readAmount(FileReader reader) {
        int input = 0;
        String amountOfCodes = "";
        input = reader.read();
        while (input != 10 && input != -1) { //new line character or end of file character
            amountOfCodes += (char) input;
            input = reader.read();
        }
        return Integer.parseInt(amountOfCodes.trim());
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

