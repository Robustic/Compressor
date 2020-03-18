package fileiotest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import datastructures.ByteList;
import fileio.ReadFile;

public class ReadFileTest {
    ReadFile readFile;
    ByteList byteList;

    @Before
    public void initialize() {
        this.readFile = new ReadFile();
        this.byteList = new ByteList();
    }
    
    @Test
    public void fileExistCheckFindsFile() {
        assertTrue(this.readFile.checkIfFileExists("huffman.txt"));
    }
    
    @Test
    public void fileExistCheckNotFindNotExistingFile() {
        assertFalse(this.readFile.checkIfFileExists("filenotfound.txt"));
    }
    
    @Test
    public void fileExistCheckNotTakeAccountDirectory() {
        assertFalse(this.readFile.checkIfFileExists("src"));
    }
    
    @Test
    public void fileReadingWorks() {
        try {
            readFile.readFile("huffman.txt", this.byteList);
            assertEquals((byte)65, this.byteList.get(0));
            assertEquals((byte)68, this.byteList.get(45));
            assertEquals((byte)10, this.byteList.get(46));
            assertEquals(47, this.byteList.size());
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    @Test
    public void fileReadingWorksForlargeFile() {
        try {
            readFile.readFile("pg10.txt", this.byteList);
            assertEquals((byte)84, this.byteList.get(3));
            assertEquals((byte)104, this.byteList.get(4));
            assertEquals((byte)101, this.byteList.get(5));
            assertEquals(4452519, this.byteList.size());
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    @Test
    public void fileReadingWhenFileNotExists() {
        try {
            readFile.readFile("notexists.txt", this.byteList);
            assertTrue(false);
        } catch (Exception e) {
            System.out.println(e);
            assertTrue(true);
            assertEquals("File reading not working or file not exists!", e.getMessage());
        }        
    }
}
