/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Uses FileOutputStream to write on a file
 *
 * @author Krista
 */
public class FileWriter {

    FileOutputStream fos;

    /**
     * Creates a new FileWriter
     *
     * @param filename Name of the output file
     */
    public FileWriter(String filename) {
        try {
            fos = new FileOutputStream(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy :(");
        }
    }

    /**
     * Writes one byte to a file
     *
     * @param b byte to be written
     */
    public void write(int b) {
        try {
            fos.write(b);
        } catch (IOException ex) {
            System.out.println("Kirjoittaminen ei onnistunut");
        }
    }

    /**
     * closes the outputStream
     */
    public void close() {
        try {
            fos.close();
        } catch (IOException ex) {
            System.out.println("Sulkeminen ei onnistunut");;
        }
    }
}
