package classes;

public class Mark {
	
	private int num;			//stores mark as number
	private LetterGrade letter;	//stores mark as LetterGrade enum
	private Status status;		//stores marks as Status enum

	//default constructor accepts mark as number and calls setInfo to set other fields accordingly
	public Mark(int num) {
		setInfo(num);
	}
	
	//constructor accepts mark as letter and calls setInfo to set other fields accordingly
	public Mark(LetterGrade letter) {
		setInfo(letter);
	}
	
	//constructor accepts mark as Pass/Fail and calls setInfo to set other fields accordingly
	public Mark(Status status) {
		setInfo(status);
	}
	
	//overloaded updateGrade methods allows user to pass in new grade as number, letter, or pass/fail 
	//and calls setInfo to reset all fields accordingly
	public void updateGrade(int num) {
		setInfo(num);
	}
	
	public void updateGrade(LetterGrade letter) {
		setInfo(letter);
	}
	
	public void updateGrade(Status status) {
		setInfo(status);
	}

	//getter methods
	
	public int getNum() {
		return num;
	}

	public LetterGrade getLetter() {
		return letter;
	}

	public Status getStatus() {
		return status;
	}
	
	//overloaded convertMark methods accepts either number or letter, and returns the other one
	//private since it will only be called within the Mark class by other methods
	private int convertMark(LetterGrade letter) {
		//return the standard numeric grade of range for that letter
		if(letter == LetterGrade.A) {
			return 100;
		} else if(letter == LetterGrade.B) {
			return 85;
		} else if(letter == LetterGrade.C) {
			return 75;
		} else if(letter == LetterGrade.D) {
			return 65;
		} else {
			return 0;
		}
	}
	
	private LetterGrade convertMark(int num) {
		if(num > 89) {
			return LetterGrade.A;
		} else if(num > 79) {
			return LetterGrade.B;
		} else if(num > 69) {
			return LetterGrade.C;
		} else if(num > 65) {
			return LetterGrade.D;
		} else {
			return LetterGrade.F;
		}
	}
	
	//getStatus method returns the status of a number as pass/fail
	//private since it will only be called within the Mark class by other methods
	private Status getStatus(int num) {
		if(num >= 65) {
			return Status.PASS;
		}
		return Status.FAIL;
	}
	
	//overloaded setInfo methods are used by constructors and updateGrade methods to set fields
	//private since it will only be called within the Mark class by these methods
	public void setInfo(int num) {
		//input validation
		if(num < 0 || num > 100) {
			throw new IllegalGradeException();
		}
		this.num = num;
		letter = convertMark(num);
		status = getStatus(num);
	}
	
	public void setInfo(LetterGrade letter) {
		this.letter = letter;
		num = convertMark(letter);
		status = getStatus(num);
	}
	
	public void setInfo(Status status) {
		this.status = status;
		//when pass/fail is passed in, a pass is graded as 100/A and a fail as 0/F
		if(status == Status.PASS) {
			num = 100;
			letter = LetterGrade.A;
		} else {
			num = 0;
			letter = LetterGrade.F;
		}
	}
	
	@Override
	public String toString() {
		return num + "/" + letter;
	}
}