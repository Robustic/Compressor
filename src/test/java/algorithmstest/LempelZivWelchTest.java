package algorithmstest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import static org.junit.Assert.assertTrue;

import algorithms.LempelZivWelch;
import datastructures.ByteList;
import fileio.ReadFile;
import userio.MessagePrinter;
import userio.ProductionPrinter;

public class LempelZivWelchTest {
    private MessagePrinter printer;
    LempelZivWelch lempelZivWelch;
    ReadFile readFile = new ReadFile();
    
    public static byte[] stringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = (byte) s.charAt(i);
        }
        return data;
    }
    
    private boolean filesAreSame(String file1, String file2) {
        ByteList list1 = new ByteList();
        ByteList list2 = new ByteList();
        try {
            readFile.readFile(file1, list1);
            readFile.readFile(file2, list2);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        try {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }     
        return true;
    }
    
    @Before
    public void initialize() {
        this.printer = new ProductionPrinter();
        this.lempelZivWelch = new LempelZivWelch(this.printer);
    }
    
    @Test
    public void encodingAlgorithmWorksForSimpleCase() {
        try {
            byte[] newBytes = stringToByteArray("ABABABACB");
            ByteList byteList = new ByteList();
            byteList.combine(newBytes);
            ByteList coded = lempelZivWelch.encode(byteList);
            assertEquals(12, coded.size());
            assertEquals(0, (int) coded.get(0));
            assertEquals(65, (int) coded.get(1));
            assertEquals(0, (int) coded.get(2));
            assertEquals(66, (int) coded.get(3));
            assertEquals(1, (int) coded.get(4));
            assertEquals(0, (int) coded.get(5));
            assertEquals(1, (int) coded.get(6));
            assertEquals(2, (int) coded.get(7));
            assertEquals(0, (int) coded.get(8));
            assertEquals(67, (int) coded.get(9));
            assertEquals(0, (int) coded.get(10));
            assertEquals(66, (int) coded.get(11));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void decodingAlgorithmWorksForSimpleCase() {
        try {
            ByteList codedList = new ByteList();
            codedList.add((byte) (0 & 0xFF));
            codedList.add((byte) (65 & 0xFF));
            codedList.add((byte) (0 & 0xFF));
            codedList.add((byte) (66 & 0xFF));
            codedList.add((byte) (1 & 0xFF));
            codedList.add((byte) (0 & 0xFF));
            codedList.add((byte) (1 & 0xFF));
            codedList.add((byte) (2 & 0xFF));
            codedList.add((byte) (0 & 0xFF));
            codedList.add((byte) (67 & 0xFF));
            codedList.add((byte) (0 & 0xFF));
            codedList.add((byte) (66 & 0xFF));
            ByteList decoded = lempelZivWelch.decode(codedList);
//            System.out.println("decoded.size(): " + decoded.size());
            assertEquals(9, decoded.size());
//            System.out.println("");
//            for (int i = 0; i < decoded.size(); i++) {
//                System.out.print((char) decoded.get(i));
//            }
//            System.out.println("\n");
            assertEquals(65, (int) decoded.get(0));
            assertEquals(66, (int) decoded.get(1));
            assertEquals(65, (int) decoded.get(2));
            assertEquals(66, (int) decoded.get(3));
            assertEquals(65, (int) decoded.get(4));
            assertEquals(66, (int) decoded.get(5));
            assertEquals(65, (int) decoded.get(6));
            assertEquals(67, (int) decoded.get(7));
            assertEquals(66, (int) decoded.get(8));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void codingAndUncodingWorksForLargeFile() {
        try {
            System.out.println("*** File: pg10.txt ***");
            this.lempelZivWelch.compress("pg10.txt", "pg10.txt.test.lempelZivWelch");
            this.lempelZivWelch = new LempelZivWelch(this.printer);
            this.lempelZivWelch.uncompress("pg10.txt.test.lempelZivWelch", "pg10.txt.test");
            assertTrue(filesAreSame("pg10.txt", "pg10.txt.test"));
            if (readFile.checkIfFileExists("pg10.txt.test")) {
                File fileToDelete = new File("pg10.txt.test");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("pg10.txt.test.lempelZivWelch")) {
                File fileToDelete = new File("pg10.txt.test.lempelZivWelch");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void codingAndUncodingWorksForJarBinaryFile() {
        try {
            System.out.println("*** File: Compressor.jar ***");
            long start = System.nanoTime();
            
            this.lempelZivWelch.compress("Compressor.jar", "Compressor.jar.test.lempelZivWelch");
            this.lempelZivWelch = new LempelZivWelch(this.printer);
            this.lempelZivWelch.uncompress("Compressor.jar.test.lempelZivWelch", "Compressor.jar.test");
            
            double elapsedTime = (double) (System.nanoTime() - start) / 1000000;
            
            System.out.println("Time to encode and decode Compressor.jar: " + elapsedTime + " ms");
            
            assertTrue(filesAreSame("Compressor.jar", "Compressor.jar.test"));
            if (readFile.checkIfFileExists("Compressor.jar.test")) {
                File fileToDelete = new File("Compressor.jar.test");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("Compressor.jar.test.lempelZivWelch")) {
                File fileToDelete = new File("Compressor.jar.test.lempelZivWelch");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
