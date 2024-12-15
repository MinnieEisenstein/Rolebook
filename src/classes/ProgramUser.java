package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProgramUser {
    public static void main(String[] args) throws StudentNotFoundException, NoStudentsException {
    	Scanner keyboard = new Scanner(System.in);
    	
    	System.out.println("What is the teacher's name?");
    	String name= keyboard.nextLine();
    	System.out.println("What is the teacher's subject");
    	String subject= keyboard.nextLine();	
        Teacher teacher= new Teacher(name, subject);
        boolean exit = false;

        while (!exit) {
            // Main menu options
            System.out.println("\nSelect an option:");
            System.out.println("1. Teacher View");
            System.out.println("2. Student View");
            System.out.println("3. Exit");
            int choice = keyboard.nextInt();
            keyboard.nextLine(); // flush buffer

            switch (choice) {
                case 1:
                    // Teacher login
                    boolean teacherLoggedIn = false;
                    int attempts = 0;
                    while (attempts < 3 && !teacherLoggedIn) {
                        System.out.println("Enter Teacher View password:");
                        String teacherPassword = keyboard.nextLine();
                        if (teacherPassword.equals(teacher.getPassword())) {
                            teacherLoggedIn = true;
                        } else {
                            attempts++;
                            System.out.println("Incorrect password. You have " + (3 - attempts) + " attempts remaining.");
                        }
                    }
                    
                    // If login successful, show teacher view
                    if (teacherLoggedIn) {
                        teacherView(keyboard, teacher);
                    } else {
                        System.out.println("Too many incorrect attempts. Returning to main menu.");
                    }
                    break;
                case 2:
                    // Student view - can be expanded later
                    studentView(keyboard);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        keyboard.close();
    }

    // Teacher view methods
    public static void teacherView(Scanner keyboard, Teacher teacher) {
        boolean exitTeacherView = false;
        while (!exitTeacherView) {
            System.out.println("\nTeacher View:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. View a specific student");
            System.out.println("4. View top marks");
            System.out.println("5. View lowest marks");
            System.out.println("6. Change password");
            System.out.println("7. Add assignment");
            System.out.println("8. View class overall menu (averages, modes, max, etc.)");
            System.out.println("9. View assignment menu");
            System.out.println("10. Remove a student");
            System.out.println("11. Return to Main Menu");
            int choice = keyboard.nextInt();
            keyboard.nextLine(); // clear buffer

            switch (choice) {
            case 1:
                addStudent(keyboard, teacher); // Add a new student
                break;
            case 2:
                teacher.getClassList(); // Show all students
                break;
            case 3: 
                viewSpecificStudent(keyboard, teacher); // View a specific student
                break;
            case 4:
                System.out.println("What number of highest marks do you want to see?");
                int top = keyboard.nextInt();
                keyboard.nextLine();
                viewTopMarks(top, teacher); // View top marks
                break;
            case 5:
                System.out.println("What number of lowest marks do you want to see?");
                int lowest = keyboard.nextInt();
                keyboard.nextLine();
                viewLowestMarks(lowest, teacher); // View lowest marks
                break;
            case 6: 
                changePasscode(teacher, keyboard); // Change teacher's password
                break;
            case 7:
                addAssignment(keyboard, teacher); // Add a new assignment
                break;
            case 8:
                viewClassStatistics(teacher); // View class overall stats (averages, modes, max, etc.)
                break;
            case 9:
                viewAssignmentMenu(keyboard, teacher); // View assignment menu
                break;
            case 10:
                removeStudent(keyboard, teacher); // Remove a student
                break;
            case 11:
                exitTeacherView = true; // Exit to main menu
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
 // Method placeholders to be implemented as needed
    private static void viewSpecificStudent(Scanner keyboard, Teacher teacher) {
        System.out.println("Enter student ID to view:");
        String studentId = keyboard.nextLine();
        // Implement logic to view specific student
    }

    private static void viewTopMarks(int top, Teacher teacher) {
        // Implement logic to view top marks
    }

    private static void viewLowestMarks(int lowest, Teacher teacher) {
        // Implement logic to view lowest marks
    }

    private static void addAssignment(Scanner keyboard, Teacher teacher) {
        // Implement logic to add assignment
    }

    private static void viewClassStatistics(Teacher teacher) {
        // Implement logic to view class statistics (averages, modes, max, etc.)
    }

    private static void viewAssignmentMenu(Scanner keyboard, Teacher teacher) {
        // Implement logic for assignment menu
    }

    private static void removeStudent(Scanner keyboard, Teacher teacher) {
        System.out.println("Enter student ID to remove:");
        String studentId = keyboard.nextLine();
        // Implement logic to remove a student
    }
    private static void changePasscode(Teacher teacher, Scanner keyboard) {
    	System.out.println("Enter Current password: ");
    	String currCode= keyboard.nextLine();
    	if(currCode.equals(teacher.getPasscode())) {
    		System.out.println("Enter new password:");
    		String new1= keyboard.nextLine();
    		System.out.println("Enter new password again:");
    		String new2= keyboard.nextLine();
    		
    		if(new1.equals(new2)) {
    			teacher.setPasscode(new1);
    			System.out.println("Password is reset. New passcode is "+ new1);
    		}
    		else {
    			System.out.println("There was a mismatch betweenthe first and second password you entered");
    		}
    		
    	}
    	else {
    		System.out.println("You entered the wrong current password.");
    	}
		
	}

	// Add a new student with an autogenerated ID and password
    public static void addStudent(Scanner keyboard, Teacher teacher) {
        System.out.println("Enter the first name of the new student:");
        String firstName = keyboard.nextLine();
        System.out.println("Enter the last name of the new student:");
        String lastName = keyboard.nextLine();

        // Generate unique ID for the student
        String studentId = "student" + (teacher.getStudents().size() + 1);
        String password = studentId; // ID as password

        Student newStudent = new Student(firstName, lastName);
        teacher.addStudent(newStudent);

        System.out.println("Student added with ID: " + studentId + " and password: " + password);
    }

    // Student view (could be expanded with more functionality)
    public static void studentView(Scanner keyboard) {
        System.out.println("Student view: (not yet implemented)");
        // Placeholder for student view functionality
    }
}