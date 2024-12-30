package classes;

public class Attendance {
    private String date; // Date of the attendance record
    private boolean present; // Whether the student was present (true for present, false for absent)
    private boolean excused; // Whether the absence is excused (true for excused, false otherwise)

    // Constructor
    public Attendance(String date, boolean present) {
        this.date = date;
        this.present = present;
        this.excused = false; // Default to not excused
    }

    // Getters
    public String getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }

    public boolean isExcused() {
        return excused;
    }

    // Setters
    public void setPresent(boolean present) {
        this.present = present;
    }

    public void excuseAbsence() {
        if (!present) { // Only excused if the student was absent
            this.excused = true;
        } else {
            System.out.println("Cannot excuse an attendance record where the student was present.");
        }
    }

    // Method to calculate if this record should count against attendance grade
    public boolean countsAgainstGrade() {
        return !present && !excused; // Only unexcused absences count against the grade
    }
}
