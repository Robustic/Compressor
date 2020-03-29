package algorithmstest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import algorithms.Huffman;
import algorithms.LempelZivWelch;
import datastructures.ByteList;
import fileio.ReadFile;
import fileio.WriteFile;
import java.io.File;
import static org.junit.Assert.assertTrue;

public class HuffmanTest {
    Huffman huffman;
    ReadFile readFile = new ReadFile();
    
    @Before
    public void initialize() {
        this.huffman = new Huffman();
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
            System.out.println("Files are different size.");
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
    
    @Test
    public void codingAndUncodingWorksForLargeFile() {
        try {
            System.out.println("*** File: pg10.txt ***");
            this.huffman.compress("pg10.txt", "pg10.txt.test.huffman");
            this.huffman.uncompress("pg10.txt.test.huffman", "pg10.txt.test");
            assertTrue(filesAreSame("pg10.txt", "pg10.txt.test"));
            if (readFile.checkIfFileExists("pg10.txt.test")) {
                File fileToDelete = new File("pg10.txt.test");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("pg10.txt.test.huffman")) {
                File fileToDelete = new File("pg10.txt.test.huffman");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
//    @Test
//    public void codingAndUncodingWorksForBinaryFile() {
//        try {
//            System.out.println("*** File: Compressor.jar ***");
//            this.huffman.compress("Compressor.jar", "Compressor.jar.test.huffman");
//            this.huffman.uncompress("Compressor.jar.test.huffman", "Compressor.jar.test");
////            assertTrue(filesAreSame("Compressor.jar", "Compressor.jar.test"));
////            if (readFile.checkIfFileExists("Compressor.jar.test")) {
////                File fileToDelete = new File("Compressor.jar.test");
////                fileToDelete.delete();
////            }
////            if (readFile.checkIfFileExists("Compressor.jar.test.huffman")) {
////                File fileToDelete = new File("Compressor.jar.test.huffman");
////                fileToDelete.delete();
////            }
//        } catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace(System.out);
////            assertTrue(false);
//        }
//    }
}
