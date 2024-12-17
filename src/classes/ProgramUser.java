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
                    studentView(keyboard, teacher);
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
            System.out.println("8. Add student comments");
            System.out.println("9. View class overall menu (averages, modes, max, etc.)");
            System.out.println("10. View assignment menu");
            System.out.println("11. Remove a student");
            //when u add an assignment- it should automatically make weight evenly
            //option- change weight of each assignment
            
            //add behavior for everyone *COMMENTS
            //add absenses(caluclate that into the grade)
            //customized report for student
            
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
            case 9:
                viewClassStatistics(teacher); // View class overall stats (averages, modes, max, etc.)
                break;
            case 8:
            	addStudentComments(keyboard, teacher); // Teacher inputs comments about individual student performance
            	break;
            case 10:
                viewAssignmentMenu(keyboard, teacher); // View assignment menu
                break;
            case 11:
                removeStudent(keyboard, teacher); // Remove a student
                break;
            case 12:
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

    private static void addStudentComments(Scanner keyboard, Teacher teacher) {
    	// Iterate through Student ArrayList and use setComment method to input a comment
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

        System.out.println("Student "+ newStudent.getFullName() +" added with ID: " + newStudent.getStudentID() + " and password: " + newStudent.getPassword());
    }

    // Student view (could be expanded with more functionality)
    public static void studentView(Scanner keyboard, Teacher teacher) throws StudentNotFoundException {
        System.out.println("\nStudent View:");
        System.out.println("Enter Student ID");
        String id = keyboard.nextLine();

        if (!teacher.getClassList().StudentIDExist(id)) {
            throw new StudentNotFoundException();
        }

        Student currentStudent = null;
        for (Student student : teacher.getClassList().getClassList()) {
            if (student.getStudentID().equals(id)) {
                currentStudent = student;
                break;
            }
        }

        if (currentStudent == null) {
            throw new StudentNotFoundException();
        }

        int attempts = 0;
        boolean authenticated = false;

        while (attempts < 3) {
            System.out.println("Enter Password:");
            String password = keyboard.nextLine();
            
            if (currentStudent.getPassword().equals(password)) {
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect password. You have " + (3 - attempts) + " attempt(s) left.");
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Returning to main menu.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. View top marks");
            System.out.println("2. View lowest mark");
            System.out.println("3. View average");
            System.out.println("4. View attendance");
            System.out.println("5. View behavior");
            System.out.println("6. View customized report");
            System.out.println("7. Return to Main menu");

            int choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                	System.out.println("How many top marks do you want to see?");
                	int top = keyboard.nextInt();
                	keyboard.nextLine();
                    System.out.println("Top Marks: " + getTopMarks(teacher,currentStudent, top));
                    break;

                case 2:
                    System.out.println("Lowest Marks: " + getLowestMark(currentStudent));
                    break;

                case 3:
                    System.out.println("Average Marks: " + currentStudent.getAverage());
                    break;

                case 4:
                    System.out.println("Attendance: " + getAttendance(currentStudent));
                    break;

                case 5:
                    System.out.println("Comment: " + getComment(currentStudent));
                    break;

                case 6:
                    System.out.println("Enter the type of report you want (e.g., 'Math Scores', 'Overall Performance'):");
                    String reportType = keyboard.nextLine();
                    System.out.println("Customized Report for " + reportType + ": " + getCustomReport(currentStudent, reportType));
                    break;

                case 7:
                    System.out.println("Returning to main menu.");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper methods for the cases (you can replace these with real implementations)
    private static String getTopMarks(Teacher teacher, Student student, int top) {
    	ArrayList<Assignment> assignments = teacher.getClassList().getStudentByID(student.getStudentID()).getAssignments();

		if (assignments == null || assignments.isEmpty()) {
			return "No assignments available yet.";
		}

		// Extract marks from assignments
		ArrayList<Integer> marks = new ArrayList<>();
		for (Assignment assignment : assignments) {
			marks.add(assignment.getMark().getNum());
		}
		
		// Sort marks in descending order
		marks.sort((a, b) -> b - a); 
		
		// Get the top N marks
		StringBuilder topMarks = new StringBuilder();
		for (int i = 0; i < Math.min(top, marks.size()); i++) {
			topMarks.append(marks.get(i)).append(i < top - 1 && i < marks.size() - 1 ? ", " : "");
		}
		
		return topMarks.toString();
		
        
    }

    private static int getLowestMark(Student student) {
        // Logic to get lowest marks
        return student.getLowestMark();
    }

    private static String getAttendance(Student student) {
        // Logic to get attendance
        return "95%"; // Example data
    }

    private static String getComment(Student student) {
        // Logic to get comment
        return student.getComment();
    }

    private static String getCustomReport(Student student, String reportType) {
        // Logic to generate a custom report based on the type
        return "Custom Report Data for: " + reportType; // Example data
    }

}