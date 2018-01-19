//Dona Anda 29856735 

public class Lab1
{
	public static void main(String[] args)
	{
		Book book1 = new Book();
		book1.setTitle("Harry Potter and the Goblet of Fire");
		book1.setPrice(12.99);
		book1.setYearPublished(2000);
		book1.setNumOfPages(734);
		book1.setAuthor("J.K.", "Rowling", 1965, 7);
		
		Book book2 = new Book(book1);
		System.out.println(book1.toString());
		System.out.println(book2.toString());
		
		book2.setNumOfPages(8);
		System.out.println(book2.getNumOfPages());
	}
}
