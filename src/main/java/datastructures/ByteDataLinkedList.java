package datastructures;

public class ByteDataLinkedList {
    private ByteData first;
    private ByteData last;
    private int count;

    public ByteDataLinkedList() {
        this.count = 0;
        this.first = new ByteData((byte)0);
        this.last = new ByteData((byte)0);
        this.first.setNext(this.last);
        this.last.setPrevious(this.first);
    }
    
    public ByteData getFirst() {
        return this.first;
    }
    
    public ByteData getLast() {
        return this.last;
    }
        
    public void add(ByteData newByteData) {
        ByteData current = this.first;
        while (true) {
            if (newByteData.getNumber() < current.getNumber() || current == this.last) {
                newByteData.setPrevious(current.getPrevious());
                newByteData.setNext(current);
                newByteData.getPrevious().setNext(newByteData);
                newByteData.getNext().setPrevious(newByteData);
                this.count++;
                break;
            }
            current = current.getNext();
        }
    }
    
    public void addArray(ByteData[] byteDatas) {
        for (int i = 0; i < byteDatas.length; i++) {
            if (byteDatas[i].getNumber() > 0) {
                add(byteDatas[i]);
            }
        }
    }
    
    public void printFirstToLast() {
        ByteData current = this.first.getNext();
        while (current != this.last) {
            System.out.println("Code: " + current.getOldCode() + ", Number: " + current.getNumber());
            current = current.getNext();
        }
    }
    
    public void printLastToFirst() {
        ByteData current = this.last.getPrevious();
        while (current != this.first) {
            System.out.println("Code: " + current.getOldCode() + ", Number: " + current.getNumber());
            current = current.getPrevious();
        }
    }
}
