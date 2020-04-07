package userio;

public interface MessagePrinter {
    void print(String message);
    void println(String message);
    void println(Exception exception);
}
