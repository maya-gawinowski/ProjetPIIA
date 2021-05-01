package Plant2;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
// Import the Scanner class to read text files
import java.util.*;

import com.calendarfx.model.Entry;

import java.lang.*;
import java.io.*;
public class Lecture {

	private static ArrayList<String> noms;
	
	public Lecture(String filename) {
		
		readFile(filename);
	}
	
	public void readFile(String filename) {
		try {
			String[] tab = new String[10];
			noms = new ArrayList<String>();
			Integer indice = 0;
//			System.out.println(filename);
			File myObj = new File("Text/"+filename);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				noms.add(data);
			}
			sortTabAsc(noms);

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

  
	public static void sortTabAsc(ArrayList<String> mots) {
		//System.out.println("sortTab");
		Collections.sort(mots);
		//System.out.println(mots);
	}
	  
	public ArrayList<String> returnTab(){
		return noms;
	}
	
	  
}


