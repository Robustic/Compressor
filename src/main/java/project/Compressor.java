package project;

import ui.UserInterface;
import userio.MessagePrinter;
import userio.ProductionPrinter;

import algorithms.LempelZivWelch;
import datastructures.ByteList;

public class Compressor {    

    public static void main(String[] args) {        
        MessagePrinter printer = new ProductionPrinter();
        UserInterface ui = new UserInterface(printer);
        ui.run(args);
    }
}
