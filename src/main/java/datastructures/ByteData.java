package datastructures;

/**
 * Class to capsulate data related to the coding of the single byte.
 */
public class ByteData {    
    private byte normalChar;
    private long compressedChar;
    private char compressedLength;
    private int count;
    
    private ByteData parent;
    private ByteData leftChild;
    private ByteData rightChild;
    
    private ByteData previous;
    private ByteData next;
    
    /**
     * Constructor.
     *
     * @param normalChar    Coding of the byte before compressing
     */
    public ByteData(byte normalChar) {
        this.normalChar = normalChar;
        this.compressedChar = -1;
        this.compressedLength = 0;        
        this.count = 0;
    }
    
    /**
     * getNormalChar
     *
     * @return
     */
    public byte getNormalChar() {
        return normalChar;
    }

    /**
     * getCompressedChar
     *
     * @return
     */
    public long getCompressedChar() {
        return compressedChar;
    }

    /**
     * getCompressedLength
     *
     * @return
     */
    public char getCompressedLength() {
        return this.compressedLength;
    }

    /**
     * getCount
     *
     * @return
     */
    public int getCount() {
        return count;
    }
    
    /**
     * Method to increase count value with one.
     */
    public void growCount() {
        this.count++;
    }

    /**
     * getParent
     *
     * @return
     */
    public ByteData getParent() {
        return parent;
    }

    /**
     * getLeftChild
     *
     * @return
     */
    public ByteData getLeftChild() {
        return leftChild;
    }

    /**
     * getRightChild
     *
     * @return
     */
    public ByteData getRightChild() {
        return rightChild;
    }

    /**
     * getPrevious
     *
     * @return
     */
    public ByteData getPrevious() {
        return previous;
    }

    /**
     * getNext
     *
     * @return
     */
    public ByteData getNext() {
        return next;
    }

    /**
     * setCount
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * setNormalChar
     *
     * @param normalChar
     */
    public void setNormalChar(byte normalChar) {
        this.normalChar = normalChar;
    }

    /**
     * setCompressedChar
     *
     * @param compressedChar
     */
    public void setCompressedChar(long compressedChar) {
        this.compressedChar = compressedChar;
    }
    
    /**
     * setCompressedLength
     *
     * @param compressedLength
     */
    public void setCompressedLength(int compressedLength) {
        this.compressedLength = (char) compressedLength;
    }
        
    /**
     * setParent
     *
     * @param parent
     */
    public void setParent(ByteData parent) {
        this.parent = parent;
    }

    /**
     * setLeftChild
     *
     * @param leftChild
     */
    public void setLeftChild(ByteData leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * setRightChild
     *
     * @param rightChild
     */
    public void setRightChild(ByteData rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * setPrevious
     *
     * @param previous
     */
    public void setPrevious(ByteData previous) {
        this.previous = previous;
    }

    /**
     * setNext
     *
     * @param next
     */
    public void setNext(ByteData next) {
        this.next = next;
    }
}
