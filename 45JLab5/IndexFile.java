//Dona Anda 29856735
//Nick DiGeronimo

// Sets up requirements for open, write,
// and close routines for the index file.
// Note it does not specify that file's location,
// so the implementing class is free to place
// the file where needed

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IndexFile implements IndexFileInterface
{
	private PrintWriter outfile;
	
	public IndexFile()
	{
		outfile = null;
	}
	
	// Open index file with name indexFileName
	// for sequential writing
	//
	// Opens a sequential text file to accept 
	// output; prints out report headings
	//
	// Throws IOException if index file cannot
	// be opened or other IO problems occur
	public void open(String indexFileName) throws IOException
	{
		outfile = new PrintWriter(indexFileName);
	}


	// Writes out the current line of the report,
	// contained in itemToWrite
	public void writeItem(MusicItem itemToWrite)
	{
		outfile.format("%-50s %-10s %5s", itemToWrite.getTitle(), itemToWrite.getAccessionNumber(), itemToWrite.getMedia());
		outfile.write("\n");
	}

	
	// closes the index file
	public void close() 
	{
		outfile.close();
	}


}