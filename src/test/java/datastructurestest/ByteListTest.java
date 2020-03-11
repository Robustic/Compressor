package datastructurestest;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import datastructures.ByteList;

public class ByteListTest {

    ByteList list;

    @Before
    public void initialize() {
        this.list = new ByteList();
    }

    @Test
    public void initializedIsEmpty() {
        assertEquals(0, this.list.size());
    }

    @Test
    public void newBytesCanBeAdded() {
        try {
            this.list.add((byte) 'a');
            this.list.add((byte) 'b');
            this.list.add((byte) 'c');
            assertEquals(3, this.list.size());
            assertEquals('a', this.list.get(0));
            assertEquals('b', this.list.get(1));
            assertEquals('c', this.list.get(2));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void negativeIndexNotAllowed() {
        try {
            this.list.add((byte) 'a');
            this.list.add((byte) 'b');
            this.list.add((byte) 'c');
            this.list.get(-1);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void tooBigIndexNotAllowed() {
        try {
            this.list.add((byte) 'a');
            this.list.add((byte) 'b');
            this.list.add((byte) 'c');
            this.list.get(3);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void givenByteListIsOk() {
        try {
            this.list.add((byte) 'a');
            this.list.add((byte) 'b');
            this.list.add((byte) 'c');
            byte[] returnedList = this.list.getBytesAsArray();
            assertEquals(3, returnedList.length);
            assertEquals('a', returnedList[0]);
            assertEquals('b', returnedList[1]);
            assertEquals('c', returnedList[2]);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void combineBytesOk() {
        try {
            byte[] firstBytes = "abc".getBytes();
            byte[] secondBytes = "defghi".getBytes();
            this.list.combine(firstBytes);
            assertEquals(3, list.size());
            this.list.combine(secondBytes);
            assertEquals(9, list.size());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}
