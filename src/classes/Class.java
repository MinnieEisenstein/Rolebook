package classes;

import java.util.ArrayList;

public class Class {

	private int grade;
	
	private String className;
	
	private Teacher teacher;
	
	private ArrayList<Student> students;
	
	private int classAverage;
	
	//Constructors
	public Class(int grade, String className, ArrayList<Student> students, int classAverage, Teacher teacher) {
		this.grade = grade;
		this.className = className;
		this.students = students;
		this.classAverage = classAverage;
		this.setTeacher(teacher);
	}
	
	public Class(int grade, String className, ArrayList<Student> students, Teacher teacher) {
		this.grade = grade;
		this.className = className;
		this.students = students;
		this.classAverage = getClassAverage();
		this.setTeacher(teacher);
	}
	
	public Class(int grade, String className, ArrayList<Student> students) {
		this.grade = grade;
		this.className = className;
		this.students = students;
		this.classAverage = getClassAverage();
		this.setTeacher(null);
	}
	
	public Class(int grade, String className) {
		this.grade = grade;
		this.className = className;
		this.students = new ArrayList<Student>();
		//this.classAverage = getClassAverage();
		this.setTeacher(null);
	}
	
	//Getters
	public int getGrade() {
		return grade;
	}
	
	public String getClassName() {
		return className;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	public Teacher getTeacher() {
		return teacher;
	}

	
	
	//Maybe do in separate method for efficiency
	public int getClassAverage() {
		int total = 0;
		for(int j = 0; j < students.size(); j++) {
		total += students.get(j).getAverage();
		}
		classAverage = total/students.size();
		return classAverage;
	}
	
	//Setters
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public void setClassAverage(int classAverage) {
		this.classAverage = classAverage;
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nGrade: " + grade);
		str.append("\nClass: " + className);
		str.append("\n Teacher: " + teacher);
		str.append("\nStudent Roster: " + students);
		str.append("\nClass Average: " + classAverage);
		return str.toString();
	}


}
