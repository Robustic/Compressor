package ui;

import algorithms.Huffman;
import fileio.ReadFile;

public class UserInterface {
    public void run(String[] args) {
        try {
            System.out.println("* Compressor 0.01 *");
            System.out.println("* MIT License     *");
            if (args.length == 3) {
                String readFileName = args[2];
                ReadFile readfile = new ReadFile();
                if (readfile.checkIfFileExists(readFileName)) {
                    if (args[0] == "huff") {
                        Huffman huffman = new Huffman();                    
                        if (args[1] == "comp") {
                            System.out.println("Compressing with Huffman.");
                            System.out.println("Input file name: '" + readFileName + "'");
                            huffman.compress(readFileName, readFileName + ".huffman");
                            System.out.println("Compressed file name: '" + readFileName + ".huffman'");

                        } else if (args[1] == "uncomp") {
                            if (readFileName.length() > 8 && readFileName.endsWith(".huffman")) {
                                System.out.println("Uncompressing with Huffman.");
                                System.out.println("Input file name: '" + readFileName + "'");
                                String writeFileName = readFileName.substring(0, readFileName.length() - 8);
                                huffman.uncompress(readFileName, writeFileName);
                                System.out.println("Uncompressed file name: '" + writeFileName + "'");
                            } else {
                                infoToHelp();
                            }
                        } else {
                            infoToHelp();
                        }
                    } else {
                        System.out.println(args[0] + " is not accepted packing algorithm.");
                        infoToHelp();
                    }
                } else {
                    System.out.println("File name " + readFileName + " does not exists.");
                }
            } else if (args.length == 1 && args[0] == "help") {
                help();
            } else {
                infoToHelp();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void infoToHelp() {
        System.out.println("Check help with command 'compressor help'");
    }
    
    public void help() {
        System.out.println("");
        System.out.println("Huffman");
        System.out.println("=======");
        System.out.println("To compress file with Huffman algorithm, run command 'compressor huff comp <filename>'.");
        System.out.println("To compress file with Huffman algorithm, run command 'compressor huff uncomp <filename.huffman>'.");
        System.out.println("");
        System.out.println("Help");
        System.out.println("====");
        System.out.println("Check help with command 'compressor help'.");        
    }
}
