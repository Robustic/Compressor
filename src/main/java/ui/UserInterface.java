package ui;

import algorithms.Huffman;
import algorithms.LempelZivWelch;
import fileio.ReadFile;
import userio.MessagePrinter;

/**
 * Class to work as user interface.
 */
public class UserInterface {
    private MessagePrinter printer;

    /**
     * Constructor.
     */
    public UserInterface(MessagePrinter messagePrinter) {
        this.printer = messagePrinter;
    }    
    
    private void huffmanSelected(String method, String readFileName) throws Exception {
        Huffman huffman = new Huffman(this.printer);                    
        if (method.equals("comp")) {
            this.printer.println("Compressing with Huffman.");
            this.printer.println("Input file name: '" + readFileName + "'");
            huffman.compress(readFileName, readFileName + ".huffman");
            this.printer.println("Compressed file name: '" + readFileName + ".huffman'");

        } else if (method.equals("uncomp")) {
            if (readFileName.length() > 8 && readFileName.endsWith(".huffman")) {
                this.printer.println("Uncompressing with Huffman.");
                this.printer.println("Input file name: '" + readFileName + "'");
                String writeFileName = readFileName.substring(0, readFileName.length() - 8);
                huffman.uncompress(readFileName, writeFileName);
                this.printer.println("Uncompressed file name: '" + writeFileName + "'");
            } else {
                infoToHelp();
            }
        } else {
            infoToHelp();
        }
    }
    
    private void lempelZivWelchSelected(String method, String readFileName) throws Exception {
        LempelZivWelch lempelZivWelch = new LempelZivWelch(this.printer);                    
        if (method.equals("comp")) {
            this.printer.println("Compressing with Lempel-Ziv-Welch.");
            this.printer.println("Input file name: '" + readFileName + "'");
            lempelZivWelch.compress(readFileName, readFileName + ".lzw");
            this.printer.println("Compressed file name: '" + readFileName + ".lzw'");

        } else if (method.equals("uncomp")) {
            if (readFileName.length() > 4 && readFileName.endsWith(".lzw")) {
                this.printer.println("Uncompressing with Lempel-Ziv-Welch.");
                this.printer.println("Input file name: '" + readFileName + "'");
                String writeFileName = readFileName.substring(0, readFileName.length() - 4);
                lempelZivWelch.uncompress(readFileName, writeFileName);
                this.printer.println("Uncompressed file name: '" + writeFileName + "'");
            } else {
                infoToHelp();
            }
        } else {
            infoToHelp();
        }
    }
    
    private void threeArcuments(String[] args) throws Exception {
        String algorithm = args[0];
        String method = args[1];
        String readFileName = args[2];
        ReadFile readfile = new ReadFile();                
        if (!readFileName.equals("") && readfile.checkIfFileExists(readFileName)) {
            if (algorithm.equals("huff")) {
                huffmanSelected(method, readFileName);
            } else if (algorithm.equals("lzw")) {
                lempelZivWelchSelected(method, readFileName);
            } else {
                this.printer.println(algorithm + " is not accepted packing algorithm.");
                infoToHelp();
            }
        } else {
            this.printer.println("File name " + readFileName + " does not exists.");
        }
    }
    
    /**
     * Method to run user interface.
     * 
     * @param args  Input as arguments to the user interface
     */
    public void runUserInterface(String[] args) {
        try {
            this.printer.println("* Compressor 1.00 *");
            this.printer.println("* MIT License     *");
            if (args.length > 0 && args[0] == null) {
                args[0] = "";
            }
            if (args.length > 1 && args[1] == null) {
                args[1] = "";
            }
            if (args.length > 2 && args[2] == null) {
                args[2] = "";
            }
            if (args.length == 3) {
                threeArcuments(args);
            } else if (args.length == 1 && args[0].equals("help")) {
                help();
            } else {
                infoToHelp();
            }
        } catch (Exception e) {
            this.printer.println(e);
        }
    }
    
    private void infoToHelp() {
        this.printer.println("Check help with command 'compressor help'");
    }
    
    private void help() {
        this.printer.println("");
        this.printer.println("Huffman");
        this.printer.println("=======");
        this.printer.println("To compress file with Huffman algorithm, run command 'compressor huff comp <filename>'.");
        this.printer.println("To compress file with Huffman algorithm, run command 'compressor huff uncomp <filename.huffman>'.");
        this.printer.println("");
        this.printer.println("Lempel-Ziv-Welch");
        this.printer.println("================");
        this.printer.println("To compress file with Lempel-Ziv-Welch algorithm, run command 'compressor lzw comp <filename>'.");
        this.printer.println("To compress file with Lempel-Ziv-Welch algorithm, run command 'compressor lzw uncomp <filename.lzw>'.");
        this.printer.println("");
        this.printer.println("Help");
        this.printer.println("====");
        this.printer.println("Check help with command 'compressor help'.");        
    }
}
