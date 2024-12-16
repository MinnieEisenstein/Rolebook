package classes;

import java.util.ArrayList;
import java.util.Random;

public class Teacher {

    private String name;
    private String teacherID;  // Unique 6-digit ID
    private String password;
    private ClassList classlist;   // Single class object (can expand to an array if needed)
    private ArrayList<Assignment> assignments;
    private String subject;

    // Static ArrayList to store existing teacher IDs to ensure uniqueness
    private static ArrayList<String> existingIDs = new ArrayList<>();

    // Constructor
    public Teacher(String name,  String subject) {
        this.name = name;
        this.teacherID = generateUniqueID();  // Generate unique ID for the teacher
        this.setSubject(subject);
        this.classlist = new ClassList(this);
        this.assignments = new ArrayList<>();  // Initialize the ArrayList
        this.password = "teacher1234";  // Generic password for all teachers
        System.out.println("The teacher's ID is "+ teacherID+ " and the password is " + password);
    }

    // Getters
    public String getTeacherID() {
    	return teacherID;
    }
   
    public String getName() {
        return name;
    }

    public String getID() {
        return teacherID;
    }

    public String getPassword() {
        return password;
    }

    public ClassList getClasslist() {
        return classlist;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public String getSubject() {
        return subject;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setID(String id) {
        this.teacherID = id;
    }

    public void setClassList(ClassList classlist) {
        this.classlist = classlist;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Add an assignment to the list
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    // Get class average
    public int getClassAverage() {
        return (int) classlist.getClassAverage();
    }

    // Get class list
    public ClassList getClassList() {
        return classlist;
    }

    // Get the number of assignments
    public int getAssignmentCount() {
        return assignments.size();
    }

    // Returns a string representation of a teacher object
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Teacher Name: ").append(name).append("\n");
        str.append("Class Name: ").append(classlist.getClassName()).append("\n");
        str.append("Subject: ").append(subject).append("\n");
        return str.toString();
    }

    // Equals method compares Teachers by ID to see if they are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Teacher other = (Teacher) obj;
        return this.getID().equals(other.getID());
    }

    // Method to generate a unique 6-digit ID
    private String generateUniqueID() {
        Random rand = new Random();
        String id;
        do {
            id = String.format("%06d", 100000 + rand.nextInt(900000)); // Generate a random 6-digit number
        } while (existingIDs.contains(id)); // Ensure the ID is unique
        existingIDs.add(id); // Add the ID to the list of existing IDs
        return id;
    }
}