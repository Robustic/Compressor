package datastructures;

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
    
    public ByteData(byte normalChar) {
        this.normalChar = normalChar;
        this.compressedChar = -1;
        this.compressedLength = 0;        
        this.count = 0;
    }
    
    public byte getNormalChar() {
        return normalChar;
    }

    public long getCompressedChar() {
        return compressedChar;
    }

    public char getCompressedLength() {
        return this.compressedLength;
    }

    public int getCount() {
        return count;
    }
    
    public void growCount() {
        this.count++;
    }

    public ByteData getParent() {
        return parent;
    }

    public ByteData getLeftChild() {
        return leftChild;
    }

    public ByteData getRightChild() {
        return rightChild;
    }

    public ByteData getPrevious() {
        return previous;
    }

    public ByteData getNext() {
        return next;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void setNormalChar(byte normalChar) {
        this.normalChar = normalChar;
    }

    public void setCompressedChar(long compressedChar) {
        this.compressedChar = compressedChar;
    }
    
    public void setCompressedLength(int compressedLength) {
        this.compressedLength = (char)compressedLength;
    }
        
    public void setParent(ByteData parent) {
        this.parent = parent;
    }

    public void setLeftChild(ByteData leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(ByteData rightChild) {
        this.rightChild = rightChild;
    }

    public void setPrevious(ByteData previous) {
        this.previous = previous;
    }

    public void setNext(ByteData next) {
        this.next = next;
    }

       
}
