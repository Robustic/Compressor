package project;

import algorithms.Huffman;
import datastructures.ByteList;
import fileio.ReadFile;
import fileio.WriteFile;

public class Compressor {    

    public static void main(String[] args) {
        System.out.println("Huffmann 0.01");
        
        Huffman huffman = new Huffman();
        
        huffman.code("huffman.txt");
        
//        ByteList byteList = new ByteList();
//        ReadFile readFile = new ReadFile();
//        WriteFile writeFile = new WriteFile();
//        
//        try {
//            readFile.readFile("test.txt", byteList);
//        } catch (Exception e) {
//            System.out.println("Error when reading file.");
//        }
//        byte[] bytes = byteList.getBytesAsArray();
//        System.out.println("Text: " + (char)bytes[0] + (char)bytes[1] + (char)bytes[2] + (char)bytes[3] + (char)bytes[4] + (char)bytes[5] + (char)bytes[6]);
//        for (int i = 0; i < bytes.length; i++) {
//            System.out.println((char)bytes[i] + "-" + bytes[i]);
//        }
//        
//        try {
//            writeFile.writeFile("testOutput.txt", byteList);
//        } catch (Exception e) {
//            System.out.println("Error when writing file.");
//        }
    }
}
