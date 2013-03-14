/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Krista
 */
public class Tiralabra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Anna pakattavan tieodston nimi");
//        String fileName = scanner.nextLine();
        countFrequencies("testitiedosto.txt");

    }

    public static void countFrequencies(String filename) throws FileNotFoundException, IOException {

        File file = new File(filename);
        int[] freqTable = new int[256];
        FileInputStream fis = new FileInputStream(file);

        int read;
        while (fis.available() > 0) {
            read = fis.read();
            freqTable[read] += 1;
        }
        fis.close();
        
        for (int i = 0; i < freqTable.length; i++) {
            System.out.println(freqTable[i]);           
        }
    }
}
