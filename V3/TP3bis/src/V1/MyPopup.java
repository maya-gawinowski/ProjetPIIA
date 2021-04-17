package V1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class MyPopup {

	private TextField textField,textField2;
	private ComboBox<String> comboBox;
	public MyPopup(Mere m) {
		// création popup
		Popup popup = new Popup();
		popup.setAutoHide(true);
		
		// créations éléments contenus dans la popup
		Label label = new Label("Ajouter une mesure");
		Label labeldate = new Label("Date : ");
		textField2 = new TextField("");
		Label labelvaleur = new Label("Valeur : ");
		textField = new TextField("");
		comboBox = new ComboBox<String>();
		comboBox.getItems().addAll("cm","Kg","pH");
        comboBox.setPromptText("Unité de mesure");
        Button bvalidate = new Button("Valider");
        
        VBox boxpop = new VBox();
        boxpop.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1;" +
                "-fx-border-color: green;");
        boxpop.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        boxpop.setAlignment(Pos.BASELINE_CENTER);
        HBox boxvaleur = new HBox();
        HBox boxdate = new HBox();
        
        boxpop.getChildren().add(label);
        boxdate.getChildren().add(labeldate);
        boxdate.getChildren().add(textField2);
        boxvaleur.getChildren().add(labelvaleur);
        boxvaleur.getChildren().add(textField);
        boxvaleur.setPadding(new Insets(10,10,10,10));
        boxdate.setPadding(new Insets(10,10,10,10));
        boxpop.getChildren().add(boxvaleur);
        boxpop.getChildren().add(boxdate);
        boxpop.getChildren().add(comboBox);
        boxpop.getChildren().add(bvalidate);
        popup.getContent().add(boxpop);
        
        // controlleur du bouton valider
       // ValidateHandler vh = new ValidateHandler(m);
        //bvalidate.setOnAction(vh);
        
        
	}
}
