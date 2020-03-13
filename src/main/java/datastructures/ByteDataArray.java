package datastructures;

public class ByteDataArray {
    private ByteData[] byteDatas;
    private ByteDataLinkedList byteDataLinkedList;
    private ByteDataBinaryTree byteDataBinaryTree;

    public ByteDataArray() {
        this.byteDatas = new ByteData[256];
        for (int i = 0; i < 256; i++) {
            this.byteDatas[i] = new ByteData((byte)(i - 128));
        }
        this.byteDataLinkedList = new ByteDataLinkedList();
        this.byteDataBinaryTree = new ByteDataBinaryTree();
    }

    public ByteData[] getByteDataArray() {
        return byteDatas;
    }
    
    public void count(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            this.byteDatas[bytes[i] + 128].growNumber();
        }        
    }
    
    public void createLinkedList() {
        this.byteDataLinkedList.addArray(getByteDataArray());                
            System.out.println("**");
            this.byteDataLinkedList.printFirstToLast();
            System.out.println("**");
            this.byteDataLinkedList.printLastToFirst();
            System.out.println("**");        
    }
}
