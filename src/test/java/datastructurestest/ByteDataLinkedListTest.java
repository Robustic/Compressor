package datastructurestest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import datastructures.ByteData;
import datastructures.ByteDataLinkedList;

public class ByteDataLinkedListTest {
    ByteData byteData2;
    ByteData byteData4;
    ByteData byteData0;
    ByteData byteData3;
    ByteData byteData1;    
    
    ByteDataLinkedList byteDataLinkedList;
    
    @Before
    public void initialize() {
        this.byteData2 = new ByteData((byte)2);
        this.byteData2.setCount(2);
        this.byteData4 = new ByteData((byte)4);
        this.byteData4.setCount(4);
        this.byteData0 = new ByteData((byte)10);
        this.byteData3 = new ByteData((byte)3);
        this.byteData3.setCount(3);
        this.byteData1 = new ByteData((byte)1);
        this.byteData1.setCount(1);
        
        this.byteDataLinkedList = new ByteDataLinkedList();
    }
    
    @Test
    public void afterInitialization() {
        byteDataLinkedList.startIteration();
        assertEquals(null, byteDataLinkedList.checkObject());
    }
    
    @Test
    public void nextByteDataIsReturnedOk() {
        this.byteDataLinkedList.add(this.byteData2);
        byteDataLinkedList.startIteration();
        assertEquals(null, byteDataLinkedList.checkObject());
        this.byteDataLinkedList.add(this.byteData4);
        byteDataLinkedList.startIteration();
        assertEquals(this.byteDataLinkedList.getFirst(), byteDataLinkedList.checkObject());
        
        ByteData[] byteDataList = new ByteData[3];
        byteDataList[0] = this.byteData3;
        byteDataList[1] = this.byteData0;
        byteDataList[2] = this.byteData1;
        this.byteDataLinkedList.addArray(byteDataList);
        byteDataLinkedList.startIteration();
        
        ByteData current = byteDataLinkedList.nextObject();
        assertEquals(this.byteData1, current);
        assertEquals(byteDataLinkedList.getFirst(), current.getPrevious());
        assertEquals(this.byteData2, current.getNext());
        
        current = byteDataLinkedList.nextObject();
        assertEquals(this.byteData2, current);
        assertEquals(this.byteData1, current.getPrevious());
        assertEquals(this.byteData3, current.getNext());
        
        current = byteDataLinkedList.nextObject();
        assertEquals(this.byteData3, current);
        assertEquals(this.byteData2, current.getPrevious());
        assertEquals(this.byteData4, current.getNext());
        
        current = byteDataLinkedList.nextObject();
        assertEquals(this.byteData4, current);
        assertEquals(this.byteData3, current.getPrevious());
        assertEquals(byteDataLinkedList.getLast(), current.getNext());
        
        current = byteDataLinkedList.nextObject();
        assertEquals(null, current);
    }
}
