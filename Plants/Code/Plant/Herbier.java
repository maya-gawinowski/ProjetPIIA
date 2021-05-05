/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

/*
 * Cette classe est la classe principale de notre code
 * Elle contient :
 * - le Main
 * - la structure de la fenetre 
 * - des fonctions d'affichage d'éléments
 */

public class Herbier extends Application {
	private VBox contenu = new VBox();
	private Scene scene;
	private VBox menu = new VBox();
	private HBox box1  = new HBox();
	
	private CalendarView agendaVue = new CalendarView();
	private Calendar agenda = new Calendar("planning de l'herbier");
    private CalendarSource agendaSource = new CalendarSource("source agenda");
	
	private ArrayList<ArrayList<String>> tableauCM = new ArrayList<ArrayList<String>>(); 
	private ArrayList<ArrayList<String>> tableauKG = new ArrayList<ArrayList<String>>(); 
	private ArrayList<ArrayList<String>> tableauPH = new ArrayList<ArrayList<String>>(); 

	private Stage myStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage=primaryStage;
		myStage.setTitle("Plant's");

		//rajout des evenements dans l'agenda
		prepAgenda();
		
		// BOITE PRINCIPALE
		VBox root = new VBox();
		root.setPrefSize(1800, 1000);
		
		// SET SCENE
		scene = new Scene(root,1800,1000,Color.LIGHTGREEN);
		
		// boite qui contient le contenu changeant à droite
		contenu = new VBox(); 
		contenu.setPrefSize(1600, 900);
		contenu.setMinSize(900, 900);			
		
		//titre et sa HBox
		Label title = new Label("Plan't");
		title.setFont(new Font("Arial",40));
		title.setTextFill(Color.BLACK);
		
		HBox topScreenLeft = new HBox();
		topScreenLeft.setPrefSize(1800, 100);
		topScreenLeft.getChildren().add(title);		
		topScreenLeft.setPadding(new Insets(0, 0, 5, 5));
		topScreenLeft.setAlignment(Pos.BOTTOM_LEFT);

		
		//bouton accueil et sa HBox
		Button accueilButton = new Button("Accueil");
		AccueilHandler accH = new AccueilHandler(this,contenu);
		accueilButton.setOnAction(accH);
		
		HBox topScreenRight = new HBox();
		topScreenRight.setPrefSize(1800, 100);
		topScreenRight.getChildren().add(accueilButton);	
		topScreenRight.setPadding(new Insets(0, 15, 0, 0));
		topScreenRight.setAlignment(Pos.CENTER_RIGHT);	
	
		// BANDEROLE EN HAUT DE LA PAGE
		box1 = new HBox();
		box1.setPrefSize(1800, 100);
		box1.setMinSize(400, 100);
		box1.getChildren().add(topScreenLeft);
		box1.getChildren().add(topScreenRight);
		box1.setPadding(new Insets(0,0,0,0));
		box1.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
		// CONTENU DU CENTRE DE LA PAGE
		HBox box2 = new HBox(); // boite qui contient l'ensemble
		box2.setPrefSize(1800, 900);
		// boite qui contient le menu sur la gauche
		menu = new VBox(); 
		
		box2.getChildren().add(menu); 
		box2.getChildren().add(contenu);
		
		// APPARENCE MENU
		menu.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		menu.setAlignment(Pos.TOP_CENTER);
		menu.setMinSize(220, 800);
		
		// REMPLIR MENU
			//AGENDA
		Label agenda = new Label("AGENDA");
		agenda.setFont(new Font("Arial",20));

		Button addEvent = new Button("Ajouter événement");
		NewEventHandler neH = new NewEventHandler(this);
		addEvent.setOnAction(neH);

		Button consAgenda = new Button("Consulter");
		AgendaHandler aH = new AgendaHandler(this, contenu);
		consAgenda.setOnAction(aH);

		VBox actionAgenda = new VBox();
		actionAgenda.setAlignment(Pos.CENTER);
		actionAgenda.setPadding(new Insets(40,0,40,0)); 
		actionAgenda.getChildren().add(agenda);
		actionAgenda.getChildren().add(consAgenda);	
		actionAgenda.getChildren().add(addEvent);
		
