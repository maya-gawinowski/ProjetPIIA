package Plant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class AgendaHandler implements EventHandler<ActionEvent>{

	private Herbier herbier;
	private VBox contenu;
    
	
	public AgendaHandler(Herbier H, VBox C) {
		herbier = H;
		contenu = C;
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		contenu.getChildren().removeAll(contenu.getChildren());
		contenu.setPrefSize(1000, 900);
		
		herbier.getAgendaVue().refreshData();
		
		setAgendaVisible();
	}

	
	
	
	private void setAgendaVisible() {
		this.contenu.getChildren().removeAll(contenu.getChildren());
		this.contenu.getChildren().add(herbier.getAgendaVue());
		
	}


	public static void prepListEvent(String nomFichier, Calendar agenda) {
		String filename = nomFichier;
		Lecture read = new Lecture(filename);
		ArrayList<String> tab = read.returnTab();
		
		//on parcours tous les evenement un par un
		for(int eventIdx = 0; eventIdx < tab.size(); eventIdx++) {
			String currentEventLine = tab.get(eventIdx);
			String[] caracEvent = currentEventLine.split("/", 20);
			Entry<String> entry = new Entry<String>();
			
			//pour on enregistre chaque caractéristiques de notre evenement courant
			for(int caracIdx = 0; caracIdx < caracEvent.length; caracIdx++) {
				
				if(caracEvent[caracIdx].contains("dateDebut:")) {
					
					//on lit et enregistre la date de depart
					String temp[] = caracEvent[caracIdx].split("dateDebut:",2);
//					System.out.println("Voici la date de debut de l'event : "+temp[1]);
					LocalDate dateStart = LocalDate.parse(temp[1]);   
					entry.changeStartDate(dateStart);
					
				}else if(caracEvent[caracIdx].contains("dateFin:")) {
					
					//on lit et enregistre la date de fin
					String temp[] = caracEvent[caracIdx].split("dateFin:",2);
//					System.out.println("Voici la date de fin de l'event : "+temp[1]);
					LocalDate dateEnd = LocalDate.parse(temp[1]);   
					entry.changeEndDate(dateEnd);
					
				}else if(caracEvent[caracIdx].contains("heureDebut:")){
					
					//on lit et enregistre l'heure de depart
					String temp[] = caracEvent[caracIdx].split("heureDebut:",2);
//					System.out.println("Voici l'heure de debut de l'event : "+temp[1]);
					LocalTime timeStart = LocalTime.parse(temp[1]);
			        entry.changeStartTime(timeStart);

				}else if(caracEvent[caracIdx].contains("heureFin:")){
					
					//on lit et enregistre l'heure de fin
					String temp[] = caracEvent[caracIdx].split("heureFin:",2);
//					System.out.println("Voici l'heure de fin de l'event : "+temp[1]);
					LocalTime timeEnd = LocalTime.parse(temp[1]);
			        entry.changeEndTime(timeEnd);

				}else if(caracEvent[caracIdx].contains("titre:")){
					
					//on lit et enregistre le titre de l'evenement
					String temp[] = caracEvent[caracIdx].split("titre:",2);
					entry.setTitle(temp[1]);	
					
				}else if(caracEvent[caracIdx].contains("location:")){
					
					//on lit et enregistre l'endroit de l'evenement s'il est different de "none"
					String temp[] = caracEvent[caracIdx].split("location:",2);
					entry.setLocation(temp[1]);
					
				}
			}
			agenda.addEntry(entry);
		}
	}
	
}
