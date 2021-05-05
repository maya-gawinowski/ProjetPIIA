/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner; // Import the Scanner class to read text files

/*
 * Cette classe permet la lecture d'un fichier
 */

public class Lecture {

	private static ArrayList<String> noms;
	
	public Lecture(String filename) {
		
		readFile(filename);
	}
	
	// Cette fonction permet de lire le contenu du nom du fichier donné en argument
	public void readFile(String filename) {
		try {
			noms = new ArrayList<String>();
			File myObj = new File("Text/"+filename); // Tous les fichiers text que nous manipulons sont dans le dossier source Text de l'eclipse-workspace
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				noms.add(data);
			}
			sortTabAsc(noms);// On trie le tableau par ordre alphabétique

			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("erreur");
			e.printStackTrace();
		}
	}

	/**
	 * lie l'intégralité du fichier et renvoie le texte dans
	 * une seule chaines de caractères
	 * @param filename le nom du fichier
	 * @return une chaine de caractère contenant l'intégralité du fichier
	 */
	public String readFilePlantation(String filename) {
		try {
			String temp = new String("");
			File file = new File("Text/"+filename);
			if(file.exists()) {
				//on prend le contenu du fichier dans le temporisateur
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					//on ajoute chaque ligne au temporisateur
					temp = temp.concat(data);
				}
				myReader.close();
				return temp;
			}else {
				System.out.println("Le fichier "+file.getName()+" est introuvable.");
				return "";
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return "";
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

