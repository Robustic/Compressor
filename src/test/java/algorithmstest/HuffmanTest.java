package algorithmstest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import algorithms.Huffman;
import datastructures.ByteList;
import fileio.ReadFile;
import fileio.WriteFile;
import java.io.File;

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
}
