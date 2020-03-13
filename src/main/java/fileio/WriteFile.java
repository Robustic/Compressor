package fileio;

import java.io.*;

import datastructures.ByteList;

public class WriteFile {

    private final int SIZE = 5; // 4096;

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
                System.out.println("buffer size: " + buffer.length);
                if (buffer.length == 0) {
                    break;
                }
                byteList.getPart(alreadyWritten, buffer);
                outputStream.write(buffer);
                alreadyWritten += buffer.length;
            }

        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(0);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }
}
