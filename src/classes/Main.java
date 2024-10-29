package classes;

public class Main {

	public static void main(String[] args) {
		//the purpose of this class is to show how each class functions
		
		//create sample teacher objects (1-3)
		
		//print them
		
		

		//create sample subject objects
		
		//print them
		
		
		
		
		
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
		
		//print them
		
		
		
		
		
		//create sample assignment objects
		
		//print them
		
		
		
		
		//create sample class objects
	}

}
