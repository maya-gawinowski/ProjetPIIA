/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage; 

/*
 * Cette classe permet d'ouvrir une fenetre de gestionnaire de fichier 
 * Cette classe est utilisé seulement pour la popup d'ajout d'une nouvelle note et observation à la fiche d'une plante.
 * L'ouverture du gestionnaire de fichier fermant automatiquement la popup, lors du choix du fichier, l'ensemble du contenu de la popup est 
 * directement sauvegardé et écris dans le fichier de la plante
 */

public class PicHandlerForNotes implements
EventHandler<ActionEvent> {
	/* 
	 * Lorsque l'utilisateur valide sont choix de fichier (dans notre cas, le choix d'une photo), 
	 * si le fichier choisi n'est pas null, alors on récupère son nom
	 * on appelle ensuite la fonction getSentence pour récupérer le contenu de la popup
	 */
	public void handle(ActionEvent event) {
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	String filePath = file.getAbsolutePath();
        	String fileName = file.getName();
        	System.out.println(fileName);	
        	setPicName(fileName);
        	myHerbier.getSentence(pop,namePlant, bName);
        }
	 }
	
	// ACCESSEURS du nom du fichier choisi
	
	private String name;
	public void setPicName(String n) {
		name=n;
	}
	
	public String getPicName() {
		return name;
	}
	
	private FileChooser fileChooser;
	private Stage stage;
	private AjoutPlanteHandler ajout;
	private Herbier myHerbier;
	private String namePlant, bName;
	private Popup pop;
	public PicHandlerForNotes(FileChooser f, Stage s, Herbier h, Popup p, String np, String n) {
		fileChooser = f;
		stage = s;
		myHerbier = h;		
		namePlant = np;
		bName = n;
		pop = p;
	}
	
	
}
