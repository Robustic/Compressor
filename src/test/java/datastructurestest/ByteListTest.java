package datastructurestest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void readingByteByByteWorks() {
        try {
            byte[] firstBytes = "abc".getBytes();
            this.list.combine(firstBytes);
            this.list.startReading();            
            assertEquals((byte)'a', this.list.readNext());
            assertEquals((byte)'b', this.list.readNext());
            this.list.startReading();
            assertTrue(this.list.checkNext());
            assertEquals((byte)'a', this.list.readNext());
            assertTrue(this.list.checkNext());
            assertEquals((byte)'b', this.list.readNext());
            assertTrue(this.list.checkNext());
            assertEquals((byte)'c', this.list.readNext());
            assertFalse(this.list.checkNext());
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void byteInIndexCanBeSet() {
        try {
            byte[] firstBytes = "abc".getBytes();
            this.list.combine(firstBytes);
            assertEquals('b', this.list.get(1));
            this.list.set(1, (byte)77);
            assertEquals('M', this.list.get(1));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void tooSmallIndexCauseException() {
        try {
            byte[] firstBytes = "abc".getBytes();
            this.list.combine(firstBytes);
            this.list.set(-1, (byte)77);
        } catch (Exception exception) {
            assertEquals("Index have to be inside list! Index -1 is outside list. List length is 3.", exception.getMessage());
        }
    }
    
    @Test
    public void tooLargeIndexCauseException() {
        try {
            byte[] firstBytes = "abc".getBytes();
            this.list.combine(firstBytes);
            this.list.set(3, (byte)77);
        } catch (Exception exception) {
            assertEquals("Index have to be inside list! Index 3 is outside list. List length is 3.", exception.getMessage());
        }
    }
    
    @Test
    public void addingEmptyBytesWorks() {
        try {
            byte[] firstBytes = "abc".getBytes();
            this.list.combine(firstBytes);
            assertEquals(3, list.size());
            this.list.addEmpties(2);
            assertEquals(5, list.size());
            assertEquals('a', this.list.get(0));
            assertEquals('b', this.list.get(1));
            assertEquals('c', this.list.get(2));
            assertEquals(0, this.list.get(3));
            assertEquals(0, this.list.get(4));
        } catch (Exception exception) {
            assertEquals("Index have to be inside list! Index 3 is outside list. List length is 3.", exception.getMessage());
        }
    }
}
