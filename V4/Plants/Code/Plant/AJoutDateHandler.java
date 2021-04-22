package Plant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Popup;

public class AJoutDateHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		buttonname = event.getSource().toString();
		if(buttonname.contains("+ Date")) {
			buttonname = "Date";
		}
		if(buttonname.contains("+ Mesure")) {
			buttonname = "Mesure";
		}
		else {
			System.out.println("nope");
		}
		monHerbier.affichePopup(myPopup,name,buttonname);
		//System.out.println("name: "+name);
	 }
	
	private Herbier monHerbier;
	private Popup myPopup;
	private String name;
	private String buttonname;
	public AJoutDateHandler(Herbier h, Popup p,String n) {
		monHerbier=h;
		myPopup = p;
		name=n;
	}
	
	
}