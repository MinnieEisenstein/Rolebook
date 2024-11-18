package classes;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//the purpose of this class is to show how each class functions
		
		//create sample teacher objects (1-3)
		Teacher teacher1 = new Teacher("Miss Gold", "100", new ClassList(9, "9a", new ArrayList<Student>(), 94), "Math");
		Teacher teacher2 = new Teacher("Mrs. Cohen", "110", new ClassList(10, "10a", new ArrayList<Student>(), 97), "Math");
		Teacher teacher3 = new Teacher("Mr. Reiss", "120", new ClassList(11, "11a", new ArrayList<Student>(), 81), "Math");

		//print them
		System.out.println(teacher1);
		System.out.println(teacher2);
		System.out.println(teacher3);		

		//create sample subject objects
		Subject subject1 = new Subject("Math");
		Subject subject2 = new Subject("Jewish History");
		Subject subject3 = new Subject("Computer Science");
		
		//print them
		System.out.println(subject1);
		System.out.println(subject2);
		System.out.println(subject3);
		
		//create sample mark objects
		
		//Mark(int num)
		System.out.println("Creating Mark objects using the constructor that takes in a number...");
		Mark mark1 = new Mark(99);
		Mark mark2 = new Mark(64);
		Mark mark3 = new Mark(85);
		Mark mark4 = new Mark(119);//should throw an error
		
		//print them
		System.out.println("Mark 1 is "  +mark1);
		System.out.println("Mark 2 is "  +mark2);
		System.out.println("Mark 3 is "  +mark3);
		System.out.println("Mark 4 is "  +mark4);
		
		//public Mark(LetterGrade letter)
		System.out.println("Creating Mark objects using the constructor that takes in a LetterGrade enum...");
		Mark mark5= new Mark(LetterGrade.C);
		Mark mark6= new Mark(LetterGrade.D);
		Mark mark7= new Mark(LetterGrade.F);
		
		System.out.println("Mark 5 is "  +mark5);
		System.out.println("Mark 6 is "  +mark6);
		System.out.println("Mark 7 is "  +mark7);
		
		
		//public Mark(Status status)
		System.out.println("Creating Mark objects using the constructor that takes in Status enum");
		Mark mark8 = new Mark(Status.PASS);
		Mark mark9 = new Mark(Status.FAIL);
		
		System.out.println("Mark 8 is "  +mark8);
		System.out.println("Mark 9 is "  +mark9);
		
		//create sample student objects	
		//	public Student(String studentName, ClassList studentClass, ArrayList<Assignment> assignments, int average) { 
		Student student1 = new Student("Brad Herman", new ClassList(9, "9a", new ArrayList<Student>(), 94), new ArrayList<Assignment>(), 98);
		Student student2 = new Student("Brian Gross", new ClassList(9, "9a", new ArrayList<Student>(), 94), new ArrayList<Assignment>(), 89);
		Student student3 = new Student("Bruce Spry", new ClassList(9, "9a", new ArrayList<Student>(), 94), new ArrayList<Assignment>(), 91);

		//print them
		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student3);
		
		//create sample assignment objects
		
		//print them
		
		
		
		
		//create sample class objects
	}

}
