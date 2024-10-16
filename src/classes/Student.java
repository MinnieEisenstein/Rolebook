package classes;

public class Student {

	private String studentName;
	//Needs teacher? Maybe student ID?
	private Class studentClass;
	//Make Array of Assignment objects for each student
	private Assignment[] assignments;
	
	private int average;
	
	//Constructors
	public Student(String studentName, Class studentClass, Assignment[] assignments, int average) { 
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = assignments;
		this.average = average;
	}
	
	public Student(String studentName, Class studentClass, Assignment[] assignments) {
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = assignments;
		this.average = this.getAverage();
	}
	
	//Getters
	public String getStudentName() {
		return studentName;
	}
	
	public Class getStudentClass() {
		return studentClass;
	}
	
	public Assignment[] getAssignments() {
		return assignments;
	}
	
	//Maybe put calculation in separate method for efficiency?
	public int getAverage() {
		int total = 0;
		for(int i = 0; i < this.assignments.length; i++) {
			total += this.assignments[i].getMark().getNum();
		}
		average = total/assignments.length;
		return average;
	}
	
	//Setters
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void setStudentClass(Class studentClass) {
		this.studentClass = studentClass;
	}
	
	public void setAssignments(Assignment[] assignments) {
		this.assignments = assignments;
	}
	
	public void setAverage(int average) {
		this.average = average;
	}
	
	//Other methods
	
	public LetterGrade getLetterAverage() {
		Mark avg = new Mark(average);
		return avg.getLetter();
	}
	
	public void addAssignment(Assignment assignment) {
		this.assignments[assignments.length] = assignment;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n" + studentName);
		str.append("\n" + studentClass);
		str.append("\nStudent's Assgnments: " + assignments);
		str.append("\nAverage: " + average);
		return str.toString();
	}
	
	
}
