/* Projet PIIA : Circé Carletti / Maya Gawinowski */

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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AccueilHandler implements EventHandler<ActionEvent>{

	Herbier herbier;
	VBox contenu;

	
	
	
	
	
	public AccueilHandler(Herbier H, VBox c) {
		herbier = H;
		contenu = c;
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		afficheAccueil(this.herbier);
	}
	
	
	
	/**
	 * Affiche la page d'accueil de notre application
	 * @param herbier l'application main 
	 */
	public static void afficheAccueil(Herbier herbier) {
		//on retire les elements de notre panneau d'affichage
		VBox contenu = herbier.getContenu();
		contenu.getChildren().removeAll(contenu.getChildren());

		
		//On configure la partie de gauche de notre panneau		
		herbier.getAgendaVue().refreshData();
			
		
		Label progDay = new Label("Programme du jour :");
		progDay.setPadding(new Insets(20,20,20,20));
		progDay.setFont(new Font("Arial",25));
		progDay.setTextFill(Color.BLACK);
			//on rajoute les rappels d'evenement de la journée 
		String eventsOfTheDay = AgendaHandler.dayPlanningToString(herbier.getAgenda());
		Text text = new Text(eventsOfTheDay);
		text.setFont(new Font("Arial", 15));
		text.setLineSpacing(10);
		text.setWrappingWidth(500);
		text.prefHeight(500);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		
		VBox dayPlanning = new VBox();
		dayPlanning.setPadding(new Insets(10, 10, 10, 10));
		dayPlanning.setAlignment(Pos.TOP_LEFT);
		dayPlanning.setPrefSize(500, 900);
		dayPlanning.getChildren().add(progDay);
		dayPlanning.getChildren().add(text);
		
		
			
		//On configure la partie de gauche de notre panneau	
		Label advice = new Label("Conseil du jour :");
		advice.setPadding(new Insets(20,20,20,20));
		advice.setFont(new Font("Arial",25));
		advice.setTextFill(Color.BLACK);

			//on récupère les informations associé à la saison actuelle dans un fichier "infoSaison"
		Text tSaisonAdvice = new Text();
		tSaisonAdvice.setFont(new Font("Arial", 15));
		tSaisonAdvice.setFill(Color.DARKGREEN);
		tSaisonAdvice.setLineSpacing(10);
		tSaisonAdvice.setWrappingWidth(700);
		tSaisonAdvice.setText(infoSaison());
		

		VBox SaisonAdvice = new VBox();
		SaisonAdvice.setPadding(new Insets(10, 10, 0, 50));
		SaisonAdvice.setMaxSize(contenu.getWidth()/2, contenu.getHeight()/2);
		SaisonAdvice.setAlignment(Pos.TOP_CENTER);
		SaisonAdvice.getChildren().add(advice);
		SaisonAdvice.getChildren().add(tSaisonAdvice);
		
		
		

		HBox main= new HBox();
		main.setPadding(new Insets(20, 0, 0, 120));
		main.setPrefSize(contenu.getWidth(), contenu.getHeight());
		main.setAlignment(Pos.TOP_LEFT);
		main.getChildren().add(dayPlanning);
		main.getChildren().add(SaisonAdvice);
		
		contenu.getChildren().add(main);
	}
	
	
	
	
	
	
	/*
	 * @return la saison en fonction du mois
	 */
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
	
	/**
	 * Configure les informations sur les saisons à afficher en fonction du mois 
	 * @return les informations sur la saison actuelle
	 */
	private static String infoSaison() {
		Month month = LocalDate.now().getMonth();
		Saison saison = getSaison(month);
		InfoPlantationsSaison prep = new InfoPlantationsSaison();
		String info = "Nous sommes au "+saison.toString()+" voici les recommandations associées à la saison : \n";
		info = info.concat(prep.infoSurSaison(saison));
		return info;
	}
}

