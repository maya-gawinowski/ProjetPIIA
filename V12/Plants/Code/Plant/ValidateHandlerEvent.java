/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class ValidateHandlerEvent implements EventHandler<ActionEvent> {
	
	private Herbier herbier;
	private Popup popup;
	TextField dateDebut, dateFin, heureDebut, heureFin, location, titre = new TextField();
	
	
	public ValidateHandlerEvent(Herbier h, Popup p, TextField titre, TextField dateDebut, TextField dateFin, TextField heureDebut, TextField heureFin, TextField location) {
		this.herbier = h;
		this.popup = p;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.location = location;
	}
	



	public void handle(ActionEvent event) {
		popup.hide();
		if(!titre.getText().isEmpty() && !dateDebut.getText().isEmpty() && !dateFin.getText().isEmpty()
				&& !heureDebut.getText().isEmpty() && !heureFin.getText().isEmpty()) {
			Entry<String> entry = new Entry<String>();
			
			
			entry.setTitle(titre.getText());
	
			//enregistre la date de depart
			String temp1[] = dateDebut.getText().split("/", 3);
			LocalDate dateStart = LocalDate.of(Integer.valueOf(temp1[2]), Integer.valueOf(temp1[1]), Integer.valueOf(temp1[0]));   
			entry.changeStartDate(dateStart);
			
			//enregistre la date de fin
			String temp2[] = dateFin.getText().split("/", 3);
			LocalDate dateEnd = LocalDate.of(Integer.valueOf(temp2[2]), Integer.valueOf(temp2[1]), Integer.valueOf(temp2[0]));   
			entry.changeEndDate(dateEnd);
			
			
			//enregistre l'heure de depart
			String temp3[] = heureDebut.getText().split("h", 2);
			LocalTime timeStart = LocalTime.of(Integer.valueOf(temp3[0]), Integer.valueOf(temp3[1]));
		    entry.changeStartTime(timeStart);
			
		    //enregistre l'heure de fin
		  	String temp4[] = heureFin.getText().split("h", 2);
		  	LocalTime timeEnd = LocalTime.of(Integer.valueOf(temp4[0]), Integer.valueOf(temp4[1]));
		  	entry.changeEndTime(timeEnd);
			
			entry.setLocation(location.getText());
			
			herbier.ajoutEvent(entry);
		}
			
	 }
}