//Dona Anda 29856735
//Nick DiGeronimo

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.*; // for ArrayList

public class MusicFile implements MusicFileInterface
{
	private Scanner infile;
	private String[] stringArray;
	private String s;
	private ArrayList<String> arrayList;
	
	public MusicFile()
	{
		infile = null;
		s = "";
		arrayList = new ArrayList<String>();
	}

	// Opens a sequential text file to accept 
	// music info and parse it
	//
	// Throws IOException if music file cannot
	// be opened or other IOproblems occur
	public void open(String indexFileName) throws IOException
	{
		//URL url = new URL(indexFileName);
		//url.openConnection()
		
		infile = new Scanner(new File(indexFileName));
	}


	// Are there more items in the file?
	// true = yes (not at end of file); false otherwise
	public boolean hasMoreItems()
	{
		return infile.hasNextLine();
	}


	// Read and return one music item, as as ArrayList<String>
	// Number of attributes will varying depending on type of item
	//	read -- but first three are always the same:
	//	accession number, title, media code
	// Hint: Read in a line, then use string tokenizer to break it
	//	into fields, with ";" (and end of string) as the delimiter
	public ArrayList<String> readItem() 
	{	
		s = infile.nextLine();
		stringArray = s.split(";");
		arrayList.clear();
		for (int i = 0; i < stringArray.length; i++)
		{
			arrayList.add(stringArray[i]);
		}
		return arrayList;
	}


	// Close the file
	public void close() 
	{
		infile.close();
	}
}

