package datastructures;

/**
 * Class capsulate data structures used by Huffman method.
 */
public class ByteDataArray {
    private ByteData[] byteDatas;
    private ByteDataLinkedList byteDataLinkedList;
    private ByteDataBinaryTree byteDataBinaryTree;
    
    private int headerLength;
    private long binaryCounter;

    /**
     * Constructor.
     */
    public ByteDataArray() {
        this.byteDatas = new ByteData[256];
        for (int i = 0; i < 256; i++) {
            this.byteDatas[i] = new ByteData((byte) (i - 128));
        }
        this.byteDataLinkedList = new ByteDataLinkedList();
        this.byteDataBinaryTree = new ByteDataBinaryTree();
        this.headerLength = 0;
        this.binaryCounter = 0;
    }

    /**
     * Returns array which keep ByteData objects.
     *
     * @return      ByteData objects as array
     */
    public ByteData[] getByteDataArray() {
        return byteDatas;
    }
    
    /**
     * Counts how many times each character founds in the file.
     *
     * @param bytes     Array where characters are counted
     */
    public void count(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            this.byteDatas[bytes[i] + 128].growCount();
        }        
    }
    
    /**
     * Counts how many different characters founds in the file.
     *
     * @return bytes    Count of different characters
     */
    public int countDifferentCharacters() {
        int sum = 0;
        for (int i = 0; i < this.byteDatas.length; i++) {
            if (this.byteDatas[i].getCount() > 0) {
                sum++;
            }
        }  
        return sum;
    }
    
    /**
     * Method reads header information of the ByteList to the data structures.
     *
     * @param readByteList      ByteList where header information is read
     */
    public void readHeader(ByteList readByteList) throws Exception {
        readByteList.startReading();
        for (int i = 0; i < 8; i++) {
            this.binaryCounter <<= 8;
            this.binaryCounter |= (readByteList.readNext() & 0xFF);
        }
        int differentChars = 1 + (int) (readByteList.readNext() & 0xFF);
        this.headerLength = 8 + 1 + differentChars * (8 + 1 + 1);
        for (int i = 0; i < differentChars; i++) {
            long compressedChar = 0;
            for (int k = 0; k < 8; k++) {
                compressedChar <<= 8;
                compressedChar |= (readByteList.readNext() & 0xFF);
            }
            char compressedLength = (char) readByteList.readNext();
            byte normalChar = readByteList.readNext();
            this.byteDatas[normalChar + 128].setNormalChar(normalChar);
            this.byteDatas[normalChar + 128].setCompressedChar(compressedChar);
            this.byteDatas[normalChar + 128].setCompressedLength(compressedLength);
        }        
    }
    
    /**
     * Method uncompress given input ByteList to the output ByteList.
     *
     * @param readByteList      Input ByteList
     * @param writeByteList     Output ByteList
     */
    public void uncompress(ByteList readByteList, ByteList writeByteList) throws Exception {
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
    }
    
    /**
     * Method writes header information of the data structures to the ByteList.
     *
     * @param writeByteList     ByteList where information is written
     */
    public void writeHeader(ByteList writeByteList) throws Exception {
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
                    writeByteList.add((byte) (toByte & 0xFF));                        
                }
                writeByteList.add((byte) compressedLength);
                writeByteList.add((byte) this.byteDatas[i].getNormalChar()); 
            }
        }
        differentCharacters--;
        writeByteList.set(8, (byte) (differentCharacters & 0xFF));            
    }
    
    /**
     * Method compress given input ByteList to the output ByteList.
     *
     * @param readByteList      Input ByteList
     * @param writeByteList     Output ByteList
     */
    public void compress(ByteList readByteList, ByteList writeByteList) throws Exception {
        writeHeader(writeByteList);
        long binaryCounter = 0;
        int spaceInLong = 64;
        long buffer = 0;
        for (int i = 0; i < readByteList.size(); i++) {
            ByteData currentByte = this.byteDatas[(int) readByteList.get(i) + 128];
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
                    writeByteList.add((byte) (toByte & 0xFF));                        
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
                writeByteList.add((byte) (toByte & 0xFF));                        
            }
        }
        long toByte = binaryCounter;
        for (int k = 7; k >= 0; k--) {
            writeByteList.set(k, (byte) (toByte & 0xFF)); 
            toByte >>= 8;                                       
        }
    }
    
    /**
     * Creates ordered linked list.
     */
    public void createLinkedList() {
        this.byteDataLinkedList.addArray(getByteDataArray());
    }
    
    /**
     * Creates binary tree from linked list.
     */
    public void createBinaryTreeFromLinkedList() {
        this.byteDataBinaryTree.createBinaryTreeFromLinkedList(this.byteDataLinkedList);
        this.byteDataBinaryTree.saveCodesForTree();
    }
    
    /**
     * Creates binary tree from binary coded labels.
     */
    public void createBinaryTreeFromBinaryCodedCodes() {
        this.byteDataBinaryTree.createBinaryTreeFromBinaryCodedCodes(this.byteDatas);
    }
}
