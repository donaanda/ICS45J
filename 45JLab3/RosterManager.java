//Dona Anda 29856735
package Class_Roster;
import java.util.Scanner;

public class RosterManager
{
	private Course[] arrayOfCourses;
	private int totalNumOfCurrentCourses;
	private Scanner input;
	private String command;
	private Course c;
	private Student s;
	
	public class CourseLimitException extends Exception {}
	
	public class DuplicateCourseException extends Exception {}
	
	public class CourseNotFoundException extends Exception {}
	
	public class EmptyCourseListException extends Exception {}
	
	public static class StudentLimitException extends Exception {}
	
	public static class DuplicateStudentException extends Exception {}
	
	public static class EmptyStudentListException extends Exception {}
	
	public static class StudentNotFoundException extends Exception {}
	
	//set variables to default value
	public RosterManager() 
	{
		arrayOfCourses = new Course[10];
		totalNumOfCurrentCourses = 0;
		input = new Scanner(System.in);
		command = "";
	}
	
	//the program
	public void run()
	{
		System.out.printf("Welcome to Class Roster Manager!\n" + 
						  "Select an action based on the following menu:\n");
		while(true)
		{	
			ClassRosterUI.printMenu();
			command = ClassRosterUI.getCommand();
			if (command.equals("ac"))
			{
				c = new Course();
				String courseCode = ClassRosterUI.enterCourseCode();
				c.setCourseCode(courseCode);
				String courseName = ClassRosterUI.enterCourseName();
				c.setCourseName(courseName);
				
				try
				{
					addCourse(c);
				}
				catch (CourseLimitException e)
				{
					System.out.println("ERROR: Course limit reached.");
				}
				catch (DuplicateCourseException e)
				{
					System.out.println("ERROR: Duplicate course.");
				}
				
	
			}
			else if (command.equals("dc"))
			{
				String courseCode = ClassRosterUI.enterCourseCode();
				try
				{
					deleteCourse(courseCode);
				}
				catch (CourseNotFoundException e)
				{
					System.out.println("ERROR: Could not find course.");
				}
				catch (EmptyCourseListException e)
				{
					System.out.println("ERROR: Empty course list.");
				}
			}
			else if (command.equals("as"))
			{
				s = new Student();
				String courseCodeForStudent = ClassRosterUI.enterCourseCodeForStudent();
				int studentID = ClassRosterUI.enterStudentID();
				s.setStudentID(studentID);
				String lastName = ClassRosterUI.enterLastName();
				s.setLastName(lastName);
				String firstName = ClassRosterUI.enterFirstName();
				s.setFirstName(firstName);
				
				try
				{
					addStudent(courseCodeForStudent, s);
				}
				catch (StudentLimitException e)
				{
					System.out.println("ERROR: Student limit reached.");
				}
				catch (DuplicateStudentException e)
				{
					System.out.println("ERROR: Duplicate student ID.");
				}
				catch (CourseNotFoundException e)
				{
					System.out.println("ERROR: Could not find course.");
				}
			}
			else if (command.equals("ds"))
			{
				String courseCodeForStudent = ClassRosterUI.enterCourseCodeForStudent();
				int studentID = ClassRosterUI.enterStudentID();
				
				try
				{
					deleteStudent(studentID, courseCodeForStudent);
				}
				catch (EmptyStudentListException e)
				{
					System.out.println("ERROR: Empty student list.");
				}
				catch (StudentNotFoundException e)
				{
					System.out.println("ERROR: Student could not be found.");
				}
				catch (CourseNotFoundException e)
				{
					System.out.println("ERROR: Could not find course.");
				}
			}
			else if (command.equals("p"))
			{
				printRoster();
			}
			else if (command.equals("q"))
			{
				break;
			}
		}
		input.close();
	}
	
