package algorithms;

import datastructures.ByteList;
import datastructures.ByteDataArray;
import fileio.ReadFile;
import fileio.WriteFile;
import userio.MessagePrinter;

/**
 * Class which represent interface for the Huffman compressing algorithm.
 */
public class Huffman {
    private MessagePrinter printer;

    /**
     * Constructor.
     */
    public Huffman(MessagePrinter messagePrinter) {
        this.printer = messagePrinter;
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
        this.printer.println("Reading input file...");        
        ByteList readByteList = new ByteList();        
        readFile(readFileName, readByteList);
        this.printer.println("Input file reading ended.");
        
        this.printer.println("Compressing...");
        long startTime = System.nanoTime();
        ByteDataArray byteDataArray = new ByteDataArray();
        byteDataArray.count(readByteList.getBytesAsArray());
        byteDataArray.createLinkedList();        
        byteDataArray.createBinaryTreeFromLinkedList();        
        
        ByteList writeByteList = new ByteList();
        byteDataArray.compress(readByteList, writeByteList);
        long endTime = System.nanoTime();
        this.printer.println("Compressing ended.");
        long duration = (endTime - startTime) / 1000000;
        this.printer.println("Compression took " + duration + " ms.");
        double compressionRate = (double) writeByteList.size() / readByteList.size() * 100;
        this.printer.println("Compressing rate " + String.format("%.2f", compressionRate) + " %.");
        
        this.printer.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        this.printer.println("Output file writing ended.");
    }
    
    /**
     * Uncompress input file to the output file.
     *
     * @param readFileName      Input file name
     * @param writeFileName     Output file name
     * @throws Exception        Exception
     */
    public void uncompress(String readFileName, String writeFileName) throws Exception {
        this.printer.println("Reading input file...");   
        ByteList readByteList = new ByteList();        
        readFile(readFileName, readByteList);
        this.printer.println("Input file reading ended.");
        
        this.printer.println("Uncompressing...");
        long startTime = System.nanoTime();
        ByteDataArray byteDataArray = new ByteDataArray();
        byteDataArray.readHeader(readByteList);
        byteDataArray.createBinaryTreeFromBinaryCodedCodes();
        
        ByteList writeByteList = new ByteList();
        byteDataArray.uncompress(readByteList, writeByteList);
        long endTime = System.nanoTime();
        this.printer.println("Uncompressing ended.");
        long duration = (endTime - startTime) / 1000000;
        this.printer.println("Uncompressing took " + duration + " ms.");
        
        this.printer.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        this.printer.println("Output file writing ended.");
    }
}
