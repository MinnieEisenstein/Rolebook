package classes;

import java.util.ArrayList;
import java.io.*;

public class Student {

	private String studentName;
	//Needs teacher? Maybe student ID?
	//maybe add an array of teachers and an array of classes
	
	private ClassList studentClass;
	//Make Array of Assignment objects for each student
	private ArrayList<Assignment> assignments;
	
	private int average;
	
	private String fileName;//name for file with student info
	private PrintWriter writer;
	private File file;
	//Constructors
	public Student(String studentName, ClassList studentClass, ArrayList<Assignment> assignments, int average) { 
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = assignments;
		this.average = average;
		
		 // Ensure the 'students' folder exists
        File folder = new File("Students");
        if (!folder.exists()) {
            folder.mkdir();  // Create the directory if it doesn't exist
        }
		 try {  
			 	fileName =createFileName(studentName/*,studentClass*/);
	            file = new File(folder,fileName);
	            writer = new PrintWriter(new FileWriter(file, true)); 
	            
	        } catch (IOException e) {
	            System.err.println("Error creating file for " + studentName);
	            
	        }
	}
	
	

	public Student(String studentName, ClassList studentClass, ArrayList<Assignment> assignments) {
		this.studentName = studentName;
		this.studentClass = studentClass;
		this.assignments = assignments;
		this.average = this.getAverage();
		
		 // Ensure the 'students' folder exists
        File folder = new File("Students");
        if (!folder.exists()) {
            folder.mkdir();  // Create the directory if it doesn't exist
        }
		 try {  
			 	fileName =createFileName(studentName/*,studentClass*/);
	            file = new File(folder,fileName);
	            writer = new PrintWriter(new FileWriter(file, true)); 
	            
	        } catch (IOException e) {
	            System.err.println("Error creating file for " + studentName);
	            
	        }
	}
	
	public Student(String studentName, ClassList curClass) {
		this.studentName = studentName;
		this.studentClass = curClass;
		this.assignments = new ArrayList<Assignment>();
		//this.average = this.getAverage();
		
		 // Ensure the 'students' folder exists
        File folder = new File("Students");
        if (!folder.exists()) {
            folder.mkdir();  // Create the directory if it doesn't exist
        }
		 try {  
			 	fileName =createFileName(studentName/*,studentClass*/);
	            file = new File(folder,fileName);
	            writer = new PrintWriter(new FileWriter(file, true)); 
	            
	        } catch (IOException e) {
	            System.err.println("Error creating file for " + studentName);
	            
	        }
	}
	
	//Getters
	public String getName() {
		return studentName;
	}
	
	public ClassList getStudentClass() {
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
	
	public void setStudentClass(ClassList studentClass) {
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
        
        // Write to file
        if (writer != null) {
            try {
                writer.println(assignment.getName() + ": " + assignment.getMark().getNum());
                writer.flush();
            } catch (Exception e) {
                System.err.println("Error writing assignment to file for " + studentName);
                e.printStackTrace();
            }
        }
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
		fileName.append(studentName);
		//file.append("_");
		//file.append(studentClass);
		fileName.append(".txt");
		return fileName.toString();
		
	}
	
}
