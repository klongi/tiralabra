/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Krista
 */
public class Tiralabra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Anna pakattavan tiedoston nimi/tiedostopolku:");
        String fileName = scanner.nextLine();
        File fileHandle = new File(fileName);
        while (!fileHandle.exists()) {
            System.out.println("Tieodstoa ei löydy");
            System.out.println("Anna pakattavan tiedoston nimi/tiedostopolku:");
            fileName = scanner.nextLine();
            fileHandle = new File(fileName);
        }

        long startTime = System.currentTimeMillis();
        
        //Frekvenssien laskeminen
        FrequencyCalculator freqCalc = new FrequencyCalculator();
        freqCalc.countFrequencies(fileName);

        // Prioriteettijono
        PriorityQueue<Node> pq = new PriorityQueue();
        int[] freqTable = freqCalc.getFrequencyTable();
        for (int i = 0; i < freqTable.length; i++) {
            if (freqTable[i] != 0) {
                pq.add(new Node(freqTable[i], i));
            }
        }
        //Huffmanpuu
        HuffmanTree tree = new HuffmanTree(pq);
        
        //Varsinainen pakkaus koodien avulla
        Packer packer = new Packer("packed.txt", fileName, tree.getHuffmanCodes());
        packer.pack();
        
        long endTime = System.currentTimeMillis();
        System.out.println("pakkaamiseen kulunut aika millisekunneissa " + (endTime - startTime));
        
        //Purkaminen
        startTime = System.currentTimeMillis();
        UnPacker unpacker = new UnPacker("packed.txt", "unpacked.txt");
        unpacker.unpack();
        endTime = System.currentTimeMillis();
        System.out.println("Purkamiseen kulunut aika millusekunneissa: " + (endTime - startTime));
       
    }

}
