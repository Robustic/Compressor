package datastructures;

public class ByteList {

    private byte[] bytes;
    private int pointer;
    private int readingPointer;

    public ByteList() {
        this.bytes = new byte[2]; // new byte[1048576];
        this.pointer = 0;
        this.readingPointer = 0;
    }
    
    public void startReading() {
        this.readingPointer = 0;
    }
    
    public boolean checkNext() {
        if (readingPointer >= pointer) {
            return false;
        } else {
            return true;
        }
    }
    
    public byte readNext() {        
        this.readingPointer++;
        return bytes[this.readingPointer - 1];        
    }

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
    
    public void getPart(int start, byte[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = this.bytes[i + start];
        }
    }
    
    public void combine(byte[] secondBytes) throws Exception {
        int totalLength = this.pointer + secondBytes.length;
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
        for (int i = 0; i < secondBytes.length; i++) {
            this.bytes[this.pointer + i] = secondBytes[i];
        }        
        this.pointer = totalLength;
    }

    public byte get(int i) throws Exception {
        if (i < 0 || i >= this.pointer) {
            throw new Exception("Index have to be inside list! Index " + i + " is outside list. List length is " + this.pointer + ".");
        }
        return this.bytes[i];
    }
    
    public void set(int i, byte byteToSet) throws Exception {
        if (i < 0 || i >= this.pointer) {
            throw new Exception("Index have to be inside list! Index " + i + " is outside list. List length is " + this.pointer + ".");
        }
        this.bytes[i] = byteToSet;
    }

    public int size() {
        return this.pointer;
    }

    public byte[] getBytesAsArray() {
        byte[] bytesList = new byte[this.pointer];
        for (int i = 0; i < this.pointer; i++) {
            bytesList[i] = this.bytes[i];
        }
        return bytesList;
    }
    
    public void addEmpties(int count) throws Exception {
        if (this.pointer + count > 1073741824) {
            throw new Exception("Too many bytes!");
        }
        this.combine(new byte[count]);
    }
    
//    public void printByteList() {
//        for (int i = 0;  i < this.pointer; i++) {
//            System.out.print(i + ":" + ((int)this.bytes[i]) + ": ");
//            System.out.println(String.format("%8s", Integer.toBinaryString(this.bytes[i] & 0xFF)).replace(' ', '0'));
//        }
//    }
}
