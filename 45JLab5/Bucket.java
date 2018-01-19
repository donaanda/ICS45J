//Dona Anda 29856735
//Nick DiGeronimo

import java.util.LinkedList;

// A bucket contains an alphabetical list, by title, of 
// music items. 
public class Bucket implements BucketInterface
{
	private LinkedList<MusicItem> bucket;
	// A bucket is a LinkedList of MusicItem items
	public Bucket()
	{
		bucket = new LinkedList<MusicItem>();
	}
	
	
	// Add the music item into the this bucket, 
	// in alphabetical order by title
	public void addItem(MusicItem itemToAdd)
	{
		Integer int1 = new Integer(0);
		Integer int2 = new Integer(bucket.size());
		if (int1.compareTo(int2) == 0)
		{
			bucket.add(itemToAdd);
		}
		else
		{
			for (int i = 0; i < bucket.size(); i++)
			{
				if (itemToAdd.getTitle().compareTo(bucket.get(bucket.size() - 1).getTitle()) > 0)
				{
					bucket.addLast(itemToAdd);
					break;
				}
				
				if (itemToAdd.getTitle().compareTo(bucket.get(i).getTitle()) < 0)
				{
					bucket.add(i, itemToAdd);
					break;
				}
			}
		}
		
	}

	
	// Accessor -- get the list
	public LinkedList<MusicItem> getItems()
	{
		return bucket;
	}
}
