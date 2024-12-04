package classes;

public class StudentExistsException extends Exception{

	public StudentExistsException() {
		super("Student already exists.");
	}
	
	public StudentExistsException(String msg) {
		super(msg);
	}
}
