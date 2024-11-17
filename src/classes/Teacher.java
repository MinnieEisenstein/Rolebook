package classes;

import java.util.ArrayList;
import java.util.Random;

public class Teacher {

	private String name;
	private String teacherID; 	//Added an equals method for future branching out use. If we want to eventually use the program for a school database- information can be retrieved by entering teacher id
	private String password;
	private ClassList class1;//had to name it class1 because class is a java keyword
	//for now- its a single class object, we can expand to make it an array if some teachers teach more than one class
	private ArrayList<Assignment> assignments;
	private String subject;
	
	// Constructor
    public Teacher(String name, String id, ClassList class1, String subject) {
        this.name= name;
        this.teacherID = id;
        this.class1= class1;
        this.setSubject(subject);
        this.assignments = new ArrayList<>(); // Initialize the ArrayList
        this.password= generateRandomString(6);
        System.out.println("Your password is "+ password + " .Please remember.");
    }
    

    //getters
	public String getName() {
		return name;
	}
	public String getID() {
		return teacherID;
	}
	public String getPassword() {
		return password;
	}
	public ClassList getClass1() {
		return class1;
	}
	public ArrayList<Assignment> getAssignments() {
        return assignments;
    }
	
	public String getSubject() {
		return subject;
	}
	//setters
	public void setName(String name) {
		this.name = name;
	}
	public void setID(String id) {
		this.teacherID = id;
	}
	public void setClass1(ClassList class1) {
		this.class1 = class1;
	}
	public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	//add an assignment to the array list
	public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
	
	public int getClassAverage() {
		return getClass1().getClassAverage();
	}
	public ClassList getClassList() {
		return getClass1();
	}
	//get number of assignments
	public int getAssignmentCount() {
		return assignments.size();
	}

	//returns a string representation of a teacher object
	@Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Teacher Name: ").append(name).append("\n");
        str.append("Class Name: ").append(class1.getClassName()).append("\n"); 
        str.append("Subject: ").append(subject).append("\n"); 
        return str.toString();
    }
	
	// Equals method compares Teachers by id to see if they are equal
	@Override 
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Teacher other = (Teacher)obj;
		return this.getID() == other.getID();
	}
	 public static String generateRandomString(int length) {
	        // Define the characters to choose from (uppercase and lowercase letters)
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	        
	        // Create a Random object to generate random numbers
	        Random random = new Random();
	        
	        // StringBuilder to build the result
	        StringBuilder randomString = new StringBuilder();

	        // Loop to generate random characters
	        for (int i = 0; i < length; i++) {
	            // Randomly select an index from the characters string
	            int index = random.nextInt(characters.length());
	            randomString.append(characters.charAt(index));
	        }
	        
	        return randomString.toString();
	    }
	

}
