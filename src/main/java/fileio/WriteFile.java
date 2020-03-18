package fileio;

import java.io.*;

import datastructures.ByteList;

public class WriteFile {

    private final int SIZE = 4096;

    public WriteFile() {
    }

    public void writeFile(String fileName, ByteList byteList) throws Exception {
        byte[] buffer;
        int alreadyWritten = 0;
        int fileSize = byteList.size();

        try (
                OutputStream outputStream = new FileOutputStream(fileName);
            ) {
            while (true) {
                if (alreadyWritten + SIZE <= fileSize) {
                    buffer = new byte[SIZE];
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
