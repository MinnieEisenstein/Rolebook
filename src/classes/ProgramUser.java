package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramUser {
    public static void main(String[] args) throws StudentNotFoundException, NoStudentsException {
    		ArrayList<School> schools= new ArrayList<>();
 
            Scanner keyboard = new Scanner(System.in);
            School school = setUpSchool(keyboard);
            int choice = -1;
            schools.add(school);

            displayMenu(keyboard, school, choice);
            
        }

    	public static void displayMenu(Scanner keyboard, School school, int choice) throws NoStudentsException {
    		System.out.println("\nEnter 1 for Student view, 2 for Teacher view, or 3 for Admin view");
    		choice = keyboard.nextInt();
    		keyboard.nextLine(); // Flush buffer
    		
    		do {
    			try {
    				if (choice == 1) {
    					enterStudentView(keyboard, school);
    				} else if (choice == 2) {
    					enterTeacherView(keyboard, school);
    				} else if (choice == 3) {
    					enterAdminView(keyboard, school);  // Admin view remains as it is
    				} else {
    					System.out.println("That is not a valid choice. Please reenter 1, 2, or 3.");
    				}
    				
    				System.out.println("Enter 1 for Student view, 2 for Teacher view, or 3 for Admin view");
    				choice = keyboard.nextInt();
    				keyboard.nextLine(); // Flush buffer
    				
    			} catch (TeacherNotFoundException | StudentNotFoundException e) {
    				System.out.println(e.getMessage());
    				// Return to the main menu after 3 failed attempts
    				displayMenu(keyboard, school, choice);
    			}
    			
    		} while (choice == 1 || choice == 2 || choice == 3);
    		
    	}
    
        // Initialization methods
        public static School setUpSchool(Scanner keyboard) {
            System.out.println("What is the name of the school?");
            String schoolname = keyboard.nextLine();
            return new School(schoolname);
        }

        // Admin view and menu
        private static void enterAdminView(Scanner keyboard, School school) {
            int choice;
            do {
                System.out.println("\nEnter the number of the action you would like to do:");
                System.out.println("1. Add a new student");
                System.out.println("2. Add a teacher");
                System.out.println("3. Add a class");
                System.out.println("4. Edit a student");
                System.out.println("5. Edit a teacher");
                System.out.println("6. Edit a class");
                System.out.println("7. Delete a student");
                System.out.println("8. Delete a teacher");
                System.out.println("9. Delete a class");
                System.out.println("Enter 0 to exit Admin View");
                System.out.println();

                choice = keyboard.nextInt();
                keyboard.nextLine(); // Clears buffer
                implementAdminMenu(choice, keyboard, school);

            } while (choice != 0);  // Continue showing menu until "0" is chosen
        }
 
        private static void implementAdminMenu(int choice, Scanner keyboard, School school) {
            switch (choice) {
                case 1:
                    addStudentToSchool(keyboard, school);
                    break;
                case 2:
                	addTeacherToSchool(keyboard, school);
                    break;
                case 3:
                    System.out.println("Under construction: Logic for adding a class.");
                    break;
                case 4:
                    System.out.println("Under construction: Logic for editing a student.");
                    break;
                case 5:
                    System.out.println("Under construction: Logic for editing a teacher.");
                    break;
                case 6:
                    System.out.println("Under construction: Logic for editing a class.");
                    break;
                case 7:
                    System.out.println("Under construction: Logic for deleting a student.");
                    break;
                case 8:
                    System.out.println("Under construction: Logic for deleting a teacher.");
                    break;
                case 9:
                    System.out.println("Under construction: Logic for deleting a class.");
                    break;
                case 0:
                    System.out.println("Exiting Admin View and returning to the main menu.");
                    break;
                default:
                    System.out.println("That is not an option, choose another\n");
            }
        }

        private static void addTeacherToSchool(Scanner keyboard, School school) {
        	System.out.println("Enter teacher's name: ");
            String name = keyboard.nextLine();
            //Split into first and last name, possibly create a Person class if easier and extend Student and Teacher classes
            System.out.println("Enter subject: ");
            String subject = keyboard.nextLine();
            Teacher teacher = new Teacher(name, subject);
           	school.addTeacher(teacher);
			//Make it that teacher can be entered without a subject, or subjects can be added to teachers for multiple subjects taught
		}

		public static void addStudentToSchool(Scanner keyboard, School school) {
            System.out.println("Enter first name: ");
            String first = keyboard.nextLine();
            System.out.println("Enter last name: ");
            String last = keyboard.nextLine();
            Student student = new Student(first, last);
            try {
           	school.addStudent(student);
            }
            catch (StudentExistsException e) {
            	System.out.println(e.getMessage());
            }
           	
            /*try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
                    writer.write(student.toString());
                    writer.newLine();
                }
            catch (IOException e) {
                e.printStackTrace();
            }*/
        }

        // Teacher view and menu
        public static void enterTeacherView(Scanner keyboard, School school) throws TeacherNotFoundException, StudentNotFoundException, NoStudentsException {
            String teacherPassword = "teacher1234";  // Assume this is the password for the teacher view
            int wrongCount = 0;
            Teacher teacher = null;

            // Check teacher's password
            for (int i = 0; i < 3; i++) {
                System.out.println("Enter the password for Teacher's View");
                String userPassword = keyboard.nextLine();

                if (userPassword.equals(teacherPassword)) {
                    break;
                } else {
                    wrongCount++;
                    System.out.println("Wrong password. You have " + (3 - wrongCount) + " try/tries left");
                }
            }
            if (wrongCount >= 3) {
                throw new TeacherNotFoundException("You have entered the wrong password 3 times. Returning to the main menu.");
            }

            // Teacher ID verification
            for (int i = 0; i < 3; i++) {
                System.out.println("Enter your Teacher ID: ");
                String teacherID = keyboard.nextLine();
                teacher = findTeacher(teacherID, school);

                if (teacher != null) {
                    break;
                } else {
                    System.out.println("No such Teacher found with that ID. You have " + (2 - i) + " try/tries left.");
                }
            }

            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher not found. Returning to the main menu.");
            }

            // Teacher verification passes
            System.out.println("Welcome to the Teacher's View!");

            // Teacher menu
            int choice;
            do {
                System.out.println("\nEnter the number of the action you would like to do:");
                System.out.println("1. Add student");
                System.out.println("2. Add assignment");
                System.out.println("3. Get average by student");
                System.out.println("4. Get class list");
                System.out.println("5. Quit");

                choice = keyboard.nextInt();
                keyboard.nextLine(); // Clears buffer

                implementTeacherMenu(choice, teacher.getClassList(), keyboard);

                if (choice != 5) {
                    System.out.println("Returning to Teacher's View menu.");
                }
            } while (choice != 5);
        }

        public static void implementTeacherMenu(int choice, ClassList curClass, Scanner keyboard) throws StudentNotFoundException, NoStudentsException {
            switch (choice) {
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
                    System.out.println("Exiting Teacher View and returning to the main menu.");
                    break;
                default:
                    System.out.println("That is not an option, choose another\n");
            }
        }

        // New methods
        public static void getAvgByStudent(ClassList curClass, Scanner keyboard) {
            System.out.println("Enter student name to get average grade:");
            String studentName = keyboard.nextLine();
            Student student = curClass.getStudentByName(studentName);
            
            if (student != null) {
                double avgGrade = student.getAverage();
                System.out.println("The average grade for " + studentName + " is: " + avgGrade);
            } else {
                System.out.println("Student not found.");
            }
        }

        public static void getClassList(ClassList curClass) {
            System.out.println("Class list:");
            for (Student student : curClass.getClassList()) {
                System.out.println(student.getName());
            }
        }

        // Student view
        public static void enterStudentView(Scanner keyboard, School school) throws StudentNotFoundException {
            System.out.println("What is your Student ID?");
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
                    System.out.println("Returning to Student's View menu.");
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

        public static void addAssignment(ClassList curClass, Scanner keyboard) throws NoStudentsException {
            if (curClass.getClassList().isEmpty()) {
                throw new NoStudentsException();
            }

            System.out.println("Enter assignment name: ");
            String assignment = keyboard.nextLine();

            for (Student student : curClass.getClassList()) {
            	System.out.println("Enter grade for " + student.getName() + " (no decimals allowed): ");
                int grade = keyboard.nextInt();
                keyboard.nextLine(); // Clear buffer
                student.addAssignment(new Assignment(assignment, grade));
            }
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
        
    }