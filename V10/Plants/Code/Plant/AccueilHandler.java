package Plant;

import java.time.LocalDate;
import java.time.Month;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AccueilHandler implements EventHandler<ActionEvent>{

	Herbier herbier;
	VBox contenu;

	HBox main = new HBox();
	
	VBox aboutDay = new VBox();
	VBox dayPlanning = new VBox();
	VBox SaisonAdvice = new VBox();
	
	VBox others = new VBox();
	
	
	
	
	public AccueilHandler(Herbier H, VBox c) {
		herbier = H;
		contenu = c;
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		afficheAccueil(this.herbier);
		
	}
	
	
	
	
	public static void afficheAccueil(Herbier herbier) {
		
		VBox contenu = herbier.getContenu();
		contenu.getChildren().removeAll(contenu.getChildren());

		HBox main = new HBox();
		
		VBox aboutDay = new VBox();
		VBox dayPlanning = new VBox();
		VBox SaisonAdvice = new VBox();
		
		VBox others = new VBox();
		
		//PARTIE GAUCHE
			//boite en haut à gauche
		
		herbier.getAgendaVue().refreshData();
		
		Label progDay = new Label("Programme du jour :");
		progDay.setFont(new Font("Arial",25));
		progDay.setTextFill(Color.BLACK);
		dayPlanning.getChildren().add(progDay);
//		dayPlanning.getChildren().add(herbier.getAgenda().);
		
		
		dayPlanning.setPadding(new Insets(10, 10, 10, 10));
		dayPlanning.setAlignment(Pos.CENTER);
		dayPlanning.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
			
			//boite en bas à gauche
		Label advice = new Label("Conseil du jour :");
		advice.setPadding(new Insets(0,10,10,0));
		advice.setFont(new Font("Arial",25));
		advice.setTextFill(Color.BLACK);
		SaisonAdvice.getChildren().add(advice);

		
		Label lSaisonAdviceLabel = new Label(infoSaison());
		SaisonAdvice.setPadding(new Insets(10, 10, 10, 10));
		SaisonAdvice.setPrefSize(contenu.getWidth()/2, contenu.getHeight()/2);
		SaisonAdvice.setAlignment(Pos.TOP_CENTER);
		SaisonAdvice.getChildren().add(lSaisonAdviceLabel);
		
			//boite de droite
		aboutDay.setPadding(new Insets(20, 20, 20, 20));
		aboutDay.setPrefSize(800, 900);
		aboutDay.setAlignment(Pos.CENTER);
		aboutDay.getChildren().add(dayPlanning);
		aboutDay.getChildren().add(SaisonAdvice);

		//PARTIE DROITE
		Label info = new Label("Informations complémentaire :");
		info.setFont(new Font("Arial",25));
		info.setTextFill(Color.BLACK);
		others.getChildren().add(info);
		
		others.setPadding(new Insets(20, 20, 20, 20));
		others.setPrefSize(800, 900);
		others.setAlignment(Pos.CENTER);
		others.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		
		main.getChildren().add(aboutDay);
		main.getChildren().add(others);
		
		contenu.getChildren().add(main);
	}
	
	
	private static Saison getSaison(Month m) {
		switch (m) {
		case JANUARY:
			return Saison.HIVER;
		case FEBRUARY:
			return Saison.HIVER;
		case MARCH:
			return Saison.HIVER;
		case APRIL:
			return Saison.PRINTEMPS;
		case MAY:
			return Saison.PRINTEMPS;
		case JUNE:
			return Saison.PRINTEMPS;
		case JULY:
			return Saison.ETE;
		case AUGUST:
			return Saison.ETE;
		case SEPTEMBER:
			return Saison.ETE;
		case OCTOBER:
			return Saison.AUTOMNE;
		case NOVEMBER:
			return Saison.AUTOMNE;
		case DECEMBER:
			return Saison.AUTOMNE;
		default:
			return null;
		}
	}
	
	
	private static String infoSaison() {
		Month month = LocalDate.now().getMonth();
		Saison saison = getSaison(month);
		InfoPlantationsSaison prep = new InfoPlantationsSaison();
		String info = "Nous sommes en "+saison.toString()+" voici les plantations associées à la saison : \n";
		info = info.concat(prep.infoSurSaison(saison));
		return info;
	}
}

