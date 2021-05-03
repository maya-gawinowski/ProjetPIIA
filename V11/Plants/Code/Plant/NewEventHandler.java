package Plant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class NewEventHandler implements EventHandler<ActionEvent> {

	private Herbier herbier;


	public NewEventHandler(Herbier herbier) {
		this.herbier = herbier;
	}

	
	@Override
	public void handle(ActionEvent arg0) {
		affichePopupAddEvent(this.herbier);
	}
	
	
	private TextField tfDateDebut,tfDateFin,tfHeureDebut, tfHeureFin, tfTitre, tfLocation;
	Popup p = new Popup();
	public void affichePopupAddEvent(Herbier herbier) {
		if (!p.isShowing()) {
            p.show(herbier.getMyStage());
		}
		
		VBox a = new VBox();
		a.setStyle( 
	    "-fx-border-style: solid inside;" + 
	    "-fx-border-width: 1;" +
	    "-fx-border-color: green;");
		a.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		a.setMinSize(200, 300);
	    
		
		Label ltitreBoite = new Label("Ajouter un nouvel evenement");
		VBox titreBoite = new VBox();
		titreBoite.setStyle( 
			    "-fx-border-style: solid inside;" + 
			    "-fx-border-width: 1;" +
			    "-fx-border-color: gray;");
		titreBoite.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		titreBoite.setAlignment(Pos.CENTER);
		titreBoite.setMinSize(a.getWidth(), 40);
		titreBoite.getChildren().add(ltitreBoite);
		
		
		VBox event = new VBox();
		event.setPadding(new Insets(20,20,20,20));
		
		Label titreEvent = new Label("titre de l'evenement : ");
		Label ldateDebut = new Label("date de debut : ");
		Label ldateFin = new Label("date de fin : ");
		Label lheureDebut = new Label("heure de debut : ");
		Label lheureFin = new Label("heure de fin : ");
		Label llocation = new Label("localisation : ");
		
		
		tfTitre = new TextField();
		event.getChildren().add(titreEvent);
		event.getChildren().add(tfTitre);
		
		tfDateDebut = new TextField();
		event.getChildren().add(ldateDebut);
		event.getChildren().add(tfDateDebut);
		
		tfDateFin = new TextField();
		event.getChildren().add(ldateFin);
		event.getChildren().add(tfDateFin);
		
		tfHeureDebut = new TextField();
		event.getChildren().add(lheureDebut);
		event.getChildren().add(tfHeureDebut);

		tfHeureFin = new TextField();
		event.getChildren().add(lheureFin);
		event.getChildren().add(tfHeureFin);

		tfLocation = new TextField();
		event.getChildren().add(llocation);
		event.getChildren().add(tfLocation);
		
		
		
		
		Button valid = new Button("Enregistrer");
		ValidateHandlerEvent v = new ValidateHandlerEvent(this.herbier, p, tfTitre, tfDateDebut, tfDateFin, tfHeureDebut, tfHeureFin, tfLocation);
		valid.setOnAction(v);
			
		Button closePopup = new Button("fermer");
		closePopup.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				p.hide();				
			}
		});
		
		VBox boutons = new VBox();
		boutons.setPadding(new Insets(20,20,20,20));
		boutons.setAlignment(Pos.CENTER);
		boutons.getChildren().add(valid);
		boutons.getChildren().add(closePopup);
		
		
		a.getChildren().add(titreBoite);
		a.getChildren().add(event);
		a.getChildren().add(boutons);
		
		p.getContent().add(a);
		p.centerOnScreen();
		
	}
}