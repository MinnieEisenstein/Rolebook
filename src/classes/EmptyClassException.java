package classes;

public class EmptyClassException extends Exception{
	public EmptyClassException(String message) {
        super(message);
    }
	
	public EmptyClassException() {
		super("The class is empty.");
	}
}
