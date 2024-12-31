package classes;

import java.util.ArrayList;
import java.util.Random;

public class Student {

    private String studentFullName;
    private ClassList studentClass;
    private ArrayList<Assignment> assignments;
    private ArrayList<Attendance> attendanceRecords; // New field for attendance records
    private double average;
    private String studentFirstName;
    private String studentLastName;
    private String password;
    private String comment;

    // Static ArrayList to store existing IDs
    private static ArrayList<String> existingIDs = new ArrayList<>();

    // Instance variable for the student's ID (now a String)
    private String studentID;

    // Constructor for creating a new Student
    public Student(String first, String last) {
        this.studentFirstName = first;
        this.studentLastName = last;
        this.studentFullName = first + " " + last;  // Ensure studentName is properly initialized
        this.assignments = new ArrayList<>();
        this.attendanceRecords = new ArrayList<>(); // Initialize attendance records
        this.password = generateRandomPasscode(5);  // Generate a 5 char password (easy to remember)

        // Generate a unique ID
        this.studentID = generateUniqueID();

        comment = "---";

        //System.out.println("The student's ID is " + studentID + " and your password is " + password);
    }

    // Getters
    public String getPassword() {
        return password;
    }

    public String getStudentID() {
        return studentID;  // Now returns a String
    }

    public String getFullName() {
        return studentFullName;
    }

    public ClassList getStudentClass() {
        return studentClass;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void setPassword(String s) {
        password = s;
    }
    public ArrayList<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }


    public double getAverage() {
        if (assignments.isEmpty() && attendanceRecords.isEmpty()) {
            return 0.0;
        }

        double totalWeightedMarks = 0.0;
        double totalWeight = 0.0;

        // Include assignment marks
        for (Assignment assignment : assignments) {
            if (assignment.getMark() >= 0) { // Only include marked assignments
                totalWeightedMarks += assignment.getMark() * assignment.getWeight() / 100.0;
                totalWeight += assignment.getWeight();
            }
        }

        // Include attendance grade
        double attendanceGrade = calculateAttendanceGrade();
        double attendanceWeight = getStudentClass().getAssignmentManager().getWeightByType("Attendance");
        totalWeightedMarks += attendanceGrade * attendanceWeight / 100.0;
        totalWeight += attendanceWeight;

        return totalWeight > 0 ? totalWeightedMarks / (totalWeight / 100.0) : 0.0;
    }


    public String getComment() {
        return comment;
    }

    // Setters
    public void setStudentName(String studentName) {
        this.studentFullName = studentName;
    }

    public void setStudentClass(ClassList studentClass) {
        this.studentClass = studentClass;
    }

    public void setAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLastName() {
        return studentLastName;
    }

    public String getFirstName() {
        return studentFirstName;
    }

    // Other methods
    public LetterGrade getLetterAverage() {
        // Since average is now a double, you can directly return the letter grade
        return LetterGrade.getLetterGrade(average);
    }

    public double getLowestMark() {
        double lowest = Double.MAX_VALUE; // Use Double.MAX_VALUE for a more appropriate initialization
        for (int i = 0; i < assignments.size(); i++) {
            // Compare the assignment's mark, which is now a double value
            if (assignments.get(i).getMark() < lowest) {
                lowest = assignments.get(i).getMark(); // Directly access the mark, now a double
            }
        }

        return lowest;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(studentLastName);
        str.append(", " + studentFirstName);
        return str.toString();
    }

    // Method to generate a unique 6-digit ID as a String
    private String generateUniqueID() {
        Random rand = new Random();
        String id;
        do {
            id = String.format("%06d", 100000 + rand.nextInt(900000));  // Generate a 6-digit number as a String
        } while (existingIDs.contains(id));  // Ensure the ID is unique
        existingIDs.add(id);  // Add the ID to the list of existing IDs
        return id;
    }

    // Equals method checks if the names are the same
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return this.getFullName().equals(other.getFullName()) && this.getStudentID().equals(other.getStudentID());
    }

    // Method to generate a random passcode
    public static String generateRandomPasscode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder passcode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            passcode.append(characters.charAt(index));
        }
        return passcode.toString();
    }

    public Assignment getAssignmentByName(String assignmentName) {
        for (Assignment assignment : assignments) {
            if (assignment.getName().equalsIgnoreCase(assignmentName)) {
                return assignment; // Return the matching assignment
            }
        }
        return null; // Return null if no assignment with the given name is found
    }

    public void addAttendanceRecord(Attendance attendance) {
        attendanceRecords.add(attendance);
    }

    // Inside the Student class

    public double calculateAttendanceGrade() {
        if (attendanceRecords.isEmpty()) {
            return 100.0; // If there are no attendance records, assume full attendance
        }

        int totalRecords = attendanceRecords.size();
        int unexcusedAbsences = 0;

        for (Attendance record : attendanceRecords) {
            if (!record.isPresent() && !record.isExcused()) {
                unexcusedAbsences++;
            }
        }

        double attendanceGrade = 100.0 * (totalRecords - unexcusedAbsences) / totalRecords;
        return Math.round(attendanceGrade * 100.0) / 100.0; // Round to 2 decimal places
    }

    // Methods to view attendance records different ways

    public void viewAttendanceRecords() {
        if (attendanceRecords.isEmpty()) {
            System.out.println("No attendance records available.");
            return;
        }

        System.out.println("Attendance Records:");
        for (Attendance record : attendanceRecords) {
            System.out.println(record);
        }
    }
    
    public Attendance getAttendanceRecordByDate(String date) {
        for (Attendance record : attendanceRecords) {
            if (record.getDate().equals(date)) {
                return record; // Return the matching attendance record
            }
        }
        return null; // Return null if no record is found for the specified date
    }
    
    public String getAttendanceRecordsAsString() {
        if (attendanceRecords.isEmpty()) {
            return "No attendance records available.";
        }

        StringBuilder recordsString = new StringBuilder();
        recordsString.append("Attendance Records:\n");

        for (Attendance record : attendanceRecords) {
            recordsString.append("Date: ").append(record.getDate())
                         .append(", Present: ").append(record.isPresent() ? "Yes" : "No")
                         .append(", Excused: ").append(record.isExcused() ? "Yes" : "No")
                         .append("\n");
        }

        return recordsString.toString();
    }


}
