package V1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

public class AjoutPlanteHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		myHerbier.afficheAjoutPlante();
	 }
	
	private Herbier myHerbier;
	public AjoutPlanteHandler(Herbier h) {
		myHerbier = h;
		
	}
}