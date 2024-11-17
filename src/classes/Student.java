package classes;

import java.util.ArrayList;
import java.io.*;

public class Student {

	private String studentName;
	//Needs teacher? Maybe student ID?
	//maybe add an array of teachers and an array of classes
	
	private Class studentClass;
	//Make Array of Assignment objects for each student
	private ArrayList<Assignment> assignments;
	
	private int average;
	
	private String fileName;//name for file with student info
	PrintWriter file;
	//Constructors
	public Student(String studentName, Class studentClass, ArrayList<Assignment> assignments, int average) { 
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = assignments;
		this.average = average;
		fileName =createFileName(studentName/*,studentClass*/);
		
		try {
		PrintWriter file = new PrintWriter(fileName);
		
		
		}
		catch(FileNotFoundException ex) {
			System.out.println("File not found.");
		}
	}
	
	

	public Student(String studentName, Class studentClass, ArrayList<Assignment> assignments) {
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = assignments;
		this.average = this.getAverage();
		fileName =createFileName(studentName/*, Class class*/);
		try {
			PrintWriter file = new PrintWriter(fileName);
			
			
			}
			catch(FileNotFoundException ex) {
				System.out.println("File not found.");
			}
	}
	
	public Student(String studentName, Class studentClass) {
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = new ArrayList<Assignment>();
		//this.average = this.getAverage();
		
		fileName =createFileName(studentName/*, Class class*/);
		try {
			PrintWriter file = new PrintWriter(fileName);
			
			
			}
			catch(FileNotFoundException ex) {
				System.out.println("File not found.");
			}
	}
	
	//Getters
	public String getName() {
		return studentName;
	}
	
	public Class getStudentClass() {
		return studentClass;
	}
	
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}
	
	//Maybe put calculation in separate method for efficiency?
	public int getAverage() {
		int total = 0;
		for(int i = 0; i < this.assignments.size(); i++) {
			total += assignments.get(i).getMark().getNum();
		}
		average = total/assignments.size();
		return average;
	}
	
	//Setters
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void setStudentClass(Class studentClass) {
		this.studentClass = studentClass;
	}
	
	public void setAssignments(ArrayList<Assignment> assignments) {
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
		assignments.add(assignment);
		file.println(assignment.getMark());
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
	
	private String createFileName(String studentName /*Class ,studentClass*/) {
		StringBuilder fileName = new StringBuilder();
		file.append(studentName);
		//file.append("_");
		//file.append(studentClass);
		file.append(".txt");
		return fileName.toString();
		
	}
	
}
