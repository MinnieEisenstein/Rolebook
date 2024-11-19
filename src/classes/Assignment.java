package classes;

public class Assignment {
	
	private String name;		//stores name of assignment, e.g. "Test 1", "Pop quiz", "Industrialism essay"
	private Mark mark;			//stores Mark object
	private String comment;		//stores comment about student's work e.g. "Excellent job, Jake!", "Improvement needed"
	private double weight;		//stores percentage of final grade this assignment is worth
								//if 0/not assigned, will automatically be assigned equal percentage

	//overloaded constructors
	public Assignment(String name, Mark mark, String comment, double weight) {
		this.name = name;
		this.mark = mark;
		this.comment = comment;
		this.weight = weight;
	}
	
	public Assignment(String name, Mark mark, String comment) {
		this(name, mark, comment, 0);
	}
	
	public Assignment(String name, Mark mark, double weight) {
		this(name, mark, "", weight);
	}
	
	public Assignment(String name, Mark mark) {
		this(name, mark, "", 0);
	}
	
	public Assignment(String name, int mark) {
		this(name, new Mark(mark));
	}
	
	//getters and setters

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
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n" + name);
		str.append(": " + mark);
		str.append("\n" + comment);
		return str.toString();
	}
	
	
	//Added an equals method for use in the Subject class
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