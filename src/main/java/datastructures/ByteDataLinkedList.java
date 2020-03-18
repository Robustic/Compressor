package datastructures;

public class ByteDataLinkedList {
    private ByteData first;
    private ByteData last;
    private ByteData iterator;

    public ByteDataLinkedList() {
        this.first = new ByteData((byte)0);
        this.last = new ByteData((byte)0);
        this.first.setNext(this.last);
        this.last.setPrevious(this.first);
    }
    
    public void startIteration() {
        this.iterator = this.first;
    }
    
    public ByteData nextIteration() {
        if (this.iterator.getNext() == this.last) {            
            return null;
        } else {
            this.iterator = this.iterator.getNext();
            return this.iterator;
        }
    }
    
    public ByteData checkIteration() {
        if (this.iterator.getNext().getNext() == this.last) {            
            return null;
        } else {
            return this.iterator;
        }
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
            if (newByteData.getCount() < current.getCount() || current == this.last) {
                newByteData.setPrevious(current.getPrevious());
                newByteData.setNext(current);
                newByteData.getPrevious().setNext(newByteData);
                newByteData.getNext().setPrevious(newByteData);
                break;
            }
            current = current.getNext();
        }
    }
    
    public void addArray(ByteData[] byteDatas) {
        for (int i = 0; i < byteDatas.length; i++) {
            if (byteDatas[i].getCount() > 0) {
                add(byteDatas[i]);
            }
        }
    }
    
    private void printFirstToLast() {
        ByteData current = this.first.getNext();
        while (current != this.last) {
            System.out.println("Code: " + current.getNormalChar() + ", Number: " + current.getCount());
            current = current.getNext();
        }
    }
    
    private void printLastToFirst() {
        ByteData current = this.last.getPrevious();
        while (current != this.first) {
            System.out.println("Code: " + current.getNormalChar() + ", Number: " + current.getCount());
            current = current.getPrevious();
        }
    }
    
    public void printLinkedList() {
        System.out.println("**");
        printFirstToLast();
        System.out.println("**");
        printLastToFirst();
        System.out.println("**"); 
    }
}
