package classes;

import java.util.Scanner;

public class RunProgram {
	public static void main(String[] args) throws StudentNotFoundException, NoStudentsException, EmptyClassException {
		Scanner keyboard = new Scanner(System.in);
		
		// Prompt user for initial information
		System.out.println("Welcome to the Rolebook Program!");
		System.out.println("What is the teacher's name?");
		String name = keyboard.nextLine();
		System.out.println("What is the teacher's subject");
		String subject = keyboard.nextLine();
		
		
		// Create ProgramUser object
		ProgramUser programUser = new ProgramUser(name, subject);
		
		// Call the runProgram method of ProgramUser to execute Rollbook functionality
		programUser.runProgram(keyboard);
		
		keyboard.close();
	}
}