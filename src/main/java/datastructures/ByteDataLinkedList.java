package datastructures;

/**
 * Method to keep ByteData objects in the order in the linked list.
 */
public class ByteDataLinkedList {
    private ByteData first;
    private ByteData last;
    private ByteData iterator;

    /**
     * Constructor.
     */
    public ByteDataLinkedList() {
        this.first = new ByteData((byte) 0);
        this.last = new ByteData((byte) 0);
        this.first.setNext(this.last);
        this.last.setPrevious(this.first);
    }
    
    /**
     * Method to set iterator to the start.
     */
    public void startIteration() {
        this.iterator = this.first;
    }
    
    /**
     * Method to get next object in the linked list and increase iterator by one.
     *
     * @return      Next object in the linked list
     */
    public ByteData nextObject() {
        if (this.iterator.getNext() == this.last) {            
            return null;
        } else {
            this.iterator = this.iterator.getNext();
            return this.iterator;
        }
    }
    
    /**
     * Method to check if the linked is near last value.
     *
     * @return      True if next value can be read, false if not.
     */
    public ByteData checkObject() {
        if (this.iterator.getNext() == this.last || this.iterator.getNext().getNext() == this.last) {            
            return null;
        } else {
            return this.iterator;
        }
    }
    
    /**
     * getFirst
     *
     * @return
     */
    public ByteData getFirst() {
        return this.first;
    }
    
    /**
     * getLast
     *
     * @return
     */
    public ByteData getLast() {
        return this.last;
    }
        
    /**
     * Adds new object to the right place in the linked list according to the count value.
     *
     * @param newByteData       New object
     */
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
    
    /**
     * Adds array of the new objects to the right place in the linked list according to the count value.
     *
     * @param byteDatas         Array of the new objects
     */
    public void addArray(ByteData[] byteDatas) {
        for (int i = 0; i < byteDatas.length; i++) {
            if (byteDatas[i].getCount() > 0) {
                add(byteDatas[i]);
            }
        }
    }
    
//    private void printFirstToLast() {
//        ByteData current = this.first.getNext();
//        while (current != this.last) {
//            System.out.println("Code: " + current.getNormalChar() + ", Number: " + current.getCount());
//            current = current.getNext();
//        }
//    }
//    
//    private void printLastToFirst() {
//        ByteData current = this.last.getPrevious();
//        while (current != this.first) {
//            System.out.println("Code: " + current.getNormalChar() + ", Number: " + current.getCount());
//            current = current.getPrevious();
//        }
//    }
//    
//    public void printLinkedList() {
//        System.out.println("**");
//        printFirstToLast();
//        System.out.println("**");
//        printLastToFirst();
//        System.out.println("**"); 
//    }
}
