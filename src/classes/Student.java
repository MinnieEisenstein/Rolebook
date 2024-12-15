package classes;

import java.util.ArrayList;
import java.util.Random;

public class Student {

    private String studentFullName;
    private ClassList studentClass;
    private ArrayList<Assignment> assignments;
    private int average;
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
        this.studentFullName = first + " " + last;  // Ensure studentName is properly initialized
        this.assignments = new ArrayList<>();
        this.password = "Student1234";  // Default password for new students
        
        // Generate a unique ID
        this.studentID = generateUniqueID();
        
        System.out.println("The student's ID is " + studentID + " and your password is " + password);
    }

    // Constructor for creating a Student with Class and Assignments
    public Student(String studentName, ClassList studentClass, ArrayList<Assignment> assignments) {
        this.studentFullName = studentName;
        this.studentClass = studentClass;
        this.assignments = assignments;
        this.average = this.getAverage();
        
        // Generate unique ID
        this.studentID = generateUniqueID();
    }

    // Constructor for creating a Student with Class
    public Student(String studentName, ClassList curClass) {
        this.studentFullName = studentName;
        this.studentClass = curClass;
        this.assignments = new ArrayList<>();
        
        // Generate unique ID
        this.studentID = generateUniqueID();
    }

    // Getters
    public String getPassword() {
        return password;
    }

    public String getStudentID() {
        return studentID;  // Now returns a String
    }

    public String getFullName() {
        return studentFullName;
    }

    public ClassList getStudentClass() {
        return studentClass;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    // Calculate average score
    public int getAverage() {
        if(assignments.size() == 0) {
            return 0;
        } else {
            int total = 0;
            for (Assignment assignment : assignments) {
                total += assignment.getMark().getNum();
            }
            average = total / assignments.size();
            return average;
        }
    }

    // Setters
    public void setStudentName(String studentName) {
        this.studentFullName = studentName;
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
    
    public String getLastName() {
        return studentLastName;
    }
    
    public String getFirstName() {
        return studentFirstName;
    }

    // Other methods
    public LetterGrade getLetterAverage() {
        Mark avg = new Mark(average);
        return avg.getLetter();
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(studentLastName);
        str.append(", " + studentFirstName);
        return str.toString();
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
    
    // Equals method checks if the names are the same
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return this.getFullName().equals(other.getFullName()) && this.getStudentID().equals(other.getStudentID());
    }
}
