package classes;

public class StudentExistsException extends RuntimeException{
	public StudentExistsException() {
		super("A student by this name already exists");
	}
}
