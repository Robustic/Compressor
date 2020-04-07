package datastructurestest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import datastructures.ByteData;

public class ByteDataTest {
    ByteData byteData;
    
    ByteData parentTest;
    ByteData leftChildTest;
    ByteData rightChildTest;
    
    ByteData previousTest;
    ByteData nextTest;

    @Before
    public void initialize() {
        this.byteData = new ByteData((byte)75);
    }
    
    @Test
    public void afterInitialization() {
        try {
            assertEquals(75, (int)this.byteData.getNormalChar());
            assertEquals(-1L, this.byteData.getCompressedChar());
            assertEquals(0, (int)this.byteData.getCompressedLength());
            assertEquals(0, this.byteData.getCount());
        } catch (Exception exception) {
            assertTrue(false);
        }
    }
    
    @Test
    public void setAndGetWorks() {
        try {
            byteData.setNormalChar((byte)112);
            byteData.setCompressedChar(11235346L);
            byteData.setCompressedLength(114);
            byteData.setCount(114325);
            
            byteData.setParent(this.parentTest);
            byteData.setLeftChild(this.leftChildTest);
            byteData.setRightChild(this.rightChildTest);
            
            byteData.setPrevious(this.previousTest);
            byteData.setNext(this.nextTest);
            
            assertEquals(112, (int)this.byteData.getNormalChar());
            assertEquals(11235346L, this.byteData.getCompressedChar());
            assertEquals(114, (int)this.byteData.getCompressedLength());
            assertEquals(114325, this.byteData.getCount());
            this.byteData.growCount();
            assertEquals(114326, this.byteData.getCount());
            
            assertEquals(this.parentTest, this.byteData.getParent());
            assertEquals(this.leftChildTest, this.byteData.getLeftChild());
            assertEquals(this.rightChildTest, this.byteData.getRightChild());
            
            assertEquals(this.previousTest, this.byteData.getPrevious());
            assertEquals(this.nextTest, this.byteData.getNext());            
        } catch (Exception exception) {
            assertTrue(false);
        }
    }
}
