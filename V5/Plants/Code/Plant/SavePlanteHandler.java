package Plant;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class SavePlanteHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		System.out.println(nom.getText());
		System.out.println(nomsci.getText());
	 }
	
	private TextField nom,nomsci;
	public SavePlanteHandler(TextField n, TextField ns) {
		nom = n;
		nomsci = ns;
	}
}