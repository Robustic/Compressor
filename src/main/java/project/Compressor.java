package project;

import ui.UserInterface;
import userio.ProductionPrinter;

public class Compressor {    

    public static void main(String[] args) {
//        String[] args = new String[3];
//        args[0] = "huff";
//        args[1] = "uncomp";
//        args[2] = "pg10.txt.huffman";
        ProductionPrinter printer = new ProductionPrinter();
        UserInterface ui = new UserInterface(printer);
        ui.run(args);
    }
}
