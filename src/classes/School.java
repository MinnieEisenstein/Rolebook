package classes;

import java.util.ArrayList;

public class School {
	private String name;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<ClassList> classes;
    private ArrayList<Subject> subjects;

    public School(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public void addStudent(Student student) throws StudentExistsException {
        // Add the student to the school's list
        if (!students.contains(student)) {
            students.add(student);
        }
        //implement StudentExistsException if student already exists
        else {
        	throw new StudentExistsException();
        }
    }

    public void addTeacher(Teacher teacher) {
        // Add the teacher to the school's list
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }

    public void addClass(ClassList newClass) {
        // Add the class to the school's list
        if (!classes.contains(newClass)) {
            classes.add(newClass);
        }
    }

    public void addSubject(Subject subject) {
        // Add the subject to the school's list
        if (!subjects.contains(subject)) {
            subjects.add(subject);
        }
    }

    // Getter methods
    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public ArrayList<ClassList> getClasses() {
        return classes;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", students=" + students.size() +
                ", teachers=" + teachers.size() +
                ", classes=" + classes.size() +
                ", subjects=" + subjects.size() +
                '}';
    }
}
