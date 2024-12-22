package classes;

public enum LetterGrade {
    A,
    B,
    C,
    D,
    F;

    // Method to convert a numerical score to a LetterGrade
    public static LetterGrade getLetterGrade(double score) {
        if (score >= 90) {
            return A;
        } else if (score >= 80) {
            return B;
        } else if (score >= 70) {
            return C;
        } else if (score >= 60) {
            return D;
        } else {
            return F;
        }
    }
}
