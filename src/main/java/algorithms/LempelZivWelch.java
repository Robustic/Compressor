package algorithms;

import datastructures.ByteDataArray;
import datastructures.ByteList;
import datastructures.Letter;
import fileio.ReadFile;
import fileio.WriteFile;
import userio.MessagePrinter;

public class LempelZivWelch {
    private MessagePrinter printer;
    private Letter root;
    private int nextCode;
    private ByteList[] translation;
    
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
    
    public void addIntToByteList(int number, ByteList byteList) throws Exception {        
        Byte firstByte = (byte) ((number >> 8) & 0xFF);
        byteList.add(firstByte);        
        byteList.add((byte) (number & 0xFF));
    }
        
    public int nextIntFromByteList(ByteList byteList) throws Exception {
        Byte byte1 = byteList.readNext();
        Byte byte2 = byteList.readNext();
        int number = (int) (byte1 & 0xFF);
        number <<= 8;
        number = (int) ((byte2 & 0xFF) | number);
        return number;
    }

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
    
    public ByteList decode(ByteList byteList) throws Exception {
        ByteList output = new ByteList();
        if (byteList.size() == 0) {
            return output;
        } else if (byteList.size() % 2 > 0) {
            throw new Exception("Lempel-Ziv-Welch decoding byte list size is not even!");
        }

        byteList.startReading();            
        int ocode = nextIntFromByteList(byteList);
        output.combine(this.translation[ocode].getBytesAsArray());

        ByteList string = new ByteList();
        byte character = 0;
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
        this.printer.println("Compression took " + duration + " ms.");
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
        this.printer.println("Uncompressing took " + duration + " ms.");
        
        this.printer.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        this.printer.println("Output file writing ended.");
    }
}
