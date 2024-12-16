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

            displayMenu(keyboard);
            
        }

    	public static void displayMenu(Scanner keyboard) throws NoStudentsException {
    		ArrayList<Student> students = new ArrayList<>();
    		ArrayList<Assignment> assignments = new ArrayList<>();
    		//Start program and ask teacher for list of students
    		System.out.println("Starting Rolebook Program...");
    		//Add students
    		addStudents(keyboard, students, assignments);
    		
    		int choice;
    		do {    				
    			//Teacher or student view, continue until user decides to quit
    			System.out.println("Enter 1 for teacher view, 2 for student view, or 0 to quit: ");
    			choice = keyboard.nextInt();
    			keyboard.nextLine(); //clear buffer
    			
    			switch(choice) {
    				case 1:
    					try{
    						enterTeacherView(keyboard, students, assignments);
        					break;
    					}catch(InvalidPasswordException e) {
    						e.getMessage();
    					}
    					break;
    				case 2:
    					enterStudentView(keyboard, students);
    					break;
    				case 0:
    					System.exit(0);
    				default:
    					System.out.println("Invalid choice, choose 0-2");
    			}
    		} while (choice!=0);
    		
    	}

    	//TEACHER METHODS
    	  
        // Teacher view
        public static void enterTeacherView(Scanner keyboard, ArrayList<Student> students, ArrayList<Assignment> assignments) throws InvalidPasswordException {
            //Get teacher verification
        	boolean verified = teacherVerification(keyboard);
        	
        	if(!verified) {
        		throw new InvalidPasswordException("Wrong password too many times, returning to main menu...");
        	}
            // Teacher verification passes
            System.out.println("Welcome to the Teacher's View!");

            // Teacher menu
            teacherMenu(keyboard, students, assignments);
        }
        
        //verify teacher's password
        public static boolean teacherVerification(Scanner keyboard) {
        	String teacherPassword = "teacher1234";  // password for the teacher view is teacher1234
            final int MAX_PASSWORD_TRIES = 3;

            //check teacher password
        	int wrongTries = 0;
            for (int i = wrongTries; i < MAX_PASSWORD_TRIES; i++) {
                System.out.println("Enter the password for Teacher's View");
                
                if(!keyboard.nextLine().equals(teacherPassword)){
                    wrongTries++;
                    System.out.println("Wrong password. You have " + (3 - wrongTries) + " try/tries left");
                }
                else {
                	break;
                }
            }
     
            //If wrong password is entered 3 times, return validation false
            if (wrongTries >= 3) {
            	return false;
            }
            //else validation is true
            return true;
            
        }
        
        //display options and get teacher's menu choice
        public static void teacherMenu(Scanner keyboard, ArrayList<Student> students, ArrayList<Assignment> assignments) {
        	int choice;
            do {
            	//Get teacher input menu choice
                System.out.println("\nEnter the number of the action you would like to do:");
                System.out.println("1. Add students");
                System.out.println("2. Add assignments");
                System.out.println("3. Add/change marks for existing assignments");
                System.out.println("4. Get student average");
                System.out.println("5. Get class average");
                System.out.println("6. Get assignment average");
                System.out.println("7. Display marks for failing students");
                System.out.println("8. Display class list");
                System.out.println("9. Quit");

                choice = keyboard.nextInt();
                keyboard.nextLine(); // Clear buffer

                implementTeacherMenu(keyboard, students, assignments, choice);

            } while (choice != 9);
        }

        public static void implementTeacherMenu(Scanner keyboard, ArrayList<Student> students, ArrayList<Assignment> assignments, int choice) {
            switch (choice) {
                case 1:
                    addStudents(keyboard, students, assignments);
                    break;
                case 2:
                    addAssignments(keyboard, students, assignments);
                    break;
                case 3:
                	if(assignments.size() == 0) {
                		System.out.println("There are no assignments.");
                	}
                	else {
	                	System.out.println("Which assignment would you like to add/change marks for?");
	                	changeMarks(keyboard, students, keyboard.nextLine());
                	}
                	break;
                case 4:
					try {
						getStudentAvg(keyboard, students);
					} catch (EmptyClassException e) {}
                    break;
                case 5:
					try {
						getClassAvg(students);
					} catch (EmptyClassException e) {}
                    break;
                case 6:
                	getAssignmentAvg(keyboard, students);
                    break;
                case 7:
                	displayFailingStudents(students);
                	break;
                case 8:
                	displayClassList(students);
                	break;
                case 9:
                	break;
                default:
                    System.out.println("Invalid option, choose another 1-9");
            }
        }
        
        //Add students
        public static void addStudents(Scanner keyboard, ArrayList<Student> students, ArrayList<Assignment> assignments) {
        	System.out.print("How many students would you like to add? ");
            int numOfStudents = keyboard.nextInt();
            keyboard.nextLine();  // Consume the leftover newline character after reading the integer
            
            while(numOfStudents <= 0) {
            	System.out.println("Please enter a valid number of students");
            	numOfStudents = keyboard.nextInt();
            	keyboard.nextLine(); //clear buffer
            }
            for(int i =0; i < numOfStudents; i++) {
            	System.out.println("Enter name of student " + (i+1) + " (first last): ");
            	String fullName = keyboard.nextLine().trim();
            	
            	while(fullName.isEmpty() || fullName.split(" ").length != 2) {
            		System.out.println("Invalid input. Please enter the first and last name (e.g. Mary Jane)");
            		fullName = keyboard.nextLine().trim();
            	}
            	
            	String fName = fullName.split(" ")[0];
	    		String lName = fullName.split(" ")[1];
	    		
	    		students.add(new Student(fName, lName));
            }
	        	
	    	//If previous students already have assignments, add null assignments to the new students
	    	if(assignments.size() > 0) {
	    		for(Student s: students) {
	    			for(int i = 0; i < assignments.size(); i++) {
	    					s.addAssignment(new Assignment(assignments.get(i).getName(), 0));
	    			}
	    		}
    		}
    		        	
        	//Put new students in alphabetical order
        	alphabetizeClasslist(students);
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
        public static void addAssignments(Scanner keyboard, ArrayList<Student> students, ArrayList<Assignment> assignments) {
        	//Ask teacher for number of assignments to add
        	System.out.println("How many assignments would you like to add?");
        	int numOfAssignments = keyboard.nextInt();
        	keyboard.nextLine(); //clear buffer
        	
        	//Ask teacher for name of each assignment and add to the assignments list
        	for(int i = 0; i < numOfAssignments; i++) {
        		System.out.println("Enter name of assignment #" + (i+1) + ": ");
               	String assignmentName = keyboard.nextLine().trim();

               	if(!assignmentName.isEmpty()) {
               		//If assignment name already exists, tell user and continue to the next assignment
               		boolean exists = false;
               		for(Assignment a: assignments) {
               			if(a.getName().equals(assignmentName)) {
               				System.out.println("Assignment already exists");
               				exists = true;
               			}
               		}
               		if(exists) {
               			continue;
               		}
                   	assignments.add(new Assignment(assignmentName));
                    	
                   	//If students exist, ask teacher if they would like to add student marks
                   	if(!students.isEmpty()) {
                   		String choice;
                   		do{
                   			System.out.println("Would you like to enter student marks for the assignment? y/n");
                   			choice = keyboard.nextLine().toLowerCase();
                    		
                   			switch(choice) {
	                   			case "y":
	                   				addMarks(keyboard, students, assignmentName);
	                   				break;
	                   			case "n":
	                   				break;
	                   			default:
	                   				System.out.println("Invalid choice. y/n");
	                   				break;
                   			}
                   		} while(!choice.equals("y") && !choice.equals("n"));
                   	}
                }
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
        public static void displayClassList(ArrayList<Student> students) {
        	for(Student s: students) {
        		System.out.println(s.toString());
        	}
        }
                
        //STUDENT METHODS
        
        // Student view
        public static void enterStudentView(Scanner keyboard, ArrayList<Student> students) {
        	System.out.println("Student view under construction... Look forward!");
        }
}
            
        	/*System.out.println("What is your Student ID?");
            String studentID = keyboard.nextLine();

            Student student = null;
            for (int i = 0; i < 3; i++) {
                System.out.println("Enter your Student password: ");
                String studentPassword = keyboard.nextLine();

                student = findStudent(studentID, studentPassword, school);
                if (student == null) {
                    System.out.println("Incorrect ID or password. You have " + (2 - i) + " try/tries left.");
                }
                else {
                	i = 3;
                }
            }

            if (student == null) {
                throw new StudentNotFoundException();
            }
            int choice=0;
            do {
                System.out.println("\nEnter the number of the action you would like to do:");
                System.out.println("1. See overall average");
                System.out.println("2. print all assignments and marks");
                System.out.println("3. Quit");
                

                choice = keyboard.nextInt();
                keyboard.nextLine(); // Clears buffer

                implementStudentMenu(choice, student, keyboard);

                if (choice != 3) {
                    System.out.println("Returning to Teacher's View menu.");
                }
            } while (choice != 3);
        }

        //display student information based on menu choice
        private static void implementStudentMenu(int choice, Student student, Scanner keyboard) {
        	//display student name and average
        	if(choice == 1) {
        		System.out.println("Student: " + student.getName() + "\nAverage: " + student.getAverage());
        	} 
        	//display student name and information about each specific assignment
        	else if(choice == 2) {
        		System.out.println("Student: " + student.getName());
        		ArrayList<Assignment> assignments = student.getAssignments();
        		for(int i = 0; i < assignments.size(); i++) {
        			System.out.println(assignments.get(i) + "\n");
        		}
        	}
        }

		// Student and Teacher operations
        public static void addStudent(ClassList curClass, Scanner keyboard) {
            System.out.println("Enter first name of new student: ");
            String first = keyboard.nextLine();
            System.out.println("Enter first last of new student: ");
            String last = keyboard.nextLine();
            curClass.getClassList().add(new Student(first, last));
        }

        
        // Find students/teachers
        public static Teacher findTeacher(String teacherID, School school) {
        	for(Teacher t : school.getTeachers()) {
            	if(t.getTeacherID().equals(teacherID)){
            		return t;
            	}				
            }
            return null;  // Placeholder return
        }

        public static Student findStudent(String studentID, String password, School school) {
            for(Student s : school.getStudents()) {
            	if(s.getStudentID().equals(studentID) && s.getPassword().equals(password)){
            		return s;
            	}				
            }
            return null;  // Placeholder return
        }
        
    }*/