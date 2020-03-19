package datastructurestest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import datastructures.ByteData;
import datastructures.ByteDataBinaryTree;
import datastructures.ByteDataLinkedList;

public class ByteDataBinaryTreeTest {
    ByteDataBinaryTree byteDataBinaryTree;
    ByteDataLinkedList byteDataLinkedList;
    
    ByteData byteData2 = new ByteData((byte)2);
    ByteData byteData4 = new ByteData((byte)4);
    ByteData byteData3 = new ByteData((byte)3);
    ByteData byteData1 = new ByteData((byte)1);
    
    ByteData[] byteDatas;
    
    @Before
    public void initialize() {        
        byteData2.setCount(2);        
        byteData4.setCount(4);        
        byteData3.setCount(3);        
        byteData1.setCount(1);
        byteDatas = new ByteData[4];
        byteDatas[1] = byteData2;
        byteDatas[3] = byteData4;
        byteDatas[2] = byteData3;
        byteDatas[0] = byteData1;
        
        this.byteDataLinkedList = new ByteDataLinkedList();
        this.byteDataLinkedList.addArray(byteDatas);
        
        this.byteDataBinaryTree = new ByteDataBinaryTree();
    }
    
    @Test
    public void afterInitialization() {
        assertEquals(null, this.byteDataBinaryTree.getRoot());
    }
    
    @Test
    public void binaryTreeGenerationFromTheLinkedListWorks() {
        assertEquals(null, this.byteDataBinaryTree.getRoot());
        this.byteDataBinaryTree.createBinaryTreeFromLinkedList(this.byteDataLinkedList);
        ByteData root = this.byteDataBinaryTree.getRoot();
        assertTrue(root != null);
        assertEquals(10, root.getCount());
        assertEquals(byteData4, root.getLeftChild());
        assertEquals(root, root.getLeftChild().getParent());
        assertEquals(6, root.getRightChild().getCount());
        assertEquals(root, root.getRightChild().getParent());
        assertEquals(byteData3, root.getRightChild().getLeftChild());        
        assertEquals(3, root.getRightChild().getRightChild().getCount());
        assertEquals(byteData1, root.getRightChild().getRightChild().getLeftChild());
        assertEquals(byteData2, root.getRightChild().getRightChild().getRightChild());
        assertEquals(root, root.getRightChild().getRightChild().getRightChild().getParent().getParent().getParent());
    }
    
    @Test
    public void compressionCodeGenerationWorks() {
        this.byteDataBinaryTree.createBinaryTreeFromLinkedList(this.byteDataLinkedList);
        this.byteDataBinaryTree.saveCodesForTree();
        
        ByteData root = this.byteDataBinaryTree.getRoot();
        assertEquals(0L, root.getLeftChild().getCompressedChar());
        assertEquals(1, (int)root.getLeftChild().getCompressedLength());
        assertEquals(2L, root.getRightChild().getLeftChild().getCompressedChar());
        assertEquals(2, (int)root.getRightChild().getLeftChild().getCompressedLength());
        assertEquals(6L, root.getRightChild().getRightChild().getLeftChild().getCompressedChar());
        assertEquals(3, (int)root.getRightChild().getRightChild().getLeftChild().getCompressedLength());
        assertEquals(7L, root.getRightChild().getRightChild().getRightChild().getCompressedChar());
        assertEquals(3, (int)root.getRightChild().getRightChild().getRightChild().getCompressedLength());
    }
    
    @Test
    public void binaryTreeGenerationFromTheBinaryCodedCodesWorks() {
        assertEquals(null, this.byteDataBinaryTree.getRoot());
        byteData2.setCompressedChar(7L);
        byteData2.setCompressedLength(3);
        byteData4.setCompressedChar(0L);
        byteData4.setCompressedLength(1);
        byteData3.setCompressedChar(2L);  
        byteData3.setCompressedLength(2);
        byteData1.setCompressedChar(6L);
        byteData1.setCompressedLength(3);
        
        ByteData[] byteDatas256 = new ByteData[256];
        for (int i = 0; i < 256; i++) {
            byteDatas256[i] = new ByteData((byte)(i - 128));
        }
        byteDatas256[1] = byteData2;
        byteDatas256[3] = byteData4;
        byteDatas256[2] = byteData3;
        byteDatas256[0] = byteData1;
        
        this.byteDataBinaryTree.createBinaryTreeFromBinaryCodedCodes(byteDatas256);

        ByteData root = this.byteDataBinaryTree.getRoot();
        assertTrue(root != null);
        assertEquals(byteData4, root.getLeftChild());
        assertEquals(root, root.getLeftChild().getParent());
        assertEquals(root, root.getRightChild().getParent());
        assertEquals(byteData3, root.getRightChild().getLeftChild());
        assertEquals(byteData1, root.getRightChild().getRightChild().getLeftChild());
        assertEquals(byteData2, root.getRightChild().getRightChild().getRightChild());
        assertEquals(root, root.getRightChild().getRightChild().getRightChild().getParent().getParent().getParent());
    }
}
