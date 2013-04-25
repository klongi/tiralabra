/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import tiralabra.decoding.UnPacker;
import tiralabra.encoding.Packer;
import java.io.File;
import java.util.Scanner;
import tiralabra.encoding.Packer_withoutCalculating;

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
        String frecfile = "frequencies_english.txt";

        System.out.println("Anna pakattavan tiedoston nimi/tiedostopolku:");
        String fileName = scanner.nextLine();
        File fileHandle = new File(fileName);
        while (!fileHandle.exists()) {
            System.out.println("Tieodstoa ei löydy");
            System.out.println("Anna pakattavan tiedoston nimi/tiedostopolku:");
            fileName = scanner.nextLine();
            fileHandle = new File(fileName);
        }

        String output = fileName;
        String method = "";
        while (!method.equals("lukemalla") && !method.equals("laskemalla")) {
            System.out.println("Suoritetaanko pakkaaminen&purkaminen lukemalla vai laskemalla frekvenssit?");
            method = scanner.nextLine();
        }

//        if (method.equals("lukemalla")) {
//            System.out.println("Anna frekvenssitiedoston nimi");
//            frecfile = scanner.nextLine();
//        }

        //Pakkaus
        long startTime = System.currentTimeMillis();
        System.out.println("aloitettu pakkaus " + output);
        Packer packer = new Packer(output + ".packed", fileName);
        if (method.equals("lukemalla")) {
            packer.readFrequencies(frecfile);
        } else {
            packer.countFrequencies();
        }
        packer.pack();
        long endTime = System.currentTimeMillis();
        System.out.println("pakkaamiseen kulunut aika millisekunneissa " + (endTime - startTime));

        //Purkaminen
        startTime = System.currentTimeMillis();
        UnPacker unpacker = new UnPacker(output + ".packed", output + ".unpacked");
        unpacker.unpack();
        endTime = System.currentTimeMillis();
        System.out.println("Purkamiseen kulunut aika millusekunneissa: " + (endTime - startTime));
    }
}
