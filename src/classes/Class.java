package classes;

public class Class {

	private int grade;
	
	private String className;
	
	//need teacher object
	
	private Student[] students;
	
	private int classAverage;
	
	//Constructors
	public Class(int grade, String className, Student[] students, int classAverage) {
		this.grade = grade;
		this.className = className;
		this.students = students;
		this.classAverage = classAverage;
	}
	
	public Class(int grade, String className, Student[] students) {
		this.grade = grade;
		this.className = className;
		this.students = students;
		this.classAverage = getClassAverage();
	}
	
	//Getters
	public int getGrade() {
		return grade;
	}
	
	public String getClassName() {
		return className;
	}
	
	public Student[] getStudents() {
		return students;
	}
	
	//Maybe do in separate method for efficiency
	public int getClassAverage() {
		int total = 0;
		for(int j = 0; j < students.length; j++) {
		total += students[j].getAverage();
		}
		classAverage = total/students.length;
		return classAverage;
	}
	
	//Setters
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setStudents(Student[] students) {
		this.students = students;
	}
	
	public void setClassAverage(int classAverage) {
		this.classAverage = classAverage;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nGrade: " + grade);
		str.append("\nClass: " + className);
		str.append("\nStudent Roster: " + students);
		str.append("\nClass Average: " + classAverage);
		return str.toString();
	}
}
