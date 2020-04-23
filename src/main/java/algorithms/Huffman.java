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
     * 
     * @param messagePrinter    MessagePrinter
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
    
    private void formWriteByteList(ByteList readByteList, ByteList writeByteList, ByteDataArray byteDataArray) throws Exception {
        byteDataArray.count(readByteList.getBytesAsArray());
        if (byteDataArray.countDifferentCharacters() == 1) {
            long bytes = readByteList.size();
            for (int k = 7; k >= 0; k--) {
                long toByte = bytes;
                toByte >>= 8 * k;
                writeByteList.add((byte) (toByte & 0xFF));                        
            }
            writeByteList.add((byte) 0);
            writeByteList.add(readByteList.get(0));
        } else {
            byteDataArray.createLinkedList();        
            byteDataArray.createBinaryTreeFromLinkedList();        
            byteDataArray.compress(readByteList, writeByteList);
        }
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
        ByteList writeByteList = new ByteList();
        if (readByteList.size() < 2) {
            writeByteList = readByteList;
        } else {
            ByteDataArray byteDataArray = new ByteDataArray();
            formWriteByteList(readByteList, writeByteList, byteDataArray);
        }
        long endTime = System.nanoTime();
        this.printer.println("Compressing ended.");
        long duration = (endTime - startTime) / 1000000;
        this.printer.println("Compression took " + duration + " ms elapsed time.");
        double compressionRate = (double) writeByteList.size() / readByteList.size() * 100;
        this.printer.println("Compressing rate " + String.format("%.2f", compressionRate) + " %.");
        
        this.printer.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        this.printer.println("Output file writing ended.");
    }
    
    private void formWriteByteList(ByteList readByteList, ByteList writeByteList) throws Exception {
        if (readByteList.get(8) == 0) {
            long bytes = 0; 
            for (int i = 0; i < 8; i++) {
                bytes <<= 8;
                bytes |= (readByteList.get(i) & 0xFF);
            }
            byte character = readByteList.get(9);
            for (long i = 0; i < bytes; i++) {
                writeByteList.add(character);
            }
        } else {
            ByteDataArray byteDataArray = new ByteDataArray();
            byteDataArray.readHeader(readByteList);
            byteDataArray.createBinaryTreeFromBinaryCodedCodes();
            byteDataArray.uncompress(readByteList, writeByteList);
        }
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
        ByteList writeByteList = new ByteList();
        if (readByteList.size() < 2) {
            writeByteList = readByteList;
        } else {
            formWriteByteList(readByteList, writeByteList);
        }
        long endTime = System.nanoTime();
        this.printer.println("Uncompressing ended.");
        long duration = (endTime - startTime) / 1000000;
        this.printer.println("Uncompressing took " + duration + " ms elapsed time.");
        
        this.printer.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        this.printer.println("Output file writing ended.");
    }
}
