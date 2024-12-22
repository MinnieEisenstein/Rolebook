package classes;

public enum AssignmentType {
    QUIZ(10), // Example weight for Quiz
    TEST(25), // Example weight for Test
    ESSAY(30), // Example weight for Essay
    EXTRA_CREDIT(5); // Example weight for Extra Credit

    private final double weight;

    // Constructor to set the weight
    AssignmentType(double weight) {
        this.weight = weight;
    }

    // Getter for weight
    public double getWeight() {
        return weight;
    }
}
