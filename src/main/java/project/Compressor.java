package project;

import ui.UserInterface;
import userio.MessagePrinter;
import userio.ProductionPrinter;

/**
 * Main class of the Compressor program.
 */
public class Compressor {    

    /**
     * Main method.
     * 
     * @param args  Input as arguments to the program
     */
    public static void main(String[] args) {        
        MessagePrinter printer = new ProductionPrinter();
        UserInterface ui = new UserInterface(printer);
        ui.runUserInterface(args);
    }
}
