package classes;

import java.util.ArrayList;

public class Teacher {

	private String name;
	private Class class1;//had to name it class1 because class is a java keyword
	//for now- its a single class object, we can expand to make it an array if some teachers teach more than one class
	private ArrayList<Assignment> assignments;
	private String subject;
	
	// Constructor
    public Teacher(String name, Class class1, String subject) {
        this.name= name;
        this.class1= class1;
        this.setSubject(subject);
        this.assignments = new ArrayList<>(); // Initialize the ArrayList
    }
    

    //getters
	public String getName() {
		return name;
	}
	public Class getClass1() {
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
	
	public void setClass1(Class class1) {
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
	public String getClassList() {
		return getClass1().getStudents().toString();
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

}
