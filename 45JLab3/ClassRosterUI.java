//Dona Anda 29856735
package Class_Roster;
import java.util.Scanner;

public class ClassRosterUI
{
	//print out the menu
	public static void printMenu()
	{
		System.out.printf(
		"----------\n" +
		"ac: Add Course\n" +
		"dc: Drop Course\n" +
		"as: Add Student\n" +
		"ds: Drop Student\n" +
		"p: Print ClassRoster\n" +
		"q: Quit Program\n" +
		"----------\n" +
		"Enter Command: ");
	}
	
	//get the command
	public static String getCommand()
	{	
		
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
	
	//get course code for student
	public static String enterCourseCodeForStudent()
	{
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter course code for Student: ");
		return input.nextLine();
	}
	
	//get student ID that is entered
	public static int enterStudentID()
	{
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter StudentID: ");
		return input.nextInt();
	}
	
	//get last name that is entered
	public static String enterLastName()
	{
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter last name: ");
		return input.nextLine();
	}
	
	//get first name that is entered
	public static String enterFirstName()
	{
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter first name: ");
		return input.nextLine();
	}
	
	//get course code that is entered
	public static String enterCourseCode()
	{
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter Course Code: ");
		return input.nextLine();
	}
	
	//get course name that is entered
	public static String enterCourseName()
	{
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter Course Name: ");
		return input.nextLine();
	}
}
