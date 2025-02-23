package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgramUser {
	AssignmentManager assignmentManager;
	Teacher teacher;

	// Constructor
	public ProgramUser(String teacherName, String subject) {
		assignmentManager = new AssignmentManager();
		teacher = new Teacher(teacherName, subject);

	}

	public void runProgram(Scanner keyboard) {
		boolean exitProgram = false;

		while (!exitProgram) {
			System.out.println("Select an option:");
			System.out.println("1. Teacher View");
			System.out.println("2. Student View");
			System.out.println("3. Exit");

			int choice = -1;

			while (true) {
				System.out.print("Enter your choice: ");
				if (keyboard.hasNextInt()) { // Check if the input is an integer
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear the buffer
					if (choice >= 1 && choice <= 3) {
						break; // Valid input
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 3.");
					}
				} else {
					System.out.println("Invalid input. Please enter a valid number.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				teacherView(keyboard, teacher);
				break;
			case 2:
				studentView(keyboard, teacher);
				break;
			case 3:
				System.out.println("Exiting the program. Goodbye!");
				exitProgram = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again."); // This won't be reached due to the loop
			}
		}
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	public void teacherView(Scanner keyboard, Teacher teacher) {
	    boolean authenticated = false;
	    int attempts = 0;

	    // Password validation with 3 attempts
	    while (!authenticated && attempts < 3) {
	        System.out.println("Enter Teacher Password:");
	        String inputPassword = keyboard.nextLine().trim();

	        if (inputPassword.equals(teacher.getPasscode())) {
	            authenticated = true;
	        } else {
	            attempts++;
	            if (attempts < 3) {
	                System.out.println("Incorrect password. You have " + (3 - attempts) + " attempt(s) left.");
	            }
	        }
	    }

	    if (!authenticated) {
	        System.out.println("Too many failed attempts. Returning to the previous menu.");
	        return; // Exit to the previous menu
	    }

	    // Main teacher menu
	    boolean exitTeacherView = false;
	    while (!exitTeacherView) {
	        System.out.println("\nTeacher View:");
	        System.out.println("1. Student Menu");
	        System.out.println("2. Assignment Menu");
	        System.out.println("3. Attendance Menu");
	        System.out.println("4. Behavior Menu");
	        System.out.println("5. General Class Menu");
	        System.out.println("6. Change Password");
	        System.out.println("7. Return to Main Menu");

	        int choice = -1; // Initialize to an invalid value
	        while (true) {
	            try {
	                System.out.println("Select an option (1-7): ");
	                choice = keyboard.nextInt();
	                keyboard.nextLine(); // Clear buffer
	                if (choice >= 1 && choice <= 7) {
	                    break; // Valid choice, exit the loop
	                } else {
	                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a number between 1 and 7.");
	                keyboard.nextLine(); // Clear invalid input
	            }
	        }

	        // Menu options
	        switch (choice) {
	            case 1:
	                studentMenu(keyboard, teacher);
	                break;
	            case 2:
	                assignmentMenu(keyboard, teacher);
	                break;
	            case 3:
	                attendanceMenu(keyboard, teacher);
	                break;
	            case 4:
	                behaviorMenu(keyboard, teacher.getClassList());
	                break;
	            case 5:
	                generalClassMenu(keyboard, teacher);
	                break;
	            case 6:
	                changePasscode(teacher, keyboard);
	                break;
	            case 7:
	                exitTeacherView = true;
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}


//-------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------
	private static void behaviorMenu(Scanner keyboard, ClassList classList) {
		boolean exitBehaviorMenu = false;
		while (!exitBehaviorMenu) {
			System.out.println("\nBehavior Menu:");
			System.out.println("1. Add Behavior for a Student");
			System.out.println("2. View Behavior for a Student");
			System.out.println("3. Return to Teacher Menu");

			int choice = -1; // Initialize to an invalid value
			while (true) {
				try {
					System.out.println("Select an option (1-3): ");
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear buffer
					if (choice >= 1 && choice <= 3) {
						break; // Valid choice, exit the loop
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 3.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 3.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				addBehaviorForStudent(keyboard, classList);
				break;
			case 2:
				viewBehaviorForStudent(keyboard, classList);
				break;
			case 3:
				exitBehaviorMenu = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------
	private void studentMenu(Scanner keyboard, Teacher teacher) {
		boolean exitStudentMenu = false;
		while (!exitStudentMenu) {
			System.out.println("\nStudent Menu:");
			System.out.println("1. Add Student");
			System.out.println("2. View Specific Student");
			System.out.println("3. Remove Student");
			System.out.println("4. Return to Teacher Menu");

			int choice = -1; // Initialize to an invalid value
			while (true) {
				try {
					System.out.println("Select an option (1-4): ");
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear buffer
					if (choice >= 1 && choice <= 4) {
						break; // Valid choice, exit the loop
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 4.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 4.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				addStudent(keyboard, teacher);
				break;
			case 2:
				viewSpecificStudent(keyboard, teacher);
				break;
			case 3:
				removeStudent(keyboard, teacher);
				break;
			case 4:
				exitStudentMenu = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again."); // This is redundant now but kept for
																			// consistency
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------
	private void assignmentMenu(Scanner keyboard, Teacher teacher) {
		boolean exitAssignmentMenu = false;
		while (!exitAssignmentMenu) {
			System.out.println("\nAssignment Menu:");
			System.out.println("1. Add Assignment");
			System.out.println("2. Change Assignment Weights");
			System.out.println("3. Add Marks");
			System.out.println("4. Change Marks");
			System.out.println("5. View Assignment Averages");
			System.out.println("6. View Assignment Mode");
			System.out.println("7. View and Mark Unmarked Assignments");
			System.out.println("8. View All Students' Marks for an Assignment");
			System.out.println("9. View Assignment Types and Weights");
			System.out.println("10. Return to Teacher Menu");

			int choice = -1; // Initialize choice to an invalid value
			while (true) {
				try {
					System.out.println("Select an option (1-10): ");
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear buffer
					if (choice >= 1 && choice <= 10) {
						break; // Valid choice, exit the loop
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 10.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 10.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				addAssignment(keyboard, teacher);
				break;
			case 2:
				setAssignmentWeights(keyboard, teacher);
				break;
			case 3:
				addMarks(keyboard, teacher.getClassList());
				break;
			case 4:
				System.out.println("Enter the assignment name to change marks:");
				String assignmentName = keyboard.nextLine();
				changeMarks(keyboard, teacher.getClassList(), assignmentName);
				break;
			case 5:
				getAssignmentAvg(keyboard, teacher.getClassList());
				break;
			case 6:
				displayAssignmentMode(keyboard, teacher);
				break;
			case 7:
				viewAndMarkUnmarkedAssignments(keyboard, teacher.getClassList());
				break;
			case 8:
				viewAllStudentsMarksForAssignment(keyboard, teacher.getClassList());
				break;
			case 9:
				displayAssignmentTypesAndWeights(teacher);
				break;
			case 10:
				exitAssignmentMenu = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again."); // This is redundant but kept for consistency
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------
	private void attendanceMenu(Scanner keyboard, Teacher teacher) {
		boolean exitAttendanceMenu = false;
		while (!exitAttendanceMenu) {
			System.out.println("\nAttendance Menu:");
			System.out.println("1. Add Attendance for All Students");
			System.out.println("2. View Attendance Records for All Students");
			System.out.println("3. View Attendance for a Specific Date");
			System.out.println("4. Edit Attendance for a Student");
			System.out.println("5. Excuse Absences for a Student");
			System.out.println("6. Return to Teacher Menu");

			int choice = -1; // Initialize choice to an invalid value
			while (true) {
				try {
					System.out.println("Select an option (1-6): ");
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear buffer
					if (choice >= 1 && choice <= 6) {
						break; // Valid choice, exit the loop
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 6.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 6.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				addAttendanceForAllStudents(keyboard, teacher.getClassList());
				break;
			case 2:
				viewAllAttendanceRecords(teacher.getClassList());
				break;
			case 3:
				viewAttendanceForSpecificDate(keyboard, teacher.getClassList());
				break;
			case 4:
				editAttendanceForStudent(keyboard, teacher.getClassList());
				break;
			case 5:
				excuseAbsences(keyboard, teacher.getClassList());
				break;
			case 6:
				exitAttendanceMenu = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again."); // This is redundant but kept for consistency
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------  
	private void generalClassMenu(Scanner keyboard, Teacher teacher) {
		boolean exitGeneralMenu = false;
		while (!exitGeneralMenu) {
			System.out.println("\nGeneral Class Menu:");
			System.out.println("1. Display All Students");
			System.out.println("2. Display All Students with Averages"); // New Option
			System.out.println("3. View Class Average");
			System.out.println("4. Display Failing Students");
			System.out.println("5. View Top Students");
			System.out.println("6. View Weakest Students");
			System.out.println("7. Return to Teacher Menu");

			int choice = -1; // Initialize with an invalid value
			while (true) {
				try {
					System.out.println("Select an option (1-7): ");
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear buffer
					if (choice >= 1 && choice <= 7) {
						break; // Valid choice, exit the loop
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 7.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 7.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				displayClassList(teacher.getClassList());
				break;
			case 2:
				displayAllStudentsWithAverages(teacher.getClassList().getClassList());
				break;
			case 3:
				getClassAvg(teacher.getClassList().getClassList());
				break;
			case 4:
				displayFailingStudents(teacher.getClassList().getClassList());
				break;
			case 5:
				int top = -1;
				while (true) {
					try {
						System.out.println("Enter the number of top marks to view:");
						top = keyboard.nextInt();
						keyboard.nextLine(); // Clear buffer
						if (top > 0) {
							break; // Valid number, exit the loop
						} else {
							System.out.println("Please enter a positive number.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a valid number.");
						keyboard.nextLine(); // Clear invalid input
					}
				}
				viewTopStudents(top, teacher);
				break;
			case 6:
				int lowest = -1;
				while (true) {
					try {
						System.out.println("Enter the number of lowest marks to view:");
						lowest = keyboard.nextInt();
						keyboard.nextLine(); // Clear buffer
						if (lowest > 0) {
							break; // Valid number, exit the loop
						} else {
							System.out.println("Please enter a positive number.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a valid number.");
						keyboard.nextLine(); // Clear invalid input
					}
				}
				viewWeakestStudents(lowest, teacher);
				break;
			case 7:
				exitGeneralMenu = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again."); // Redundant but ensures smooth flow
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------
	// Student view
	public void studentView(Scanner keyboard, Teacher teacher) {
		System.out.println("\nStudent View:");
		System.out.println("Enter Student ID:");
		String id = keyboard.nextLine();

		if (!teacher.getClassList().StudentIDExist(id)) {
			System.out.println("Student not found. Please check the ID and try again.");
			return;
		}

		Student currentStudent = null;
		for (Student student : teacher.getClassList().getClassList()) {
			if (student.getStudentID().equals(id)) {
				currentStudent = student;
				break;
			}
		}

		if (currentStudent == null) {
			System.out.println("Student not found. Please check the ID and try again.");
			return;
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
			System.out.println("5. View behavior"); // Updated
			System.out.println("6. View customized report");
			System.out.println("7. Change password");
			System.out.println("8. View grading structure");
			System.out.println("9. View all marks"); // New Option
			System.out.println("10. Return to Main menu");

			int choice = -1; // Initialize with invalid value
			while (true) {
				try {
					System.out.println("Select an option (1-10): ");
					choice = keyboard.nextInt();
					keyboard.nextLine(); // Clear buffer
					if (choice >= 1 && choice <= 10) {
						break; // Valid choice, exit loop
					} else {
						System.out.println("Invalid choice. Please enter a number between 1 and 10.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a number between 1 and 10.");
					keyboard.nextLine(); // Clear invalid input
				}
			}

			switch (choice) {
			case 1:
				System.out.println("How many top marks do you want to see?");
				int top = -1;
				while (true) {
					try {
						top = keyboard.nextInt();
						keyboard.nextLine();
						if (top > 0) {
							break; // Valid input
						} else {
							System.out.println("Please enter a positive number.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a positive number.");
						keyboard.nextLine(); // Clear invalid input
					}
				}
				System.out.println("Top Marks: " + getTopMarks(currentStudent, top));
				break;

			case 2:
				System.out.println("How many lowest marks do you want to see?");
				int lowest = -1;
				while (true) {
					try {
						lowest = keyboard.nextInt();
						keyboard.nextLine();
						if (lowest > 0) {
							break; // Valid input
						} else {
							System.out.println("Please enter a positive number.");
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a positive number.");
						keyboard.nextLine(); // Clear invalid input
					}
				}
				System.out.println("Lowest Marks: " + getLowestMarks(currentStudent, lowest));
				break;

			case 3:
				System.out.println("Average Marks: " + currentStudent.getAverage());
				break;

			case 4:
				System.out.println("Attendance: " + getAttendance(currentStudent));
				break;

			case 5:
				viewBehaviorLog(currentStudent); // New method for viewing behavior
				break;

			case 6:
				generateCustomReport(keyboard, currentStudent);
				break;

			case 7:
				changePassword(currentStudent, keyboard);
				break;

			case 8:
				viewGradingStructure(teacher);
				break;

			case 9:
				viewAllMarks(currentStudent); // New Case
				break;

			case 10:
				System.out.println("Returning to main menu.");
				exit = true;
				break;

			default:
				System.out.println("Invalid choice. Please try again."); // Redundant with validation
			}
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------

//** the ones at the bottom are random/not implemented yet

//all of the helper methods

	// *********************************************
	// student menu (teacher view) methods
	// ----------------------------------------------------------------------------------------------------------------------------------------------
	public void addStudent(Scanner keyboard, Teacher teacher) {// Add a new student with an auto-generated ID and
																// password
		System.out.println("Enter the first name of the new student:");
		String firstName = keyboard.nextLine();
		System.out.println("Enter the last name of the new student:");
		String lastName = keyboard.nextLine();

		Student newStudent = new Student(firstName, lastName, teacher.getClasslist());
		teacher.addStudent(newStudent);

		System.out.println("Student " + newStudent.getFullName() + " added with ID: " + newStudent.getStudentID()
				+ " and password: " + newStudent.getPassword());
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------------------------------------------------- 
	private void viewSpecificStudent(Scanner keyboard, Teacher teacher) {
	    // Display all students
	    System.out.println("Current Students in Class:");
	    for (Student s : teacher.getClassList().getClassList()) {
	        System.out.println("- ID: " + s.getStudentID() + ", Name: " + s.getFullName());
	    }

	    System.out.println("Enter the student ID:");
	    String studentId = keyboard.nextLine();

	    // Find the student by ID
	    Student student = teacher.getClassList().getStudentByID(studentId);

	    if (student == null) {
	        System.out.println("Student not found.");
	        return;
	    }

	    // Display student details
	    System.out.println("\nStudent Details:");
	    System.out.println("Name: " + student.getFullName());
	    System.out.println("ID: " + student.getStudentID());
	    System.out.println("Grades: " + student.getAssignments());
	    System.out.println("Average Grade: " + student.getAverage());

	    boolean exit = false;
	    while (!exit) {
	        // Student-specific menu
	        System.out.println("\nWhat would you like to do?");
	        System.out.println("1. Add an assignment");
	        System.out.println("2. Change a grade");
	        System.out.println("3. Add behavior comments");
	        System.out.println("4. Record attendance");
	        System.out.println("5. Return to previous menu");

	        int choice = -1;
	        while (true) {
	            try {
	                System.out.print("Enter your choice: ");
	                choice = keyboard.nextInt();
	                keyboard.nextLine(); // Clear the buffer
	                if (choice >= 1 && choice <= 5) {
	                    break; // Valid choice
	                } else {
	                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a number between 1 and 5.");
	                keyboard.nextLine(); // Clear invalid input
	            }
	        }

	        switch (choice) {
	            case 1:
	                System.out.println("Enter the name of the assignment:");
	                String name = keyboard.nextLine().trim();

	                System.out.println("Enter a comment for the assignment (or leave blank):");
	                String assignmentComment = keyboard.nextLine().trim();

	                // Display assignment types from AssignmentManager
	                AssignmentManager assignmentManager = teacher.getClassList().getAssignmentManager();
	                System.out.println("Choose the type of the assignment:");
	                ArrayList<AssignmentType> assignmentTypes = assignmentManager.getAssignmentTypes();
	                for (int i = 0; i < assignmentTypes.size(); i++) {
	                    AssignmentType type = assignmentTypes.get(i);
	                    System.out.println((i + 1) + ". " + type.getName() + " (Default weight: " + type.getWeight() + "%)");
	                }

	                int typeChoice = -1;
	                while (typeChoice < 1 || typeChoice > assignmentTypes.size()) {
	                    try {
	                        System.out.print("Enter the number corresponding to the assignment type: ");
	                        typeChoice = keyboard.nextInt();
	                        keyboard.nextLine(); // Clear buffer
	                    } catch (InputMismatchException e) {
	                        System.out.println("Invalid input. Please enter a valid number.");
	                        keyboard.nextLine(); // Clear invalid input
	                    }
	                }

	                // Get the chosen type
	                AssignmentType chosenType = assignmentTypes.get(typeChoice - 1);
	                Assignment assignment = new Assignment(name, assignmentComment, chosenType.getWeight(), chosenType);
	                student.addAssignment(assignment);
	                System.out.println("Assignment added successfully.");
	                break;

	            case 2:
	                System.out.println("Enter the name of the assignment to change:");
	                String assignmentToChange = keyboard.nextLine();
	                Assignment assignmentToModify = student.getAssignmentByName(assignmentToChange);
	                if (assignmentToModify != null) {
	                    double newGrade = -1;
	                    while (newGrade < 0 || newGrade > 100) {
	                        try {
	                            System.out.print("Enter the new grade: ");
	                            newGrade = keyboard.nextDouble();
	                            keyboard.nextLine(); // Clear buffer
	                        } catch (InputMismatchException e) {
	                            System.out.println("Invalid input. Please enter a valid grade (0-100).");
	                            keyboard.nextLine(); // Clear invalid input
	                        }
	                    }
	                    assignmentToModify.setMark(newGrade);
	                    System.out.println("Grade updated successfully.");
	                } else {
	                    System.out.println("Assignment not found.");
	                }
	                break;

	            case 3:
	                String date = null;
	                while (true) {
	                    System.out.print("Enter the date for the behavior (YYYY-MM-DD): ");
	                    date = keyboard.nextLine().trim();
	                    if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
	                        break; // Valid date
	                    } else {
	                        System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
	                    }
	                }

	                System.out.println("Enter the behavior details:");
	                String behaviorDetails = keyboard.nextLine();
	                student.addBehavior(new Behavior(date, behaviorDetails));
	                System.out.println("Behavior added successfully.");
	                break;

	            case 4:
	                String attendanceDate = null;
	                while (true) {
	                    System.out.print("Enter the date for attendance (YYYY-MM-DD): ");
	                    attendanceDate = keyboard.nextLine().trim();
	                    if (attendanceDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
	                        break; // Valid date
	                    } else {
	                        System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
	                    }
	                }

	                boolean isPresent = false;
	                while (true) {
	                    System.out.print("Was the student present? (y/n): ");
	                    String presentInput = keyboard.nextLine().trim().toLowerCase();
	                    if (presentInput.equals("y")) {
	                        isPresent = true;
	                        break;
	                    } else if (presentInput.equals("n")) {
	                        isPresent = false;
	                        break;
	                    } else {
	                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
	                    }
	                }

	                student.addAttendanceRecord(new Attendance(attendanceDate, isPresent));
	                System.out.println("Attendance recorded successfully.");
	                break;

	            case 5:
	                exit = true;
	                break;

	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}


//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void removeStudent(Scanner keyboard, Teacher teacher) {
		// Display all students
		System.out.println("Current Students in Class:");
		displayClassList(teacher.getClassList());

		// Prompt for student ID
		System.out.println("Enter the student ID to remove:");
		String studentId = keyboard.nextLine();

		// Attempt to remove the student
		if (teacher.getClassList().removeStudentByID(studentId)) {
			System.out.println("Student with ID " + studentId + " has been removed.");
		} else {
			System.out.println("No student found with ID " + studentId + ".");
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//*************************************
	// assignment menu methods
//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void addAssignments(Scanner keyboard, ClassList classList) { // Add assignments
		AssignmentManager assignmentManager = classList.getAssignmentManager(); // Access the AssignmentManager

		// Ask the teacher for the number of assignments to add
		System.out.println("How many assignments would you like to add?");
		int numOfAssignments = keyboard.nextInt();
		keyboard.nextLine(); // Clear buffer

		// Ask the teacher for the name of each assignment and type
		for (int i = 0; i < numOfAssignments; i++) {
			System.out.println("Enter name of assignment #" + (i + 1) + ": ");
			String assignmentName = keyboard.nextLine().trim();

			// Ask the teacher for the assignment type (Quiz, Essay, Extra Credit, etc.)
			System.out.println("Choose the assignment type: ");
			ArrayList<AssignmentType> assignmentTypes = assignmentManager.getAssignmentTypes();
			for (int j = 0; j < assignmentTypes.size(); j++) {
				AssignmentType type = assignmentTypes.get(j);
				System.out.println((j + 1) + ". " + type.getName() + " (Default weight: " + type.getWeight() + "%)");
			}

			int typeChoice = -1;
			while (typeChoice < 1 || typeChoice > assignmentTypes.size()) {
				System.out.println("Enter the number corresponding to the assignment type:");
				typeChoice = keyboard.nextInt();
				keyboard.nextLine(); // Clear buffer
			}

			// Get the chosen type
			AssignmentType chosenType = assignmentTypes.get(typeChoice - 1);

			// Ask for a comment on the assignment
			System.out.println("Enter comment for the assignment: ");
			String comment = keyboard.nextLine();

			// Create a new assignment with the chosen type
			Assignment newAssignment = new Assignment(assignmentName, comment, chosenType.getWeight(), chosenType);

			// Add the assignment to the class
			if (classList.assignmentExists(assignmentName)) {
				System.out.println("An assignment with this name already exists. Skipping...");
			} else {
				classList.addAssignmentToClass(newAssignment);
				System.out.println("Assignment added successfully.");
			}
		}
	}

	public void addAssignment(Scanner keyboard, Teacher teacher) {
		// Access the AssignmentManager to handle assignment types and weights
		AssignmentManager assignmentManager = teacher.getClassList().getAssignmentManager();

		// Ask for assignment name
		System.out.println("Enter the name of the assignment:");
		String name = keyboard.nextLine().trim();

		// Ask for a comment
		System.out.println("Enter a comment for the assignment (or leave blank):");
		String comment = keyboard.nextLine().trim();

		// Display available assignment types
		ArrayList<AssignmentType> assignmentTypes = assignmentManager.getAssignmentTypes();
		if (assignmentTypes.isEmpty()) {
			System.out.println(
					"No assignment types available. Please add assignment types in the Assignment Manager first.");
			return;
		}

		System.out.println("Choose the type of the assignment:");
		for (int i = 0; i < assignmentTypes.size(); i++) {
			AssignmentType type = assignmentTypes.get(i);
			System.out.println((i + 1) + ". " + type.getName() + " (Default weight: " + type.getWeight() + "%)");
		}

		// Get the teacher's choice
		int typeChoice = -1;
		while (typeChoice < 1 || typeChoice > assignmentTypes.size()) {
			System.out.println("Enter the number corresponding to the assignment type:");
			if (keyboard.hasNextInt()) {
				typeChoice = keyboard.nextInt();
				keyboard.nextLine(); // Clear the buffer
				if (typeChoice < 1 || typeChoice > assignmentTypes.size()) {
					System.out.println("Invalid choice. Please select a valid assignment type.");
				}
			} else {
				System.out.println("Please enter a valid number.");
				keyboard.nextLine(); // Clear the buffer
			}
		}

		// Get the chosen type
		AssignmentType chosenType = assignmentTypes.get(typeChoice - 1);

		// Create a new Assignment with the chosen type and weight
		Assignment assignment = new Assignment(name, comment, chosenType.getWeight(), chosenType);

		// Add the assignment to the class list (and to students)
		if (teacher.getClassList().assignmentExists(name)) {
			System.out.println("An assignment with this name already exists.");
		} else {
			teacher.getClassList().addAssignmentToClass(assignment);
			System.out.println("Assignment added successfully.");
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void setAssignmentWeights(Scanner keyboard, Teacher teacher) {
		AssignmentManager assignmentManager = teacher.getClassList().getAssignmentManager(); // Access the
		// AssignmentManager

		System.out.println("\nCurrent Assignment Weights:");
		assignmentManager.displayAssignmentTypesAndWeights();

		System.out.println("\nWould you like to adjust the assignment weights? (yes/no)");
		String response = keyboard.nextLine().trim().toLowerCase();

		if (!response.equals("yes")) {
			System.out.println("No changes made to the assignment weights.");
			return;
		}

		boolean weightsValid = false;
		while (!weightsValid) {
			double totalWeight = 0.0;

			// Ask the teacher to input weights for each assignment type
			for (AssignmentType type : assignmentManager.getAssignmentTypes()) {
				System.out.printf("Enter weight for %s: ", type.getName());
				double weight = -1;
				while (weight < 0 || weight > 100) {
					try {
						weight = Double.parseDouble(keyboard.nextLine().trim());
						if (weight < 0 || weight > 100) {
							System.out.println("Weight must be between 0 and 100. Please try again.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a numeric value.");
					}
				}
				type.setWeight(weight);
				totalWeight += weight;
			}

			// Validate total weight
			if (totalWeight != 100.0) {
				System.out.printf("Total weight is %.2f%%. The total must equal 100%%. Please re-enter all weights.\n",
						totalWeight);
			} else {
				weightsValid = true;
			}
		}

		System.out.println("Weights updated successfully.");
		assignmentManager.displayAssignmentTypesAndWeights();
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void addMarks(Scanner keyboard, ClassList classList) {// Add students marks for specified assignment
																	// passed in as an argument
		// Check if there are assignments in the class
		if (classList.getAssignments().isEmpty()) {
			System.out.println("No assignments available in the class.");
			return;
		}

		// Display assignments for the teacher to select
		System.out.println("Choose an assignment to add marks for:");
		ArrayList<Assignment> assignments = classList.getAssignments();
		for (int i = 0; i < assignments.size(); i++) {
			System.out.println((i + 1) + ". " + assignments.get(i).getName());
		}

		// Get the teacher's choice
		int assignmentChoice = -1;
		while (assignmentChoice < 1 || assignmentChoice > assignments.size()) {
			System.out.println("Enter the number corresponding to the assignment:");
			assignmentChoice = keyboard.nextInt();
			keyboard.nextLine(); // Clear the buffer
		}

		// Get the selected assignment
		Assignment selectedAssignment = assignments.get(assignmentChoice - 1);

		// Add marks for each student
		ArrayList<Student> students = classList.getClassList();
		if (students.isEmpty()) {
			System.out.println("No students in the class to assign marks to.");
			return;
		}

		System.out.println("Enter marks for the assignment: " + selectedAssignment.getName());
		for (Student student : students) {
			System.out.println("Enter mark for " + student.getFullName() + " (ID: " + student.getStudentID() + "):");
			double mark = -1;
			while (mark < 0 || mark > 100) {
				System.out.println("Please enter a valid mark between 0 and 100:");
				mark = keyboard.nextDouble();
				keyboard.nextLine(); // Clear the buffer
			}

			// Update the mark for the selected assignment for the current student
			boolean assignmentFound = false;
			for (Assignment assignment : student.getAssignments()) {
				if (assignment.getName().equals(selectedAssignment.getName())) {
					assignment.setMark(mark);
					assignmentFound = true;
					break;
				}
			}

			// If the student does not already have this assignment, add it
			if (!assignmentFound) {
				Assignment newAssignment = new Assignment(selectedAssignment.getName(), selectedAssignment.getComment(),
						selectedAssignment.getWeight(), selectedAssignment.getType());
				newAssignment.setMark(mark);
				student.addAssignment(newAssignment);
			}
		}

		System.out.println("Marks successfully added for all students.");
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void changeMarks(Scanner keyboard, ClassList classList, String assignmentName) {
	    // Change existing student marks
	    int choice = -1;
	    do {
	        try {
	            System.out.println("1. Change marks for all students.");
	            System.out.println("2. Change marks for a specific student.");
	            System.out.print("Enter your choice: ");
	            choice = keyboard.nextInt();
	            keyboard.nextLine(); // Clear buffer

	            switch (choice) {
	                case 1:
	                    // Change marks for all students
	                    ArrayList<Student> students = classList.getClassList();
	                    for (Student student : students) {
	                        boolean assignmentFound = false;
	                        for (Assignment assignment : student.getAssignments()) {
	                            if (assignment.getName().equals(assignmentName)) {
	                                System.out.println("Current mark for " + student.getFullName() + " (ID: "
	                                        + student.getStudentID() + ") is: "
	                                        + (assignment.getMark() == -1 ? "Not marked yet" : assignment.getMark()));
	                                double mark = getValidMark(keyboard, "Enter new mark for " + student.getFullName() + ":");
	                                assignment.setMark(mark);
	                                assignmentFound = true;
	                                break;
	                            }
	                        }
	                        // If the student does not have the assignment, notify the teacher
	                        if (!assignmentFound) {
	                            System.out.println("Assignment not found for " + student.getFullName() + ". Skipping...");
	                        }
	                    }
	                    break;

	                case 2:
	                    // Change marks for a specific student
	                    System.out.print("Enter the ID of the student: ");
	                    String studentID = keyboard.nextLine().trim();
	                    Student targetStudent = classList.getStudentByID(studentID);

	                    if (targetStudent == null) {
	                        System.out.println("Student with ID " + studentID + " not found.");
	                    } else {
	                        boolean assignmentFound = false;
	                        for (Assignment assignment : targetStudent.getAssignments()) {
	                            if (assignment.getName().equals(assignmentName)) {
	                                System.out.println("Current mark for " + targetStudent.getFullName() + " is: "
	                                        + (assignment.getMark() == -1 ? "Not marked yet" : assignment.getMark()));
	                                double mark = getValidMark(keyboard, "Enter new mark for " + targetStudent.getFullName() + ":");
	                                assignment.setMark(mark);
	                                assignmentFound = true;
	                                System.out.println("Mark updated successfully.");
	                                break;
	                            }
	                        }

	                        if (!assignmentFound) {
	                            System.out.println("Assignment not found for " + targetStudent.getFullName() + ".");
	                        }
	                    }
	                    break;

	                default:
	                    System.out.println("Invalid input. Please choose 1 or 2.");
	                    break;
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	            keyboard.nextLine(); // Clear invalid input
	        }
	    } while (choice != 1 && choice != 2);
	}

	// Helper method to validate marks input
	private double getValidMark(Scanner keyboard, String prompt) {
	    double mark = -1;
	    while (mark < 0 || mark > 100) {
	        try {
	            System.out.print(prompt);
	            mark = keyboard.nextDouble();
	            keyboard.nextLine(); // Clear buffer
	            if (mark < 0 || mark > 100) {
	                System.out.println("Invalid mark. Please enter a value between 0 and 100.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a numeric value.");
	            keyboard.nextLine(); // Clear invalid input
	        }
	    }
	    return mark;
	}


//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void getAssignmentAvg(Scanner keyboard, ClassList classList) {
		// Get assignment average
		ArrayList<Student> students = classList.getClassList();
		ArrayList<Assignment> classAssignments = classList.getAssignments();

		if (classAssignments.isEmpty()) {
			System.out.println("There are no assignments yet.");
			return;
		}

		double marksSum = 0.0;
		int marksCount = 0;
		boolean askAgain;

		do {
			askAgain = false;
			System.out.println("Current Assignments in Class:");
			for (Assignment assignment : classList.getAssignments()) {
				System.out.printf("- %s (Type: %s, Weight: %.2f%%)%n", assignment.getName(),
						assignment.getType().getName(), assignment.getWeight());
			}

			// Ask for the specific assignment
			System.out.println("Enter the name of the assignment to calculate its average:");
			String assignmentName = keyboard.nextLine().trim();

			boolean assignmentExists = false;

			// Iterate through all students to calculate the average for the given
			// assignment
			for (Student student : students) {
				for (Assignment assignment : student.getAssignments()) {
					if (assignment.getName().equalsIgnoreCase(assignmentName)) {
						assignmentExists = true;
						if (assignment.getMark() >= 0) { // Include only marked assignments
							marksSum += assignment.getMark(); // Sum up the marks
							marksCount++; // Count how many marks have been summed
						}
						break;
					}
				}
			}

			if (!assignmentExists) {
				System.out.println("Assignment not found. Would you like to enter a different name? (y/n)");
				String choice = keyboard.nextLine().toLowerCase();

				if (choice.equals("y")) {
					askAgain = true;
				} else {
					System.out.println("Returning to the menu.");
					return;
				}
			}
		} while (askAgain);

		// Display the assignment average
		if (marksCount > 0) {
			double average = marksSum / marksCount;
			System.out.printf("The average mark for the assignment is: %.2f%n", average);
		} else {
			System.out.println("No marks found for this assignment.");
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void viewAllStudentsMarksForAssignment(Scanner keyboard, ClassList classList) {
		ArrayList<Assignment> assignments = classList.getAssignments();

		if (assignments.isEmpty()) {
			System.out.println("No assignments found.");
			return;
		}

		// Display assignments for the teacher to choose from
		System.out.println("Choose an assignment to view marks:");
		for (int i = 0; i < assignments.size(); i++) {
			Assignment assignment = assignments.get(i);
			System.out.printf("%d. %s | Type: %s | Weight: %.2f%%%n", i + 1, assignment.getName(),
					assignment.getType().getName(), assignment.getWeight());
		}

		int assignmentChoice = -1;
		while (assignmentChoice < 1 || assignmentChoice > assignments.size()) {
			System.out.println("Enter the number corresponding to the assignment:");
			assignmentChoice = keyboard.nextInt();
			keyboard.nextLine(); // Clear the buffer
		}

		// Get the selected assignment
		Assignment selectedAssignment = assignments.get(assignmentChoice - 1);

		// Display marks for all students for the chosen assignment
		System.out.printf("\nMarks for Assignment: %s%n", selectedAssignment.getName());
		boolean marksExist = false;

		for (Student student : classList.getClassList()) {
			for (Assignment assignment : student.getAssignments()) {
				if (assignment.getName().equals(selectedAssignment.getName())) {
					System.out.printf("Student: %s | Mark: %.2f%n", student.getFullName(), assignment.getMark());
					marksExist = true;
					break;
				}
			}
		}

		if (!marksExist) {
			System.out.println("No marks found for this assignment.");
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------

	private void displayAssignmentTypesAndWeights(Teacher teacher) {
		System.out.println("Current Assignment Types and Weights:");
		AssignmentManager assignmentManager = teacher.getClassList().getAssignmentManager();

		double totalWeight = 0;
		for (AssignmentType type : assignmentManager.getAssignmentTypes()) {
			totalWeight += type.getWeight();
			System.out.printf("%s: %.2f%%%n", type.getName(), type.getWeight());
		}

		System.out.printf("Total Weight: %.2f%%%n", totalWeight);
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void displayAssignmentMode(Scanner keyboard, Teacher teacher) {
		System.out.println("Current Assignments in Class:");
		for (Assignment assignment : teacher.getClassList().getAssignments()) {
			System.out.printf("- %s (Type: %s, Weight: %.2f%%)%n", assignment.getName(), assignment.getType().getName(),
					assignment.getWeight());
		}

		System.out.println("Enter the name of the assignment to calculate its mode:");
		String assignmentName = keyboard.nextLine().trim();

		// Get the ClassList object
		ClassList classList = teacher.getClassList();

		// Calculate the mode using the ClassList method (only for marked assignments)
		ArrayList<Double> modes = classList.calculateModeForAssignment(assignmentName);

		// Display results
		if (modes.isEmpty()) {
			System.out.println("No mode found for the assignment: " + assignmentName
					+ " (all marks are unique, unmarked, or no marks available).");
		} else {
			System.out.print("The mode(s) for the assignment " + assignmentName + " is/are: ");
			for (int i = 0; i < modes.size(); i++) {
				System.out.print(modes.get(i));
				if (i < modes.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void viewAndMarkUnmarkedAssignments(Scanner keyboard, ClassList classList) {
		ArrayList<Student> students = classList.getClassList();
		ArrayList<Assignment> assignments = classList.getAssignments();

		// Collect assignments with missing marks
		ArrayList<String> unmarkedAssignments = new ArrayList<>();

		for (Assignment assignment : assignments) {
			boolean isUnmarked = false;
			for (Student student : students) {
				for (Assignment studentAssignment : student.getAssignments()) {
					if (studentAssignment.getName().equalsIgnoreCase(assignment.getName())
							&& studentAssignment.getMark() == -1) { // Assuming unmarked is represented by -1
						isUnmarked = true;
						break;
					}
				}
				if (isUnmarked)
					break;
			}
			if (isUnmarked) {
				unmarkedAssignments.add(assignment.getName());
			}
		}

		// Display unmarked assignments
		if (unmarkedAssignments.isEmpty()) {
			System.out.println("All assignments are marked.");
		} else {
			System.out.println("\nUnmarked Assignments:");
			for (int i = 0; i < unmarkedAssignments.size(); i++) {
				System.out.println((i + 1) + ". " + unmarkedAssignments.get(i));
			}

			// Prompt the teacher to mark an assignment
			System.out.println("Enter the number of the assignment to mark, or 0 to return:");
			int choice = keyboard.nextInt();
			keyboard.nextLine(); // clear buffer

			if (choice > 0 && choice <= unmarkedAssignments.size()) {
				String selectedAssignment = unmarkedAssignments.get(choice - 1);

				// Mark the selected assignment for all students
				for (Student student : students) {
					for (Assignment studentAssignment : student.getAssignments()) {
						if (studentAssignment.getName().equalsIgnoreCase(selectedAssignment)
								&& studentAssignment.getMark() == -1) { // Unmarked
							System.out.println("Enter mark for " + student.getFullName() + " (ID: "
									+ student.getStudentID() + "):");
							double mark = -1;
							while (mark < 0 || mark > 100) {
								System.out.println("Please enter a valid mark between 0 and 100:");
								mark = keyboard.nextDouble();
								keyboard.nextLine(); // clear buffer
							}
							studentAssignment.setMark(mark);
						}
					}
				}
				System.out.println("Marks updated successfully for assignment: " + selectedAssignment);
			} else if (choice != 0) {
				System.out.println("Invalid choice. Returning to assignment menu.");
			}
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//*************************************

//general class menu
//-------------------------------------------------------------------------------------------------------------------------------------------------
//Print list of names of students passed in as an argument
	public void displayClassList(ClassList classList) {
		ArrayList<Student> students = classList.getClassList();
		alphabetizeClasslist(students); // Ensure alphabetical order
		for (Student student : students) {
			System.out.println(student.getFullName() + " - ID: " + student.getStudentID());
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void displayAllStudentsWithAverages(ArrayList<Student> students) {
		if (students == null || students.isEmpty()) {
			System.out.println("There are no students in the class.");
			return;
		}

		System.out.println("\nStudents and Their Averages:");
		for (Student student : students) {
			System.out.printf("Name: %s | ID: %s | Average: %.2f%n", student.getFullName(), student.getStudentID(),
					student.getAverage());
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void changePasscode(Teacher teacher, Scanner keyboard) {
	    System.out.println("Enter Current password: ");
	    String currCode = keyboard.nextLine();
	    
	    if (currCode.equals(teacher.getPasscode())) {
	        String new1 = "";
	        String new2 = "";
	        boolean validPassword = false;

	        // Loop to ensure the password meets criteria
	        while (!validPassword) {
	            System.out.println("Enter new password (at least 5 characters, no spaces):");
	            new1 = keyboard.nextLine().trim();
	            
	            if (new1.length() < 5) {
	                System.out.println("Password must be at least 5 characters long.");
	            } else if (new1.contains(" ")) {
	                System.out.println("Password must not contain spaces.");
	            } else {
	                validPassword = true;
	            }
	        }

	        System.out.println("Enter new password again:");
	        new2 = keyboard.nextLine().trim();

	        if (new1.equals(new2)) {
	            teacher.setPasscode(new1);
	            System.out.println("Password is reset. New passcode is " + new1);
	        } else {
	            System.out.println("There was a mismatch between the first and second password you entered.");
	        }

	    } else {
	        System.out.println("You entered the wrong current password.");
	    }
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
//Get class's average
	public void getClassAvg(ArrayList<Student> students) {
		if (students == null || students.isEmpty()) {
			System.out.println("There are no students in the class.");
			return;
		}
		int marksSum = 0;
		for (Student s : students) {
			marksSum += s.getAverage();
		}
		System.out.println(marksSum / students.size());
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
//Get students who are failing the class, and their marks
	public void displayFailingStudents(ArrayList<Student> students) {
		if (students == null || students.isEmpty()) {
			System.out.println("No students in the class.");
			return;
		}

		final int PASSING_GRADE = 65;
		ArrayList<Student> failingStudents = new ArrayList<>();

		// Add failing students to the failingStudents ArrayList
		for (Student s : students) {
			// Include students with an average below the passing grade and with marks
			// assigned
			if (s.getAverage() < PASSING_GRADE && s.getAverage() != 0) {
				failingStudents.add(s);
			}
		}

		// Alphabetize the failing students
		alphabetizeClasslist(failingStudents);

		// Print the failing students and their marks
		if (failingStudents.isEmpty()) {
			System.out.println("There are no failing students.");
		} else {
			printStudentsWithMarks(failingStudents);
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void viewTopStudents(int top, Teacher teacher) {
		ArrayList<Student> students = teacher.getClassList().getClassList();

		if (students.isEmpty()) {
			System.out.println("No students in the class.");
			return;
		}

		// Sort students by their average in descending order
		students.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));

		System.out.println("\nTop " + top + " Students:");
		for (int i = 0; i < Math.min(top, students.size()); i++) {
			Student student = students.get(i);
			System.out.println((i + 1) + ". " + student.getFullName() + " - Average: " + student.getAverage());
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	public void viewWeakestStudents(int lowest, Teacher teacher) {
		ArrayList<Student> students = teacher.getClassList().getClassList();

		if (students.isEmpty()) {
			System.out.println("No students in the class.");
			return;
		}

		// Sort students by their average in ascending order
		students.sort((s1, s2) -> Double.compare(s1.getAverage(), s2.getAverage()));

		System.out.println("\nWeakest " + lowest + " Students:");
		for (int i = 0; i < Math.min(lowest, students.size()); i++) {
			Student student = students.get(i);
			System.out.println((i + 1) + ". " + student.getFullName() + " - Average: " + student.getAverage());
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------

//***********************************************************
//Student view menu
//------------------------------------------------------------------------------
	private void changePassword(Student student, Scanner keyboard) {
		System.out.println("Enter Current password: ");
		String currCode = keyboard.nextLine();

		if (currCode.equals(student.getPassword())) {
			String new1 = "";
			String new2 = "";

			while (true) {
				System.out.println("Enter new password (minimum 5 characters, no spaces):");
				new1 = keyboard.nextLine();

				if (new1.length() < 5) {
					System.out.println("Password must be at least 5 characters long. Please try again.");
					continue; // Re-ask for input
				}

				if (new1.contains(" ")) {
					System.out.println("Password cannot contain spaces. Please try again.");
					continue; // Re-ask for input
				}

				System.out.println("Enter new password again:");
				new2 = keyboard.nextLine();

				if (!new1.equals(new2)) {
					System.out.println("Passwords do not match. Please try again.");
				} else {
					break; // Exit loop if passwords match
				}
			}

			student.setPassword(new1);
			System.out.println("Password is reset successfully.");
		} else {
			System.out.println("You entered the wrong current password.");
		}
	}

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
	private String getAttendance(Student currentStudent) {
	    ArrayList<Attendance> attendanceRecords = currentStudent.getAttendanceRecords();

	    if (attendanceRecords.isEmpty()) {
	        return "No attendance records available.";
	    }

	    StringBuilder attendanceDetails = new StringBuilder();
	    attendanceDetails.append("Attendance Records:\n");

	    for (Attendance record : attendanceRecords) {
	        if (record.isPresent()) {
	            // Only include "Present" for present records
	            attendanceDetails.append("Date: ").append(record.getDate()).append(", Status: Present\n");
	        } else {
	            // Include both "Absent" and "Excused/Unexcused" for absent records
	            String excusedStatus = record.isExcused() ? "Excused" : "Unexcused";
	            attendanceDetails.append("Date: ").append(record.getDate())
	                             .append(", Status: Absent, Excused: ").append(excusedStatus).append("\n");
	        }
	    }

	    attendanceDetails.append(String.format("Attendance Grade: %.2f%%", currentStudent.calculateAttendanceGrade()));

	    return attendanceDetails.toString();
	}

//------------------------------------------------------------------------------

// ------------------------------------------------------------------------------
	public void getStudentAvg(Scanner keyboard, ArrayList<Student> students) throws EmptyClassException {
		if (students == null || students.isEmpty()) {
			System.out.println("There are no students in the class.");
			return;
		}

		// Display all students for reference
		System.out.println("Current Students in Class:");
		for (Student s : students) {
			System.out.println("- " + s.getFullName());
		}

		System.out.println("Enter the student's full name to get their average grade:");
		String studentName = keyboard.nextLine().trim(); // Trim input to remove leading/trailing spaces

		boolean studentExists = false;
		for (Student s : students) {
			// Case-insensitive comparison for name matching
			if (s.getFullName().equalsIgnoreCase(studentName)) {
				System.out.printf("The average grade for %s is: %.2f%n", s.getFullName(), s.getAverage());
				studentExists = true;
				break; // Exit loop once the student is found
			}
		}

		// If the student does not exist, display a message
		if (!studentExists) {
			System.out.println("Student not found. Please ensure the name is entered correctly.");
		}
	}

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------
	private String getTopMarks(Student student, int top) {
		// Get assignments from the student
		ArrayList<Assignment> assignments = student.getAssignments();

		if (assignments == null || assignments.isEmpty()) {
			return "No assignments available yet.";
		}

		// Extract assignments and sort by marks in descending order
		assignments.sort((a1, a2) -> Double.compare(a2.getMark(), a1.getMark()));

		// Get the top N marks with assignment names
		StringBuilder topMarks = new StringBuilder();
		topMarks.append("Top ").append(Math.min(top, assignments.size())).append(" Marks:\n");
		for (int i = 0; i < Math.min(top, assignments.size()); i++) {
			Assignment assignment = assignments.get(i);
			topMarks.append((i + 1)).append(". ").append(assignment.getName()).append(": ").append(assignment.getMark())
					.append("\n");
		}

		return topMarks.toString();
	}
//--------------------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------------------
	private String getLowestMarks(Student student, int lowest) {
		// Get assignments from the student
		ArrayList<Assignment> assignments = student.getAssignments();

		if (assignments == null || assignments.isEmpty()) {
			return "No assignments available yet.";
		}

		// Extract assignments and sort by marks in ascending order
		assignments.sort((a1, a2) -> Double.compare(a1.getMark(), a2.getMark()));

		// Get the lowest N marks with assignment names
		StringBuilder lowestMarks = new StringBuilder();
		lowestMarks.append("Lowest ").append(Math.min(lowest, assignments.size())).append(" Marks:\n");
		for (int i = 0; i < Math.min(lowest, assignments.size()); i++) {
			Assignment assignment = assignments.get(i);
			lowestMarks.append((i + 1)).append(". ").append(assignment.getName()).append(": ")
					.append(assignment.getMark()).append("\n");
		}

		return lowestMarks.toString();
	}

//--------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------
	private void viewAllMarks(Student student) {
		ArrayList<Assignment> assignments = student.getAssignments();

		if (assignments.isEmpty()) {
			System.out.println("No assignments found.");
			return;
		}

		System.out.println("\nAll Marks:");
		for (Assignment assignment : assignments) {
			System.out.printf("Assignment: %s | Type: %s | Weight: %.2f%% | Mark: %.2f%n", assignment.getName(),
					assignment.getType().getName(), assignment.getWeight(), assignment.getMark());
		}
	}

//--------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------
	private void viewGradingStructure(Teacher teacher) {
		AssignmentManager assignmentManager = teacher.getClassList().getAssignmentManager();
		ArrayList<AssignmentType> assignmentTypes = assignmentManager.getAssignmentTypes();

		System.out.println("\nGrading Structure:");
		System.out.printf("%-20s%-10s%n", "Assignment Type", "Weight");
		System.out.println("-------------------------------");

		double totalWeight = 0.0;

		for (AssignmentType type : assignmentTypes) {
			System.out.printf("%-20s%-10.2f%%%n", type.getName(), type.getWeight());
			totalWeight += type.getWeight();
		}

		System.out.println("-------------------------------");
		System.out.printf("%-20s%-10.2f%%%n", "Total Weight", totalWeight);

		// Validate if the total weight adds up to 100%
		if (totalWeight != 100.0) {
			System.out.println(
					"\nWarning: Total weight does not equal 100%. Please inform your teacher to adjust the grading structure.");
		}
	}

//------------------------------------------------------------------------------

//--------------------------------------------------------------------------------
	private static void generateCustomReport(Scanner keyboard, Student currentStudent) {
		System.out.println("Creating a custom report for " + currentStudent.getFullName() + " (ID: "
				+ currentStudent.getStudentID() + ")");

		// Ask for all assignments
		boolean includeAllAssignments = getYesOrNoInput(keyboard,
				"Do you want to include all assignment marks? (y/n):");

		// Ask for average
		boolean includeAverage = getYesOrNoInput(keyboard, "Do you want to include the average? (y/n):");

		// Ask for mode
		boolean includeMode = getYesOrNoInput(keyboard, "Do you want to include the mode of marks? (y/n):");

		// Ask for attendance
		boolean includeAttendance = getYesOrNoInput(keyboard, "Do you want to include attendance? (y/n):");

		// Ask for behavior
		boolean includeBehavior = getYesOrNoInput(keyboard, "Do you want to include behavior records? (y/n):");

		// Generate and display the custom report
		System.out.println("\nCustomized Report:");
		System.out.println("------------------");

		if (includeAllAssignments) {
			System.out.println("All Assignment Marks:");
			for (Assignment assignment : currentStudent.getAssignments()) {
				String mark = assignment.getMark() >= 0 ? String.format("%.2f", assignment.getMark()) : "Not Marked";
				System.out.printf("- %s (Type: %s): %s%n", assignment.getName(), assignment.getType().getName(), mark);
			}
		}

		if (includeAverage) {
			System.out.printf("Average: %.2f%n", currentStudent.getAverage());
		}

		if (includeMode) {
			ArrayList<Double> modes = calculateModeForStudent(currentStudent);
			if (modes.isEmpty()) {
				System.out.println("Mode: No mode found (all marks are unique or not marked).");
			} else {
				System.out.print("Mode(s): ");
				for (int i = 0; i < modes.size(); i++) {
					System.out.print(modes.get(i));
					if (i < modes.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println();
			}
		}

		if (includeAttendance) {
			System.out.printf("Attendance Grade: %.2f%%%n", currentStudent.calculateAttendanceGrade());
			System.out.println("Attendance Records:");
			System.out.println(currentStudent.getAttendanceRecordsAsString());
		}

		if (includeBehavior) {
			System.out.println("Behavior Records:");
			System.out.println(currentStudent.getBehaviorsAsString());
		}
	}

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
	private static ArrayList<Double> calculateModeForStudent(Student student) {
		ArrayList<Double> marks = new ArrayList<>();

		// Collect all marks for assignments that have been graded
		for (Assignment assignment : student.getAssignments()) {
			if (assignment.getMark() >= 0) { // Only include marked assignments
				marks.add(assignment.getMark());
			}
		}

		// If no marks are found, return an empty list
		if (marks.isEmpty()) {
			return new ArrayList<>();
		}

		// Find the frequency of each mark
		ArrayList<Double> uniqueMarks = new ArrayList<>();
		ArrayList<Integer> frequencies = new ArrayList<>();

		for (double mark : marks) {
			if (uniqueMarks.contains(mark)) {
				int index = uniqueMarks.indexOf(mark);
				frequencies.set(index, frequencies.get(index) + 1);
			} else {
				uniqueMarks.add(mark);
				frequencies.add(1);
			}
		}

		// Find the maximum frequency
		int maxFrequency = 0;
		for (int frequency : frequencies) {
			if (frequency > maxFrequency) {
				maxFrequency = frequency;
			}
		}

		// If the maximum frequency is 1, there's no mode
		if (maxFrequency == 1) {
			return new ArrayList<>(); // No mode
		}

		// Collect all marks that have the maximum frequency
		ArrayList<Double> modes = new ArrayList<>();
		for (int i = 0; i < frequencies.size(); i++) {
			if (frequencies.get(i) == maxFrequency) {
				modes.add(uniqueMarks.get(i));
			}
		}

		return modes;
	}

//------------------------------------------------------------------------------

//------------------------------------------------------------------------------
	private static boolean getYesOrNoInput(Scanner keyboard, String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = keyboard.nextLine().trim().toLowerCase();
			if (input.equals("y")) {
				return true;
			} else if (input.equals("n")) {
				return false;
			} else {
				System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
			}
		}
	}
//------------------------------------------------------------------------------

//***********************************************************
//Random:
//Put students in alphabetical order of last name
	public void alphabetizeClasslist(ArrayList<Student> students) {
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				// Compare by last name first
				return s1.getLastName().compareTo(s2.getLastName());
			}
		});
	}

// Display students passed in as an argument, their marks, and their average
	public void printStudentsWithMarks(ArrayList<Student> students) {
		if (students == null || students.isEmpty()) {
			System.out.println("No students to display.");
			return;
		}

		System.out.println("Students, their marks, and averages:");
		for (Student s : students) {
			System.out.println("Student: " + s.getFirstName() + " " + s.getLastName());

			// Print all marks for the student
			if (s.getAssignments().isEmpty()) {
				System.out.println("  No assignments available.");
			} else {
				System.out.println("  Marks:");
				for (Assignment assignment : s.getAssignments()) {
					if (assignment.getMark() >= 0) { // Only include marked assignments
						System.out.println("    " + assignment.getName() + ": " + assignment.getMark());
					}
				}
			}

			// Print the average at the end
			double average = s.getAverage();
			System.out.println("  Average: " + (average >= 0 ? average : "No marks available"));
			System.out.println();
		}
	}

	private void addStudentComments(Scanner keyboard, Teacher teacher) {
		// Iterate through Student ArrayList and use setComment method to input a
		// comment
	}

	private String getCustomReport(Student student, String reportType) {
		// Logic to generate a custom report based on the type
		return "Custom Report Data for: " + reportType; // Example data
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void addAttendanceForAllStudents(Scanner keyboard, ClassList classList) {
		// Validate and get the date
		String date;
		while (true) {
			System.out.println("Enter the date for attendance (YYYY-MM-DD):");
			date = keyboard.nextLine().trim();
			if (date.matches("\\d{4}-\\d{2}-\\d{2}")) { // Validate date format using regex
				break;
			} else {
				System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.");
			}
		}

		// Iterate over all students and record their attendance
		for (Student student : classList.getClassList()) {
			boolean validInput = false;
			while (!validInput) {
				System.out.printf("Was %s (ID: %s) present? (y/n): ", student.getFullName(), student.getStudentID());
				String input = keyboard.nextLine().trim().toLowerCase();

				if (input.equals("y")) {
					student.addAttendanceRecord(new Attendance(date, true));
					validInput = true;
				} else if (input.equals("n")) {
					student.addAttendanceRecord(new Attendance(date, false));
					validInput = true;
				} else {
					System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
				}
			}
		}

		System.out.println("Attendance recorded for all students.");
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void viewAllAttendanceRecords(ClassList classList) {
	    for (Student student : classList.getClassList()) {
	        System.out.printf("Attendance records for %s (ID: %s):\n", student.getFullName(), student.getStudentID());
	        System.out.println(student.getFormattedAttendanceRecords());
	    }
	}


//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void viewAttendanceForSpecificDate(Scanner keyboard, ClassList classList) {
	    // Collect unique attendance dates from all students
	    ArrayList<String> uniqueDates = new ArrayList<>();
	    for (Student student : classList.getClassList()) {
	        for (Attendance record : student.getAttendanceRecords()) {
	            if (!uniqueDates.contains(record.getDate())) {
	                uniqueDates.add(record.getDate());
	            }
	        }
	    }

	    if (uniqueDates.isEmpty()) {
	        System.out.println("No attendance records available.");
	        return;
	    }

	    // Display all dates in a number-based menu
	    System.out.println("\nAvailable Attendance Dates:");
	    for (int i = 0; i < uniqueDates.size(); i++) {
	        System.out.printf("%d. %s\n", i + 1, uniqueDates.get(i));
	    }
	    System.out.printf("%d. Return to Previous Menu\n", uniqueDates.size() + 1);

	    int choice = -1;
	    while (true) {
	        System.out.println("Enter the number corresponding to the date you want to view (or the last number to return):");
	        try {
	            choice = Integer.parseInt(keyboard.nextLine().trim());
	            if (choice >= 1 && choice <= uniqueDates.size() + 1) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please enter a number from the list.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }

	    // Handle return to previous menu
	    if (choice == uniqueDates.size() + 1) {
	        System.out.println("Returning to the previous menu.");
	        return;
	    }

	    // Display attendance for the selected date
	    String selectedDate = uniqueDates.get(choice - 1);
	    System.out.println("\nAttendance for " + selectedDate + ":");
	    for (Student student : classList.getClassList()) {
	        Attendance attendance = student.getAttendanceRecordByDate(selectedDate);
	        if (attendance != null) {
	            if (attendance.isPresent()) {
	                System.out.printf("%s (ID: %s): Present\n", student.getFullName(), student.getStudentID());
	            } else {
	                String excusedStatus = attendance.isExcused() ? "Excused" : "Unexcused";
	                System.out.printf("%s (ID: %s): Absent (%s)\n", student.getFullName(), student.getStudentID(), excusedStatus);
	            }
	        } else {
	            System.out.printf("%s (ID: %s): No record\n", student.getFullName(), student.getStudentID());
	        }
	    }
	}


//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void editAttendanceForStudent(Scanner keyboard, ClassList classList) {
	    // Display all students in a number-based menu
	    ArrayList<Student> students = classList.getClassList();
	    if (students.isEmpty()) {
	        System.out.println("No students available in the class.");
	        return;
	    }

	    System.out.println("Select a student to edit attendance:");
	    for (int i = 0; i < students.size(); i++) {
	        Student student = students.get(i);
	        System.out.printf("%d. %s (ID: %s)\n", i + 1, student.getFullName(), student.getStudentID());
	    }

	    int studentChoice = -1;
	    while (true) {
	        System.out.println("Enter the number corresponding to the student (or 0 to cancel):");
	        try {
	            studentChoice = Integer.parseInt(keyboard.nextLine().trim());
	            if (studentChoice == 0) {
	                System.out.println("Edit canceled.");
	                return;
	            } else if (studentChoice > 0 && studentChoice <= students.size()) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please select a valid number from the list.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }

	    Student selectedStudent = students.get(studentChoice - 1);

	    // Get attendance records for the selected student
	    ArrayList<Attendance> attendanceRecords = selectedStudent.getAttendanceRecords();
	    if (attendanceRecords.isEmpty()) {
	        System.out.println("No attendance records found for this student.");
	        return;
	    }

	    System.out.printf("Attendance records for %s (ID: %s):\n", selectedStudent.getFullName(),
	            selectedStudent.getStudentID());
	    for (int i = 0; i < attendanceRecords.size(); i++) {
	        Attendance record = attendanceRecords.get(i);
	        String status = record.isPresent() ? "Present" : "Absent";
	        System.out.printf("%d. Date: %s - %s\n", i + 1, record.getDate(), status);
	    }

	    // Allow the teacher to select a record to edit
	    int recordChoice = -1;
	    while (true) {
	        System.out.println("Enter the number of the record to edit (or 0 to cancel):");
	        try {
	            recordChoice = Integer.parseInt(keyboard.nextLine().trim());
	            if (recordChoice == 0) {
	                System.out.println("Edit canceled.");
	                return;
	            } else if (recordChoice > 0 && recordChoice <= attendanceRecords.size()) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please select a valid number from the list.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }

	    Attendance recordToEdit = attendanceRecords.get(recordChoice - 1);

	    // Update the attendance record
	    String input;
	    while (true) {
	        System.out.printf("Editing attendance for %s on %s (currently %s). Was the student present? (y/n): ",
	                selectedStudent.getFullName(), recordToEdit.getDate(),
	                recordToEdit.isPresent() ? "Present" : "Absent");
	        input = keyboard.nextLine().trim().toLowerCase();
	        if (input.equals("y") || input.equals("n")) {
	            break;
	        } else {
	            System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
	        }
	    }

	    boolean isPresent = input.equals("y");
	    recordToEdit.setPresent(isPresent);
	    System.out.println("Attendance updated.");
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private void excuseAbsences(Scanner keyboard, ClassList classList) {
	    // Display all students and let the teacher pick one
	    ArrayList<Student> students = classList.getClassList();
	    if (students.isEmpty()) {
	        System.out.println("No students found in the class.");
	        return;
	    }

	    System.out.println("\nSelect a student to excuse absences:");
	    for (int i = 0; i < students.size(); i++) {
	        System.out.printf("%d. %s (ID: %s)\n", i + 1, students.get(i).getFullName(), students.get(i).getStudentID());
	    }
	    System.out.printf("%d. Return to Previous Menu\n", students.size() + 1);

	    int studentChoice = -1;
	    while (true) {
	        System.out.println("Enter the number corresponding to the student (or the last number to return):");
	        try {
	            studentChoice = Integer.parseInt(keyboard.nextLine().trim());
	            if (studentChoice >= 1 && studentChoice <= students.size() + 1) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please enter a number from the list.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	        }
	    }

	    // Handle "Return to Previous Menu"
	    if (studentChoice == students.size() + 1) {
	        System.out.println("Returning to the previous menu.");
	        return;
	    }

	    Student selectedStudent = students.get(studentChoice - 1);

	    // Get unexcused absences
	    ArrayList<Attendance> attendanceRecords = selectedStudent.getAttendanceRecords();
	    if (attendanceRecords.isEmpty()) {
	        System.out.printf("No attendance records found for %s (ID: %s).\n", selectedStudent.getFullName(), selectedStudent.getStudentID());
	        return;
	    }

	    ArrayList<Attendance> unexcusedAbsences = new ArrayList<>();
	    for (Attendance record : attendanceRecords) {
	        if (!record.isPresent() && !record.isExcused()) {
	            unexcusedAbsences.add(record);
	        }
	    }

	    // Handle case where there are no unexcused absences
	    if (unexcusedAbsences.isEmpty()) {
	        System.out.printf("No unexcused absences found for %s (ID: %s).\n", selectedStudent.getFullName(), selectedStudent.getStudentID());
	        return;
	    }

	    // Loop through unexcused absences
	    boolean exit = false;
	    while (!exit) {
	        // Display unexcused absences
	        System.out.printf("\nUnexcused Absence Records for %s (ID: %s):\n", selectedStudent.getFullName(), selectedStudent.getStudentID());
	        for (int i = 0; i < unexcusedAbsences.size(); i++) {
	            System.out.printf("%d. Date: %s\n", i + 1, unexcusedAbsences.get(i).getDate());
	        }
	        System.out.printf("%d. Return to Previous Menu\n", unexcusedAbsences.size() + 1);

	        // Prompt for choice
	        int choice = -1;
	        while (true) {
	            System.out.println("Enter the number of the absence to excuse (or the last number to return):");
	            try {
	                choice = Integer.parseInt(keyboard.nextLine().trim());
	                if (choice >= 1 && choice <= unexcusedAbsences.size() + 1) {
	                    break;
	                } else {
	                    System.out.println("Invalid choice. Please enter a number from the list.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a valid number.");
	            }
	        }

	        // Handle "Return to Previous Menu"
	        if (choice == unexcusedAbsences.size() + 1) {
	            System.out.println("Returning to the previous menu.");
	            exit = true;
	            continue;
	        }

	        // Excuse the selected absence
	        Attendance recordToExcuse = unexcusedAbsences.get(choice - 1);

	        // Confirm excusing the absence
	        String confirmation;
	        while (true) {
	            System.out.printf("Are you sure you want to excuse the absence on %s? (y/n): ", recordToExcuse.getDate());
	            confirmation = keyboard.nextLine().trim().toLowerCase();
	            if (confirmation.equals("y") || confirmation.equals("n")) {
	                break;
	            } else {
	                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
	            }
	        }

	        if (confirmation.equals("y")) {
	            recordToExcuse.excuseAbsence();
	            unexcusedAbsences.remove(recordToExcuse);
	            System.out.println("Absence excused successfully.");
	        } else {
	            System.out.println("Excuse canceled.");
	        }

	        // Handle case where all absences have been excused
	        if (unexcusedAbsences.isEmpty()) {
	            System.out.println("All absences have been excused. Returning to the previous menu.");
	            exit = true;
	        }
	    }
	}




//-------------------------------------------------------------------------------------------------------------------------------------------------

	// behavior methods
//-------------------------------------------------------------------------------------------------------------------------------------------------
	private static void addBehaviorForStudent(Scanner keyboard, ClassList classList) {
		System.out.println("Enter the student ID to add behavior:");
		String studentId = keyboard.nextLine().trim();
		Student student = classList.getStudentByID(studentId);

		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		String date;
		while (true) {
			System.out.println("Enter the date of the behavior (YYYY-MM-DD):");
			date = keyboard.nextLine().trim();
			if (date.matches("\\d{4}-\\d{2}-\\d{2}")) { // Validate date format
				break;
			} else {
				System.out.println("Invalid date format. Please try again.");
			}
		}

		System.out.println("Enter the behavior details:");
		String behaviorDetails = keyboard.nextLine().trim();

		student.addBehavior(new Behavior(date, behaviorDetails));
		System.out.println("Behavior added successfully.");
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------	

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private static void viewBehaviorForStudent(Scanner keyboard, ClassList classList) {
		System.out.println("Enter the student ID to view behavior:");
		String studentId = keyboard.nextLine().trim();
		Student student = classList.getStudentByID(studentId);

		if (student == null) {
			System.out.println("Student not found.");
			return;
		}

		System.out.println("Behavior Records for " + student.getFullName() + ":");
		System.out.println(student.getBehaviorsAsString());
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------
	private static void viewBehaviorLog(Student currentStudent) {
		ArrayList<Behavior> behaviorLog = currentStudent.getBehaviors();

		if (behaviorLog.isEmpty()) {
			System.out.println("No behavior records found for this student.");
			return;
		}

		System.out.println("\nBehavior Records:");
		for (Behavior behavior : behaviorLog) {
			System.out.printf("Date: %s - %s%n", behavior.getDate(), behavior.getDetails());
		}
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------
}