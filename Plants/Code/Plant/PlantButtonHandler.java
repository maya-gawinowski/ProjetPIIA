/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import javafx.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * Cette classe permet l'affichage de la fiche d'une plante
 * Elle permet également de remplir la fiche d'une plante en lisant le fichier associé à la plante
 * et "parse" les informations 
 */

public class PlantButtonHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		System.out.println(nom);
		
		prepInfos();
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
	private VBox menu,contenu;
	private Scene scene;
	private HBox box1;
	public PlantButtonHandler(String n, Herbier h, VBox m, Scene s, VBox c, HBox b) {
		this.nom=n;
		this.monHerbier=h;
		this.menu = m;
		this.contenu = c;
		this.scene = s;
		this.box1 = b;
		
		datesPlantation = new ArrayList<String>();
		datesRempotage = new ArrayList<String>();
		datesArrosage = new ArrayList<String>();
		datesEntretien = new ArrayList<String>();
		datesRecolte = new ArrayList<String>();
		mesures = new ArrayList<String>();
		notes = new ArrayList<String>();
		image = new String();
	}
	
	/*
	 * Cette fonction permet de lire le fichier de la plante
	 * puis de découper les informations pour ne récupérer que les informations importante
	 */
	public void prepInfos() {
		String filename = nom+".txt";
		// Lecture du fichier
		Lecture read = new Lecture(filename);
		// On récupère le contenu du fichier sous la forme d'un tableau trié par ordre alphabétique
		ArrayList<String> tab = read.returnTab();

		// On parcours le tableau et on range chaque type d'information (nom, nom scientifique, mesures, ...) dans un tableau ou une String correspondante
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
		
		afficherFiche(nomPlante,nomScientifique,image,datesPlantation,datesRempotage,datesArrosage,datesEntretien,datesRecolte,mesures,notes);
	}

	
	/*
	 * Cette fonction remplis la VBox contenu avec toutes les informations sur une plante choisie par l'utilisateur
	 * on remplis le Nom vulgaire, le Nom scientifique, l'image, les dates sous forme de tableaux, les mesures sous forme de graphe, les notes et observations
	 */
		public void afficherFiche(String nomPlante,String nomScientifique,String image,ArrayList<String> datesPlantation,ArrayList<String> datesRempotage,ArrayList<String> datesArrosage,ArrayList<String> datesEntretien,ArrayList<String> datesRecolte,ArrayList<String> mesures,ArrayList<String> notes) {
			// Composition de l'affichage du contenu :
			/*
			 * contentBis : Une Vbox qui contient tous les éléments relatifs à la fiche d'une plante.
			 * scrollPane : Une ScrollPane dont le contenu est set sur  contentBis.
			 * contenu : une VBox qui détermine l'espace de la fenêtre ou se trouve la fiche de la plante. Elle contient scrollPane.
			 * */
			
			contenu.getChildren().removeAll(contenu.getChildren());
			
			// ces ArrayLists sont remplis plus bas et contiendront des ArrayList eux mêmes contenant le bon format de données pour ensuite remplir les graphes de mesures
			ArrayList<ArrayList<String>> tableauCM = new ArrayList<ArrayList<String>>(); 
			ArrayList<ArrayList<String>> tableauKG = new ArrayList<ArrayList<String>>(); 
			ArrayList<ArrayList<String>> tableauPH = new ArrayList<ArrayList<String>>(); 
			
			VBox contentBis = new VBox();
			
			// Une scrollPane pour pouvoir consulter l'ensemble de la page
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(contentBis);
			scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
			scrollPane.setPrefSize(1100, 750);
			
			// NOM PLANTE
			Label titre = new Label(nomPlante);
			titre.setFont(new Font("Arial",40));
			titre.setTextFill(Color.DARKGREEN);
			titre.setPadding(new Insets(30,30,10,30));
			contentBis.getChildren().add(titre); // ajout du nom de la plante à la fenêtre
		
			// NOM SCIENTIFIQUE
			Label nomsci = new Label("nom scientifique : "+nomScientifique);
			nomsci.setPadding(new Insets(0,30,10,30));
			contentBis.getChildren().add(nomsci); // ajout du nom scientifique à la fenêtre
			
			//IMAGE
			final ImageView selectedImage = new ImageView();   
			String imageName = "Images/"+image;
	        FileInputStream inputstream;
	        Image image1 = null;
			try {
				inputstream = new FileInputStream(imageName);
				image1 = new Image(inputstream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
	        VBox im = new VBox();
	        im.setPadding(new Insets(50,10,10,10));
	        selectedImage.setImage(image1);
	        im.getChildren().add(selectedImage);
	        contentBis.getChildren().add(im); // ajout de la photo de la plante à la fenêtre
	        
			//DATES
	        HBox dateHead = new HBox();
	        Button ajoutdate = new Button("+ Date");
	        Popup popup = new Popup();
	        popup.setX(300);
	        popup.setY(200);
	        popup.setAutoHide(true);
	        AJoutDateHandler ajdh = new AJoutDateHandler(monHerbier,popup,nomPlante);
	        ajoutdate.setOnAction(ajdh);
			Label lDates = new Label("Dates");
			lDates.setFont(new Font("Arial",20));
			lDates.setPadding(new Insets(10,0,10,10));
			lDates.setTextFill(Color.DARKGREEN);
			dateHead.getChildren().add(ajoutdate);
			dateHead.getChildren().add(lDates);
			contentBis.getChildren().add(dateHead);
			HBox dates = new HBox();
			dates.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			
			//PLANTATION
			VBox boxPlantation = new VBox();
			boxPlantation.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			boxPlantation.setPadding(new Insets(10,10,10,10));
			Label plantation = new Label("Plantation");
			plantation.setPadding(new Insets(5,10,5,10));
			plantation.setBackground(new Background(
					   new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			plantation.setTextFill(Color.DARKGREEN);
			boxPlantation.getChildren().add(plantation);
			for (int i =0;i<datesPlantation.size();i++) {
				//System.out.println(datesPlantation);
				Label date = new Label(datesPlantation.get(i));
				boxPlantation.getChildren().add(date);
			}
			dates.getChildren().add(boxPlantation);
			
			//ARROSAGE
			VBox boxArrosage = new VBox();
			boxArrosage.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			boxArrosage.setPadding(new Insets(10,10,10,10));
			Label arrosage = new Label("Arrosage");
			arrosage.setPadding(new Insets(5,10,5,10));
			arrosage.setBackground(new Background(
					   new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			arrosage.setTextFill(Color.DARKGREEN);
			boxArrosage.getChildren().add(arrosage);
			for (int i =0;i<datesArrosage.size();i++) {
				//System.out.println(datesArrosage);
				Label date = new Label(datesArrosage.get(i));
				boxArrosage.getChildren().add(date);
			}
			dates.getChildren().add(boxArrosage);
			
			//REMPOTAGE
			VBox boxRempotage = new VBox();
			boxRempotage.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			boxRempotage.setPadding(new Insets(10,10,10,10));
			Label rempotage = new Label("Rempotage");
			rempotage.setPadding(new Insets(5,10,5,10));
			rempotage.setBackground(new Background(
					   new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			rempotage.setTextFill(Color.DARKGREEN);
			boxRempotage.getChildren().add(rempotage);
			for (int i =0;i<datesRempotage.size();i++) {
				//System.out.println(datesRempotage);
				Label remp = new Label(datesRempotage.get(i));
				boxRempotage.getChildren().add(remp);
			}
			dates.getChildren().add(boxRempotage);
			
			//ENTRETIEN
			VBox boxEntretien = new VBox();
			boxEntretien.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			boxEntretien.setPadding(new Insets(10,10,10,10));
			Label entr = new Label("Entretien");
			entr.setPadding(new Insets(5,10,5,10));
			entr.setBackground(new Background(
					   new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			entr.setTextFill(Color.DARKGREEN);
			boxEntretien.getChildren().add(entr);
			for (int i =0;i<datesEntretien.size();i++) {
				//System.out.println(datesEntretien);
				Label entretien = new Label(datesEntretien.get(i));
				boxEntretien.getChildren().add(entretien);
			}
			dates.getChildren().add(boxEntretien);
			
			//RECOLTE
			VBox boxRecolte = new VBox();
			boxRecolte.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			boxRecolte.setPadding(new Insets(10,10,10,10));
			Label rec = new Label("Récolte");
			rec.setPadding(new Insets(5,10,5,10));
			rec.setBackground(new Background(
					   new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			rec.setTextFill(Color.DARKGREEN);
			boxRecolte.getChildren().add(rec);
			for (int i =0;i<datesRecolte.size();i++) {
				//System.out.println(datesRecolte);
				Label recolte = new Label(datesRecolte.get(i));
				boxRecolte.getChildren().add(recolte);
			}
			dates.getChildren().add(boxRecolte);
			
			contentBis.getChildren().add(dates); 
			
			//MESURES
			HBox headmesure = new HBox();
			Button bmesure = new Button("+ Mesure");
			Label lmesure = new Label("Mesures");
			headmesure.getChildren().add(bmesure);
			headmesure.getChildren().add(lmesure);
			Popup popMesure = new Popup();
			popMesure.setX(300);
	        popMesure.setY(200);
			AJoutDateHandler adh2 = new AJoutDateHandler(monHerbier,popMesure,nomPlante);
			bmesure.setOnAction(adh2);
			lmesure.setFont(new Font("Arial",20));
			lmesure.setPadding(new Insets(10,0,0,10));
			lmesure.setTextFill(Color.DARKGREEN);
			contentBis.getChildren().add(bmesure);
			contentBis.getChildren().add(lmesure);
			
			/*
			 * Dans les fichiers .txt de plantes les mesures sont sous le format :
			 * date;valeur unité
			 * on split cette string plusieurs fois pour avoir un ArrayList qui contient <date,mesure> dans l'ArrayList correspondant à l'unité de mesure associée
			 * */
			
			for (int i =0;i<mesures.size();i++) {
				// On remplis nos tableau de mesures mesuresCM mesuresKG mesuresPH
				if(mesures.get(i).contains("cm")) {
					String[] temp = mesures.get(i).split(" cm"); // on enleve cm de la string
					String [] temp2 = temp[0].split(";"); // on découpe la string pour récupérer la date et la valeur
					ArrayList<String> mesurescm = new ArrayList<String>();
					mesurescm.add(temp2[0]);
					mesurescm.add(temp2[1]);
					tableauCM.add(mesurescm);
				}
				if(mesures.get(i).contains("kg")) {
					String[] temp = mesures.get(i).split(" kg");
					String [] temp2 = temp[0].split(";");
					ArrayList<String> mesureskg = new ArrayList<String>();
					mesureskg.add(temp2[0]);
					mesureskg.add(temp2[1]);
					tableauKG.add(mesureskg);
				}
				if(mesures.get(i).contains("pH")) {
					String[] temp = mesures.get(i).split(" pH");
					String[] temp2 = temp[0].split(";");
					ArrayList<String> mesuresph = new ArrayList<String>();
					mesuresph.add(temp2[0]);
					mesuresph.add(temp2[1]);
					tableauPH.add(mesuresph);
				}
				
				

			}
			
			// On dessine les LineChart associés à nos tableaux de mesures
			// tableau mesures en CM
			if(!tableauCM.isEmpty()) {
				final CategoryAxis xAxis = new CategoryAxis();
		        final NumberAxis yAxis = new NumberAxis();
		        xAxis.setLabel("Date");  
		        yAxis.setLabel("Mesure (cm)");  
		        
		        final LineChart<String,Number> lineChart = 
		                new LineChart<String,Number>(xAxis,yAxis);
		                
		        lineChart.setTitle("Mesures de ma plante en cm");
		                                
		        XYChart.Series series = new XYChart.Series();
		        series.setName("Growth of my plant");
		        
		        for(int k=0;k<tableauCM.size();k++) {
		        	series.getData().add(new XYChart.Data(tableauCM.get(k).get(0),Integer.valueOf( tableauCM.get(k).get(1))));
		        }
		        contentBis.getChildren().add(lineChart);
		        lineChart.getData().add(series);
			}
			// tableau mesures en KG
			if(!tableauKG.isEmpty()) {
				System.out.println("HELLO KG");
				final CategoryAxis xAxis = new CategoryAxis();
		        final NumberAxis yAxis = new NumberAxis();
		        xAxis.setLabel("Date");  
		        yAxis.setLabel("Mesure (kg)");  
		        
		        final LineChart<String,Number> lineChart = 
		                new LineChart<String,Number>(xAxis,yAxis);
		                
		        lineChart.setTitle("Mesures de ma plante en Kg");
		                                
		        XYChart.Series series = new XYChart.Series();
		        series.setName("Growth of my plant");
		        
		        for(int k=0;k<tableauKG.size();k++) {
		        	series.getData().add(new XYChart.Data(tableauKG.get(k).get(0),Integer.valueOf( tableauKG.get(k).get(1))));
		        }
		        contentBis.getChildren().add(lineChart);
		        lineChart.getData().add(series);
			}
			// tableau mesures en PH
			if(!tableauPH.isEmpty()) {
				final CategoryAxis xAxis = new CategoryAxis();
		        final NumberAxis yAxis = new NumberAxis();
		        xAxis.setLabel("Date");  
		        yAxis.setLabel("Mesure (pH)");  
		        
		        final LineChart<String,Number> lineChart = 
		                new LineChart<String,Number>(xAxis,yAxis);
		                
		        lineChart.setTitle("Mesures de ma plante en pH");
		                                
		        XYChart.Series series = new XYChart.Series();
		        series.setName("Growth of my plant");
		        
		        for(int k=0;k<tableauPH.size();k++) {
		        	series.getData().add(new XYChart.Data(tableauPH.get(k).get(0),Integer.valueOf( tableauPH.get(k).get(1))));
		        }
		        contentBis.getChildren().add(lineChart);
		        lineChart.getData().add(series);
			}
			
			// NOTES & OBSERVATIONS
			/*
			 * Dans les fichiers .txt de plantes les notes et observations sont sous le format :
			 * date;valeurDate*com;textCom*photo;nomPhoto
			 * on split cette string plusieurs fois :
			 * d'abord en focntion des '*' puis si les nouvelles strings contiennent 'date;', 'note;', 'photo;'
			 * on enleve 'date;', 'note;', 'photo;' avec split pour récupérer le contenu dont on a besoin
			 * */
			
			// parse l'ArrayList de note 
			HBox boxnotes = new HBox();
			Button ajoutnotes = new Button("+ Notes");
	        Popup popNotes = new Popup();
	        popNotes.setX(300);
	        popNotes.setY(200);
	        popNotes.setAutoHide(true);
	        AJoutDateHandler adh3 = new AJoutDateHandler(monHerbier,popNotes,nomPlante);
	        ajoutnotes.setOnAction(adh3);
			Label lnotes = new Label("Notes & Observations");
			lnotes.setFont(new Font("Arial",20));
			lnotes.setPadding(new Insets(10,0,0,10));
			lnotes.setTextFill(Color.DARKGREEN);
			boxnotes.getChildren().add(ajoutnotes);
			boxnotes.getChildren().add(lnotes);
			contentBis.getChildren().add(boxnotes);
			VBox boxNotes = new VBox();
			ScrollPane sp = new ScrollPane();
			sp.setContent(boxNotes);
			sp.setPrefSize(800,400);
			for (int i =0;i<notes.size();i++) {
				String someString = notes.get(i);
				char someChar = '*';
				int count = 0;
				 
				for (int k = 0; k < someString.length(); k++) {
				    if (someString.charAt(k) == someChar) {
				        count++;
				    }
				}
				String [] temporary;
				temporary = someString.split("\\*");
				for(int h=0;h<temporary.length;h++) {
					if(temporary[h].contains("date;")) {
						String[] date = temporary[h].split("date;",2);
						Label d = new Label("date : "+date[1]);
						d.setPadding(new Insets(10,0,0,10));
						boxNotes.getChildren().add(d);
					}
					if(temporary[h].contains("com;")) {
						String[] comment = temporary[h].split("com;",2);
						Label note = new Label("commentaire : "+comment[1]);
						note.setPadding(new Insets(10,0,0,10));
						boxNotes.getChildren().add(note);
					}
					if(temporary[h].contains("photo;")) {
						String[] pic = temporary[h].split("photo;",2);
						final ImageView selectedImage2 = new ImageView();   
						String nomImage = "Images/"+pic[1];
						 FileInputStream inputstream2;
					        Image image2 = null;
							try {
								inputstream2 = new FileInputStream(nomImage);
								image2 = new Image(inputstream2);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} 
				        VBox im2 = new VBox();
				        im2.setPadding(new Insets(10,10,10,10));
				        selectedImage2.setImage(image2);
				        im2.getChildren().add(selectedImage2);
				        boxNotes.getChildren().add(im2);
					}
				}
				
				
			}
			contentBis.getChildren().add(sp);
			
			contenu.getChildren().add(scrollPane);
			
		}

	
}
