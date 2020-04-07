package userio;

public class ProductionPrinter implements MessagePrinter {
    public void print(String message) {
        System.out.print(message);
    }
    
    public void println(String message) {
        System.out.println(message);
    }
    
    public void println(Exception exception) {
        System.out.println(exception);
    }
}
