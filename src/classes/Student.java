package classes;

import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class Student {

    private String studentName;
    private ClassList studentClass;
    private ArrayList<Assignment> assignments;
    private int average;
    private String fileName;
    private PrintWriter writer;
    private File file;
    private String studentFirstName;
    private String studentLastName;
    private String password;
    
    // Static ArrayList to store existing IDs
    private static ArrayList<String> existingIDs = new ArrayList<>();
    
    // Instance variable for the student's ID (now a String)
    private String studentID;

    // Constructor for creating a new Student
    public Student(String first, String last) {
        this.studentFirstName = first;
        this.studentLastName = last;
        this.studentName = first + " " + last;  // Ensure studentName is properly initialized
        this.assignments = new ArrayList<>();
        this.password = "Student1234";
        
        // Generate a unique ID
        this.studentID = generateUniqueID();
        
        System.out.println("The student's ID is " + studentID + " and your password is " + password);
        
        // Ensure the 'students' folder exists
        File folder = new File("Students");
        if (!folder.exists()) {
            folder.mkdir();  // Create the directory if it doesn't exist
        }
        
        try {
            fileName = createFileName(studentName);
            file = new File(folder, fileName);
            writer = new PrintWriter(new FileWriter(file, true));
        } catch (IOException e) {
            System.err.println("Error creating file for " + studentName);
        }
    }
    
    // Constructor for creating a Student with Class and Assignments
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
            fileName = createFileName(studentName);
            file = new File(folder, fileName);
            writer = new PrintWriter(new FileWriter(file, true));
        } catch (IOException e) {
            System.err.println("Error creating file for " + studentName);
        }
    }
    
    // Constructor for creating a Student with Class
    public Student(String studentName, ClassList curClass) {
        this.studentName = studentName;
        this.studentClass = curClass;
        this.assignments = new ArrayList<>();
        
        // Ensure the 'students' folder exists
        File folder = new File("Students");
        if (!folder.exists()) {
            folder.mkdir();  // Create the directory if it doesn't exist
        }

        try {
            fileName = createFileName(studentName);
            file = new File(folder, fileName);
            writer = new PrintWriter(new FileWriter(file, true));
        } catch (IOException e) {
            System.err.println("Error creating file for " + studentName);
        }
    }

    // Getters
    public String getPassword() {
        return password;
    }

    public String getStudentID() {
        return studentID;  // Now returns a String
    }

    public String getName() {
        return studentName;
    }

    public ClassList getStudentClass() {
        return studentClass;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    // Calculate average score
    public int getAverage() {
        int total = 0;
        for (int i = 0; i < this.assignments.size(); i++) {
            total += assignments.get(i).getMark().getNum();
        }
        average = total / assignments.size();
        return average;
    }

    // Setters
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

    // Other methods
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
        str.append("\nStudent's Assignments: " + assignments);
        str.append("\nAverage: " + average);
        return str.toString();
    }

    private String createFileName(String studentName) {
        StringBuilder fileName = new StringBuilder();
        fileName.append(studentName);
        fileName.append(".txt");
        return fileName.toString();
    }

    // Method to generate a unique 6-digit ID as a String
    private String generateUniqueID() {
        Random rand = new Random();
        String id;
        do {
            id = String.format("%06d", 100000 + rand.nextInt(900000));  // Generate a 6-digit number as a String
        } while (existingIDs.contains(id));  // Ensure the ID is unique
        existingIDs.add(id);  // Add the ID to the list of existing IDs
        return id;
    }
    
    //Equals method checks if the names are the same
    @Override
    public boolean equals(Object obj) {
    	if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student)obj;
		return this.getPassword().equals(other.getPassword()) && this.getStudentID().equals(other.getStudentID());
    }
}