package classes;

public class AssignmentExistsException extends RuntimeException {
	public AssignmentExistsException(String message) {
		super(message);
	}
	
	public AssignmentExistsException() {
		super("The assignment already exists");
	}
}
