package fileio;

import java.io.*;

import datastructures.ByteList;

/**
 * Class to write to file.
 */
public class WriteFile {

    private final int maxSizeOfByteArray = 4096;

    /**
     * Constructor.
     */
    public WriteFile() {
    }

    /**
     * Method to write ByteList content to the file.
     * Old content of the file will be overwritten.
     * 
     * @param fileName      File where to write
     * @param byteList      ByteList which is written to the file
     * @throws Exception    If writing fails
     */
    public void writeFile(String fileName, ByteList byteList) throws Exception {
        byte[] buffer;
        int alreadyWritten = 0;
        int fileSize = byteList.size();

        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            while (true) {
                if (alreadyWritten + maxSizeOfByteArray <= fileSize) {
                    buffer = new byte[maxSizeOfByteArray];
                } else {
                    buffer = new byte[fileSize - alreadyWritten];
                }
                if (buffer.length == 0) {
                    break;
                }
                byteList.getPart(alreadyWritten, buffer);
                outputStream.write(buffer);
                alreadyWritten += buffer.length;
            }
        } catch (IOException exception) {
            System.out.println(exception);
            throw new Exception("File writing not working!");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new Exception("File writing not working!");
        }
    }    
}
