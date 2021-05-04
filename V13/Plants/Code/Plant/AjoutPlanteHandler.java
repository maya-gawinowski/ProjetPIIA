/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * Cette classe est le controlleur appelé par le bouton "ajouter" dans la partie Plante du menu.
 * Elle permet de définir et d'afficher la page de saisie d'une nouvelle plante
 */

public class AjoutPlanteHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		afficheAjoutPlante();
	 }
	
	private Herbier myHerbier;
	private VBox contenu;
	private Stage stage;
	public AjoutPlanteHandler(Herbier h, VBox c, Stage s) {
		myHerbier = h;
		contenu=c;
		stage = s;
		
	}
	
	private Desktop desktop = Desktop.getDesktop();
	private final FileChooser fileChooser = new FileChooser();
	/*
	 * Cette fonction permet l'affichage de tous les éléments de la page d'ajout d'une plante
	 */
	public void afficheAjoutPlante() {
		contenu.getChildren().removeAll(contenu.getChildren());
		
		VBox contentBis = new VBox();
		// TITRE
		Label titre = new Label("Ajouter une plante");
		titre.setFont(new Font("Arial",40));
		titre.setTextFill(Color.DARKGREEN);
		titre.setPadding(new Insets(30,30,10,0));
		contentBis.getChildren().add(titre);
		
		HBox nomplante = new HBox();
		nomplante.setPadding(new Insets(30,30,10,0));
		
		// Espace pour saisir le nom de la plante
		Label lnom = new Label ("Nom vulgaire : ");
		TextField tfnom = new TextField();
		nomplante.getChildren().add(lnom);
		nomplante.getChildren().add(tfnom);
		contentBis.getChildren().add(nomplante);
		
		HBox nomsci = new HBox();
		nomsci.setPadding(new Insets(10,30,10,0));
		
		// Espace pour saisir le nom scientifique de la plante
		Label lnomsci = new Label("Nom scientifique : ");
		TextField tfnomsci = new TextField();
		nomsci.getChildren().add(lnomsci);
		nomsci.getChildren().add(tfnomsci);
		contentBis.getChildren().add(nomsci);
		
		// on définis le répertoire par défaut de l'ordinateur qui s'ouvre lorsque le bouton est appuyé
		File defaultDirectory = new File("/Users/mayagawinowski/eclipse-workspace/Plants/Images");
		fileChooser.setInitialDirectory(defaultDirectory);
		Button buttPic = new Button("Choisissez une photo");
		
		PictureHandler ph = new PictureHandler(fileChooser,stage);
		buttPic.setOnAction(ph);
		
		contentBis.getChildren().add(buttPic);
		
		/*
		 * Bouton valider.
		 * Ce dernier appelle un nouveau controlleur SavePlanteHandler qui va récupérer les zones de textes
		 * pour en récupérer le contenu saisi par l'utilisateur afin de l'enregistrer dans le fichier de la plante
		 */
		Button valider = new Button("Enregistrer");
		SavePlanteHandler sph = new SavePlanteHandler(tfnom,tfnomsci, ph);
		String nn = ph.getPicName();
		
		valider.setOnAction(sph);
		
		contentBis.getChildren().add(valider);
		contentBis.setPadding(new Insets(30,30,10,30));
		contenu.getChildren().add(contentBis);
	}
}