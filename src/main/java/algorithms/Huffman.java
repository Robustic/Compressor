package algorithms;

import datastructures.ByteList;
import datastructures.ByteDataArray;
import fileio.ReadFile;
import fileio.WriteFile;

/**
 * Class which represent interface for the Huffman compressing algorithm.
 */
public class Huffman {

    /**
     * Constructor.
     */
    public Huffman() {
    }
    
    private void readFile(String fileName, ByteList byteList) throws Exception {
        ReadFile readFile = new ReadFile();
        readFile.readFile(fileName, byteList);
    }
    
    private void writeFile(String fileName, ByteList byteList) throws Exception {
        WriteFile writeFile = new WriteFile();
        writeFile.writeFile(fileName, byteList);
    }
    
    /**
     * Compress input file to the output file.
     *
     * @param readFileName      Input file name
     * @param writeFileName     Output file name
     * @throws Exception        Exception
     */
    public void compress(String readFileName, String writeFileName) throws Exception {
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
        byteDataArray.compress(readByteList, writeByteList);
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
    
    /**
     * Uncompress input file to the output file.
     *
     * @param readFileName      Input file name
     * @param writeFileName     Output file name
     * @throws Exception        Exception
     */
    public void uncompress(String readFileName, String writeFileName) throws Exception {
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
        byteDataArray.uncompress(readByteList, writeByteList);
        long endTime = System.nanoTime();
        System.out.println("Uncompressing ended.");
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Uncompressing took " + duration + " ms.");
        
        System.out.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        System.out.println("Output file writing ended.");
    }
}
