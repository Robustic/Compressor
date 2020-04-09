package userio;

public class TestPrinter implements MessagePrinter {
    private String[] messages;
    private int pointer;

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
    
    public void print(String message) {
        addNewWithoutNewLine(message);
    }
    
    public void println(String message) {
        addNew(message);
    }
    
    public void println(Exception exception) {
        addNew(exception.getMessage());
    }
    
    public int messagesSize() {
        if (this.messages.length > this.pointer && !this.messages[this.pointer].equals("")) {
            return this.pointer + 1;
        }
        return this.pointer;
    }
    
    public String messageInIndex(int i) {
        return this.messages[i];
    }
    
    public void printMessageInIndex(int i) {
        System.out.println(this.messages[i]);
    }
}
