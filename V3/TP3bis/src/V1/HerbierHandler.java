package V1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Scene;

public class HerbierHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		//System.out.println("Herbier Handler");

		// Ouverture de fichier
		Lecture read = new Lecture("nom.txt");
		
		ArrayList<String> tab = read.returnTab();
		System.out.println(tab);
		affichageHerbier(tab);
	}
	
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
	
	public void affichageHerbier(ArrayList<String> tab) {
		contenu.getChildren().removeAll(contenu.getChildren());
	
		// créations des widgets 
		VBox temp = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(temp);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setPrefSize(500, 700);
		
		// parcours l'ArrayList contenant tous les noms de plantes et les ajoute à la fenetre sous forme de boutons
		for(int i=0;i<tab.size();i++) {
			PlantButtonHandler pbh = new PlantButtonHandler(tab.get(i),myHerbier, menu, scene, contenu, box1);// controlleur de chaque bouton
			Button l = new Button(tab.get(i));
			l.setOnAction(pbh);
			temp.getChildren().add(l);
		}
		contenu.getChildren().add(scrollPane);// on ajoute le tout à la fenêtre
		
	}
}