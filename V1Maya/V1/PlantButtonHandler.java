package V1;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlantButtonHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		System.out.println(nom);
		
		String filename = nom+".txt";
		Lecture read = new Lecture(filename);
		ArrayList<String> tab = read.returnTab();
		System.out.println(tab);
		
		
		
		for(int i=0;i<tab.size();i++) {
			String temp = tab.get(i);
			String[] s;
			// Nom de la plante
			if(temp.contains("1:")) {
				s = temp.split("1:",2);
				this.nomPlante = s[1];
			}
			// Nom scientifique
			if(temp.contains("2:")) {
				s = temp.split("2:",2);
				this.nomScientifique = s[1];
			}
			// DATES
			if(temp.contains("plantation:")) {
				s = temp.split("plantation:",2);
				datesPlantation.add(s[1]);
			}
			if(temp.contains("rempotage:")) {
				s = temp.split("rempotage:",2);
				this.datesRempotage.add(s[1]);
			}
			if(temp.contains("arrosage:")) {
				s = temp.split("arrosage:",2);
				this.datesArrosage.add(s[1]);
			}
			if(temp.contains("entretien:")) {
				s = temp.split("entretien:",2);
				this.datesEntretien.add(s[1]);
			}
			if(temp.contains("recolte:")) {
				s = temp.split("recolte:",2);
				this.datesRecolte.add(s[1]);
			}
			// MESURES
			if(temp.contains("mesures:")) {
				s = temp.split("mesures:",2);
				this.mesures.add(s[1]);
			}
			// NOTES & OBSERVATIONS
			//IMAGE
			if(temp.contains("image:")) {
				s = temp.split("image:",2);
				this.image=s[1];
			}
			if(temp.contains("notes:")) {
				s = temp.split("notes:",2);
				this.notes.add(s[1]);
			}
		}
		
		Collections.sort(datesPlantation, Collections.reverseOrder());
		Collections.sort(datesRempotage, Collections.reverseOrder());
		Collections.sort(datesArrosage, Collections.reverseOrder());
		Collections.sort(datesEntretien, Collections.reverseOrder());
		Collections.sort(datesRecolte, Collections.reverseOrder());
		Collections.sort(notes, Collections.reverseOrder());
		
		monHerbier.afficherFiche(nomPlante,nomScientifique,image,datesPlantation,datesRempotage,datesArrosage,datesEntretien,datesRecolte,mesures,notes);
	}
	private String nomPlante;
	private String nomScientifique;
	private String image;
	
	
	private ArrayList<String> datesPlantation;
	private ArrayList<String> datesRempotage;
	private ArrayList<String> datesArrosage;
	private ArrayList<String> datesEntretien;
	private ArrayList<String> datesRecolte;
	private ArrayList<String> mesures;
	private ArrayList<String> notes;
	
	private String nom;
	private Herbier monHerbier;
	public PlantButtonHandler(String n, Herbier h) {
		this.nom=n;
		this.monHerbier=h;
		
		datesPlantation = new ArrayList<String>();
		datesRempotage = new ArrayList<String>();
		datesArrosage = new ArrayList<String>();
		datesEntretien = new ArrayList<String>();
		datesRecolte = new ArrayList<String>();
		mesures = new ArrayList<String>();
		notes = new ArrayList<String>();
		image = new String();
	}

	
}