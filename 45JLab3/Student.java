//Dona Anda 29856735
package Class_Roster;

public class Student
{
	private int studentID;
	private String firstName;
	private String lastName;
	
	//set variables to default value
	public Student()
	{
		studentID = 0;
		firstName = "";
		lastName = "";
	}
	
	//set student ID
	public void setStudentID(int studentID)
	{
		this.studentID = studentID;
	}
	
	//set first name
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	//set last name
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	//get student ID
	public int getStudentID()
	{
		return studentID;
	}
	
	//get first name
	public String getFirstName()
	{
		return firstName;
	}
	
	//get last name
	public String getLastName()
	{
		return lastName;
	}
}
