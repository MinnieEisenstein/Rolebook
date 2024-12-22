package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassList {

    private int grade;
    private String className;
    private Teacher teacher;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments; // List of assignments for the class

    // Constructors
    public ClassList(Teacher teacher) {
        this.grade = 0;
        this.className = null;
        this.teacher = teacher;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    // Method to check if an assignment exists in the class
    public boolean assignmentExists(String assignmentName) {
        for (Assignment assignment : assignments) {
            if (assignment.getName().equals(assignmentName)) {
                return true; // Assignment already exists
            }
        }
        return false; // Assignment does not exist
    }

    // Method to add an assignment to the class and all students
    public void addAssignmentToClass(Assignment assignment) {
        // Add the assignment to the class's assignments list
        assignments.add(assignment);

        // Also add the assignment to each student in the class
        for (Student student : students) {
            student.addAssignment(assignment);
        }

        System.out.println("Assignment '" + assignment.getName() + "' added to the class and all students.");
    }

    // Getters
    public int getGrade() {
        return grade;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Student> getClassList() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public double getClassAverage() {
        double total = 0;
        for (int j = 0; j < students.size(); j++) {
            total += students.get(j).getAverage();
        }
        double classAverage = total / students.size();
        return classAverage;
    }

    public Student getStudentByName(String studentName) {
        for (Student student : students) {
            if (student.getFullName().equalsIgnoreCase(studentName)) {
                return student;
            }
        }
        return null; // Return null if the student is not found
    }

    // Setters
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Method to add an assignment to all students
    public void addAssignment(Assignment assignment) {
        for (Student student : students) {
            student.addAssignment(assignment); // Add assignment to each student's record
        }
    }

    // Method to get the average for a specific student by student name (or ID)
    public int getAvgByStudent(ClassList curClass, Scanner keyboard) {
        System.out.println("Enter the student's name or ID to get their average: ");
        String studentSearch = keyboard.next();

        Student student = curClass.getStudentByName(studentSearch);
        if (student != null) {
            return student.getAverage();  // Return the student's average if found
        } else {
            System.out.println("Student not found.");
            return -1;  // Return -1 if the student is not found
        }
    }

    // Method to check if a student ID exists in the class list
    public boolean StudentIDExist(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return true; // ID found
            }
        }
        return false; // ID not found
    }

    // Method in ClassList to find a student by their ID
    public Student getStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null; // Return null if no student is found with the given ID
    }

    // Method to remove a student by ID from the class list
    public boolean removeStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                students.remove(student);
                return true; // Return true if the student is successfully removed
            }
        }
        return false; // Return false if no student is found with the given ID
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Students in Class:\n");

        if (students.isEmpty()) {
            result.append("No students enrolled.\n");
        } else {
            for (Student student : students) {
                result.append("- ID: ").append(student.getStudentID())
                      .append(", Name: ").append(student.getFullName())
                      .append("\n");
            }
        }

        return result.toString();
    }

    public void addStudent(Student newStudent) {
        this.students.add(newStudent);
    }

    // Method to set weight for an assignment type (QUIZ, TEST, ESSAY, etc.)
    public void setWeightForType(AssignmentType type, double newWeightForOther) {
        double totalWeight = 0;

        // First, calculate the total weight before the update
        for (Assignment assignment : assignments) {
            if (assignment.getType() != type) {
                totalWeight += assignment.getWeight();
            }
        }

        // Calculate the new weight for the selected type and adjust others to make total 100%
        double newWeight = 100 - totalWeight;

        // Set the weight for the given assignment type
        for (Assignment assignment : assignments) {
            if (assignment.getType() == type) {
                assignment.setWeight(newWeightForOther);
                break;
            }
        }

        // Distribute the remaining weight to other assignment types to ensure the total is 100%
        for (Assignment assignment : assignments) {
            if (assignment.getType() != type) {
                double remainingWeight = (100 - newWeightForOther) / (assignments.size() - 1);
                assignment.setWeight(remainingWeight);
            }
        }

        // Output the updated weights to the teacher
        System.out.println("Updated weights for assignment types:");
        for (Assignment assignment : assignments) {
            System.out.println(assignment.getType() + ": " + assignment.getWeight());
        }
    }

    public double getWeightForType(AssignmentType type) {
        double weightForType = 0;

        // Iterate over the assignments to find the total weight for the given type
        for (Assignment assignment : assignments) {
            if (assignment.getType() == type) {
                weightForType += assignment.getWeight();
            }
        }

        // Return the total weight for the given assignment type
        return weightForType;
    }
}
