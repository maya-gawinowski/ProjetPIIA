package Plant2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class SavePlanteHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		System.out.println(nom.getText());
		System.out.println(nomsci.getText());
		String filename = nom.getText()+".txt";
		String thenomsci = "2:"+nomsci.getText();
		String thenom = nom.getText();
		saveInfos(filename, thenom, thenomsci);
	 }
	
	private TextField nom,nomsci;
	public SavePlanteHandler(TextField n, TextField ns) {
		nom = n;
		nomsci = ns;
	}
	
	public void saveInfos(String f, String n, String ns) {
		Ecriture e = new Ecriture();
		System.out.println(f);
		String Newligne=System.getProperty("line.separator");
		try {
			e.ecrire(f, "1:"+n+Newligne);
			e.ecrire(f, ns+Newligne);
			e.ecrire("nom.txt", n+Newligne);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
