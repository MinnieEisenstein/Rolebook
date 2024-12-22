package classes;

import java.util.Scanner;

public enum AssignmentType {
	 QUIZ(10),     // Quizzes have a default weight of 10%
	    ESSAY(30),    // Essays have a default weight of 30%
	    TEST(50),     // Tests have a default weight of 50%
	    HOMEWORK(10), // Homework has a default weight of 10%
	    EXTRA_CREDIT(0); // Extra credit has no base weight, it's added on top of existing grades

	    private final double defaultWeight;

	    // Constructor
	    AssignmentType(double defaultWeight) {
	        this.defaultWeight = defaultWeight;
	    }

	    // Getter for default weight
	    public double getDefaultWeight() {
	        return defaultWeight;
	    }

	    // Method to change the default weight for types that allow it
	    public static double setCustomWeight(Scanner keyboard) {
	        System.out.println("Enter the custom weight for this assignment type: ");
	        return keyboard.nextDouble();
	    }

}
