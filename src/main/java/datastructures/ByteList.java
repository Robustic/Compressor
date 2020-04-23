package datastructures;

/**
 * Class to keep bytes in the list.
 * List is structured with array and pointer.
 */
public class ByteList {
    private byte[] bytes;
    private int pointer;
    private int readingPointer;

    /**
     * Constructor.
     */
    public ByteList() {
        this.bytes = new byte[2];
        this.pointer = 0;
        this.readingPointer = 0;
    }
    
    /**
     * Constructor.
     */
    public ByteList(byte firstByte) {
        this.bytes = new byte[2];
        this.bytes[0] = firstByte;
        this.pointer = 1;
        this.readingPointer = 0;
    }
    
    /**
     * Constructor.
     */
    public ByteList(ByteList other) {
        this.bytes = new byte[other.size()];
        for (int i = 0; i < other.size(); i++) {
            this.bytes[i] = other.bytes[i];
        }
        this.pointer = other.pointer;
        this.readingPointer = other.readingPointer;
    }
    
    /**
     * Method to set reading pointer to the start of the list.
     */
    public void startReading() {
        this.readingPointer = 0;
    }
    
    /**
     * Method to check if there is still bytes in the list to read.
     * 
     * @return      True if there is still bytes to read, false if not.
     */
    public boolean checkNext() {
        if (readingPointer >= pointer) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Method to return byte indicated by pointer.
     * After reading pointer is increased.
     * 
     * @return      Byte showed by pointer.
     */
    public byte readNext() {        
        this.readingPointer++;
        return bytes[this.readingPointer - 1];        
    }

    /**
     * Method to add single byte to the list.
     * If array is full, size is doubled if under size limit.
     * 
     * @param newbyte       Byte to add to the list.
     * @throws Exception    Exception if size limit is achieved.
     */
    public void add(byte newbyte) throws Exception {
        while (this.pointer >= this.bytes.length) {
            doubleSize();
        }
        this.bytes[this.pointer] = newbyte;
        this.pointer++;
    }
    
    private void doubleSize() throws Exception {
        if (this.bytes.length < 1073741824) {
            byte[] newbytes = new byte[2 * this.bytes.length];
            for (int i = 0; i < this.pointer; i++) {
                newbytes[i] = this.bytes[i];
            }
            this.bytes = newbytes;
        } else {
            throw new Exception("Too many bytes!");
        }
    }
    
    /**
     * Reads bytes from the list to the given array.
     * 
     * @param start     Location where reading is started.
     * @param buffer    Buffer where to read.
     */
    public void getPart(int start, byte[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = this.bytes[i + start];
        }
    }
    
    /**
     * Method to combine given array to the byte list.
     * 
     * @param bytesToCombine    Array to combine with byte list
     * @throws Exception        Exception is thrown if size limit is exceeded
     */
    public void combine(byte[] bytesToCombine) throws Exception {
        int totalLength = this.pointer + bytesToCombine.length;
        if (totalLength > 1073741824) {
            throw new Exception("Too many bytes!");
        }        
        if (totalLength > this.bytes.length) {
            int newArraySize = this.bytes.length;
            while (newArraySize < totalLength) {
                newArraySize = newArraySize * 2;
            }
            byte[] newArray = new byte[newArraySize];
            for (int i = 0; i < pointer; i++) {
                newArray[i] = this.bytes[i];
            }
            this.bytes = newArray;
        }
        for (int i = 0; i < bytesToCombine.length; i++) {
            this.bytes[this.pointer + i] = bytesToCombine[i];
        }        
        this.pointer = totalLength;
    }

    /**
     * Returns byte from the given index.
     * 
     * @param i             Given index
     * @return              Byte in the index
     * @throws Exception    Exception is thrown if the index is out of the list
     */
    public byte get(int i) throws Exception {
        if (i < 0 || i >= this.pointer) {
            throw new Exception("Index have to be inside list! Index " + i + " is outside list. List length is " + this.pointer + ".");
        }
        return this.bytes[i];
    }
    
    /**
     * Returns byte to the given index.
     * 
     * @param i             Given index
     * @param byteToSet     Byte to set to the index
     * @throws Exception    Exception is thrown if the index is out of the list
     */
    public void set(int i, byte byteToSet) throws Exception {
        if (i < 0 || i >= this.pointer) {
            throw new Exception("Index have to be inside list! Index " + i + " is outside list. List length is " + this.pointer + ".");
        }
        this.bytes[i] = byteToSet;
    }

    /**
     * Returns size of the list.
     *
     * @return      Size of the list
     */
    public int size() {
        return this.pointer;
    }

    /**
     * Returns bytes as an array.
     *
     * @return     Bytes as an array 
     */
    public byte[] getBytesAsArray() {
        byte[] bytesList = new byte[this.pointer];
        for (int i = 0; i < this.pointer; i++) {
            bytesList[i] = this.bytes[i];
        }
        return bytesList;
    }
    
    /**
     * Method to add given number of the empty bytes to the end of the list.
     *
     * @param count         Number of the empty bytes
     * @throws Exception    Exception is thrown if size limit is exceeded
     */
    public void addEmpties(int count) throws Exception {
        if (this.pointer + count > 1073741824) {
            throw new Exception("Too many bytes!");
        }
        this.combine(new byte[count]);
    }
}
