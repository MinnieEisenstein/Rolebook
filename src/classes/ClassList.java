package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassList {

    private int grade;
    private String className;
    private Teacher teacher;
    private ArrayList<Student> students;
    private int classAverage;

    // Constructors
    public ClassList(int grade, String className, ArrayList<Student> students, int classAverage) {
        this.grade = grade;
        this.className = className;
        this.students = students;
        this.classAverage = classAverage;
    }

    public ClassList(int grade, String className, ArrayList<Student> students, Teacher teacher) {
        this.grade = grade;
        this.className = className;
        this.students = students;
        this.classAverage = getClassAverage();
        this.setTeacher(teacher);
    }

    public ClassList(int grade, String className, ArrayList<Student> students) {
        this.grade = grade;
        this.className = className;
        this.students = students;
        this.classAverage = getClassAverage();
        this.setTeacher(null);
    }

    public ClassList(int grade, String className) {
        this.grade = grade;
        this.className = className;
        this.students = new ArrayList<Student>();
        this.setTeacher(null);
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

    public int getClassAverage() {
        int total = 0;
        for (int j = 0; j < students.size(); j++) {
            total += students.get(j).getAverage();
        }
        classAverage = total / students.size();
        return classAverage;
    }

    public Student getStudentByName(String studentName) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
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

    public void setClassAverage(int classAverage) {
        this.classAverage = classAverage;
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

    @Override
    public String toString() {
        return "\nClass: " + className + "\nGrade: " + grade + "\nTeacher: " + teacher
                + "\nClass Average: " + classAverage;
    }
}