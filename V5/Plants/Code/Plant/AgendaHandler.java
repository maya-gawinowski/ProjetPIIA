package Plant;


import com.calendarfx.view.CalendarView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class AgendaHandler implements EventHandler<ActionEvent>{

	private Herbier herbier;
	private VBox contenu;
    
	
	public AgendaHandler(Herbier H) {
		herbier = H;
		contenu = new VBox();
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		herbier.emptyContenu();
		
		contenu.setPrefSize(1000, 900);
		CalendarView myAgenda = herbier.getAgenda();
		myAgenda.autosize();
		contenu.getChildren().add(myAgenda);
		herbier.setContenu(contenu);
	}

}
