package algorithmstest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import algorithms.LempelZivWelch;
import datastructures.ByteList;

public class LempelZivWelchTest {
    LempelZivWelch lempelZivWelch;
    
    public static byte[] stringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = (byte) s.charAt(i);
        }
        return data;
    }
    
    @Before
    public void initialize() {
        this.lempelZivWelch = new LempelZivWelch();
    }
    
    @Test
    public void encodingAlgorithmWorksForSimpleCase() {
        try {
            byte[] newBytes = stringToByteArray("ABABABACB");
            ByteList byteList = new ByteList();
            byteList.combine(newBytes);
            ByteList coded = lempelZivWelch.code(byteList);
            assertEquals(12, coded.size());
            assertEquals(0, (int) coded.get(0));
            assertEquals(65, (int) coded.get(1));
            assertEquals(0, (int) coded.get(2));
            assertEquals(66, (int) coded.get(3));
            assertEquals(1, (int) coded.get(4));
            assertEquals(0, (int) coded.get(5));
            assertEquals(1, (int) coded.get(6));
            assertEquals(2, (int) coded.get(7));
            assertEquals(0, (int) coded.get(8));
            assertEquals(67, (int) coded.get(9));
            assertEquals(0, (int) coded.get(10));
            assertEquals(66, (int) coded.get(11));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void decodingAlgorithmWorksForSimpleCase() {
        try {
//            byte[] newBytes = stringToByteArray("ABABABACB");
//            ByteList byteList = new ByteList();
//            byteList.combine(newBytes);
//            ByteList coded = lempelZivWelch.code(byteList);
//            assertEquals(12, coded.size());
//            assertEquals(0, (int) coded.get(0));
//            assertEquals(65, (int) coded.get(1));
//            assertEquals(0, (int) coded.get(2));
//            assertEquals(66, (int) coded.get(3));
//            assertEquals(1, (int) coded.get(4));
//            assertEquals(0, (int) coded.get(5));
//            assertEquals(1, (int) coded.get(6));
//            assertEquals(2, (int) coded.get(7));
//            assertEquals(0, (int) coded.get(8));
//            assertEquals(67, (int) coded.get(9));
//            assertEquals(0, (int) coded.get(10));
//            assertEquals(66, (int) coded.get(11));
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
