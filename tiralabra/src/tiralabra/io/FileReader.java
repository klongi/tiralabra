/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Uses FileInputStream to read a file
 * 
 * @author Krista
 */
public class FileReader {
    FileInputStream fis;
    
    /**
     * Creates a new FileReader
     * 
     * @param filename Name of the file to be red
     */
    public FileReader(String filename) {
        File file = new File(filename);
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy");
        }
    }
    
    /**
     * Creates a new FileReader
     * 
     * @param file File to be red
     */
    public FileReader(File file) {
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy");
        }
    }
    
    /**
     * Reads one byte from a file
     * 
     * @return int byte
     */
    public int read() {
        try {
            return fis.read();
        } catch (IOException ex) {
            System.out.println("IOException");;
        }
        return -1;
    }
    
    /**
     * Returns an estimate of the number of remaining bytes that can be read
     * 
     * @return number of bytes
     */
    public int available() {
        try {
            return fis.available();
        } catch (IOException ex) {
            System.out.println("IOException");;
        }
        return -1;
    }
    
    /**
     * Closes the inputStream
     */
    public void close(){
        try {
            fis.close();
        } catch (IOException ex) {
            System.out.println("IOException");;
        }
    }
}
