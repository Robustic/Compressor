package userio;

/**
 * Interface to handle output messages.
 */
public interface MessagePrinter {

    /**
     * Print message without line break.
     * 
     * @param message       Message to print.
     */
    void print(String message);

    /**
     * Print message with line break.
     * 
     * @param message       Message to print.
     */
    void println(String message);

    /**
     * Print exception with line break.
     * 
     * @param exception     Exception to print as message
     */
    void println(Exception exception);
}
