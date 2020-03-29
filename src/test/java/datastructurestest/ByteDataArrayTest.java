package datastructurestest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import algorithms.Huffman;
import datastructures.ByteData;
import datastructures.ByteDataArray;
import datastructures.ByteDataBinaryTree;
import datastructures.ByteDataLinkedList;
import datastructures.ByteList;
import fileio.ReadFile;
import fileio.WriteFile;

public class ByteDataArrayTest {
    ByteDataArray byteDataArray;
    ByteList readByteListCode;
    ByteList writeByteListCode;
    
    ByteList readByteListUncode;
    ByteList writeByteListUncode;
    
    @Before
    public void initialize() { 
        this.byteDataArray = new ByteDataArray();
        this.readByteListCode = new ByteList();
        this.writeByteListCode = new ByteList();
        this.readByteListUncode = new ByteList();
        this.writeByteListUncode = new ByteList();
        
        try {
            String string = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_BED";
            for (int i = 0; i < string.length(); i++) {
                this.readByteListCode.add((byte)string.charAt(i));
            }
            this.readByteListCode.add((byte)10);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void afterInitialization() {
        assertEquals(256, this.byteDataArray.getByteDataArray().length);
    }
    
    @Test
    public void countingOfBytesWorks() {
        byte[] bytes = new byte[7];
        bytes[0] = (byte)127;
        bytes[1] = (byte)15;
        bytes[2] = (byte)-128;
        bytes[3] = (byte)26;
        bytes[4] = (byte)15;
        bytes[5] = (byte)-128;
        bytes[6] = (byte)-128;
        
        this.byteDataArray.count(bytes);
        
        assertEquals(3, this.byteDataArray.getByteDataArray()[0].getCount());
        assertEquals(0, this.byteDataArray.getByteDataArray()[1].getCount());
        assertEquals(2, this.byteDataArray.getByteDataArray()[143].getCount());
        assertEquals(1, this.byteDataArray.getByteDataArray()[154].getCount());
        assertEquals(1, this.byteDataArray.getByteDataArray()[255].getCount());
    }
    
    @Test
    public void codingOfBytesWorks() {
        this.byteDataArray.count(this.readByteListCode.getBytesAsArray());
        this.byteDataArray.createLinkedList();        
        this.byteDataArray.createBinaryTreeFromLinkedList();
        this.byteDataArray.compress(this.readByteListCode, this.writeByteListCode);
        byte[] bytes = this.writeByteListCode.getBytesAsArray();        

        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[0]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[1]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[2]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[3]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[4]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[5]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[6]);
        assertEquals((byte)Integer.parseInt("01111010", 2), bytes[7]);
        assertEquals((byte)Integer.parseInt("00000110", 2), bytes[8]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[9]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[10]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[11]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[12]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[13]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[14]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[15]);
        assertEquals((byte)Integer.parseInt("00011100", 2), bytes[16]);
        assertEquals((byte)Integer.parseInt("00000101", 2), bytes[17]);
        assertEquals((byte)10, bytes[18]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[19]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[20]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[21]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[22]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[23]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[24]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[25]);
        assertEquals((byte)Integer.parseInt("00000010", 2), bytes[26]);
        assertEquals((byte)Integer.parseInt("00000010", 2), bytes[27]);
        assertEquals((byte)65, bytes[28]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[29]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[30]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[31]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[32]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[33]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[34]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[35]);
        assertEquals((byte)Integer.parseInt("00001111", 2), bytes[36]);
        assertEquals((byte)Integer.parseInt("00000100", 2), bytes[37]);
        assertEquals((byte)66, bytes[38]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[39]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[40]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[41]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[42]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[43]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[44]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[45]);
        assertEquals((byte)Integer.parseInt("00011101", 2), bytes[46]);
        assertEquals((byte)Integer.parseInt("00000101", 2), bytes[47]);
        assertEquals((byte)67, bytes[48]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[49]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[50]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[51]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[52]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[53]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[54]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[55]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[56]);
        assertEquals((byte)Integer.parseInt("00000010", 2), bytes[57]);
        assertEquals((byte)68, bytes[58]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[59]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[60]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[61]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[62]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[63]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[64]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[65]);
        assertEquals((byte)Integer.parseInt("00000110", 2), bytes[66]);
        assertEquals((byte)Integer.parseInt("00000011", 2), bytes[67]);
        assertEquals((byte)69, bytes[68]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[69]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[70]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[71]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[72]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[73]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[74]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[75]);
        assertEquals((byte)Integer.parseInt("00000001", 2), bytes[76]);
        assertEquals((byte)Integer.parseInt("00000010", 2), bytes[77]);
        assertEquals((byte)95, bytes[78]);
        
        assertEquals((byte)Integer.parseInt("10010011", 2), bytes[79]);
        assertEquals((byte)Integer.parseInt("01000010", 2), bytes[80]);
        assertEquals((byte)Integer.parseInt("01000011", 2), bytes[81]);
        assertEquals((byte)Integer.parseInt("11011100", 2), bytes[82]);
        assertEquals((byte)Integer.parseInt("01100001", 2), bytes[83]);
        
        assertEquals((byte)Integer.parseInt("10011111", 2), bytes[84]);
        assertEquals((byte)Integer.parseInt("10000111", 2), bytes[85]);
        assertEquals((byte)Integer.parseInt("11101111", 2), bytes[86]);
        assertEquals((byte)Integer.parseInt("11001100", 2), bytes[87]);
        assertEquals((byte)Integer.parseInt("11111110", 2), bytes[88]);
        
        assertEquals((byte)Integer.parseInt("10001100", 2), bytes[89]);
        assertEquals((byte)Integer.parseInt("00110111", 2), bytes[90]);
        assertEquals((byte)Integer.parseInt("11011101", 2), bytes[91]);
        assertEquals((byte)Integer.parseInt("10011111", 2), bytes[92]);
        assertEquals((byte)Integer.parseInt("11000111", 2), bytes[93]);
        assertEquals((byte)Integer.parseInt("00000000", 2), bytes[94]);
    }
    
    @Test
    public void uncodingWorks() {
        try {
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("01111010", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000110", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00011100", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000101", 2));
            this.readByteListUncode.add((byte)10);
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000010", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000010", 2));
            this.readByteListUncode.add((byte)65);
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00001111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000100", 2));
            this.readByteListUncode.add((byte)66);
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00011101", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000101", 2));
            this.readByteListUncode.add((byte)67);
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000010", 2));
            this.readByteListUncode.add((byte)68);
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000110", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000011", 2));
            this.readByteListUncode.add((byte)69);
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000001", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000010", 2));
            this.readByteListUncode.add((byte)95);

            this.readByteListUncode.add((byte)Integer.parseInt("10010011", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("01000010", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("01000011", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("11011100", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("01100001", 2));

            this.readByteListUncode.add((byte)Integer.parseInt("10011111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("10000111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("11101111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("11001100", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("11111110", 2));

            this.readByteListUncode.add((byte)Integer.parseInt("10001100", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00110111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("11011101", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("10011111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("11000111", 2));
            this.readByteListUncode.add((byte)Integer.parseInt("00000000", 2));
            
            this.byteDataArray.readHeader(this.readByteListUncode);
            this.byteDataArray.createBinaryTreeFromBinaryCodedCodes();
            this.byteDataArray.uncompress(this.readByteListUncode, this.writeByteListUncode);
            
            assertEquals((byte)10, this.writeByteListUncode.get(this.writeByteListUncode.size() - 1));
            
            String uncoded = "";
            for (int i = 0; i < this.writeByteListUncode.size() - 1; i++) {
                uncoded += (char)this.writeByteListUncode.get(i);
            }
            String string = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_BED";
            
            assertEquals(string, uncoded);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
