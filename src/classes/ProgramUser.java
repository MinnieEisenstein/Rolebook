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
        System.out.println("Welcome to the Rolebook Program!");
        System.out.println("What is the teacher's name?");
        String name = keyboard.nextLine();
        System.out.println("What is the teacher's subject");
        String subject = keyboard.nextLine();
        Teacher teacher = new Teacher(name, subject);
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
                    // Student view
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
    
    //when u add an assignment- it should automatically make weight evenly
    //option- change weight of each assignment
    
    //add behavior for everyone *COMMENTS
    //add absenses(caluclate that into the grade)
    //customized report for student
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
        	System.out.println("12. Set or Change assignment weights"); // New option
        	System.out.println("13. Return to Main Menu");
            int choice = keyboard.nextInt();
            keyboard.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addStudent(keyboard, teacher); // Add a new student
                    break;
                case 2:
                	displayClassList(teacher.getClassList()); // Show all students
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
                    addStudentComments(keyboard, teacher); // Teacher inputs comments about individual student performance
                    break;
                case 9:
                    viewClassStatistics(teacher); // View class overall stats (averages, modes, max, etc.)
                    break;
                case 10:
                    viewAssignmentMenu(keyboard, teacher); // View assignment menu
                    break;
                case 11:
                    removeStudent(keyboard, teacher); // Remove a student
                    break;
                case 12:
                    setAssignmentWeights(keyboard, teacher); // Set or change assignment weights
                    break;
                case 13:
                    exitTeacherView = true; // Exit to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method placeholders to be implemented as needed
    private static void viewSpecificStudent(Scanner keyboard, Teacher teacher) {
        System.out.println("Enter the student ID:");
        String studentId = keyboard.nextLine();

        // Find the student by ID
        Student student = teacher.getClassList().getStudentByID(studentId);

        if (student == null) {
            throw new StudentNotFoundException();
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
            System.out.println("3. Add a comment");
            System.out.println("4. Record attendance");
            System.out.println("5. Return to previous menu");
            int choice = keyboard.nextInt();
            keyboard.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter the assignment name:");
                    String assignmentName = keyboard.nextLine();
                    System.out.println("Enter the grade:");
                    int grade = keyboard.nextInt();
                    keyboard.nextLine(); // Clear the buffer
                    student.addAssignment(new Assignment(assignmentName, new Mark(grade)));
                    System.out.println("Assignment added successfully.");
                    break;

                case 2:
                    System.out.println("Enter the name of the assignment to change:");
                    String assignmentToChange = keyboard.nextLine();
                    Assignment assignment = student.getAssignmentByName(assignmentToChange);
                    if (assignment != null) {
                        System.out.println("Enter the new grade:");
                        int newGrade = keyboard.nextInt();
                        keyboard.nextLine(); // Clear the buffer
                        assignment.setMark(new Mark(newGrade));
                        System.out.println("Grade updated successfully.");
                    } else {
                        System.out.println("Assignment not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter your comment:");
                    String comment = keyboard.nextLine();
                    student.addComment(comment);
                    System.out.println("Comment added successfully.");
                    break;

                case 4:
                    System.out.println("Enter the attendance record (e.g., Present/Absent):");
                    String attendance = keyboard.nextLine();
                    student.addAttendance(attendance);
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

    private static void viewTopMarks(int top, Teacher teacher) {
        // Implement logic to view top marks
    }

    private static void viewLowestMarks(int lowest, Teacher teacher) {
        // Implement logic to view lowest marks
    }

    private static void addAssignment(Scanner keyboard, Teacher teacher) {
        // Ask for assignment name
        System.out.println("Enter the name of the assignment:");
        String name = keyboard.nextLine().trim();

        // Ask for a comment
        System.out.println("Enter a comment for the assignment (or leave blank):");
        String comment = keyboard.nextLine().trim();

        // Ask for the assignment type (weight is automatically set based on the type)
        System.out.println("Enter the type of the assignment (e.g., Test, Quiz, Essay, Extra Credit):");
        String typeInput = keyboard.nextLine().trim();
        AssignmentType type = AssignmentType.valueOf(typeInput.toUpperCase()); // Convert input to enum value

        // Automatically set weight based on the assignment type
        double weight = teacher.getClassList().getWeightForType(type); // Get the weight for the selected type

        // Check if weight is valid (it should be set automatically)
        if (weight == 0.0) {
            System.out.println("Invalid assignment type or weight not defined. Please try again.");
            return;
        }

        // Create a new Assignment with the chosen type and auto-assigned weight
        Assignment assignment = new Assignment(name, comment, weight, type);

        // Add the assignment to the class list (and to students)
        if (teacher.getClassList().assignmentExists(name)) {
            System.out.println("An assignment with this name already exists.");
        } else {
            teacher.getClassList().addAssignmentToClass(assignment);
            System.out.println("Assignment added successfully.");
        }
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

    public static void setAssignmentWeights(Scanner keyboard, Teacher teacher) {
        // Get the list of assignment types and their weights
        ClassList classList = teacher.getClassList();
        
        // List assignment types with their current weights
        System.out.println("Current Assignment Weights:");
        double totalWeight = 0;
        for (AssignmentType type : AssignmentType.values()) {
            double weight = classList.getWeightForType(type);  // Assuming a method to get the weight for each type
            System.out.println(type + ": " + weight + "%");
            totalWeight += weight;
        }

        System.out.println("\nTotal weight: " + totalWeight + "%");

        // Check if total weight equals 100%
        if (totalWeight != 100) {
            System.out.println("Warning: The total weight is not 100%. Please adjust the weights.");
            return;
        }

        // Ask the teacher if they want to change any weight
        System.out.println("\nWould you like to change any assignment weights? (yes/no)");
        String response = keyboard.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            // Prompt teacher for which assignment type to change
            System.out.println("Which assignment type would you like to change? Choose from:");
            for (AssignmentType type : AssignmentType.values()) {
                System.out.println(type);
            }

            String chosenType = keyboard.nextLine().trim();
            AssignmentType typeToChange;
            
            // Handle input case insensitivity or invalid input
            try {
                typeToChange = AssignmentType.valueOf(chosenType.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid assignment type. Please try again.");
                return;
            }
            
            // Ask for new weight
            System.out.println("Enter new weight for " + typeToChange + ": ");
            double newWeight = keyboard.nextDouble();
            keyboard.nextLine(); // Clear buffer
            
            // Check if new weight is valid
            if (newWeight < 0 || newWeight > 100) {
                System.out.println("Invalid weight. Please enter a value between 0 and 100.");
                return;
            }

            // Update the weight for the selected assignment type
            classList.setWeightForType(typeToChange, newWeight);

            // Recalculate the total weight after change
            totalWeight = 0;
            for (AssignmentType type : AssignmentType.values()) {
                totalWeight += classList.getWeightForType(type);
            }

            // If total weight exceeds 100%, adjust other weights
            if (totalWeight > 100) {
                double excessWeight = totalWeight - 100;
                double weightToRedistribute = excessWeight;
                
                // Redistribute excess weight among other types
                for (AssignmentType type : AssignmentType.values()) {
                    if (!type.equals(typeToChange)) {
                        double currentWeight = classList.getWeightForType(type);
                        double newWeightForOther = currentWeight - (weightToRedistribute / (AssignmentType.values().length - 1));
                        classList.setWeightForType(type, newWeightForOther);
                    }
                }
            }

            // Display the new weights for confirmation
            System.out.println("\nUpdated Assignment Weights:");
            totalWeight = 0;
            for (AssignmentType type : AssignmentType.values()) {
                double weight = classList.getWeightForType(type);
                System.out.println(type + ": " + weight + "%");
                totalWeight += weight;
            }
            
            System.out.println("\nTotal weight: " + totalWeight + "%");

            // Ask the teacher for confirmation
            System.out.println("Do you confirm these changes? (yes/no)");
            String confirmation = keyboard.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes")) {
                System.out.println("The assignment weights have been successfully updated.");
            } else {
                System.out.println("The weights were not updated.");
            }
        }
    }

    
    private static void removeStudent(Scanner keyboard, Teacher teacher) {
        System.out.println("Enter student ID to remove:");
        String studentId = keyboard.nextLine();
        
        ClassList classList = teacher.getClassList(); // Assuming the teacher has a ClassList
        if (classList.removeStudentByID(studentId)) {
            System.out.println("Student with ID " + studentId + " has been removed.");
        } else {
            System.out.println("No student found with ID " + studentId + ".");
        }
    }
        
       

    // Add a new student with an autogenerated ID and password
    public static void addStudent(Scanner keyboard, Teacher teacher) {
        System.out.println("Enter the first name of the new student:");
        String firstName = keyboard.nextLine();
        System.out.println("Enter the last name of the new student:");
        String lastName = keyboard.nextLine();

        

        Student newStudent = new Student(firstName, lastName);
        teacher.addStudent(newStudent);

        System.out.println("Student " + newStudent.getFullName() + " added with ID: " + newStudent.getStudentID() + " and password: " + newStudent.getPassword());
    }
    
    //Put students in alphabetical order of last name
    public static void alphabetizeClasslist(ArrayList<Student> students) {
    	Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // Compare by last name first
                return s1.getLastName().compareTo(s2.getLastName());
            }
        });        }

    //Add assignments
    public static void addAssignments(Scanner keyboard, ClassList classList) {
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
            for (AssignmentType type : AssignmentType.values()) {
                System.out.println(type.ordinal() + 1 + ". " + type.name() + " (Default weight: " + type.getDefaultWeight() + "%)");
            }
            int typeChoice = keyboard.nextInt() - 1; // Get the chosen type index
            keyboard.nextLine(); // Clear buffer

            // Get the chosen type
            AssignmentType chosenType = AssignmentType.values()[typeChoice];

            // Ask for a comment on the assignment
            System.out.println("Enter comment for the assignment: ");
            String comment = keyboard.nextLine();

            // Create a new assignment with the chosen type
            Assignment newAssignment = new Assignment(assignmentName, chosenType, comment);

            // Optionally, allow the teacher to set a custom weight
            if (chosenType != AssignmentType.EXTRA_CREDIT) {
                System.out.println("Would you like to set a custom weight for this assignment? (y/n)");
                String choice = keyboard.nextLine().toLowerCase();
                if (choice.equals("y")) {
                    double customWeight = AssignmentType.setCustomWeight(keyboard);
                    newAssignment.setWeight(customWeight); // Set the custom weight
                }
            } else {
                // Allow the teacher to set a custom weight for Extra Credit assignments
                System.out.println("Enter a custom weight for the Extra Credit assignment: ");
                double customWeight = keyboard.nextDouble();
                newAssignment.setWeight(customWeight); // Set the custom weight
            }

            // Add the assignment to the class
            classList.addAssignmentToClass(newAssignment);
            System.out.println("Assignment added successfully.");
        }
    }


    //Add students marks for specified assignment passed in as an argument
    public static void addMarks(Scanner keyboard, ArrayList<Student> students, String assignmentName) {
    	for(Assignment a : students.get(0).getAssignments()) {
        	//If students already have the assignment (which means they have a mark for it, can be null), ask if should change the marks
        	if(a.getName().equals(assignmentName)) {
        		String choice;
        		do{
        			System.out.println("Marks already exist for this assignment, would you like to change them? y/n");
           			choice = keyboard.nextLine().toLowerCase();

           			switch(choice) {
           				case "y":
           					changeMarks(keyboard, students, assignmentName);
           					break;
           				case "n":
           					System.out.println("Marks not added");
           					return;
           				default:
           					System.out.println("Invalid option. y/n");
           			}
        		}while(!choice.equals("y") && !choice.equals("n"));
           	}
        }

        //if assignment is new, add assignment to each student and ask for marks
        for(Student s: students) {
           	System.out.println("Enter mark for " + s.getFullName() + ":");
           	s.addAssignment(new Assignment(assignmentName, new Mark(keyboard.nextInt())));
           	keyboard.nextLine(); //clear buffer
        }
    }

    //Change existing student marks
    public static void changeMarks(Scanner keyboard, ArrayList<Student> students, String assignmentName) {
    	int choice;
    	do{
    		//Ask teacher if they want to change all student's marks (e.g. after curve), or for a specific student
    		System.out.println("1. Change marks for  all students.");
        	System.out.println("2. Change marks for specific student.");
        	choice = keyboard.nextInt();
        	keyboard.nextLine(); //clear buffer

        	switch(choice) {
        		case 1:
        			//Run through student's marks and change all for the assignment
        			for(Student s: students) {
        				System.out.println("Enter mark for " + s.getFullName() + ":");
        				for(Assignment a: s.getAssignments()) {
        					if(a.getName().equals(assignmentName)) {
        						a.setMark(new Mark(keyboard.nextInt()));
        						keyboard.nextLine(); //clear buffer
        						break;
        					}
        				}
        			}
        			break;
        		case 2:
        			//Ask for specific student and change mark
        			System.out.println("Which student would you like to change a mark for?");
        			String studentName = keyboard.nextLine().trim();
        			boolean studentExists = false;

	        		for(Student s: students) {
	        			if(s.getFullName().equals(studentName)) {
	        				studentExists = true;
	        				for(Assignment a: s.getAssignments()) {
	        					if(a.getName().equals(assignmentName)) {
	        						System.out.println("Enter mark: ");
	        						a.setMark(new Mark(keyboard.nextInt()));
	        						keyboard.nextLine(); //clear buffer
	        						break;
	        					}
	        				}
	        			}	        			
	        		}
	        		if(!studentExists) {
	        			System.out.println("Student not found.");
	        		}
	        		break;
	        	default:
	        		System.out.println("Invalid input. 1-2");
	        		break;
	        	}
	        } while(choice != 1 && choice != 2);
    }


    //Get student's average
    public static void getStudentAvg(Scanner keyboard, ArrayList<Student> students) throws EmptyClassException {
    	if(students.size() == 0) {
    		throw new EmptyClassException("There are 0 students in the class");
    	}
        System.out.println("Enter student name to get average grade:");
        String studentName = keyboard.nextLine();

        boolean studentExists = false;
        for(Student s: students) {
        	if(s.getFullName().equals(studentName)) {
        		System.out.println(s.getAverage());
        		 studentExists =true;
        	}
        }
        //If student does not exists, display message
        if(!studentExists) {
            System.out.println("Student not found.");
        }
    }

    //Get class's average
    public static void getClassAvg(ArrayList<Student> students) throws EmptyClassException {
    	if(students.size() == 0) {
    		throw new EmptyClassException("There are 0 students in the class");
    	}
    	int marksSum = 0;
    	for(Student s: students) {
    		marksSum += s.getAverage();
    	}
    	System.out.println(marksSum/students.size());
    }

    //Get assignment average
    public static void getAssignmentAvg(Scanner keyboard, ArrayList<Student> students) {
    	if(students.get(0).getAssignments().size() == 0) {
    		System.out.println("There are no assignments yet.");
    	}
    	else {
        	int marksSum = 0;

        	if(students.get(0).getAssignments().size()== 0) {
        		System.out.println("There are no assignments yet.");
        	}
        	else {
            	boolean askAgain = false;

	        	do{
	        		//Ask for specific assignment
	        		System.out.println("Which assignment?");
		        	String assignmentName = keyboard.nextLine();
		        	boolean assignmentExists = false;

		        	for(Student s: students) {
		        		//if assignment exists, add up marks for average 
		        		for(Assignment a: s.getAssignments()) {
		        			if(a.getName().equals(assignmentName)) {
		        				assignmentExists = true;
		        				marksSum += a.getMark().getNum();
		        				break;
		        			}
		        		}
		        		//if assignment does not exists, give option to choose another
		        		if(!assignmentExists) {
		        			String choice;
		        			do {
			        			System.out.println("Assignment does not exists, would you like to enter a different one? y/n");
			        			choice = keyboard.nextLine().toLowerCase();
			        			switch(choice) {
			        				case "y":
			        					askAgain = true;
			        					break;
			        				case "n":
			        					return;
			        				default:
			        					System.out.println("Invalid input. y/n");
			        			}
		        			}while(!choice.equals("y") && !choice.equals("n"));
		        		}
		        	}
	        	}while(askAgain);
        	}
        	//Display assignment average
        	System.out.println(marksSum/students.size());
    	}
    }

    //Get students who are failing the class, and their marks
    public static void displayFailingStudents(ArrayList<Student> students) {
    	final int PASSING_GRADE = 65;
    	ArrayList<Student> failingStudents = new ArrayList<>();

    	//Add failing students to the failingStudents ArrayList
    	for(Student s: students) {
    		if(s.getAverage() < PASSING_GRADE && s.getAverage() != 0) {
    			failingStudents.add(s);
    		}
    	}

    	//Print the failing students and their marks
    	if(failingStudents.size() < 1) {
    		System.out.println("There are no failing students.");
    	}
    	else {
        	printStudentsWithMarks(failingStudents);
    	}
    }

    //Display students passed in as an argument and their marks
    public static void printStudentsWithMarks(ArrayList<Student> students) {
    	for(Student s: students) {
    		System.out.println(s.getFirstName() + " " + s.getLastName() + ": " + s.getAverage() + "\n");
    	}
    }

    //Print list of names of students passed in as an argument
    public static void displayClassList(ClassList classList) {
    	System.out.println(classList);
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
            System.out.println("7.Change password");
            System.out.println("8. Return to Main menu");

            int choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("How many top marks do you want to see?");
                    int top = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Top Marks: " + getTopMarks(teacher, currentStudent, top));
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
                	changePassword(currentStudent, keyboard);

                case 8:
                    System.out.println("Returning to main menu.");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper methods for the cases (you can replace these with real implementations)
    
    private static void changePassword(Student student, Scanner keyboard) {
        System.out.println("Enter Current password: ");
        String currCode = keyboard.nextLine();
        if (currCode.equals(student.getPassword())) {
            System.out.println("Enter new password:");
            String new1 = keyboard.nextLine();
            System.out.println("Enter new password again:");
            String new2 = keyboard.nextLine();

            if (new1.equals(new2)) {
                student.setPassword(new1);
                System.out.println("Password is reset. New passcode is " + new1);
            } else {
                System.out.println("There was a mismatch between the first and second password you entered.");
            }

        } else {
            System.out.println("You entered the wrong current password.");
        }
    }
    private static void changePasscode(Teacher teacher, Scanner keyboard) {
        System.out.println("Enter Current password: ");
        String currCode = keyboard.nextLine();
        if (currCode.equals(teacher.getPasscode())) {
            System.out.println("Enter new password:");
            String new1 = keyboard.nextLine();
            System.out.println("Enter new password again:");
            String new2 = keyboard.nextLine();

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
