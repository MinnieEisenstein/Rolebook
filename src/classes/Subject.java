package classes;

import java.util.ArrayList;

public class Subject {
	private String name;
	ArrayList<Assignment> assignments;
	
	/* Constructor takes name as an argument */
	public Subject(String name) {
		this.name = name;
	}
	
	/* Changes subject name */
	public void editName(String name) {
		this.name = name;
	}
	
	/* Adds assignment to the ArrayList.
	   If the assignment name already exists in this subject, throws the AssignmentExistsException
	 */
	public void addAssignment(Assignment assignment) {
		boolean exists = false;
		for(Assignment a : assignments) {
			if(a.equals(assignment)) {
				exists = true;
				throw new AssignmentExistsException("This assignment name already exists");
				//In main() continue with "enter a new name, or 'c' to cancel", or something like that. In case name was entered wrong, not to lose the other data entered.
			}
		}
		if(!exists) {
		assignments.add(assignment);
		}
	}
	
	/* Returns an copied ArrayList of the assignments 
	   (Notice: If there are errors when it comes to client code, try changing copying ArrayList syntax- just tried to simplify it)
	*/
	public ArrayList<Assignment> getAssignments(){
		return new ArrayList<>(assignments);
	}
	
}