			//PLANTES
		Label plante = new Label("PLANTE");
		plante.setFont(new Font("Arial",20));
		
		Button consPlante = new Button("Consulter");
		HerbierHandler hh = new HerbierHandler(this,contenu, scene, menu, box1);
		consPlante.setOnAction(hh);
		
		Button ajoutPlante = new Button("Ajouter");
		AjoutPlanteHandler ap = new AjoutPlanteHandler(this,contenu,myStage);
		ajoutPlante.setOnAction(ap);
		
		VBox actionPlante = new VBox();
		actionPlante.setAlignment(Pos.CENTER);
		actionPlante.setPadding(new Insets(40,0,40,0)); 
		actionPlante.getChildren().add(plante);
		actionPlante.getChildren().add(consPlante);
		actionPlante.getChildren().add(ajoutPlante);	
		
		
		// REMPLIR LE MENU
		menu.getChildren().add(actionAgenda);
		menu.getChildren().add(actionPlante);
		menu.setPadding(new Insets(50,0,0,0)); 
		
		
		// REMPLIR ROOT
		root.getChildren().add(box1);
		root.getChildren().add(box2);
		
		AccueilHandler.afficheAccueil(this);
		
		primaryStage.setScene(scene);
		
		CloseStageHandler csh = new CloseStageHandler(this);
		primaryStage.setOnCloseRequest(csh);
		
