/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.io.File;
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
        
        String output = fileName.substring(0, fileName.length()-4);

        //Pakkaus
        long startTime = System.currentTimeMillis();
        System.out.println("aloitettu pakkaus "+ output);
        Packer packer = new Packer(output+"_packed.txt", fileName);
        packer.pack();
        long endTime = System.currentTimeMillis();
        System.out.println("pakkaamiseen kulunut aika millisekunneissa " + (endTime - startTime));
        
        //Purkaminen
        startTime = System.currentTimeMillis();
        UnPacker unpacker = new UnPacker(output+"_packed.txt", output+"_unpacked.txt");
        unpacker.unpack();
        endTime = System.currentTimeMillis();
        System.out.println("Purkamiseen kulunut aika millusekunneissa: " + (endTime - startTime));
       
    }

}
