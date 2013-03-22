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
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Anna pakattavan tieodston nimi");
//        String fileName = scanner.nextLine();
        FrequencyCalculator freqCalc = new FrequencyCalculator();
        freqCalc.countFrequencies("biggesttest.txt");

        PriorityQueue<Node> pq = new PriorityQueue();
        int[] freqTable = freqCalc.getFrequencyTable();
        for (int i = 0; i < freqTable.length; i++) {
            if (freqTable[i] != 0) {
                pq.add(new Node(freqTable[i], i));
            }
        }
        
        HuffmanTree tree = new HuffmanTree(pq);
        Packer packer = new Packer("biggesttest_packed.txt", "biggesttest.txt", tree.getHuffmanCodes());
        packer.pack();
        UnPacker unpacker = new UnPacker("biggesttest_packed.txt", "biggesttest_unpacked.txt");
        unpacker.unpack();

      

    }

}
