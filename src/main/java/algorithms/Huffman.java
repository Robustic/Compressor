package algorithms;

import datastructures.ByteList;
import datastructures.ByteDataArray;
import datastructures.ByteDataLinkedList;
import fileio.ReadFile;
import fileio.WriteFile;


public class Huffman {
    
    public void readFile(String fileName, ByteList byteList) {
        ReadFile readFile = new ReadFile();
        // WriteFile writeFile = new WriteFile();
        
        try {
            readFile.readFile(fileName, byteList);
        } catch (Exception e) {
            System.out.println("Error when reading file.");
        }
    }
    
    public void code(String fileName) {
        ByteList byteList = new ByteList();
        
        readFile(fileName, byteList);
        
        ByteDataArray byteDataArray = new ByteDataArray();
        byteDataArray.count(byteList.getBytesAsArray());
        byteDataArray.createLinkedList();
        
        
    }
}
