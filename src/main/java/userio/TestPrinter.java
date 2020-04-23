package userio;

/**
 * Class to save messages to the list when running unit tests.
 */
public class TestPrinter implements MessagePrinter {
    private String[] messages;
    private int pointer;

    /**
     * Constructor.
     */
    public TestPrinter() {
        this.messages = new String[1];
        this.messages[0] = new String();
        this.pointer = 0;
    }
    
    private void doubleSize() {
        String[] newList = new String[2 * this.messages.length];
        for (int i = 0; i < this.pointer; i++) {
            newList[i] = this.messages[i];
        }
        for (int i = this.pointer; i < newList.length; i++) {
            newList[i] = new String();
        }
        this.messages = newList;
    }
    
    private void addNew(String message) {
        while (this.pointer >= this.messages.length) {
            doubleSize();
        }
        this.messages[pointer] = this.messages[pointer] + message;
        this.pointer++;
    }
    
    private void addNewWithoutNewLine(String message) {
        while (this.pointer >= this.messages.length) {
            doubleSize();
        }
        this.messages[pointer] = this.messages[pointer] + message;
    }
    
    /**
     * Save message without line break.
     * 
     * @param message       Message to save.
     */
    public void print(String message) {
        addNewWithoutNewLine(message);
    }
    
    /**
     * Save message with line break.
     * 
     * @param message       Message to save.
     */
    public void println(String message) {
        addNew(message);
    }
    
    /**
     * Save exception with line break.
     * 
     * @param exception     Exception to save as message
     */
    public void println(Exception exception) {
        addNew(exception.getMessage());
    }
    
    /**
     * Method to get size of the messages.
     * 
     * @return  Size of the messages
     */
    public int messagesSize() {
        if (this.messages.length > this.pointer && !this.messages[this.pointer].equals("")) {
            return this.pointer + 1;
        }
        return this.pointer;
    }
    
    /**
     * Method to get message in the index.
     * 
     * @param i     Index
     * @return      Message as String
     */
    public String messageInIndex(int i) {
        return this.messages[i];
    }
    
    /**
     * Method to print message in the index with System.out.println().
     * 
     * @param i     Index
     */
    public void printMessageInIndex(int i) {
        System.out.println(this.messages[i]);
    }
}
