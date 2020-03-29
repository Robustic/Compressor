package algorithms;

import datastructures.ByteDataArray;
import datastructures.ByteList;
import datastructures.Letter;
import fileio.ReadFile;
import fileio.WriteFile;

public class LempelZivWelch {
    private Letter root;
    private int nextCode;
    private ByteList[] translation;
    
    public LempelZivWelch() {
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
                //System.out.print((char) + current.getCode() + " ");
            } else {
                System.out.println("ERROR");
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
                System.out.println("Virhetoiminto, objektia ei ole alustettu.");
            }
        }
        current.initializeChild((int) (newByte & 0xFF), this.nextCode);
//        System.out.print("Byte: ");
//        for (int i = 0; i < bytes.size(); i++) {
//            System.out.print((int) (bytes.get(i) & 0xFF) + " ");
//        }
//        System.out.print((int) (newByte & 0xFF) + " ");
//        System.out.print(", ");
//        for (int i = 0; i < bytes.size(); i++) {
//            System.out.print((char)(bytes.get(i)) + " ");
//        }
//        System.out.print((char)(newByte) + " ");
//        System.out.println(", code: " + this.nextCode);
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
//        System.out.println("*** ENCODE ***");
        ByteList codedByteList = new ByteList();
        try { 
            if (byteList.size() == 0) {
                return codedByteList;
            }
                        
            byteList.startReading();            
            ByteList partialString = new ByteList(byteList.readNext());
            while (byteList.checkNext()) {
                Byte symbol = byteList.readNext();
                if (isWordInitialized(partialString, symbol)) {
                    //System.out.println("Loytyi jo taulukosta, luetaan seuraava symboli.");
                    partialString.add(symbol);
                } else {
//                    System.out.println("Print: " + outputTheCode(partialString));
                    addIntToByteList(outputTheCode(partialString), codedByteList);
                    if (nextCode < 65536) {
                        initializeWord(partialString, symbol);
                    }
                    partialString = new ByteList(symbol);
                }
            }
//            System.out.println("Print: " + outputTheCode(partialString));
            addIntToByteList(outputTheCode(partialString), codedByteList);
            
        } catch (Exception e) {
            
        }
        return codedByteList;
    }
    
//    public ByteList codeToByteList(int code) {
//        ByteList newByteList = new ByteList();
//        
//        return newByteList;
//    }
//    
//    private void tree(ByteList byteList) {
//        
//    }
    
    public ByteList decode(ByteList byteList) throws Exception {
//        System.out.println("*** DECODE ***");
        ByteList output = new ByteList();
        try { 
            if (byteList.size() == 0) {
                return output;
            } else if (byteList.size() % 2 > 0) {
                throw new Exception("Decoding byte list size is not even!");
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
//            for (int i = 256; i < nextCode; i++) {
//                //System.out.print("Code " + i + ": ");
////                for (int k = 0; k < translation[i].size(); k++) {
////                    System.out.print((int) translation[i].get(k) + ", ");
////                }
////                System.out.println("");
//            }
//            System.out.println("Print: " + outputTheCode(partialString));
//            addIntToByteList(outputTheCode(partialString), output);
            
        } catch (Exception e) {
            
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
        System.out.println("Reading input file...");        
        ByteList readByteList = new ByteList();        
        readFile(readFileName, readByteList);
        System.out.println("Input file reading ended.");
        
        System.out.println("Compressing...");
        long startTime = System.nanoTime();        
        ByteList writeByteList = encode(readByteList);
        long endTime = System.nanoTime();
        
        System.out.println("Compressing ended.");
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Compression took " + duration + " ms.");
        double compressionRate = (double) writeByteList.size() / readByteList.size() * 100;
        System.out.println("Compressing rate " + String.format("%.2f", compressionRate) + " %.");
        
        System.out.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        System.out.println("Output file writing ended.");
    }
    
    /**
     * Uncompress input file to the output file.
     *
     * @param readFileName      Input file name
     * @param writeFileName     Output file name
     * @throws Exception        Exception
     */
    public void uncompress(String readFileName, String writeFileName) throws Exception {
        System.out.println("Reading input file...");   
        ByteList readByteList = new ByteList();        
        readFile(readFileName, readByteList);
        System.out.println("Input file reading ended.");
        
        System.out.println("Uncompressing...");
        long startTime = System.nanoTime();
        ByteList writeByteList = decode(readByteList);
        long endTime = System.nanoTime();
        
        System.out.println("Uncompressing ended.");
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Uncompressing took " + duration + " ms.");
        
        System.out.println("Writing output file...");
        writeFile(writeFileName, writeByteList);
        System.out.println("Output file writing ended.");
    }
}
