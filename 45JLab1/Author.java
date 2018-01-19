//Dona Anda 29856735 

public class Author
{
	private String firstName;
	private String lastName;
	private int birthYear;
	private int numOfPublications;
	
	public Author(String firstName, String lastName, int birthYear, int numOfPublications)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.numOfPublications = numOfPublications;
	}
	
	public Author()
	{}
	
	Author(Author a)
	{
		firstName = a.firstName;
		lastName = a.lastName;
		birthYear = a.birthYear;
		numOfPublications = a.numOfPublications;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public int getBirthYear()
	{
		return birthYear;
	}
	
	public int getNumOfPublications()
	{
		return numOfPublications;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setBirthYear(int birthYear)
	{
		this.birthYear = birthYear;
	}
	
	public void setNumOfPublications(int numOfPublications)
	{
		this.numOfPublications = numOfPublications;
	}
}
