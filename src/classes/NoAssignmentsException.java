package classes;

public class NoAssignmentsException extends Exception{
	public NoAssignmentsException() {
		super("There are no assignments yet.");
	}
	
	public NoAssignmentsException(String message) {
		super(message);
	}
}
