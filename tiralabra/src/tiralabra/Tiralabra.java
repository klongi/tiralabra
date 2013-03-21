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
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Anna pakattavan tieodston nimi");
//        String fileName = scanner.nextLine();
        FrequencyCalculator freqCalc = new FrequencyCalculator();
        freqCalc.countFrequencies("cormen.txt");

        PriorityQueue<Node> pq = new PriorityQueue();
        int[] freqTable = freqCalc.getFrequencyTable();
        for (int i = 0; i < freqTable.length; i++) {
            if (freqTable[i] != 0) {
                pq.add(new Node(freqTable[i], i));
            }
        }
        
       while (pq.size() > 1) {
           Node smallest1 = pq.remove();
           Node smallest2 = pq.remove();
           Node newNode = new Node(smallest1.getFrequency()+smallest2.getFrequency(), -1);
           newNode.setLeftChild(smallest1);
           newNode.setRightChild(smallest2);
           pq.add(newNode);
       }

       Node root = pq.remove();
       System.out.println(root.getFrequency());
        System.out.println(root.getLeftChild().getFrequency());
        System.out.println(root.getLeftChild().getCharacter());
      makeCodes(root, "");     

    }

      public static void makeCodes(Node node, String code) {
        if(node != null) {
            makeCodes(node.getLeftChild(), code+"0");
            if (node.getLeftChild() == null && node.getRightChild() == null){
                System.out.println("koodi: " + code + " freq: " + node.getFrequency() + " merkki: " + node.getCharacter());
            }
            makeCodes(node.getRightChild(), code+"1");
        }
    }
    
}
