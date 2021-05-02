package Plant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class CloseStageHandler implements EventHandler<WindowEvent> {

	private Calendar calendar;
	private String fichierSauvegarde = "listeEvenements.txt";
	
	public CloseStageHandler(Herbier h) {
		if(h.getAgenda() == null) {
			System.out.println("pas de calendrier!");
		}else {
			this.calendar = h.getAgenda();
		}
	}

	@Override
	public void handle(WindowEvent arg0) {
		if(calendar != null) {
			String data = inventaireEvenements();
			try {
				EcritureEvent.ecrire(fichierSauvegarde, data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	
	private String inventaireEvenements() {
		List<Entry<?>> events = calendar.findEntries("");
		
		ArrayList<String> listEvent = new ArrayList<String>();
		
		for(Entry<?> e : events) {
			String temp = "";
			
			//on prend la date de debut de l'evenement et on l'ajoute au temporisateur
			temp = temp.concat("dateDebut:"+ e.getStartDate().toString());
			//on prend l'heure de debut de l'evenement et on l'ajoute au temporisateur
			temp = temp.concat("/heureDebut:"+ e.getStartTime().toString());
			//on prend la date de fin de l'evenement et on l'ajoute au temporisateur
			temp = temp.concat("/dateFin:"+ e.getEndDate().toString());
			//on prend l'heure de fin de l'evenement et on l'ajoute au temporisateur
			temp = temp.concat("/heureFin:"+ e.getEndTime().toString());
			//on prend le titre de l'evenement et on l'ajoute au temporisateur
			temp = temp.concat("/titre:"+ e.getTitle());
			
			//on prend l'endroit de l'evenement et on l'ajoute au temporisateur
			temp = temp.concat("/location:");
			if(e.getLocation() != null) {
				temp = temp.concat(e.getLocation());
			}
			//l'enregistrement de l'evenement est fait, reste à changer de ligne 
			temp = temp.concat("\n");
			
			if(!listEvent.contains(temp)) {
				listEvent.add(temp);
			}
			
		}
		
		
		String inventaireEvents = new String("");
		for(String event : listEvent) {
			inventaireEvents = inventaireEvents.concat(event);
		}
		
		return inventaireEvents;
	}
}
