package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class ProgramUser {
    // might want to change everything to try-catch instead of throws

    public static void main(String[] args) throws StudentNotFoundException, NoStudentsException {
        Scanner keyboard = new Scanner(System.in);

        // As of now, teacher only has one class

        // setting up the school
        System.out.println("Welcome to the School RoleBook Program!");

        // set up the school
        School BYBP = setUpSchool(keyboard);
        int choice;
        String teacherPassword = "Teacher1234";

        System.out.println("Are you a student or teacher?");
        System.out.println("Enter 1 for Student and 2 for Teacher.");
        choice = keyboard.nextInt();

        do {
            if (choice == 1) {
                enterStudentView(keyboard);
            } else if(choice ==2){
            	
                enterTeacherView(choice, keyboard, teacherPassword, BYBP);
            }else {
            	System.out.println("That is not a valid choice.Please reenter 1 or 2.");         
            }
        }while(choice != 1 && choice != 2);
    }

    private static School setUpSchool(Scanner keyboard) {
        System.out.println("Let's create the school!");
        System.out.println("What is the name of the school?");
        String schoolname = keyboard.nextLine();
        // add confirmation?
        School school = new School(schoolname);
        addClasses(keyboard, school);

        return school;
    }

    private static void addClasses(Scanner keyboard, School school) {
        // public ClassList(int grade, String className, ArrayList<Student> students)

        // ask to add teacher...
        // when creating a teacher it should tell it the ID and password...
    }

    private static void enterStudentView(Scanner keyboard) {
        System.out.println("What is your name?");
        String name = keyboard.nextLine();
        System.out.println("What is your Student ID?");
        String studentID = keyboard.nextLine();
        System.out.println("What is your Student password?");
        String studentPassword = keyboard.nextLine();

        // check if student exists in school
    }

    private static void enterTeacherView(int choice, Scanner keyboard, String teacherPassword, School school)
            throws StudentNotFoundException, NoStudentsException {
        // first make sure it's really a teacher
        int wrongCount = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter the password for teacher's View");
            String userPassword = keyboard.nextLine();

            if (userPassword.equals(teacherPassword)) {
                break;
            } else {
                wrongCount++;
                System.out.println("Wrong password. you have " + (3 - wrongCount) + " try/tries left");
            }
        }
        if (wrongCount >= 3) {
            System.out.println("you are not authorized to enter Teacher's view");
            return;
        }

        // authorization - find out which teacher and verify

        System.out.println("Enter your Teacher ID: ");
        String teacherID = keyboard.nextLine();

        if (findTeacher(teacherID, school) == null) {
            System.out.println("no such Teacher found with that ID");
            return;
        } // change to an exception
        System.out.println("Enter your password: ");
        String password = keyboard.nextLine();
        if (!verifyPassword(findTeacher(teacherID, school), password)) {
            System.out.println("Sorry verification failed.");
            return;
        }
        Teacher teacher = findTeacher(teacherID, school);

        // Menu
        do {
            System.out.println("\nEnter the number of the action you would like to do:");
            System.out.println("1. Add student");
            System.out.println("2. Add assignment");
            System.out.println("3. Get average by student");
            System.out.println("4. Get class list");
            System.out.println("5. Quit");
            System.out.println();
            choice = keyboard.nextInt();
            keyboard.nextLine(); // clears buffer

            implementMenu(choice, teacher.getClassList(), keyboard);
        } while (choice != 5);
    }

    private static boolean verifyPassword(Teacher teacher, String password) {
        if (teacher.getID().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private static Teacher findTeacher(String teacherID, School school) {
        // Loop through each teacher in the list and check if their name matches
        for (int i = 0; i < school.getTeachers().size(); i++) {
            if (school.getTeachers().get(i).getID().equals(teacherID)) {
                return school.getTeachers().get(i);
            }
        }

        // If no teacher is found with the given name, return null or throw an exception
        return null;
    }

    public static void implementMenu(int choice, ClassList curClass, Scanner keyboard)
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

    public static void addStudent(ClassList curClass, Scanner keyboard) {
        // get student name from teacher input
        System.out.println("Enter name of new student: ");
        String name = keyboard.nextLine();

        curClass.getStudents().add(new Student(name, curClass));
    }

    public static void addAssignment(ClassList curClass, Scanner keyboard) throws NoStudentsException {
        // if no students, throw exception
        if (curClass.getStudents().size() < 1) {
            throw new NoStudentsException();
        }
        // get assignment name from teacher input
        System.out.println("Enter name of new assignment: ");
        String assignmentName = keyboard.nextLine();

        // For each student in the class, asks for student's mark and adds new assignment
        // to student
        for (Student student : curClass.getStudents()) {
            System.out.println("Mark for " + student.getName() + ": ");
            student.addAssignment(new Assignment(assignmentName, new Mark(keyboard.nextInt())));
            keyboard.nextLine(); // clears buffer
        }
    }

    public static void getAvgByStudent(ClassList curClass, Scanner keyboard)
            throws StudentNotFoundException, NoStudentsException {
        // Ask for student whose avg should be displayed
        System.out.println("Which student's average would you like?");
        String studentName = keyboard.nextLine();

        if (FoundStudent(curClass, studentName)) {
            for (Student s : curClass.getStudents()) {
                // find matching student
                if (s.getName().equalsIgnoreCase(studentName)) {
                    System.out.println(s.getName() + "'s average is " + s.getAverage());
                }
            }
        } else {

            throw new StudentNotFoundException();
        }
    }

    // displays class list of names
    public static void getClassList(ClassList curClass) {
        if (curClass.getStudents().size() == 0) {
            System.out.println("Class is empty");
        }
        for (Student s : curClass.getStudents()) {
            System.out.println(s.getName());
        }
    }

    public static boolean FoundStudent(ClassList curClass, String studentName) throws NoStudentsException {
        for (Student s : curClass.getStudents()) {
            // if no students yet throw exception
            if (curClass.getStudents().size() < 1) {
                throw new NoStudentsException();
            }
            // find matching student
            if (s.getName().equalsIgnoreCase(studentName)) {
                return true;
            }
        }
        return false;
    }
}
