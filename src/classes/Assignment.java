package classes;

public class Assignment {
    
    private String name;        // stores name of assignment, e.g. "Test 1", "Pop quiz", "Industrialism essay"
    private Mark mark;          // stores Mark object
    private String comment;     // stores comment about student's work e.g. "Excellent job, Jake!", "Improvement needed"
    private double weight;      // stores percentage of final grade this assignment is worth
                               // if 0/not assigned, will automatically be assigned equal percentage
    private AssignmentType type; // stores the type of the assignment (Quiz, Test, Essay, Extra Credit, etc.)

    // constructor with AssignmentType
    public Assignment(String name, Mark mark, String comment, double weight, AssignmentType type) {
        this.name = name;
        this.mark = mark;
        this.comment = comment;
        this.weight = weight;
        this.type = type;
    }

    // constructor without Mark object, for when no mark is provided yet
    public Assignment(String name, AssignmentType type, String comment) {
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public Mark getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public double getWeight() {
        return weight;
    }

    public AssignmentType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setType(AssignmentType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n" + name);
        str.append(": " + mark);
        str.append("\n" + comment);
        str.append("\nType: " + type);  // Show the assignment type
        str.append("\nWeight: " + weight + "%");  // Show the weight
        return str.toString();
    }

    // Added an equals method for use in the Subject class
    /* Equals method compares Assignments by name to see if they are equal */
    @Override 
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Assignment other = (Assignment)obj;
        return this.getName().equals(other.getName());
    }
}
