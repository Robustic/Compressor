package userio;

/**
 * Class to print messages with System.out.print().
 */
public class ProductionPrinter implements MessagePrinter {
    
    /**
     * Print message without line break.
     * 
     * @param message       Message to print.
     */
    public void print(String message) {
        System.out.print(message);
    }
    
    /**
     * Print message with line break.
     * 
     * @param message       Message to print.
     */
    public void println(String message) {
        System.out.println(message);
    }
    
    /**
     * Print exception with line break.
     * 
     * @param exception     Exception to print as message
     */
    public void println(Exception exception) {
        System.out.println(exception);
    }
}
