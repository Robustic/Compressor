package performancetests;

import java.io.File;
import org.junit.AfterClass;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Before;
import org.junit.BeforeClass;
import ui.UserInterface;
import userio.TestPrinter; 

import fileio.ReadFile;
import fileio.WriteFile;
import datastructures.ByteList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PerformanceTest {
    private TestPrinter printer;
    private UserInterface ui;
    ReadFile readFile = new ReadFile();
    WriteFile writeFile = new WriteFile();
    
    private static void writeTestFile(ArrayList<byte[]> list, Random random, int limit) throws Exception {
        WriteFile writeFile = new WriteFile();
        ByteList outputByteList = new ByteList();
        while (outputByteList.size() < limit) {
            byte[] word = list.get(random.nextInt(list.size()));
            if (outputByteList.size() + word.length <= limit) {
                outputByteList.combine(word);
            } else {
                int lengthToAdd = limit - outputByteList.size();
                for (int i = 0; i < lengthToAdd; i++) {
                    outputByteList.add(word[i]);
                }
            }                
            if (outputByteList.size() < limit)
                outputByteList.add((byte)32);
        }
        writeFile.writeFile("testFileToPerformanceTest_" + limit + ".txt", outputByteList);
    }
    
    @BeforeClass
    public static void writeTestFiles() {
        try {
            Random random = new Random(1337);
            ReadFile readFile = new ReadFile();            
            ByteList inputByteList = new ByteList();
            readFile.readFile("pg10.txt", inputByteList);
            ArrayList<byte[]> list = new ArrayList<byte[]>();
            for (int i = 0; i < inputByteList.size(); i++) {
                int start = i;
                while (i < inputByteList.size() && 33 <= inputByteList.get(i) && inputByteList.get(i) <= 125) {
                    i++;
                }
                if (start < i) {
                    byte[] newWord = new byte[i - start];
                    for (int k = 0; k < newWord.length; k++) {
                        newWord[k] = inputByteList.get(start + k);
                    }
                    list.add(newWord);
                }                
            }
            
            writeTestFile(list, random, 10000);
            writeTestFile(list, random, 100000);
            writeTestFile(list, random, 1000000);
            writeTestFile(list, random, 10000000);
            writeTestFile(list, random, 100000000);            
        } catch (Exception exception) {
            assertTrue(false);
        }        
    }
    
    @AfterClass
    public static void deleteTestFiles() {
        try {       
            ReadFile readFile = new ReadFile(); 
            if (readFile.checkIfFileExists("testFileToPerformanceTest_10000.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_10000.txt");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("testFileToPerformanceTest_100000.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_100000.txt");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("testFileToPerformanceTest_1000000.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_1000000.txt");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("testFileToPerformanceTest_10000000.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_10000000.txt");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("testFileToPerformanceTest_100000000.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_100000000.txt");
                fileToDelete.delete();
            }
        } catch (Exception exception) {
            assertTrue(false);
        }        
    }
    
    @Before
    public void initialize() {
        this.printer = new TestPrinter();
        this.ui = new UserInterface(this.printer);
    }
    
    private boolean filesAreSame(String file1, String file2) {
        ByteList list1 = new ByteList();
        ByteList list2 = new ByteList();
        try {
            this.readFile.readFile(file1, list1);
            this.readFile.readFile(file2, list2);
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
    
    public void huffman_x(int size) {
        try {
            System.out.println("******************************");
            System.out.println("Huffman: " + size + " bytes");
            System.out.println("==============================");
            String[] args = new String[3];
            args[0] = "huff";
            args[1] = "comp";
            args[2] = "testFileToPerformanceTest_" + size + ".txt";
            this.ui.runUserInterface(args);
            this.printer.printMessageInIndex(2);
            this.printer.printMessageInIndex(3);
            this.printer.printMessageInIndex(8);
            this.printer.printMessageInIndex(9);
            System.out.println("==============================");
            
            assertTrue(readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".txt.huffman"));
            
            File file = new File("testFileToPerformanceTest_" + size + ".txt.huffman");
            assertTrue(file.renameTo(new File("testFileToPerformanceTest_" + size + ".new.txt.huffman")));
            
            args[1] = "uncomp";
            args[2] = "testFileToPerformanceTest_" + size + ".new.txt.huffman";
            
            this.printer = new TestPrinter();
            this.ui = new UserInterface(this.printer);
            
            this.ui.runUserInterface(args);
            this.printer.printMessageInIndex(2);
            this.printer.printMessageInIndex(8);
            System.out.println("******************************");
            
            assertTrue(readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".new.txt"));
            
            assertTrue(filesAreSame("testFileToPerformanceTest_" + size + ".txt", "testFileToPerformanceTest_" + size + ".new.txt"));
            
            if (readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".new.txt.huffman")) {
                File fileToDelete = new File("testFileToPerformanceTest_" + size + ".new.txt.huffman");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".new.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_" + size + ".new.txt");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void huffman_10000() {
        try {
            huffman_x(10000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void huffman_100000() {
        try {
            huffman_x(100000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void huffman_1000000() {
        try {
            huffman_x(1000000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void huffman_10000000() {
        try {
            huffman_x(10000000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void huffman_100000000() {
        try {
            huffman_x(100000000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    public void lzw_x(int size) {
        try {
            System.out.println("******************************");
            System.out.println("Lempel-Ziv-Welch: " + size + " bytes");
            System.out.println("==============================");
            String[] args = new String[3];
            args[0] = "lzw";
            args[1] = "comp";
            args[2] = "testFileToPerformanceTest_" + size + ".txt";
            this.ui.runUserInterface(args);
            this.printer.printMessageInIndex(2);
            this.printer.printMessageInIndex(3);
            this.printer.printMessageInIndex(8);
            this.printer.printMessageInIndex(9);
            System.out.println("==============================");
            
            assertTrue(readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".txt.lzw"));
            
            File file = new File("testFileToPerformanceTest_" + size + ".txt.lzw");
            assertTrue(file.renameTo(new File("testFileToPerformanceTest_" + size + ".newlzw.txt.lzw")));
            
            args[1] = "uncomp";
            args[2] = "testFileToPerformanceTest_" + size + ".newlzw.txt.lzw";
            
            this.printer = new TestPrinter();
            this.ui = new UserInterface(this.printer);
            
            this.ui.runUserInterface(args);
            this.printer.printMessageInIndex(2);
            this.printer.printMessageInIndex(8);
            System.out.println("******************************");
            
            assertTrue(readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".newlzw.txt"));
            
            assertTrue(filesAreSame("testFileToPerformanceTest_" + size + ".txt", "testFileToPerformanceTest_" + size + ".newlzw.txt"));
            
            if (readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".newlzw.txt.lzw")) {
                File fileToDelete = new File("testFileToPerformanceTest_" + size + ".newlzw.txt.lzw");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("testFileToPerformanceTest_" + size + ".newlzw.txt")) {
                File fileToDelete = new File("testFileToPerformanceTest_" + size + ".newlzw.txt");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void lzw_10000() {
        try {
            lzw_x(10000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void lzw_100000() {
        try {
            lzw_x(100000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void lzw_1000000() {
        try {
            lzw_x(1000000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void lzw_10000000() {
        try {
            lzw_x(10000000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void lzw_100000000() {
        try {
            lzw_x(100000000);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
