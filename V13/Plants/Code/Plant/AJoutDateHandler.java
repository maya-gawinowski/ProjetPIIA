/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

/*
 * Cette classe est le controlleur des boutons d'ajout d'informations dans la fiche d'une plante.
 * après avoir défini quel bouton a été pressé, le code renvoie vers affichage de la popup correspondante : fonction affichePopup dans Herbier.java
 */

public class AJoutDateHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		buttonname = event.getSource().toString();
		if(buttonname.contains("+ Date")) {
			buttonname = "Date";
		}
		if(buttonname.contains("+ Mesure")) {
			buttonname = "Mesure";
		}
		if(buttonname.contains("+ Notes")) {
			buttonname = "Notes";
			System.out.println("Notes");
		}
		else {
			System.out.println("nope");
		}
		monHerbier.affichePopup(myPopup,name,buttonname);
		//System.out.println("name: "+name);
	 }
	
	private Herbier monHerbier;
	private Popup myPopup;
	private String name;
	private String buttonname;
	public AJoutDateHandler(Herbier h, Popup p,String n) {
		monHerbier=h;
		myPopup = p;
		name=n;
	}
	
	
}
