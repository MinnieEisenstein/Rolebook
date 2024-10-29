package classes;

import java.util.Scanner;

public class ProgramUser {
//might want to change everything to try catch instead of throws
	
	public static void main(String[] args) throws StudentNotFoundException, NoStudentsException {
		Scanner keyboard = new Scanner(System.in);
		
		//As of now, teacher only has one class
		Class curClass = new Class(0, "class1");
		int choice;
		
		//Menu
		do{
			System.out.println("\nEnter the number of the action you would like to do:");
			System.out.println("1. Add student");
			System.out.println("2. Add assignment");
			System.out.println("3. Get average by student");
			System.out.println("4. Get class list");
			System.out.println("5. Quit");
			System.out.println();
			choice = keyboard.nextInt();
			keyboard.nextLine(); //clears buffer
			
			implementMenu(choice, curClass, keyboard);
		} while(choice!=5);
		

	}

	public static void implementMenu(int choice, Class curClass, Scanner keyboard) throws StudentNotFoundException, NoStudentsException {
		
		switch(choice) {
			case 1: 
				addStudent(curClass, keyboard);
				break;
			case 2: 
				addAssignment(curClass, keyboard);
				break;
			case 3: 
				getAvgByStudent(curClass, keyboard);
				break;
			case 4:
				getClassList(curClass);
				break;
			case 5:
				System.exit(0);
			default:
				System.out.println("That is not an option, choose another\n");
		}
	}
	
	public static void addStudent(Class curClass, Scanner keyboard) {
		//get student name from teacher input
		System.out.println("Enter name of new student: ");
		String name = keyboard.nextLine();
		
		curClass.getStudents().add(new Student(name, curClass));
	}
	
	public static void addAssignment(Class curClass, Scanner keyboard) throws NoStudentsException {
		//if no students, throw exception
		if (curClass.getStudents().size() < 1) {
			throw new NoStudentsException();
		}
		//get assignment name from teacher input
		System.out.println("Enter name of new assignment: ");
		String assignmentName = keyboard.nextLine();
		
		//For each student in the class, asks for students mark and adds new assignment to student
		for(Student student : curClass.getStudents()) {
			System.out.println("Mark for " + student.getName() + ": ");
			student.addAssignment(new Assignment(assignmentName, new Mark(keyboard.nextInt())));
			keyboard.nextLine(); //clears buffer
		}
	}
	
	public static void getAvgByStudent(Class curClass, Scanner keyboard) throws StudentNotFoundException, NoStudentsException {
		//Ask for student who's avg should be displayed
		System.out.println("Which student's average would you like?");
		String studentName = keyboard.nextLine();
		
		if(FoundStudent(curClass, studentName)) {
			for (Student s : curClass.getStudents()) {
				//find matching student
				if(s.getName().equalsIgnoreCase(studentName)) {
					System.out.println(s.getName() + "'s average is " + s.getAverage());
				}	
			}	
		}else {
				
			throw new StudentNotFoundException();
		}
	}
	
	//displays class list of names
	public static void getClassList(Class curClass) {
		if(curClass.getStudents().size() == 0) {
			System.out.println("Class is empty");
		}
		for(Student s : curClass.getStudents()) {
			System.out.println(s.getName());
		}
	}
	public static boolean FoundStudent(Class curClass, String studentName) throws NoStudentsException {
		for (Student s : curClass.getStudents()) {
			//if no students yet throw exception
			if(curClass.getStudents().size()< 1) {
				throw new NoStudentsException();
			}
			//find matching student
			if(s.getName().equalsIgnoreCase(studentName)) {
				return true;
			}	
		}
		return false;
	}
	
}
