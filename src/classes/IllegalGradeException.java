package classes;

public class IllegalGradeException extends RuntimeException {
	public IllegalGradeException() {
		super("Grade range is from 0 - 100");
	}
}
