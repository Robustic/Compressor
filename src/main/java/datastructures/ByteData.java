package datastructures;

public class ByteData {    
    private byte oldCode;
    private int number;
    
    private ByteData parent;
    private ByteData leftChild;
    private ByteData rightChild;
    
    private ByteData previous;
    private ByteData next;

    public ByteData(byte oldCode) {
        this.oldCode = oldCode;
        this.number = 0;
    }
    
    public byte getOldCode() {
        return oldCode;
    }

    public int getNumber() {
        return number;
    }
    
    public void growNumber() {
        this.number++;
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
