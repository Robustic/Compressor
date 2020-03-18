package datastructures;

public class ByteDataBinaryTree {
    private ByteData root;

    public ByteDataBinaryTree() {
    }

    public ByteData getRoot() {
        return root;
    }
    
    public void createBinaryTreeFromLinkedList(ByteDataLinkedList linkedList) {
        linkedList.startIteration();
        while (linkedList.checkIteration() != null) {
            this.root = new ByteData((byte)0);
            ByteData left = linkedList.nextIteration();
            ByteData right = linkedList.nextIteration();
            this.root.setLeftChild(left);
            this.root.setRightChild(right);
            left.setParent(this.root);
            right.setParent(this.root);
            this.root.setCount(left.getCount() + right.getCount());
            linkedList.add(this.root);
        }        
    }
    
    public void createBinaryTreeFromBinaryCodedCodes(ByteData[] byteDatas) {
        this.root = new ByteData((byte)0);
        for (int i = 0; i < 256; i++) {
            if (byteDatas[i].getCompressedLength() > 0) {
                ByteData current = this.root;
                int length = (byteDatas[i].getCompressedLength() & 0xFF);
                long compressed = byteDatas[i].getCompressedChar();
                for (int k = length - 1; k >= 0; k--) {
                    if ((compressed & (1L << k)) == 0) {
                        if (current.getLeftChild() == null) {
                            ByteData newChild = new ByteData((byte)0);
                            newChild.setParent(current);
                            current.setLeftChild(newChild);
                            current = newChild;
                        } else {
                            current = current.getLeftChild();
                        }
                    } else {
                        if (current.getRightChild() == null) {
                            ByteData newChild = new ByteData((byte)0);
                            newChild.setParent(current);
                            current.setRightChild(newChild);
                            current = newChild;
                        } else {
                            current = current.getRightChild();
                        }
                    }
                }                
                current.setNormalChar(byteDatas[i].getNormalChar());
                current.setCompressedChar(compressed);
                current.setCompressedLength((char)length);
            }
        }
    }
    
    public void saveCodesForTree() {
        saveCode(this.root, 0, 0);
    }
    
    private void saveCode(ByteData current, int level, long code) {       
        current.setCompressedChar(code);
        current.setCompressedLength(level);
        level += 1;
        if (current.getLeftChild() != null) {
            saveCode(current.getLeftChild(), level, code * 2);
        }
        if (current.getRightChild() != null) {
            saveCode(current.getRightChild(), level, code * 2 + 1);
        }
    }
    
//    public void printCodeTree() {
//        System.out.println("***");
//        printCode(this.root, 0, 0);
//        System.out.println("***");
//    }
//    
//    private void printCode(ByteData current, int level, long code) {        
//        for (int i = 0; i < level; i++) {
//            System.out.print("|-");
//        }
//        System.out.print(current.getNormalChar() + " length: " + (int)current.getCompressedLength()+ ", Binary: ");
//        for(int i = 0; i < level - Long.toBinaryString((long)code).length(); i++) {
//            System.out.print('0');
//        }
//        System.out.println(Long.toBinaryString((long)code));
//        level += 1;
//        if (current.getLeftChild() != null) {
//            printCode(current.getLeftChild(), level, code * 2);
//        }
//        if (current.getRightChild() != null) {
//            printCode(current.getRightChild(), level, code * 2 + 1);
//        }
//    }
    
//    public void printBinaryTree() {
//        System.out.println("***");
//        printNode(this.root, 0);
//        System.out.println("***");
//    }
//    
//    private void printNode(ByteData current, int level) {
//        level += 1;
//        for (int i = 0; i < level; i++) {
//            System.out.print("  ");
//        }
//        System.out.println(current.getNormalChar() + ": " + current.getCount());
//        if (current.getLeftChild() != null) {
//            printNode(current.getLeftChild(), level);
//        }
//        if (current.getRightChild() != null) {
//            printNode(current.getRightChild(), level);
//        }
//    }
}
