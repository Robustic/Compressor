package datastructures;

public class ByteDataArray {
    private ByteData[] byteDatas;
    private ByteDataLinkedList byteDataLinkedList;
    private ByteDataBinaryTree byteDataBinaryTree;
    
    private int headerLength;
    private long binaryCounter;

    public ByteDataArray() {
        this.byteDatas = new ByteData[256];
        for (int i = 0; i < 256; i++) {
            this.byteDatas[i] = new ByteData((byte)(i - 128));
        }
        this.byteDataLinkedList = new ByteDataLinkedList();
        this.byteDataBinaryTree = new ByteDataBinaryTree();
        this.headerLength = 0;
        this.binaryCounter = 0;
    }

    public ByteData[] getByteDataArray() {
        return byteDatas;
    }
    
    public void count(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            this.byteDatas[bytes[i] + 128].growCount();
        }        
    }
    
    public void readHeader(ByteList readByteList) {
        try {
            readByteList.startReading();
            for (int i = 0; i < 8; i++) {
                this.binaryCounter <<= 8;
                this.binaryCounter |= (readByteList.readNext() & 0xFF);
            }
            int differentChars = (readByteList.readNext() & 0xFF);
            this.headerLength = 8 + 1 + differentChars * (8 + 1 + 1);
            for (int i = 0; i < differentChars; i++) {
                long compressedChar = 0;
                for (int k = 0; k < 8; k++) {
                    compressedChar <<= 8;
                    compressedChar |= (readByteList.readNext() & 0xFF);
                }
                char compressedLength = (char)readByteList.readNext();
                byte normalChar = readByteList.readNext();
                this.byteDatas[normalChar + 128].setNormalChar(normalChar);
                this.byteDatas[normalChar + 128].setCompressedChar(compressedChar);
                this.byteDatas[normalChar + 128].setCompressedLength(compressedLength);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void uncode(ByteList readByteList, ByteList writeByteList) {
        try {
            ByteData currentByteData = byteDataBinaryTree.getRoot();
            long binaryIterator = 1L;
            for (int i = this.headerLength; i < readByteList.size(); i++) {
                if (binaryIterator > this.binaryCounter) {
                    break;
                }
                byte currentByte = readByteList.get(i);
                for (int k = 7; k >= 0; k--) {                
                    if (binaryIterator > this.binaryCounter) {
                        break;
                    }
                    if ((currentByte & (1 << k)) == 0) {
                        currentByteData = currentByteData.getLeftChild();
                    } else {
                        currentByteData = currentByteData.getRightChild();
                    }
                    if (currentByteData.getLeftChild() == null) {
                        writeByteList.add(currentByteData.getNormalChar());
                        currentByteData = byteDataBinaryTree.getRoot();
                    }
                    binaryIterator++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void writeHeader(ByteList writeByteList) {
        try {
            writeByteList.addEmpties(9);
            int differentCharacters = 0;
            for (int i = 0; i < byteDatas.length; i++) {
                if (this.byteDatas[i].getCount() > 0) {
                    differentCharacters++;
                    long compressedChar = this.byteDatas[i].getCompressedChar();
                    char compressedLength = this.byteDatas[i].getCompressedLength();
                    for (int k = 7; k >= 0; k--) {
                        long toByte = compressedChar;
                        toByte >>= 8 * k;
                        writeByteList.add((byte)(toByte & 0xFF));                        
                    }
                    writeByteList.add((byte)compressedLength);
                    writeByteList.add((byte)this.byteDatas[i].getNormalChar()); 
                }
            }
            writeByteList.set(8, (byte)(differentCharacters & 0xFF));
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    public void code(ByteList readByteList, ByteList writeByteList) {
        writeHeader(writeByteList);
        long binaryCounter = 0;
        int spaceInLong = 64;
        long buffer = 0;
        try {
            for (int i = 0; i < readByteList.size(); i++) {
                ByteData currentByte = this.byteDatas[(int)readByteList.get(i) + 128];
                long compressedChar = currentByte.getCompressedChar();
                int compressedLength = currentByte.getCompressedLength();
                binaryCounter += compressedLength;
                long firsPart = compressedChar;                
                if (spaceInLong <= compressedLength) {
                    firsPart >>= compressedLength - spaceInLong;
                    buffer += firsPart;
                    for (int k = 7; k >= 0; k--) {
                        long toByte = buffer;
                        toByte >>= 8 * k;
                        writeByteList.add((byte)(toByte & 0xFF));                        
                    }
                    if (spaceInLong < compressedLength) {
                        int secondLength = compressedLength - spaceInLong;
                        spaceInLong = 64 - secondLength;
                        compressedChar <<= spaceInLong;
                        buffer = compressedChar;
                    } else {
                        spaceInLong = 64;
                        buffer = 0;                        
                    }                    
                } else {
                    firsPart <<= spaceInLong - compressedLength;
                    buffer += firsPart;
                    spaceInLong -= compressedLength;
                }
            }
            if (spaceInLong < 64) {
                for (int k = 7; k >= 0; k--) {
                    long toByte = buffer;
                    toByte >>= 8 * k;
                    writeByteList.add((byte)(toByte & 0xFF));                        
                }
            }
            long toByte = binaryCounter;
            for (int k = 7; k >= 0; k--) {
                writeByteList.set(k, (byte)(toByte & 0xFF)); 
                toByte >>= 8;                                       
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void createLinkedList() {
        this.byteDataLinkedList.addArray(getByteDataArray());         
        // this.byteDataLinkedList.printLinkedList();
    }
    
    public void createBinaryTreeFromLinkedList() {
        this.byteDataBinaryTree.createBinaryTreeFromLinkedList(this.byteDataLinkedList);
        this.byteDataBinaryTree.saveCodesForTree();
        // this.byteDataBinaryTree.printBinaryTree();
        // // this.byteDataBinaryTree.printCodeTree();
    }
    
    public void createBinaryTreeFromBinaryCodedCodes() {
        this.byteDataBinaryTree.createBinaryTreeFromBinaryCodedCodes(this.byteDatas);
        // this.byteDataBinaryTree.printCodeTree();
    }
}
