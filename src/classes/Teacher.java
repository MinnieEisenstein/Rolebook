package classes;

import java.util.ArrayList;
import java.util.Random;

public class Teacher {

    private String name;
    private String teacherID;  // Unique 6-digit ID
    private ClassList classlist;   // Single class object (can expand to an array if needed)
    private ArrayList<Assignment> assignments;
    private String subject;
    private String passcode;

    // Static ArrayList to store existing teacher IDs to ensure uniqueness
    private static ArrayList<String> existingIDs = new ArrayList<>();

    // Constructor
    public Teacher(String name,  String subject) {
        this.name = name;
        this.teacherID = generateUniqueID();  // Generate unique ID for the teacher
        this.setSubject(subject);
        this.classlist = new ClassList(this);
        this.assignments = new ArrayList<>();  // Initialize the ArrayList
        this.passcode= generateRandomPasscode(6);
        System.out.println("The teacher's ID is "+ teacherID+ " and the password is " + passcode);
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

    public ClassList getClasslist() {
        return classlist;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public String getSubject() {
        return subject;
    }
    public String getPasscode() {
        return passcode;
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
    public void setPasscode(String passcode) {
    	this.passcode = passcode;
    }

    // Add an assignment to the list
    public boolean addAssignment(Assignment assignment) {
        if (assignments.contains(assignment)) {
            // If the assignment already exists, return false
            return false;
        }
        assignments.add(assignment);
        return true; // Assignment added successfully
    }

    // Get class average
    public double getClassAverage() {
        return classlist.getClassAverage();
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
    //method to generate a random passcode
    public static String generateRandomPasscode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder passcode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            passcode.append(characters.charAt(index));
        }
		return passcode.toString();
  }

	public void addStudent(Student newStudent) {
		this.getClasslist().addStudent(newStudent);
		
	}

	public ArrayList<Student> getStudents() {
		return this.classlist.getClassList();
	}
}