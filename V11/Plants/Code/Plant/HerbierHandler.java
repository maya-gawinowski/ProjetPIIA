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
		Label titre = new Label("Herbier");
		titre.setFont(new Font("Arial",40));
		titre.setTextFill(Color.DARKGREEN);
		titre.setPadding(new Insets(30,30,10,30));
		contenu.getChildren().add(titre);
		// créations des widgets 
		VBox temp = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(temp);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setPrefSize(500, 700);
		
		String lettreRef = String.valueOf(tab.get(0).charAt(0));
		lettreRef = lettreRef.toUpperCase();
		// parcours l'ArrayList contenant tous les noms de plantes et les ajoute à la fenetre sous forme de boutons
		for(int i=0;i<tab.size();i++) {
			/*String firstlettre = String.valueOf(tab.get(i).charAt(0));
			String letterbis = String.valueOf(tab.get(i+1).charAt(0));
			System.out.println(firstlettre);
			System.out.println(letterbis);
			if(i==0) {
				Label l = new Label(firstlettre);
				l.setFont(new Font("Arial",30));
				l.setTextFill(Color.DARKGREEN);
				l.setPadding(new Insets(30,30,10,30));
				temp.getChildren().add(l);
			}
			if(i>0 && firstlettre != letterbis) {
				Label l = new Label(letterbis);
				l.setFont(new Font("Arial",30));
				l.setTextFill(Color.DARKGREEN);
				l.setPadding(new Insets(30,30,10,30));
				temp.getChildren().add(l);
			}
			else {
				System.out.println("hello");
			}*/
			
			String newLetter = String.valueOf(tab.get(i).charAt(0));
			newLetter = newLetter.toUpperCase();
			if(!newLetter.contains(lettreRef)) {
				Label l = new Label(newLetter);
				l.setFont(new Font("Arial",30));
				l.setTextFill(Color.DARKGREEN);
				l.setPadding(new Insets(30,30,10,30));
				temp.getChildren().add(l);
				lettreRef=newLetter;
			}
			else if(newLetter.contains(lettreRef)) {
				System.out.println("égal");
				System.out.println(lettreRef);
			}
			
			PlantButtonHandler pbh = new PlantButtonHandler(tab.get(i),myHerbier, menu, scene, contenu, box1);// controlleur de chaque bouton
			Button l = new Button(tab.get(i));
			l.setOnAction(pbh);
			temp.getChildren().add(l);
		}
		contenu.getChildren().add(scrollPane);// on ajoute le tout à la fenêtre
		
	}
}
