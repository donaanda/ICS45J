//Dona Anda 29856735 

public class Book
{
	private int numOfPages;
	private int year;
	private double price;
	private String title;
	private Author author;
	
	public Book(int numOfPages, int year, double price, String title, Author author)
	{
		this.numOfPages = numOfPages;
		this.year = year;
		this.price = price;
		this.title = title;
		this.author = author;
	}
	
	public Book()
	{}
	
	Book(Book b)
	{
		numOfPages = b.numOfPages;
		year = b.year;
		price = b.price;
		title = b.title;
		author = b.author;
	}
	
	public String toString()
	{
		return "Title: " + this.title + "\n" + 
				"Published in: " + this.year + "\n" + 
				"Number of Pages: " + this.numOfPages + "\n" + 
				"Price: $" + this.price + "\n" + 
				"Written by " + this.author.getFirstName() + " " + this.author.getLastName() + ", who was born in " + 
				this.author.getBirthYear() + " and has " + this.author.getNumOfPublications() + " Publications" + "\n";
	}
	
	public int getNumOfPages()
	{
		return numOfPages;
	}
	
	public int getYearPublished()
	{
		return year;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Author getAuthor()
	{
		return author;
	}
	
	public void setNumOfPages(int numberOfPages)
	{
		this.numOfPages = numberOfPages;
	}
	
	public void setYearPublished(int year)
	{
		this.year = year;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setAuthor(String firstName, String lastName, int birthYear, int numOfPublications)
	{	
		this.author = new Author();
		this.author.setFirstName(firstName);
		this.author.setLastName(lastName);
		this.author.setBirthYear(birthYear);
		this.author.setNumOfPublications(numOfPublications);
		
	}
}
