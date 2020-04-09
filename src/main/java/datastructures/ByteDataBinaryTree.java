package datastructures;

/**
 * Class which capsulate binary tree structure.
 */
public class ByteDataBinaryTree {
    private ByteData root;

    /**
     * Constructor.
     */
    public ByteDataBinaryTree() {
    }

    /**
     * getRoot
     *
     * @return
     */
    public ByteData getRoot() {
        return root;
    }
    
    /**
     * Creates binary tree from the linked list.
     *
     * @param linkedList    Orderer ByteData as linked list
     */
    public void createBinaryTreeFromLinkedList(ByteDataLinkedList linkedList) {
        linkedList.startIteration();
        while (linkedList.checkObject() != null) {
            this.root = new ByteData((byte) 0);
            ByteData left = linkedList.nextObject();
            ByteData right = linkedList.nextObject();
            this.root.setLeftChild(left);
            this.root.setRightChild(right);
            left.setParent(this.root);
            right.setParent(this.root);
            this.root.setCount(left.getCount() + right.getCount());
            linkedList.add(this.root);
        }        
    }
    
    private ByteData createLeafForTheCharacter(long compressed, int k, ByteData current) {
        if ((compressed & (1L << k)) == 0) {
            if (current.getLeftChild() == null) {
                ByteData newChild = new ByteData((byte) 0);
                newChild.setParent(current);
                current.setLeftChild(newChild);
                current = newChild;
            } else {
                current = current.getLeftChild();
            }
        } else {
            if (current.getRightChild() == null) {
                ByteData newChild = new ByteData((byte) 0);
                newChild.setParent(current);
                current.setRightChild(newChild);
                current = newChild;
            } else {
                current = current.getRightChild();
            }
        }
        return current;
    }
    
    /**
     * Creates binary tree from the ByteData binary coded labels.
     *
     * @param byteDatas     ByteDatas in the array
     */
    public void createBinaryTreeFromBinaryCodedCodes(ByteData[] byteDatas) {
        this.root = new ByteData((byte) 0);
        for (int i = 0; i < 256; i++) {
            if (byteDatas[i].getCompressedLength() > 0) {
                ByteData current = this.root;
                int length = (byteDatas[i].getCompressedLength() & 0xFF);
                long compressed = byteDatas[i].getCompressedChar();
                for (int k = length - 1; k >= 0; k--) {
                    current = createLeafForTheCharacter(compressed, k, current);
                }
                byteDatas[i].setParent(current.getParent());
                if (current.getParent().getLeftChild() == current) {
                    byteDatas[i].getParent().setLeftChild(byteDatas[i]);
                } else {
                    byteDatas[i].getParent().setRightChild(byteDatas[i]);
                }
            }
        }
    }
    
    /**
     * Method to define binary coded labels for the each ByteData as leaf.
     */
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
}