		primaryStage.show();
		
	}
	
	/*
	 * se charge de préparer notre agenda à l'ouverture de l'application en lui 
	 * rajoutant les évènement enregistrer précédemment
	 */
	private void prepAgenda() {        
        //on ajoute à l'agenda les element precedemment enregister dans le fichier "listeEvenement.txt"
        AgendaHandler.prepListEvent("listeEvenements.txt", agenda);

        //on ajoute l'évènement au calendrier
        this.agendaSource.getCalendars().addAll(agenda);
        this.agendaVue.getCalendarSources().setAll(agendaSource);
	}
	
	/**
	 * @return the agendaSource
	 */
	public CalendarSource getAgendaSource() {
		return agendaSource;
	}


	/**
	 * @return the agendaVue
	 */
	public CalendarView getAgendaVue() {
		return agendaVue;
	}


	/**
	 * @return the agenda
	 */
	public Calendar getAgenda() {
		return agenda;
	}
	
	static int countOccurences(String str, String word)
	{
	    // split the string by spaces in a
	    String a[] = str.split(" ");
	 
	    // search for pattern in a
	    int count = 0;
	    for (int i = 0; i < a.length; i++)
	    {
	    // if match found increase count
	    if (word.equals(a[i]))
	        count++;
	    }
	    //System.out.println("COUNT");
	    //System.out.println(count);
	    return count;
	}
	
	
	
	
	/*
	 * Cette fonction permet l'affichage d'une popup pour ajouter des informations à la fiche d'une plante
	 */
	private TextField tfdate,tfdatemesure,tfvalmesure;
	private TextArea tfNotes;
	private PicHandlerForNotes ph;
	private ComboBox<String> comboBox, comboMesure;
	public void affichePopup(Popup popup, String namePlante,String bName) {
		
		// On rend la popup visible
		if (!popup.isShowing())
            popup.show(myStage);
		
		// POPUP AJOUT DE DATE
		if(bName=="Date") {
			VBox a = new VBox();
			a.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			a.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	        a.setAlignment(Pos.BASELINE_CENTER);
	        
			Label titre = new Label("Ajouter une nouvelle date");
			
			// Date 
			HBox date = new HBox();
			Label ldate = new Label("Date : ");
			tfdate = new TextField();
			date.getChildren().add(ldate);
			date.getChildren().add(tfdate);
			
			// Choix du type de date (plantation, arrosage, entretien, rempotage, recolte)
			comboBox = new ComboBox<String>();
	        comboBox.getItems().addAll("plantation","arrosage","entretien","rempotage","recolte");
	        comboBox.setPromptText("type");
	        
	        // Bouton valider
			Button bdate = new Button("Valider");
			ValidateHandler v = new ValidateHandler(this,popup,namePlante,bName);
			bdate.setOnAction(v);
			
			a.getChildren().add(titre);
			a.getChildren().add(ldate);
			a.getChildren().add(tfdate);
			a.getChildren().add(comboBox);
			a.getChildren().add(bdate);
			
			popup.getContent().add(a);
		}
		// POPUP AJOUT DE MESURES
		if(bName=="Mesure") {
			VBox a = new VBox();
			a.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			a.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	        a.setAlignment(Pos.BASELINE_CENTER);
	        
	        Label titre = new Label("Ajouter Mesure");
	        a.getChildren().add(titre);
	        
	        // Date de la mesure
	        HBox dateMesure = new HBox();
	        Label dadate = new Label("Date : ");
	        tfdatemesure = new TextField();
	        dateMesure.getChildren().add(dadate);
	        dateMesure.getChildren().add(tfdatemesure);
	        a.getChildren().add(dateMesure);
	        
	        // Valeur de la mesure
	        HBox valMesure = new HBox();
	        Label vaval = new Label("Valeur : ");
	        tfvalmesure = new TextField();
	        valMesure.getChildren().add(vaval);
	        valMesure.getChildren().add(tfvalmesure);
	        a.getChildren().add(valMesure);
	        
	        // Choix de l'unité de mesure
	        comboMesure = new ComboBox<String>();
	        comboMesure.getItems().addAll("cm","kg","pH");
	        comboMesure.setPromptText("Unité de mesure");
	        a.getChildren().add(comboMesure);
	        
	        // Bouton valider
	        Button bmesure = new Button("Valider");
	        a.getChildren().add(bmesure);
	        ValidateHandler vh = new ValidateHandler(this,popup,namePlante,bName);
	        bmesure.setOnAction(vh);
	        
	        popup.getContent().add(a);
		}
		// POPUP AJOUT DE NOTES ET OBSERVATIONS
		if(bName=="Notes") {
			VBox notes = new VBox();
			notes.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			notes.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	        
	        Label titre = new Label("Ajouter Notes et Observations");
	        notes.getChildren().add(titre);
	        
	        // Date de la note
	        HBox date = new HBox();
			Label ldate = new Label("Date : ");
			tfdate = new TextField();
			date.getChildren().add(ldate);
			date.getChildren().add(tfdate);
	        notes.getChildren().add(date);
	        
	        // Text de la note
			tfNotes = new TextArea();
			tfNotes.selectRange(6, 9);
			notes.getChildren().add(tfNotes);
			
			// Définition de l'objet permettant d'ouvrir le gestionnaire de fichier pour choisir une image
			final FileChooser fileChooser = new FileChooser();
			File defaultDirectory = new File("/Users/mayagawinowski/eclipse-workspace/Plants/Images"); // initialisation du dossier sur lequel s'ouvrira le gestionnaire de fichier
			fileChooser.setInitialDirectory(defaultDirectory);
			// Bouton pour choisir une photo
			Button buttonPic = new Button("Choisissez une photo");
			ph = new PicHandlerForNotes(fileChooser,myStage,this, popup, namePlante, bName);
			buttonPic.setOnAction(ph);
			notes.getChildren().add(buttonPic);
			
			// Bouton valider
			Button BNotes = new Button ("Valider");
			notes.getChildren().add(BNotes);
			ValidateHandler vh = new ValidateHandler(this, popup, namePlante, bName);
			BNotes.setOnAction(vh);
			
			popup.getContent().add(notes);
		}
		
	}
	
	/*
	 * Cette fonction appelé depuis ValidateHandler permet de récupérer le contenu de la popup saisi par l'utilisateur
	 * afin d'ensuite écrire une nouvelle ligne dans le fichier de la plante
	 */
	public void getSentence(Popup p,String name,String bName) {
		p.hide(); // on cache la popup
		
		String phrase = new String();
		String Newligne=System.getProperty("line.separator"); // permet d'insérer des sauts de ligne à la fin de la String à écrire 
		
		// Récupération des informations de la popup d'ajout de Dates
		if(bName=="Date") {
			// on récupère les valeurs
			String date = tfdate.getText();
			String type = comboBox.getValue();
			String newSentence = type+":"+date;
			System.out.println("GET SENTENCE");
			int inddate;
			// on vérifie que le format de la date entrée correspond au format année/mois/jour
			try {
				Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(date);
				inddate=1;
				
			} catch (ParseException e1) {
				inddate = 0;
			}
			// si le format de la date est bon et que l'utilisateur a bien choisi un type de date, alors on crée une string au format
			// typeDeDate:valeurDeLaDate
			// exemple : plantation:2021/05/01
			if(type!=null && inddate!=0 && date.length()==10) {
				if (date.length()==10) {					
					phrase = newSentence+Newligne;
		        	
				}
			}
		}
		// Récupération des informations de la popup d'ajout de Mesures
		if(bName=="Mesure") {
			// On récupère les valeurs
			String date = tfdatemesure.getText();
			String value = tfvalmesure.getText();
			String unit = comboMesure.getValue();
			int val;
			int inddate;
			// on vérifie que la valeur de la mesure entrée par l'utilisateur est bien convertible en Integer
			try {
	    	    val = Integer.parseInt((String) value);
	    	  } catch (NumberFormatException nfe) {
	    	    val = 0; 
	    	  }
			// On vérifie que la date de la mesure est au bon format
	    	try {
				Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(date);
				inddate = 1;
				
			} catch (ParseException e1) {
				inddate = 0;
			}
	    	// si le format de la date est correct, la valeur est un Int et l'unité de mesure n'est pas null, alors on crée une string au format
	    	// mesures:valeurDeLaDate;valeurDeLaMesure unitéDeMesure
	    	// Exemple : mesures:2021/05/01;14 cm
	    	if(val!=0  && inddate!=0 && unit!=null) {
	        	phrase = "mesures:"+date+";"+val+" "+unit+Newligne;
	    	}
		}
		// Récupération des informations de la popup d'ajout des mesures
		if (bName=="Notes") {
			// on récupère les valeurs
			String text = tfNotes.getText();
			String result = text.replaceAll("\n"," ");
			String date = tfdate.getText();
			String pic = ph.getPicName();

			int inddate;
			// On vérifie que la date de la note est au bon format
			try {
				Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(date);
				inddate = 1;
				
			} catch (ParseException e1) {
				inddate = 0;
			}
			// la date est au bon format, le text saisi est existant et l'utilisateur n'a pas choisi de photo, alors on crée une string au format
			// notes:date;valeurDeLaDate*com;TextSansLesRetoursALaLigne
			// Exemple : notes:date;2021/05/01*com;Nouvelles feuilles
			if(inddate!=0 && text.length()>0 && pic==null) {
				phrase = "notes:date;"+date+"*com;"+result+Newligne;
			}
			// la date est au bon format, le text saisi est existant et l'utilisateur a choisi une photo, alors on crée une string au format
			// notes:date;valeurDeLaDate*com;TextSansLesRetoursALaLigne*photo;nomDeLaPhoto
			// Exemple : notes:date;2021/05/01*com;Nouvelles feuilles*photo:plante.png
			else if(inddate!=0 && text.length()>0 && pic!=null) {
				phrase = "notes:date;"+date+"*com;"+result+"*photo;"+pic+Newligne;
			}
		}
		
		// ECRITURE DE LA STRING CREE
		Ecriture e = new Ecriture();
		try {
			e.ecrire(name+".txt", phrase);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// On met à jour l'affichage en rechargant la page pour que les nouvelles informations apparaissent directement après validation 
		PlantButtonHandler pbh = new PlantButtonHandler(name, this, menu, scene, contenu, box1);
		pbh.prepInfos();
	}
	
	public VBox getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(VBox c) {
		this.contenu = c;
	}
	
	
	

	/**
	 * @return the myStage
	 */
	public Stage getMyStage() {
		return myStage;
	}

	/*
	 * ajoute un evenement à notre calendrier
	 */
	public void ajoutEvent(Entry<String> entry) {
		agenda.addEntry(entry);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}


}
