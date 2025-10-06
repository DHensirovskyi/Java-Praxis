package Exceptions.Tasks;

public class TooYoungException extends Exception{
    public TooYoungException() {
        super("You are too young, try again");
    }

    public TooYoungException(String message) {
        super(message);
    }
}
