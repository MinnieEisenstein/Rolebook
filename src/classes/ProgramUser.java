package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class ProgramUser {
    public static void main(String[] args) throws StudentNotFoundException, NoStudentsException {
        Scanner keyboard = new Scanner(System.in);

        // Setting up the school
        System.out.println("Welcome to the School RoleBook Program!");

        School school = setUpSchool(keyboard);
        int choice;
        String teacherPassword = "Teacher1234";

        System.out.println("Enter 1 for Student view, 2 for Teacher view, or 3 for Admin view");
        choice = keyboard.nextInt();
        keyboard.nextLine(); // Flush buffer

        do {
            if (choice == 1) {
                enterStudentView(keyboard);
            } else if (choice == 2) {
                enterTeacherView(choice, keyboard, teacherPassword, school);
            } else if (choice == 3) {
                enterAdminView(keyboard);
            } else {
                System.out.println("That is not a valid choice. Please reenter 1, 2, or 3.");
            }
        } while (choice != 1 && choice != 2);
    }

    // Initialization methods
    public static School setUpSchool(Scanner keyboard) {
        System.out.println("What is the name of the school?");
        String schoolname = keyboard.nextLine();
        return new School(schoolname);
    }

    // Admin view and menu
    private static void enterAdminView(Scanner keyboard) {
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

        int choice = keyboard.nextInt();
        keyboard.nextLine(); // Clears buffer
        implementAdminMenu(choice, keyboard);
    }

    private static void implementAdminMenu(int choice, Scanner keyboard) {
        switch (choice) {
            case 1:
                addStudentToSchool(keyboard);
                break;
            case 2:
                // Logic for adding a teacher
                break;
            case 3:
                // Logic for adding a class
                break;
            case 4:
                // Logic for editing a student
                break;
            case 5:
                // Logic for editing a teacher
                break;
            case 6:
                // Logic for editing a class
                break;
            case 7:
                // Logic for deleting a student
                break;
            case 8:
                // Logic for deleting a teacher
                break;
            case 9:
                // Logic for deleting a class
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("That is not an option, choose another\n");
        }
    }

    public static void addStudentToSchool(Scanner keyboard) {
        System.out.println("Enter first name: ");
        String first = keyboard.nextLine();
        System.out.println("Enter last name: ");
        String last = keyboard.nextLine();
        Student student = new Student(first, last);
        // Additional logic for adding the student
    }

    // Teacher view and menu
    public static void enterTeacherView(int choice, Scanner keyboard, String teacherPassword, School school)
            throws StudentNotFoundException, NoStudentsException {
        int wrongCount = 0;

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
            System.out.println("You are not authorized to enter Teacher's View");
            return;
        }

        System.out.println("Enter your Teacher ID: ");
        String teacherID = keyboard.nextLine();

        Teacher teacher = findTeacher(teacherID, school);
        if (teacher == null) {
            System.out.println("No such Teacher found with that ID");
            return;
        }

        System.out.println("Enter your password: ");
        String password = keyboard.nextLine();
        if (!verifyPassword(teacher, password)) {
            System.out.println("Sorry, verification failed.");
            return;
        }

        do {
            System.out.println("\nEnter the number of the action you would like to do:");
            System.out.println("1. Add student");
            System.out.println("2. Add assignment");
            System.out.println("3. Get average by student");
            System.out.println("4. Get class list");
            System.out.println("5. Quit");
            System.out.println();

            choice = keyboard.nextInt();
            keyboard.nextLine(); // Clears buffer

            implementTeacherMenu(choice, teacher.getClassList(), keyboard);
        } while (choice != 5);
    }

    public static void implementTeacherMenu(int choice, ClassList curClass, Scanner keyboard)
            throws StudentNotFoundException, NoStudentsException {
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
                System.exit(0);
            default:
                System.out.println("That is not an option, choose another\n");
        }
    }

    // Student view
    public static void enterStudentView(Scanner keyboard) {
        System.out.println("What is your name?");
        String name = keyboard.nextLine();
        System.out.println("What is your Student ID?");
        String studentID = keyboard.nextLine();
        System.out.println("What is your Student password?");
        String studentPassword = keyboard.nextLine();
        // Logic for verifying student and actions
    }

    // Utility methods
    public static boolean verifyPassword(Teacher teacher, String password) {
        return teacher.getID().equals(password);
    }

    public static Teacher findTeacher(String teacherID, School school) {
        for (Teacher teacher : school.getTeachers()) {
            if (teacher.getID().equals(teacherID)) {
                return teacher;
            }
        }
        return null;
    }

    public static void addStudent(ClassList curClass, Scanner keyboard) {
        System.out.println("Enter name of new student: ");
        String name = keyboard.nextLine();
        curClass.getStudents().add(new Student(name, curClass));
    }

    public static void addAssignment(ClassList curClass, Scanner keyboard) throws NoStudentsException {
        if (curClass.getStudents().isEmpty()) {
            throw new NoStudentsException();
        }

        System.out.println("Enter name of new assignment: ");
        String assignmentName = keyboard.nextLine();

        for (Student student : curClass.getStudents()) {
            System.out.println("Mark for " + student.getName() + ": ");
            student.addAssignment(new Assignment(assignmentName, new Mark(keyboard.nextInt())));
            keyboard.nextLine(); // Clears buffer
        }
    }

    public static void getAvgByStudent(ClassList curClass, Scanner keyboard)
            throws StudentNotFoundException, NoStudentsException {
        System.out.println("Which student's average would you like?");
        String studentName = keyboard.nextLine();

        if (FoundStudent(curClass, studentName)) {
            for (Student s : curClass.getStudents()) {
                if (s.getName().equalsIgnoreCase(studentName)) {
                    System.out.println(s.getName() + "'s average is " + s.getAverage());
                }
            }
        } else {
            throw new StudentNotFoundException();
        }
    }

    public static void getClassList(ClassList curClass) {
        if (curClass.getStudents().isEmpty()) {
            System.out.println("Class is empty");
        }
        for (Student s : curClass.getStudents()) {
            System.out.println(s.getName());
        }
    }

    public static boolean FoundStudent(ClassList curClass, String studentName) throws NoStudentsException {
        if (curClass.getStudents().isEmpty()) {
            throw new NoStudentsException();
        }
        for (Student s : curClass.getStudents()) {
            if (s.getName().equalsIgnoreCase(studentName)) {
                return true;
            }
        }
        return false;
    }
}