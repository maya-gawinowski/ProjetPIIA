/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage; 

/*
 * Cette classe permet d'ouvrir une fenetre de gestionnaire de fichier 
 */

public class PictureHandler implements
EventHandler<ActionEvent> {
	/* 
	 * Lorsque l'utilisateur valide sont choix de fichier (dans notre cas, le choix d'une photo), 
	 * si le fichier choisi n'est pas null, alors on récupère son nom
	 */
	public void handle(ActionEvent event) {
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	String fileName = file.getName();
        	setPicName(fileName); // on sauvegarde le nom du fichier dans une variable afin de la récupérer depuis ailleurs dans le code du projet
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
	public PictureHandler(FileChooser f, Stage s) {
		fileChooser = f;
		stage = s;
	}
}