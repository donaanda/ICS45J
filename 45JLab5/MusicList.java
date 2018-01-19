//Dona Anda 29856735
//Nick DiGeronimo

import java.util.ArrayList;

// The list of music items (an ArrayList of 26 cells,
// representing the letters A to Z, with each cell 
// a Bucket that contains a list of MusicItems whose
// title all start with the letter the cell represents,
// and in sorted order by title.
public class MusicList implements MusicListInterface
{
	private ArrayList<Bucket> listOfMusicItems;
	private Bucket b;
	private int countsOfItems;
	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	// Make a bucket for each of the 26 list locations;
	// Set counts of items to zero
	public MusicList()
	{
		listOfMusicItems = new ArrayList<Bucket>();
		for (int g = 0; g < 26; g++)
		{
			b = new Bucket();
			listOfMusicItems.add(b);
		}
		
		countsOfItems = 0;
	}

	
	// Add an item into the correct bucket in the list
	// Bucket to use is one corresponding to first letter in title
	// Increment the appropriate media count
	public void addItem(MusicItem item)
	{
		int arrayIndex = ALPHABET.indexOf(item.getTitle().substring(1, 2).toUpperCase());
		for (int i = 0; i < 26; i++)
		{	
			Integer int1 = new Integer(i);
			Integer int2 = new Integer(arrayIndex);
			if (int1.compareTo(int2) == 0)
			{
				listOfMusicItems.get(i).addItem(item);
				countsOfItems++;
				break;
			}
		}
		
	}
	
	
// Accessors	
	
	public ArrayList<Bucket> getBuckets() 
	{
		return listOfMusicItems;
	}
	
	public int getTotalItemCount()  {
		return countsOfItems;
	}
	
	public int getPaperItemCount()   {
		return 0;
	}
	
	public int getCompactMediaItemCount()   {
		return 0;
	}
	
	public int getVinylItemCount()   {
		return 0;
	}
	
	public int getWaxCylinderItemCount()   {
		return 0;
	}
}


