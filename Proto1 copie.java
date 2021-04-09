package V1;


import java.time.Instant;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
public class Proto1 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fitts test");

		// BOITE PRINCIPALE
		VBox root = new VBox();

		// BANDEROLE EN HAUT DE LA PAGE
		Label titre = new Label("Plan't");
		titre.setFont(new Font("Arial",40));
		titre.setTextFill(Color.BLACK);

		HBox box1 = new HBox();
		box1.getChildren().add(titre);
		box1.setBackground(new Background(new BackgroundFill(Color.web("#C1FFAA"), CornerRadii.EMPTY, Insets.EMPTY)));
		box1.setPadding(new Insets(30,30,30,30));

		// CONTENU DU CENTRE DE LA PAGE
		HBox box2 = new HBox(); // boite qui contient l'ensemble
		VBox menu = new VBox(); // boite qui contient le menu sur la gauche
		VBox contenu = new VBox(); // boite qui contient le contenu changeant à droite
		box2.getChildren().add(menu);
		box2.getChildren().add(contenu);


		// APPARENCE MENU
		menu.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		menu.setAlignment(Pos.BASELINE_CENTER);

		// REMPLIR MENU
			//AGENDA
		Label agenda = new Label("AGENDA");
		agenda.setFont(new Font("Arial",20));
		agenda.setPadding(new Insets(20,20,20,20));

		Button consagenda = new Button("Consulter");
		Button addevent = new Button("Ajouter événement");

		consagenda.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				contenu.getChildren().removeAll(contenu.getChildren());
				System.out.println("consagenda");
				Label test = new Label("consulter agenda");
				VBox boxou = new VBox();
				boxou.getChildren().add(test);
				contenu.getChildren().add(boxou);
			}
		});

		addevent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				contenu.getChildren().removeAll(contenu.getChildren());
				System.out.println("addevent");
				Label test = new Label("Ajouter évènement");
				VBox boxou = new VBox();
				boxou.getChildren().add(test);
				contenu.getChildren().add(boxou);
			}
		});

			//PLANTES
		Label plante = new Label("PLANTE");
		plante.setFont(new Font("Arial",20));
		plante.setPadding(new Insets(20,20,20,20));
		Button consplante = new Button("Consulter");
		Button ajoutplante = new Button("Ajouter");

		consplante.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				contenu.getChildren().removeAll(contenu.getChildren());
				System.out.println("addevent");
				Label test = new Label("Consulter les plantes");
				VBox boxou = new VBox();
				boxou.getChildren().add(test);
				contenu.getChildren().add(boxou);
			}
		});

		ajoutplante.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				contenu.getChildren().removeAll(contenu.getChildren());
				System.out.println("addevent");
				Label test = new Label("Ajouter plante");
				VBox boxou = new VBox();
				boxou.getChildren().add(test);
				contenu.getChildren().add(boxou);
			}
		});

		// REMPLIR LE MENU
		menu.getChildren().add(agenda);
		menu.getChildren().add(consagenda);
		menu.getChildren().add(addevent);
		menu.getChildren().add(plante);
		menu.getChildren().add(consplante);
		menu.getChildren().add(ajoutplante);

		menu.setPadding(new Insets(25,30,400,30));


		// REMPLIR ROOT
		root.getChildren().add(box1);
		root.getChildren().add(box2);



		// SET SCENE
		Scene scene = new Scene(root,900,600,Color.LIGHTGREEN);

		primaryStage.setScene(scene);


		primaryStage.show();

	}


	public static void main(String[] args) {
		Application.launch(args);
	}

}
