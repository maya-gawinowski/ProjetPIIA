/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/*
 * Cette classe relié au bouton "consulter" dans la partie Plante du menu,
 * permet l'affichage de l'Herbier (liste des plantes de l'utilisateur) 
 */

public class HerbierHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {

		// Ouverture de fichier
		Lecture read = new Lecture("nom.txt");
		
		ArrayList<String> tab = read.returnTab();
		System.out.println(tab);
		affichageHerbier(tab);
	}
	
	
	// Le constructeur permet de récupérer des informations importantes comme le nom de la plante manipulée
	private Herbier myHerbier;
	private VBox contenu, menu;
	private HBox box1;
	private Scene scene;
	public HerbierHandler(Herbier h, VBox c, Scene s, VBox m, HBox b) {
		myHerbier=h;
		contenu = c;
		menu = m;
		box1 = b;
	}
	
	/*
	 * Cette fonction permet l'affichage de la page de l'Herbier
	 */
	public void affichageHerbier(ArrayList<String> tab) {
		contenu.getChildren().removeAll(contenu.getChildren());
		
		// Titre
		Label titre = new Label("Herbier");
		titre.setFont(new Font("Arial",40));
		titre.setTextFill(Color.DARKGREEN);
		titre.setPadding(new Insets(30,30,10,30));
		contenu.getChildren().add(titre);
		
		// ScrollPane
		VBox temp = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(temp);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setPrefSize(500, 700);
		
		/* On récupère la première lettre du premier nom de plante de l'Herbier trié par ordre alphabétique
		* Cela permettra par la suite d'afficher des lettres pour permettre à l'utilisateur de se repérer par rapport au lettres
		* qui délimitent les noms des plantes
		* */
		String lettreRef = String.valueOf(tab.get(0).charAt(0));
		lettreRef = lettreRef.toUpperCase();
		Label prems = new Label(lettreRef);
		prems.setFont(new Font("Arial",30));
		prems.setTextFill(Color.DARKGREEN);
		prems.setPadding(new Insets(30,30,10,30));
		temp.getChildren().add(prems);
		// on parcours l'ArrayList contenant tous les noms de plantes et les ajoute à la fenetre sous forme de boutons
		for(int i=0;i<tab.size();i++) {
			// on récupère la première lettre du nom de la plante
			String newLetter = String.valueOf(tab.get(i).charAt(0));
			newLetter = newLetter.toUpperCase();
			// si les deux lettres sont différentes, alors on ajoute entre les deux noms un nouveau label contenant cette nouvelle letre
			if(!newLetter.contains(lettreRef)) {
				Label l = new Label(newLetter);
				l.setFont(new Font("Arial",30));
				l.setTextFill(Color.DARKGREEN);
				l.setPadding(new Insets(30,30,10,30));
				temp.getChildren().add(l);
				lettreRef=newLetter;
			}
			
			// Bouton portant le nom de la plante et permettant d'afficher la fiche de la plante
			PlantButtonHandler pbh = new PlantButtonHandler(tab.get(i),myHerbier, menu, scene, contenu, box1);// controlleur de chaque bouton
			Button l = new Button(tab.get(i));
			l.setOnAction(pbh);
			temp.getChildren().add(l);
		}
		contenu.getChildren().add(scrollPane);// on ajoute le tout à la fenêtre
		
	}
}
