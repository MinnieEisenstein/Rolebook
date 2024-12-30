package classes;

import java.util.ArrayList;

public class AssignmentManager {
    private ArrayList<AssignmentType> assignmentTypes;

    public AssignmentManager() {
        assignmentTypes = new ArrayList<>();
        // Initialize with default assignment types and weights
        assignmentTypes.add(new AssignmentType("Quiz", 10.0));
        assignmentTypes.add(new AssignmentType("Essay", 20.0));
        assignmentTypes.add(new AssignmentType("Test", 25.0));
        assignmentTypes.add(new AssignmentType("Project", 30.0));
        assignmentTypes.add(new AssignmentType("Homework", 10.0));
        assignmentTypes.add(new AssignmentType("Attendance", 5.0)); // Added Attendance with default weight
    }

    // Getter for the assignment types
    public ArrayList<AssignmentType> getAssignmentTypes() {
        return assignmentTypes;
    }

    // Display current assignment types and weights
    public void displayAssignmentTypesAndWeights() {
        System.out.println("\nCurrent Assignment Types and Weights:");
        double totalWeight = 0;
        for (AssignmentType type : assignmentTypes) {
            totalWeight += type.getWeight();
            System.out.printf("%s: %.2f%%\n", type.getName(), type.getWeight());
        }
        System.out.printf("Total Weight: %.2f%%\n", totalWeight);
    }

    // Display assignment types without weights (e.g., for menu options)
    public void displayAssignmentTypes() {
        int index = 1;
        for (AssignmentType type : assignmentTypes) {
            System.out.println(index + ". " + type.getName());
            index++;
        }
    }

    // Get the name of an assignment type by its index
    public String getTypeByIndex(int index) {
        if (index > 0 && index <= assignmentTypes.size()) {
            return assignmentTypes.get(index - 1).getName();
        }
        return null;
    }

    // Get the weight of an assignment type by name
    public double getWeightByType(String typeName) {
        for (AssignmentType type : assignmentTypes) {
            if (type.getName().equalsIgnoreCase(typeName)) {
                return type.getWeight();
            }
        }
        return 0.0; // Return 0 if the type does not exist
    }

    // Add a new assignment type with a specified weight
    public void addAssignmentType(String typeName, double weight) {
        for (AssignmentType type : assignmentTypes) {
            if (type.getName().equalsIgnoreCase(typeName)) {
                System.out.println("This type already exists. Choose a different name.");
                return;
            }
        }
        assignmentTypes.add(new AssignmentType(typeName, weight));
        System.out.println("Assignment type added successfully.");
    }

 // Adjust weights programmatically with an external input
    public boolean adjustWeights(ArrayList<Double> newWeights) {
        if (newWeights.size() != assignmentTypes.size()) {
            System.out.println("Invalid input: Weight count does not match the number of assignment types.");
            return false;
        }

        double totalWeight = 0.0;
        for (double weight : newWeights) {
            if (weight < 0 || weight > 100) {
                System.out.println("Invalid input: Each weight must be between 0 and 100.");
                return false;
            }
            totalWeight += weight;
        }

        if (totalWeight != 100.0) {
            System.out.println("Invalid input: Total weight must equal 100%. Please try again.");
            return false;
        }

        // Update the weights
        for (int i = 0; i < assignmentTypes.size(); i++) {
            assignmentTypes.get(i).setWeight(newWeights.get(i));
        }

        System.out.println("Weights updated successfully.");
        return true;
    }
}
