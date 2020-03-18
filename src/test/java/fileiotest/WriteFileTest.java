package fileiotest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import datastructures.ByteList;
import fileio.ReadFile;
import fileio.WriteFile;
import java.io.File;

public class WriteFileTest {    
    WriteFile writeFile;
    ReadFile readFile;
    ByteList byteListWrite;
    ByteList byteListRead;
    
    @Before
    public void initialize() {        
        this.writeFile = new WriteFile();
        this.readFile = new ReadFile();
        this.byteListWrite = new ByteList();
        this.byteListRead = new ByteList();        
    }
    
    @Test
    public void writedFileExists() {
        try {
            if (readFile.checkIfFileExists("writetestfiletodirectory.txt")) {
                File fileToDelete = new File("writetestfiletodirectory.txt");
                fileToDelete.delete();
            }
            assertFalse(this.readFile.checkIfFileExists("writetestfiletodirectory.txt"));
            byte[] bytes = new byte[5];
            bytes[0] = 'a';
            bytes[1] = 'Y';
            bytes[2] = '3';
            bytes[3] = '.';
            bytes[4] = 10;
            this.byteListWrite.combine(bytes);
            this.writeFile.writeFile("writetestfiletodirectory.txt", byteListWrite);
            assertTrue(this.readFile.checkIfFileExists("writetestfiletodirectory.txt"));
            
            readFile.readFile("writetestfiletodirectory.txt", this.byteListRead);
            assertEquals((byte)97, this.byteListRead.get(0));
            assertEquals((byte)89, this.byteListRead.get(1));
            assertEquals((byte)51, this.byteListRead.get(2));
            assertEquals((byte)46, this.byteListRead.get(3));
            assertEquals((byte)10, this.byteListRead.get(4));
            assertEquals(5, this.byteListRead.size());
            
            if (readFile.checkIfFileExists("writetestfiletodirectory.txt")) {
                File fileToDelete = new File("writetestfiletodirectory.txt");
                fileToDelete.delete();
            }
            assertFalse(this.readFile.checkIfFileExists("writetestfiletodirectory.txt"));
        } catch (Exception e) {
            System.out.println("e");
        }        
    }
    
    @Test
    public void writedLargeFileExists() {
        try {
            if (readFile.checkIfFileExists("writetestfiletodirectory.txt")) {
                File fileToDelete = new File("writetestfiletodirectory.txt");
                fileToDelete.delete();
            }
            assertFalse(this.readFile.checkIfFileExists("writetestfiletodirectory.txt"));
            byte[] bytes = new byte[1000000];
            for (int i = 0; i < 1000000; i++) {
                bytes[i] = (byte)((i % 256) - 128);
            }
            this.byteListWrite.combine(bytes);
            this.writeFile.writeFile("writetestfiletodirectory.txt", byteListWrite);
            assertTrue(this.readFile.checkIfFileExists("writetestfiletodirectory.txt"));
            
            readFile.readFile("writetestfiletodirectory.txt", this.byteListRead);
            boolean isItSame = true;
            for (int i = 0; i < 1000000; i++) {
                if (this.byteListRead.get(i) != (byte)((i % 256) - 128)) {
                    isItSame = false;
                }
            }
            assertEquals(1000000, this.byteListRead.size());
            assertTrue(isItSame);
            
            if (readFile.checkIfFileExists("writetestfiletodirectory.txt")) {
                File fileToDelete = new File("writetestfiletodirectory.txt");
                fileToDelete.delete();
            }
            assertFalse(this.readFile.checkIfFileExists("writetestfiletodirectory.txt"));
        } catch (Exception e) {
            System.out.println("e");
        }        
    }
}
