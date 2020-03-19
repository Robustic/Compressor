package project;

import ui.UserInterface;

public class Compressor {    

    public static void main(String[] args) {
//        String[] args = new String[3];
//        args[0] = "huff";
//        args[1] = "uncomp";
//        args[2] = "pg10.txt.huffman";
        
        UserInterface ui = new UserInterface();
        ui.run(args);
    }
}
