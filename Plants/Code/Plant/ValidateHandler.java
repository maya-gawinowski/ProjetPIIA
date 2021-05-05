/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

/*
 * Cette classe est le controlleur relié au bouton validé de chaque popup.
 * Elle permet seulement d'appeler la fonction getSentence dans le fichier Herbier.java afin de récupérer les informations
 * saisies par l'utilisateur dans la popup.
 */

public class ValidateHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		//System.out.println("COUCOU");
		myHerbier.getSentence(myPopup,namePlante,bName);
	 }
	
	private Herbier myHerbier;
	private Popup myPopup;
	private String namePlante;
	private String bName;
	public ValidateHandler(Herbier h,Popup p,String n,String b) {
		myHerbier = h;
		myPopup = p;
		bName = b;
		namePlante=n;
	}
}