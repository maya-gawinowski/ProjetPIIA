package V1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;

public class SceneGeneral extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Plant's");
		
		//titre de l'app et sa HBox
		Label title = new Label("PLANT'S");
		title.setFont(new Font("Arial",40));
		title.setTextFill(Color.BLACK);

		HBox topScreenLeft = new HBox();
		topScreenLeft.setPrefSize(1800, 50);
		topScreenLeft.getChildren().add(title);		
		Insets insetTopScreenLeft = new Insets(10, 10, 20, 10);
		topScreenLeft.setPadding(insetTopScreenLeft);
		topScreenLeft.setAlignment(Pos.BOTTOM_LEFT);

		//bouton accueil et sa HBox
		Button accueilButton = new Button("Accueil");
		
		HBox topScreenRight = new HBox();
		topScreenRight.setPrefSize(1800, 100);
		topScreenRight.getChildren().add(accueilButton);		
		Insets insetTopScreenRight = new Insets(10, 10, 10, 10);
		topScreenRight.setPadding(insetTopScreenRight);
		topScreenRight.setAlignment(Pos.CENTER_RIGHT);	

		
		
		//items du menu de gauche
		Label labelAgenda = new Label("Agenda");
		labelAgenda.setFont(new Font("Arial",20));
		Button consAgenda = new Button("Consulter"); 
		Button addEvent = new Button("Ajouter Évènements"); 
		Label labelPlantes = new Label("Herbier");
		labelPlantes.setFont(new Font("Arial",20));
		Button consHerbier = new Button("Consulter"); 
		Button addPlante = new Button("Ajouter Plante"); 
		Insets inset = new Insets(0, 0, 30, 0);
		labelAgenda.setPadding(inset);
		labelPlantes.setPadding(inset);


		
		VBox agendaMenu = new VBox();
		agendaMenu.getChildren().add(labelAgenda);
		agendaMenu.getChildren().add(consAgenda);
		agendaMenu.getChildren().add(addEvent);
		agendaMenu.setPrefSize(300, 300);		
		agendaMenu.setAlignment(Pos.TOP_CENTER);

		VBox herbierMenu = new VBox();
		herbierMenu.getChildren().add(labelPlantes);
		herbierMenu.getChildren().add(consHerbier);
		herbierMenu.getChildren().add(addPlante);
		herbierMenu.setPrefSize(300, 450);		
		herbierMenu.setAlignment(Pos.TOP_CENTER);
		
		
		//HBox contenant le menu de gauche
		VBox menu = new VBox();
		menu.getChildren().add(agendaMenu);	
		menu.getChildren().add(herbierMenu);	
		Insets menuInset = new Insets(20, 0, 20, 0);
		menu.setPadding(menuInset);	
		menu.setMinSize(300, 900);
		menu.setAlignment(Pos.CENTER);
		menu.setBackground(new Background(new BackgroundFill(Paint.valueOf("LIGHTGRAY"), CornerRadii.EMPTY, Insets.EMPTY)));

		
		//VBox contenant la partie changeante du site
		VBox main = new VBox();
		main.setPrefSize(1600, 900);		
		main.setAlignment(Pos.TOP_LEFT);
		Insets mainInset = new Insets(10, 10, 10, 10);
		menu.setPadding(mainInset);		

		

		//HBox contenant l'entete du site
		HBox topScreen = new HBox();
		topScreen.setPrefSize(1800, 100);
		topScreen.getChildren().add(topScreenLeft);
		topScreen.getChildren().add(topScreenRight);
		topScreen.setBackground(new Background(new BackgroundFill(Paint.valueOf("PALEGREEN"), CornerRadii.EMPTY, Insets.EMPTY)));
				
		//HBox deuxieme partie (partie centrale/basse) de l'écran
		HBox sndBox = new HBox();
		sndBox.setPrefSize(1800, 900);
		sndBox.getChildren().add(menu);
		sndBox.getChildren().add(main);
		sndBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), CornerRadii.EMPTY, Insets.EMPTY)));

		//fenetre integrale
		VBox box = new VBox();
		box.setPrefSize(1800, 1000);
		box.getChildren().add(topScreen);
		box.getChildren().add(sndBox);

		
		
		
		Scene scene = new Scene(box, Color.LIGHTGREEN);
		primaryStage.setScene(scene);
		
		
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
