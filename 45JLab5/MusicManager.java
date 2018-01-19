//Dona Anda 29856735
//Nick DiGeronimo 

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner; //for scanner


// Manage the making of the index and category counts
class MusicManager implements MusicManagerInterface
{
	private MusicList ML;


	// Constructs the music list
	public MusicManager()
	{
		ML = new MusicList();
	}


	// Prepares an index file from an incoming list of music items;
	// displays to the screen the media category counts
	// and a total count of items processed.
	public void makeIndexAndDisplayCounts()
	{
		MusicFile MF = new MusicFile();
		IndexFile IF = new IndexFile();
		Scanner s = new Scanner(System.in);
		System.out.printf("Web or File: ");
		String input = s.nextLine();
		
		if (input.equals("Web"))
		{
			System.out.printf("Enter the URL: ");
			String inputURL = s.nextLine();
			String line;
			Scanner remoteIn = null;
			try
			{
				URL remoteFileLocation = new URL(inputURL);
				URLConnection connection = remoteFileLocation.openConnection();
				remoteIn = new Scanner(connection.getInputStream());
				while (remoteIn.hasNextLine())
				{
					System.out.println(remoteIn.nextLine());
				}
			}
			catch (IOException e)
			{
				System.out.println(e.toString());
			}
			finally
			{
				if (remoteIn != null)
				{
					remoteIn.close();
				}
			}
		}
		
		if (input.equals("File"))
		{
			System.out.printf("Enter the file: ");
			String inputFile = s.nextLine();
			try
			{
				MF.open(inputFile);
				while (MF.hasMoreItems())
				{
					ArrayList<String> singleLine = MF.readItem();
					MusicItem MI = new MusicItem(singleLine);
					ML.addItem(MI);
				}
			}
			catch (IOException e)
			{
				System.out.println(e.toString());
			}
			finally
			{
				MF.close();
			}
			
			try
			{
				IF.open("output.txt");

				for (int i = 0; i < 26; i++)
				{	
					for (int j = 0; j < ML.getBuckets().get(i).getItems().size(); j++)
					{
						IF.writeItem(ML.getBuckets().get(i).getItems().get(j));
					}
				}
			}
			catch (IOException e)
			{
				System.out.println(e.toString());
			}
			finally
			{
				System.out.println("The index' file's name is output.txt");
				System.out.println("All items are " + ML.getTotalItemCount());
				IF.close();
			}
		}

	}
}
