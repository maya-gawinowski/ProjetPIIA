/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
		herbier.getAgendaVue().setShowAddCalendarButton(false);
		herbier.getAgendaVue().setShowPrintButton(false);
		herbier.getAgendaVue().setShowDeveloperConsole(false);	
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		contenu.getChildren().removeAll(contenu.getChildren());
		contenu.setPrefSize(1000, 900);
		
		herbier.getAgendaVue().refreshData();
		
		setAgendaVisible();
	}

	
	/**
	 * affiche l'agenda dans le panneau contenu
	 * de notre herbier
	 */
	private void setAgendaVisible() {
		this.contenu.getChildren().removeAll(contenu.getChildren());
		contenu.getChildren().add(herbier.getAgendaVue());
	}


	/**
	 * recupere les précédents évènements enregistrer dans un
	 * fichier pour les ajouter aux évènemenent de notre agenda
	 * @param nomFichier le nom du fichier où les évènement sont enregistrés
	 * @param agenda le Calendar dans lequel on souhaite ajouter nos évènement
	 */
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
					LocalDate dateStart = LocalDate.parse(temp[1]);   
					entry.changeStartDate(dateStart);
					
				}else if(caracEvent[caracIdx].contains("dateFin:")) {
					
					//on lit et enregistre la date de fin
					String temp[] = caracEvent[caracIdx].split("dateFin:",2);
					LocalDate dateEnd = LocalDate.parse(temp[1]);   
					entry.changeEndDate(dateEnd);
					
				}else if(caracEvent[caracIdx].contains("heureDebut:")){
					
					//on lit et enregistre l'heure de depart
					String temp[] = caracEvent[caracIdx].split("heureDebut:",2);
					LocalTime timeStart = LocalTime.parse(temp[1]);
			        entry.changeStartTime(timeStart);

				}else if(caracEvent[caracIdx].contains("heureFin:")){
					
					//on lit et enregistre l'heure de fin
					String temp[] = caracEvent[caracIdx].split("heureFin:",2);
					LocalTime timeEnd = LocalTime.parse(temp[1]);
			        entry.changeEndTime(timeEnd);

				}else if(caracEvent[caracIdx].contains("titre:")){
					
					//on lit et enregistre le titre de l'evenement
					String temp[] = caracEvent[caracIdx].split("titre:",2);
					entry.setTitle(temp[1]);	
					
				}else if(caracEvent[caracIdx].contains("location:")){
					
					//on lit et enregistre l'endroit de l'evenement
					String temp[] = caracEvent[caracIdx].split("location:",2);
					entry.setLocation(temp[1]);
					
				}
			}
			//on ajoute l'evenement a notre calendrier
			agenda.addEntry(entry);
		}
	}
	
	/**
	 * Recherche et retourne les evenement de la journée
	 * @param c le calendrier ciblé
	 * @return la chaine de caractère decrivant les evenements prévus pour la journée
	 */
	public static String dayPlanningToString(Calendar c){
		
		ArrayList<String> eventOfTheDay = new ArrayList<String>();
		//events  répertorie l'integralité des évènement de notre calendrier
		List<Entry<?>> events = c.findEntries("");
		
		for(Entry<?> e : events) {
			//on vérifie si l'évenement est prévue pour la journée actuelle 
			//et on l'ajoute à notre liste d'evenement de la journée si 
			//c'est le cas.
			if(e.getStartDate().equals(LocalDate.now())) {
				String titreEvent = e.getTitle();
				String heureDebut = String.valueOf(e.getStartTime().getHour());
				String minuteDebut = String.valueOf(e.getStartTime().getMinute());
				String event = heureDebut+"h"+minuteDebut+" : "+titreEvent;
				if((e.getLocation() != null) && !(e.getLocation().isBlank())){
					String lieu = e.getLocation();
					event = event.concat(" a "+lieu);
				}
				eventOfTheDay.add(event);
			}
		}
		
		//on instancie la chaine de caractere à renvoyer et on la modifie avec
		//les informations des évènements s'il en existe
		String listEventToString = "Vous n'avez rien de prévu aujourd'hui.";
		
		if(!eventOfTheDay.isEmpty()) {
			listEventToString = "";
			for(int i = 0; i < eventOfTheDay.size(); i++) {
				String currentEvent = eventOfTheDay.get(i);
				listEventToString = listEventToString.concat(currentEvent+"\n");
			}
		}
		return listEventToString;
	}
	
}

