/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/*
 * Cette classe est le controlleur qui relie le bouton "Enregistrer" de la page Ajouter un nouvelle plante
 * Elle permet de récupérer l'ensemble du contenu saisi par l'utilisateur puis de l'écrire dans le fichier de la plante
 */

public class SavePlanteHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		// On récupère le contenu des éléments saisi par l'utilisateur
		String filename = nom.getText()+".txt";
		String thenomsci = "2:"+nomsci.getText();
		String thenom = nom.getText();
		String picName = pictureHandler.getPicName();
		System.out.println("hello : "+picName);
		saveInfos(filename, thenom, thenomsci,picName);
	 }
	
	private TextField nom,nomsci;
	private PictureHandler pictureHandler;
	public SavePlanteHandler(TextField n, TextField ns, PictureHandler ph) {
		nom = n;
		nomsci = ns;
		pictureHandler = ph;
	}
	
	/*
	 * Cette fonction écris les informations dans le fichier de la plante
	 */
	public void saveInfos(String f, String n, String ns, String p) {
		Ecriture e = new Ecriture();
		System.out.println(f);
		String Newligne=System.getProperty("line.separator");
		try {
			e.ecrire(f, "1:"+n+Newligne);
			e.ecrire(f, ns+Newligne);
			e.ecrire("nom.txt", n+Newligne);
			if (p!="") {
				e.ecrire(f, "image:"+p+Newligne);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}