package classes;

public class NoStudentsException extends Exception {
	public NoStudentsException(String message) {
		super(message);
	}
	
	public NoStudentsException() {
		super("There are no students in the class");
	}
}
