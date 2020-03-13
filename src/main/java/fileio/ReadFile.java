package fileio;

import java.io.*;

import datastructures.ByteList;

public class ReadFile {

    private final int SIZE = 5; // 4096;

    public ReadFile() {
    }

    public void readFile(String fileName, ByteList byteList) throws Exception {
        byte[] buffer;
        int alreadyRead = 0;
        long fileSize = new File(fileName).length();

        if (fileSize > 536870912) {
            throw new Exception("Too big file!");
        }

        try (
                InputStream inputStream = new FileInputStream(fileName);) {
            while (true) {
                if (alreadyRead + SIZE <= fileSize) {
                    buffer = new byte[SIZE];
                } else {
                    buffer = new byte[(int) fileSize - alreadyRead];
                }
                if (inputStream.read(buffer) == -1 || buffer.length == 0) {
                    break;
                }
                byteList.combine(buffer);
                alreadyRead += buffer.length;
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
