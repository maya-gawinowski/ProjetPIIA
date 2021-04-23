package Plant;

import com.calendarfx.view.CalendarView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class AccueilHandler implements EventHandler<ActionEvent>{

	Herbier herbier;
	VBox contenu;

	HBox main = new HBox();
	
	VBox aboutDay = new VBox();
	VBox dayPlanning = new VBox();
	VBox seasonAdvice = new VBox();
	
	VBox others = new VBox();
	
	
	
	
	public AccueilHandler(Herbier H, VBox c) {
		herbier = H;
		contenu = c;
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		contenu.getChildren().removeAll(contenu.getChildren());
		
		//PARTIE GAUCHE
			//boite en haut à gauche
		
		
		CalendarView myAgenda = herbier.getAgenda();
		if(myAgenda.getDayPage().getAgendaView() != null) {
			Label progDay = new Label("Programme du jour :");
			progDay.setFont(new Font("Arial",25));
			progDay.setTextFill(Color.BLACK);
			dayPlanning.getChildren().add(progDay);
			dayPlanning.getChildren().add(myAgenda.getDayPage().getAgendaView());
		}else{
			Label progDayEmpty = new Label("Rien de prévu aujourd'hui!");
			progDayEmpty.setFont(new Font("Arial",25));
			progDayEmpty.setTextFill(Color.BLACK);
			dayPlanning.getChildren().add(progDayEmpty);
		}
		
		dayPlanning.setPadding(new Insets(10, 10, 10, 10));
		dayPlanning.setAlignment(Pos.CENTER);
		dayPlanning.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
			
			//boite en bas à gauche
		Label advice = new Label("Conseil du jour :");
		advice.setPadding(new Insets(0,10,10,0));
		advice.setFont(new Font("Arial",25));
		advice.setTextFill(Color.BLACK);
		seasonAdvice.getChildren().add(advice);
		
		seasonAdvice.setPadding(new Insets(10, 10, 10, 10));
		seasonAdvice.setPrefSize(contenu.getWidth()/2, contenu.getHeight()/2);
		seasonAdvice.setAlignment(Pos.TOP_CENTER);
			
			//boite de droite
		aboutDay.setPadding(new Insets(20, 20, 20, 20));
		aboutDay.setPrefSize(800, 900);
		aboutDay.setAlignment(Pos.CENTER);
		aboutDay.getChildren().add(dayPlanning);
		aboutDay.getChildren().add(seasonAdvice);

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
}