	//add course 
	public void addCourse(Course c)	throws DuplicateCourseException, CourseLimitException	
	{
		//check if add more than 10 courses
		if (totalNumOfCurrentCourses == 10)
			throw new CourseLimitException();
		
		//check if new course is duplicate
		for (int i = 0; i < totalNumOfCurrentCourses; i++)
		{
			if (c.getCourseCode().equals(arrayOfCourses[i].getCourseCode()))
			{
				throw new DuplicateCourseException();
			}
		}
		
		//add the course
		arrayOfCourses[totalNumOfCurrentCourses] = c;
		totalNumOfCurrentCourses++;
	}
	
	//delete course
	public void deleteCourse(String	courseCode)	throws CourseNotFoundException, EmptyCourseListException
	{
		//Check if the course exists
		Integer T = new Integer(totalNumOfCurrentCourses);
		if (T.equals(0))
		{
			throw new EmptyCourseListException();
		}
		
		boolean found = false;
		for (int j = 0; j < totalNumOfCurrentCourses; j++)
		{	
			if (courseCode.compareTo(arrayOfCourses[j].getCourseCode()) == 0)
			{
				found = true;
				break;
			}
		}
		if (found == false)
		{
			throw new CourseNotFoundException();
		}
		
		//find the course and delete it
		boolean shift = false;
		for (int i = 0; i < totalNumOfCurrentCourses - 1; i++)
		{	
			if (courseCode.equals(arrayOfCourses[i].getCourseCode()))
			{
				arrayOfCourses[i] = arrayOfCourses[i+1];
				shift = true;
			}
			
			if (shift == true)
			{
				arrayOfCourses[i] = arrayOfCourses[i+1];
			}
		}
		totalNumOfCurrentCourses--;
	}
	
	//add student to the course
	public void addStudent(String courseCode, Student s) throws StudentLimitException, DuplicateStudentException, CourseNotFoundException
	{
		//check if course is found
		boolean found = false;
		for (int j = 0; j < totalNumOfCurrentCourses; j++)
		{	
			if (courseCode.compareTo(arrayOfCourses[j].getCourseCode()) == 0)
			{
				found = true;
				break;
			}
		}
		if (found == false)
		{
			throw new CourseNotFoundException();
		}
		
		//find the course and add students
		for (int i = 0; i < totalNumOfCurrentCourses; i++)
		{
			if (courseCode.equals(arrayOfCourses[i].getCourseCode()))
			{
				arrayOfCourses[i].addStudent(s);
			}
		}
	}
	
	public void deleteStudent(int id, String courseCode) throws EmptyStudentListException, StudentNotFoundException, CourseNotFoundException
	{
		//check if course is found
		boolean found = false;
		for (int j = 0; j < totalNumOfCurrentCourses; j++)
		{	
			if (courseCode.compareTo(arrayOfCourses[j].getCourseCode()) == 0)
				{
					found = true;
					break;
				}
		}
		if (found == false)
		{
			throw new CourseNotFoundException();
		}
				
		//remove student from the course
		for (int i = 0; i < totalNumOfCurrentCourses; i++)
		{
			if (courseCode.equals(arrayOfCourses[i].getCourseCode()))
			{
				arrayOfCourses[i].removeStudent(id);
			}
		}
	}
	
	//print roster on console
	public void printRoster()
	{
		for (int i = 0; i < totalNumOfCurrentCourses; i++)
		{
			System.out.println(arrayOfCourses[i].getCourseCode() + ": " + arrayOfCourses[i].getCourseName());
			System.out.println("Enrolled: " + arrayOfCourses[i].getCurrentEnrollment());
			for (int j = 0; j < arrayOfCourses[i].getCurrentEnrollment(); j++)
			{
				System.out.println("	  " + arrayOfCourses[i].getStudent()[j].getStudentID() + " | " + 
											  arrayOfCourses[i].getStudent()[j].getLastName() + ", " + 
											  arrayOfCourses[i].getStudent()[j].getFirstName());
			}
		}
	}
	
}
