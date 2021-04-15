package Plants;

import Plants.Herbier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

public class ValidateHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		System.out.println("COUCOU");
		myHerbier.getSentence(myPopup,namePlante,bName);
	 }
	
	private Herbier myHerbier;
	private Popup myPopup;
	private String namePlante;
	private String bName;
	public ValidateHandler(Herbier h,Popup p,String n,String b) {
		myHerbier = h;
		myPopup = p;
		bName = b;
		namePlante=n;
	}
}