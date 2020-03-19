package fileio;

import java.io.*;

import datastructures.ByteList;

/**
 * Class to read any file.
 */
public class ReadFile {
    private final int maxSizeOfByteArray = 4096;

    /**
     * Constructor.
     */
    public ReadFile() {
    }
    
    /**
     * Method to check if file exists.
     * 
     * @param fileName  File which is checked if exists
     * @return          True if file exists, false if not
     */
    public boolean checkIfFileExists(String fileName) {
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) { 
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to read file to the ByteList.
     * 
     * @param fileName      File to be read
     * @param byteList      ByteList where file content is written
     * @throws Exception    If reading fails
     */
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
                if (alreadyRead + maxSizeOfByteArray <= fileSize) {
                    buffer = new byte[maxSizeOfByteArray];
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
            System.out.println(exception);
            throw new Exception("File reading not working or file not exists!");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new Exception("File reading not working!");
        }
    }
    
//    public void printFileAsCodes(String fileName) throws Exception {
//        byte[] buffer;
//        int alreadyRead = 0;
//        long fileSize = new File(fileName).length();
//
//        if (fileSize > 536870912) {
//            throw new Exception("Too big file!");
//        }
//
//        try (
//                InputStream inputStream = new FileInputStream(fileName);
//                ) {
//            System.out.println(" *** File content:  ***");
//            while (true) {
//                if (alreadyRead + maxSizeOfByteArray <= fileSize) {
//                    buffer = new byte[maxSizeOfByteArray];
//                } else {
//                    buffer = new byte[(int) fileSize - alreadyRead];
//                }
//                if (inputStream.read(buffer) == -1 || buffer.length == 0) {
//                    break;
//                }
//                for (int i = 0; i < buffer.length; i++) {
//                    System.out.println((int)buffer[i]);
//                }
//                alreadyRead += buffer.length;
//            }
//            System.out.println(" ***  ***");
//        } catch (IOException exception) {
//            exception.printStackTrace();
//            System.exit(0);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            System.exit(0);
//        }
//    }
}