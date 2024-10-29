package classes;

public class StudentNotFoundException extends Exception {
	public StudentNotFoundException() {
		super("Student was not found");
	}
}
