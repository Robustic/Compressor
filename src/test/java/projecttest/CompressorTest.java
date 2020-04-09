package projecttest;

import project.Compressor;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import fileio.ReadFile;
import java.io.File;


public class CompressorTest {
    private Compressor compressor;
    private ReadFile readFile = new ReadFile();
    
    @Before
    public void initialize() {
        this.compressor = new Compressor();
    }
    
    @Test
    public void runningWorks() {
        try {            
            String[] args = new String[3];
            args[0] = "huff";
            args[1] = "comp";
            args[2] = "huffman.txt";
            this.compressor.main(args);
                        
            assertTrue(readFile.checkIfFileExists("huffman.txt.huffman"));
            
            if (readFile.checkIfFileExists("huffman.txt.huffman")) {
                File fileToDelete = new File("huffman.txt.huffman");
                fileToDelete.delete();
            }
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
