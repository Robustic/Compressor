package algorithms;

import datastructures.ByteList;
import datastructures.Letter;

public class LempelZivWelch {
    private Letter root;
    private int nextCode;
    
    public LempelZivWelch() {
        this.root = new Letter();
        this.root.initialize(-1);
        for (int i = 0; i < 256; i++) {
            this.root.initializeChild(i, i);
        }
        this.nextCode = 256;
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
        System.out.print("Byte: ");
        for (int i = 0; i < bytes.size(); i++) {
            System.out.print((int) (bytes.get(i) & 0xFF) + " ");
        }
        System.out.print((int) (newByte & 0xFF) + " ");
        System.out.print(", ");
        for (int i = 0; i < bytes.size(); i++) {
            System.out.print((char)(bytes.get(i)) + " ");
        }
        System.out.print((char)(newByte) + " ");
        System.out.println(", code: " + this.nextCode);
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

    public ByteList code(ByteList byteList) {
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
                    System.out.println("Print: " + outputTheCode(partialString));
                    addIntToByteList(outputTheCode(partialString), codedByteList);
                    initializeWord(partialString, symbol);
                    partialString = new ByteList(symbol);
                }
            }
            System.out.println("Print: " + outputTheCode(partialString));
            addIntToByteList(outputTheCode(partialString), codedByteList);
            
        } catch (Exception e) {
            
        }
        return codedByteList;
    }
}
