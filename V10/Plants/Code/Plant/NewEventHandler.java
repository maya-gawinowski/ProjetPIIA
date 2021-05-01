package Plant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NewEventHandler implements EventHandler<ActionEvent> {

	private Herbier herbier;


	public NewEventHandler(Herbier herbier) {
		this.herbier = herbier;
	}

	@Override
	public void handle(ActionEvent arg0) {
		herbier.affichePopupAddEvent();
	}
	
}