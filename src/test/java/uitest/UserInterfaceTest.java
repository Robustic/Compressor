package uitest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import datastructures.ByteList;
import fileio.ReadFile;
import fileio.WriteFile;
import java.io.File;
import ui.UserInterface;
import userio.TestPrinter;

public class UserInterfaceTest {
    private TestPrinter printer;
    private UserInterface ui;
    ReadFile readFile = new ReadFile();
    
    @Before
    public void initialize() {
        this.printer = new TestPrinter();
        this.ui = new UserInterface(this.printer);
    }
    
    @Test
    public void withNoArguments() {
        try {
            String[] args = new String[0];
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withHelp() {
        try {
            String[] args = new String[1];
            args[0] = "help";
            this.ui.runUserInterface(args);
            assertEquals(16, this.printer.messagesSize());
            assertEquals("To compress file with Huffman algorithm, run command 'compressor huff comp <filename>'.", this.printer.messageInIndex(5));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withOneStringButWrongWord() {
        try {
            String[] args = new String[1];
            args[0] = "helppi";
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withHelpAndTwoStrings() {
        try {
            String[] args = new String[2];
            args[0] = "help";
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withHelpAndThreeStrings() {
        try {
            String[] args = new String[3];
            args[0] = "help";
            args[1] = "";
            args[2] = "ERROR";
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("File name ERROR does not exists.", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withHelpAndEmptyThreeStrings() {
        try {
            String[] args = new String[3];
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("File name  does not exists.", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileAndWrongAlgorithm() {
        try {
            String[] args = new String[3];
            args[0] = "wrongAlgo";
            args[2] = "pg10.txt";
            this.ui.runUserInterface(args);
            assertEquals(4, this.printer.messagesSize());
            assertEquals("wrongAlgo is not accepted packing algorithm.", this.printer.messageInIndex(2));
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(3));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileAndHuffmanAlgorithmButWrongMethod() {
        try {
            String[] args = new String[3];
            args[0] = "huff";
            args[2] = "pg10.txt";
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileAndLempelZivWelchAlgorithmButWrongMethod() {
        try {
            String[] args = new String[3];
            args[0] = "lzw";
            args[2] = "pg10.txt";
            this.ui.runUserInterface(args);
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));
        } catch (Exception e) {
            assertTrue(false);
        }
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
    
    @Test
    public void withExistingFileWithWrongFilenameExtensionAndHuffmanAlgorithmAndCompressingAndUncompressingMethod() {
        try {
            String[] args = new String[3];
            args[0] = "huff";
            args[1] = "uncomp";
            args[2] = "Compressor.jar";
            this.ui.runUserInterface(args);            
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));         
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileWithShortFilenameExtensionAndHuffmanAlgorithmAndCompressingAndUncompressingMethod() {
        try {
            String[] args = new String[3];
            args[0] = "huff";
            args[1] = "uncomp";
            args[2] = "pom.xml";
            this.ui.runUserInterface(args);            
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));         
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileAndHuffmanAlgorithmAndCompressingAndUncompressingMethod() {
        try {
            String[] args = new String[3];
            args[0] = "huff";
            args[1] = "comp";
            args[2] = "pg10.txt";
            this.ui.runUserInterface(args);
            assertEquals(13, this.printer.messagesSize());
            assertEquals("Compressing with Huffman.", this.printer.messageInIndex(2));
            assertEquals("Input file name: 'pg10.txt'", this.printer.messageInIndex(3));
            assertEquals("Compressing rate 57.94 %.", this.printer.messageInIndex(9));
            assertEquals("Compressed file name: 'pg10.txt.huffman'", this.printer.messageInIndex(12));
                        
            assertTrue(readFile.checkIfFileExists("pg10.txt.huffman"));
            
            File file = new File("pg10.txt.huffman");
            assertTrue(file.renameTo(new File("pg10.new.txt.huffman")));
            
            this.printer = new TestPrinter();
            this.ui = new UserInterface(this.printer);
            args[1] = "uncomp";
            args[2] = "pg10.new.txt.huffman";            
            this.ui.runUserInterface(args);
            assertEquals(12, this.printer.messagesSize());
            assertEquals("Uncompressing with Huffman.", this.printer.messageInIndex(2));
            assertEquals("Input file name: 'pg10.new.txt.huffman'", this.printer.messageInIndex(3));
            assertEquals("Uncompressed file name: 'pg10.new.txt'", this.printer.messageInIndex(11));
            
            assertTrue(readFile.checkIfFileExists("pg10.new.txt"));
            
            assertTrue(filesAreSame("pg10.txt", "pg10.new.txt"));
            
            if (readFile.checkIfFileExists("pg10.new.txt.huffman")) {
                File fileToDelete = new File("pg10.new.txt.huffman");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("pg10.new.txt")) {
                File fileToDelete = new File("pg10.new.txt");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileWithWrongFilenameExtensionAndLZWAlgorithmAndCompressingAndUncompressingMethod() {
        try {
            String[] args = new String[3];
            args[0] = "lzw";
            args[1] = "uncomp";
            args[2] = "Compressor.jar";
            this.ui.runUserInterface(args);            
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));         
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileWithShortFilenameExtensionAndLZWAlgorithmAndCompressingAndUncompressingMethod() {
        try {
            WriteFile writeFile = new WriteFile();
            writeFile.writeFile("abc", new ByteList());
            String[] args = new String[3];
            args[0] = "lzw";
            args[1] = "uncomp";
            args[2] = "abc";
            this.ui.runUserInterface(args);
            if (readFile.checkIfFileExists("abc")) {
                File fileToDelete = new File("abc");
                fileToDelete.delete();
            }
            assertEquals(3, this.printer.messagesSize());
            assertEquals("Check help with command 'compressor help'", this.printer.messageInIndex(2));         
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void withExistingFileAndLZWAlgorithmAndCompressingAndUncompressingMethod() {
        try {
            
            String[] args = new String[3];
            args[0] = "lzw";
            args[1] = "comp";
            args[2] = "pg10.txt";
            this.ui.runUserInterface(args);
            assertEquals(13, this.printer.messagesSize());
            assertEquals("Compressing with Lempel-Ziv-Welch.", this.printer.messageInIndex(2));
            assertEquals("Input file name: 'pg10.txt'", this.printer.messageInIndex(3));
            assertEquals("Compressing rate 36.70 %.", this.printer.messageInIndex(9));
            assertEquals("Compressed file name: 'pg10.txt.lzw'", this.printer.messageInIndex(12));
                        
            assertTrue(readFile.checkIfFileExists("pg10.txt.lzw"));
            
            File file = new File("pg10.txt.lzw");
            assertTrue(file.renameTo(new File("pg10.new.txt.lzw")));
            
            this.printer = new TestPrinter();
            this.ui = new UserInterface(this.printer);
            args[1] = "uncomp";
            args[2] = "pg10.new.txt.lzw";
            this.ui.runUserInterface(args);
            assertEquals(12, this.printer.messagesSize());
            assertEquals("Uncompressing with Lempel-Ziv-Welch.", this.printer.messageInIndex(2));
            assertEquals("Input file name: 'pg10.new.txt.lzw'", this.printer.messageInIndex(3));
            assertEquals("Uncompressed file name: 'pg10.new.txt'", this.printer.messageInIndex(11));
            
            assertTrue(readFile.checkIfFileExists("pg10.new.txt"));
            
            assertTrue(filesAreSame("pg10.txt", "pg10.new.txt"));
            
            if (readFile.checkIfFileExists("pg10.new.txt.lzw")) {
                File fileToDelete = new File("pg10.new.txt.lzw");
                fileToDelete.delete();
            }
            if (readFile.checkIfFileExists("pg10.new.txt")) {
                File fileToDelete = new File("pg10.new.txt");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
