//Dona Anda 29856735
//Nick DiGeronimo

import java.util.*;



class MusicItem implements MusicItemInterface
{

	private String[] arrayString = new String[3]; 

	// Construct a music item from item
	// position 0: accession number
	// position 1: title
	// position 2: media code
	public MusicItem(ArrayList<String> item)
	{
		arrayString[ACCESSION_NUMBER_POSITION] = item.get(ACCESSION_NUMBER_POSITION);
		arrayString[TITLE_POSITION] = item.get(TITLE_POSITION);
		arrayString[MEDIA_CODE_POSITION] = item.get(MEDIA_CODE_POSITION);
	}

	
	// Force each media type to provide its supplemental
	// information in a form suitable for printing
	public String displaySupplementalInfo()
	{
		return "string";
	}


	// Accessors
	public String getAccessionNumber()
	{
		return arrayString[ACCESSION_NUMBER_POSITION];
	}
	
	public String getTitle() 
	{
		return arrayString[TITLE_POSITION];
	}
	
	public String getMedia() 
	{
		return arrayString[MEDIA_CODE_POSITION];
	}
}
