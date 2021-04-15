package Plants;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class HerbierHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		//System.out.println("Herbier Handler");

		// Ouverture de fichier
		Lecture read = new Lecture("nom.txt");
		
		ArrayList<String> tab = read.returnTab();
		System.out.println(tab);
		myHerbier.affichageHerbier(tab);
	}
	
	private Herbier myHerbier;
	
	public HerbierHandler(Herbier h) {
		myHerbier=h;
	}
}