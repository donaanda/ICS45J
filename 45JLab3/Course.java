//Dona Anda 29856735
package Class_Roster;

import Class_Roster.RosterManager.StudentLimitException;
import Class_Roster.RosterManager.DuplicateStudentException;
import Class_Roster.RosterManager.EmptyStudentListException;
import Class_Roster.RosterManager.StudentNotFoundException;

public class Course
{
	private String courseCode;
	private String courseName;
	private int currentEnrollment;
	private Student[] arrayOfStudents;
	
	//set variables to default value
	public Course()
	{
		courseCode = "";
		courseName = "";
		currentEnrollment = 0;
		arrayOfStudents = new Student[50];
		
	}
	
	//add student to the course
	public void addStudent(Student s) throws StudentLimitException, DuplicateStudentException
	{
		//check if students can be added to the course
		if (currentEnrollment == 50)
		{
			throw new StudentLimitException();
		}
		
		//check if student ID is duplicate
		Integer sID = new Integer(s.getStudentID());
		for (int i = 0; i < currentEnrollment; i++)
		{	
			if (sID.equals(arrayOfStudents[i].getStudentID()))
			{
				throw new DuplicateStudentException();
			}
		}
		
		//add students to the course
		arrayOfStudents[currentEnrollment] = s;
		currentEnrollment++;
	}
	
	//remove student from the course
	public void removeStudent(int studentId) throws EmptyStudentListException, StudentNotFoundException
	{
		//check if student list is empty
		Integer c = new Integer(currentEnrollment);
		if (c.equals(0))
		{
			throw new EmptyStudentListException();
		}
		
		//check if student can be found
		Integer sID = new Integer(studentId);
		boolean found = false;
		for (int j = 0; j < currentEnrollment; j++)
		{	
			if (sID.compareTo(arrayOfStudents[j].getStudentID()) == 0)
			{
				found = true;
				break;
			}
		}
		if (found == false)
		{
			throw new StudentNotFoundException();
		}
		
		//find the student and drop him/her
		boolean shift = false;
		for (int i = 0; i < currentEnrollment - 1; i++)
		{	
			if (sID.equals(arrayOfStudents[i].getStudentID()))
			{
				arrayOfStudents[i] = arrayOfStudents[i+1];
				shift = true;
			}
			
			if (shift == true)
			{
				//shift elements down
				arrayOfStudents[i] = arrayOfStudents[i+1];
			}

		}
		currentEnrollment--;
	}
	
	//get array of students
	public Student[] getStudent()
	{
		return arrayOfStudents;
	}
	
	//set the course code
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	
	//set the course name
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	
	//get the course code
	public String getCourseCode()
	{
		return courseCode;
	}
	
	// get the course name
	public String getCourseName()
	{
		return courseName;
	}
	
	//get current enrollment
	public int getCurrentEnrollment()
	{
		return currentEnrollment;
	}
}
