package V1;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Popup;

public class AjoutPlanteHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		afficheAjoutPlante();
	 }
	
	private Herbier myHerbier;
	private VBox contenu;
	public AjoutPlanteHandler(Herbier h, VBox c) {
		myHerbier = h;
		contenu=c;
		
	}
	
	public void afficheAjoutPlante() {
		contenu.getChildren().removeAll(contenu.getChildren());
		VBox contentBis = new VBox();
		Label titre = new Label("Ajouter une plante");
		titre.setFont(new Font("Arial",40));
		titre.setTextFill(Color.DARKGREEN);
		titre.setPadding(new Insets(30,30,10,0));
		contentBis.getChildren().add(titre);
		HBox nomplante = new HBox();
		nomplante.setPadding(new Insets(30,30,10,0));
		Label lnom = new Label ("Nom vulgaire : ");
		TextField tfnom = new TextField();
		nomplante.getChildren().add(lnom);
		nomplante.getChildren().add(tfnom);
		contentBis.getChildren().add(nomplante);
		HBox nomsci = new HBox();
		nomsci.setPadding(new Insets(10,30,10,0));
		Label lnomsci = new Label("Nom scientifique : ");
		TextField tfnomsci = new TextField();
		nomsci.getChildren().add(lnomsci);
		nomsci.getChildren().add(tfnomsci);
		contentBis.getChildren().add(nomsci);
		Button valider = new Button("Enregistrer");
		savePlanteHandler sph = new savePlanteHandler(tfnom,tfnomsci);
		valider.setOnAction(sph);
		contentBis.getChildren().add(valider);
		contentBis.setPadding(new Insets(30,30,10,30));
		contenu.getChildren().add(contentBis);
	}
}