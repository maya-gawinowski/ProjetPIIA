package Plant;

import com.calendarfx.view.CalendarView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class AccueilHandler implements EventHandler<ActionEvent>{

	Herbier herbier;
	VBox contenu;
	
	public AccueilHandler(Herbier H, VBox c) {
		herbier = H;
		contenu = c;
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		contenu.getChildren().removeAll(contenu.getChildren());
		
		VBox dayAdvice = new VBox();
		Insets dayAdviceInset = new Insets(10, 10, 10, 10);
		dayAdvice.setPadding(dayAdviceInset);
		dayAdvice.setPrefSize(contenu.getWidth()/2, contenu.getHeight()/2);
		dayAdvice.setAlignment(Pos.CENTER);
		dayAdvice.setBackground(new Background(new BackgroundFill(Paint.valueOf("blue"), CornerRadii.EMPTY, Insets.EMPTY)));

		VBox dayPlanning = new VBox();
		CalendarView myAgenda = herbier.getAgenda();
		dayPlanning.getChildren().add(myAgenda.getDayPage().getAgendaView());
		Insets dayPlanningInset = new Insets(10, 10, 10, 10);
		dayPlanning.setPadding(dayPlanningInset);
		dayPlanning.setAlignment(Pos.CENTER);
		dayPlanning.setBackground(new Background(new BackgroundFill(Paint.valueOf("red"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		contenu.getChildren().add(dayAdvice);
		contenu.getChildren().add(dayPlanning);
		herbier.setContenu(contenu);
	}
}