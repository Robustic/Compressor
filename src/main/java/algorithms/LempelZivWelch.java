package algorithms;

import datastructures.ByteList;
import datastructures.Letter;
import fileio.ReadFile;
import fileio.WriteFile;
import userio.MessagePrinter;

/**
 * Class which represent interface for the Lempel-Ziv-Welch compressing algorithm.
 */
public class LempelZivWelch {
    private MessagePrinter printer;
    private Letter root;
    private int nextCode;
    private ByteList[] translation;
    
    /**
     * Constructor.
     * 
     * @param messagePrinter    MessagePrinter
     */
    public LempelZivWelch(MessagePrinter messagePrinter) {
        this.printer = messagePrinter;
        this.root = new Letter();
        this.root.initialize(-1);
        for (int i = 0; i < 256; i++) {
            this.root.initializeChild(i, i);
        }
        this.nextCode = 256;
        this.translation = new ByteList[65536];
        for (int i = 0; i < 256; i++) {
            this.translation[i] = new ByteList((byte) i);
        }
        
    }
    
    /**
     * Method to check if word is already initialized.
     *
     * @param bytes         Word without last letter
     * @param newByte       Last letter of the word
     * @return              True if word is already initialized
     * @throws Exception    Exception
     */
    public boolean isWordInitialized(ByteList bytes, byte newByte) throws Exception {
        Letter current = this.root;
        for (int i = 0; i < bytes.size(); i++) {
            if (current.isChildAlreadyInitialized((int) (bytes.get(i) & 0xFF))) {
                current = current.getChildInIndex((int) (bytes.get(i) & 0xFF));
            } else {
                return false;
            }
        }
        if (current.isChildAlreadyInitialized((int) (newByte & 0xFF))) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns code for the word.
     *
     * @param bytes         Word as byte list
     * @return              Code
     * @throws Exception    Exception
     */
    public int outputTheCode(ByteList bytes) throws Exception {
        Letter current = this.root;
        for (int i = 0; i < bytes.size(); i++) {
            if (current.isChildAlreadyInitialized((int) (bytes.get(i) & 0xFF))) {
                current = current.getChildInIndex((int) (bytes.get(i) & 0xFF));
            } else {
                throw new Exception("Trying to read code from the child which is not yet initialized!");
            }
        }
        return current.getCode();
    }
    
    /**
     * Method to initialize word.
     *
     * @param bytes         Word without last letter
     * @param newByte       Last letter of the word
     * @throws Exception    Exception
     */
    public void initializeWord(ByteList bytes, byte newByte) throws Exception {
        Letter current = this.root;
        for (int i = 0; i < bytes.size(); i++) {
            if (current.isChildAlreadyInitialized((int) (bytes.get(i) & 0xFF))) {
                current = current.getChildInIndex((int) (bytes.get(i) & 0xFF));
            } else {
                throw new Exception("Trying to read code from the child which is not yet initialized!");
            }
        }
        current.initializeChild((int) (newByte & 0xFF), this.nextCode);
        this.nextCode++;
    }
    
    /**
     * Adds code to the end of the ByteList.
     *
     * @param code          Code
     * @param byteList      ByteList
     * @throws Exception    Exception
     */
    public void addIntToByteList(int code, ByteList byteList) throws Exception {        
        Byte firstByte = (byte) ((code >> 8) & 0xFF);
        byteList.add(firstByte);        
        byteList.add((byte) (code & 0xFF));
    }
        
    /**
     * Reads next int from the ByteList.
     *
     * @param byteList      ByteList
     * @return              Code as integer
     * @throws Exception    Exception
     */
    public int nextIntFromByteList(ByteList byteList) throws Exception {
        Byte byte1 = byteList.readNext();
        Byte byte2 = byteList.readNext();
        int number = (int) (byte1 & 0xFF);
        number <<= 8;
        number = (int) ((byte2 & 0xFF) | number);
        return number;
    }

    /**
     * Encodes ByteList.
     *
     * @param byteList      ByteList as input
     * @return              Encoded ByteList
     * @throws Exception    Exception
     */
    public ByteList encode(ByteList byteList) throws Exception {
        ByteList codedByteList = new ByteList();
        if (byteList.size() == 0) {
            return codedByteList;
        }

        byteList.startReading();            
        ByteList partialString = new ByteList(byteList.readNext());
        while (byteList.checkNext()) {
            Byte symbol = byteList.readNext();
            if (isWordInitialized(partialString, symbol)) {
                partialString.add(symbol);
            } else {
                addIntToByteList(outputTheCode(partialString), codedByteList);
                if (nextCode < 65536) {
                    initializeWord(partialString, symbol);
                }
                partialString = new ByteList(symbol);
            }
        }
        addIntToByteList(outputTheCode(partialString), codedByteList);
        return codedByteList;
    }
    
    private void lzw(ByteList byteList, ByteList output) throws Exception {
        byteList.startReading();            
        int ocode = nextIntFromByteList(byteList);
        output.combine(this.translation[ocode].getBytesAsArray());

        ByteList string = new ByteList();
        byte character = (byte) ocode;
        while (byteList.checkNext()) {
            int ncode = nextIntFromByteList(byteList);                
            if (this.translation[ncode] != null) {
                string = new ByteList(this.translation[ncode]);
            } else {
                string = new ByteList(this.translation[ocode]);
                string.add(character);
            }
            output.combine(string.getBytesAsArray());
            character = string.get(0);
            if (nextCode < 65536) {
                this.translation[nextCode] = new ByteList(this.translation[ocode]);
                this.translation[nextCode].add(character);
                nextCode++;     
            }
            ocode = ncode;
        }
    }
    
    /**
     * Decodes ByteList.
     *
     * @param byteList      ByteList as input
     * @return              Decoded ByteList
     * @throws Exception    Exception
     */
    public ByteList decode(ByteList byteList) throws Exception {
        ByteList output = new ByteList();
        if (byteList.size() == 0) {
            return output;
        } else if (byteList.size() % 2 > 0) {
            throw new Exception("Lempel-Ziv-Welch decoding byte list size is not even!");
        }
        lzw(byteList, output);        
        return output;
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
        ByteList writeByteList = encode(readByteList);
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
        ByteList writeByteList = decode(readByteList);
        long endTime = System.nanoTime();
        
        this.printer.println("Uncompressing ended.");
        long duration = (endTime - startTime) / 1000000;
        this.printer.println("Uncompressing took " + duration + " ms elapsed time.");
        
        this.printer.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        this.printer.println("Output file writing ended.");
    }
}
