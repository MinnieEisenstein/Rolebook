package classes;

public class NoStudentsException extends Exception {
	public NoStudentsException() {
		super("There are no students yet.");
	}
}
