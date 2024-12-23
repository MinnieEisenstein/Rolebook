package classes;

public class AssignmentType {
    private String name;
    private double weight;

    public AssignmentType(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
