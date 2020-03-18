package algorithms;

import datastructures.ByteList;
import datastructures.ByteDataArray;
import fileio.ReadFile;
import fileio.WriteFile;

public class Huffman {
    
    public void readFile(String fileName, ByteList byteList) {
        ReadFile readFile = new ReadFile();        
        try {
            readFile.readFile(fileName, byteList);
        } catch (Exception e) {
            System.out.println("Error when reading file.");
            System.out.println(e);
        }
    }
    
    public void writeFile(String fileName, ByteList byteList) {
        WriteFile writeFile = new WriteFile();        
        try {
            writeFile.writeFile(fileName, byteList);
        } catch (Exception e) {
            System.out.println("Error when writing file.");
            System.out.println(e);
        }
    }
    
    public void code(String readFileName, String writeFileName) {
        System.out.println("Reading input file...");        
        ByteList readByteList = new ByteList();        
        readFile(readFileName, readByteList);
        System.out.println("Input file reading ended.");
        
        System.out.println("Compressing...");
        long startTime = System.nanoTime();
        ByteDataArray byteDataArray = new ByteDataArray();
        byteDataArray.count(readByteList.getBytesAsArray());
        byteDataArray.createLinkedList();        
        byteDataArray.createBinaryTreeFromLinkedList();        
        
        ByteList writeByteList = new ByteList();
        byteDataArray.code(readByteList, writeByteList);
        long endTime = System.nanoTime();
        System.out.println("Compressing ended.");
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Compression took " + duration + " ms.");
        double compressionRate = (double) writeByteList.size() / readByteList.size() * 100;
        System.out.println("Compressing rate " + String.format("%.2f", compressionRate) + " %.");
        
        System.out.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        System.out.println("Output file writing ended.");
    }
    
    public void uncode(String readFileName, String writeFileName) {
        System.out.println("Reading input file...");   
        ByteList readByteList = new ByteList();        
        readFile(readFileName, readByteList);
        System.out.println("Input file reading ended.");
        
        System.out.println("Uncompressing...");
        long startTime = System.nanoTime();
        ByteDataArray byteDataArray = new ByteDataArray();
        byteDataArray.readHeader(readByteList);
        byteDataArray.createBinaryTreeFromBinaryCodedCodes();
        
        ByteList writeByteList = new ByteList();
        byteDataArray.uncode(readByteList, writeByteList);
        long endTime = System.nanoTime();
        System.out.println("Uncompressing ended.");
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Uncompressing took " + duration + " ms.");
        
        System.out.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        System.out.println("Output file writing ended.");
    }
}
