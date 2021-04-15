package Plants;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import java.util.ArrayList;
import java.util.Date;

import com.calendarfx.view.CalendarView;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.*; 
import javafx.scene.paint.*; 
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;

public class Herbier extends Application {
	


	private VBox contenu;
	private Scene scene;
	private VBox menu;
	private HBox box1;
	CalendarView agenda = new CalendarView();
	
	private ArrayList<ArrayList<String>> tableauCM = new ArrayList<ArrayList<String>>(); 
	private ArrayList<ArrayList<String>> tableauKG = new ArrayList<ArrayList<String>>(); 
	private ArrayList<ArrayList<String>> tableauPH = new ArrayList<ArrayList<String>>(); 

	private Stage myStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Plant's");
		myStage = primaryStage;
		
		// BOITE PRINCIPALE
		VBox root = new VBox();
		// SET SCENE
		scene = new Scene(root,1000,800,Color.LIGHTGREEN);
		
		//titre et sa HBox
		Label title = new Label("Plan't");
		title.setFont(new Font("Arial",40));
		title.setTextFill(Color.BLACK);
		HBox topScreenLeft = new HBox();
		topScreenLeft.setPrefSize(1800, 50);
		topScreenLeft.getChildren().add(title);		
		Insets insetTopScreenLeft = new Insets(10, 10, 20, 10);
		topScreenLeft.setPadding(insetTopScreenLeft);
		topScreenLeft.setAlignment(Pos.BOTTOM_LEFT);
		
		//bouton accueil et sa HBox
		Button accueilButton = new Button("Accueil");
		accueilHandler accH = new accueilHandler(this);
		accueilButton.setOnAction((EventHandler<ActionEvent>) accH);
		HBox topScreenRight = new HBox();
		topScreenRight.setPrefSize(1800, 100);
		topScreenRight.getChildren().add(accueilButton);		
		Insets insetTopScreenRight = new Insets(10, 10, 10, 10);
		topScreenRight.setPadding(insetTopScreenRight);
		topScreenRight.setAlignment(Pos.CENTER_RIGHT);	
	
		// BANDEROLE EN HAUT DE LA PAGE
		box1 = new HBox();
		box1.setPrefSize(1800, 100);
		box1.getChildren().add(topScreenLeft);
		box1.getChildren().add(topScreenRight);
		box1.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		box1.setPadding(new Insets(30,30,30,30));
		
		// CONTENU DU CENTRE DE LA PAGE
		HBox box2 = new HBox(); // boite qui contient l'ensemble
		box2.setPrefSize(1800, 900);
		// boite qui contient le menu sur la gauche
		menu = new VBox(); 
		// boite qui contient le contenu changeant à droite
		contenu = new VBox(); 
		box2.getChildren().add(menu); 
		box2.getChildren().add(contenu);
		
		
		// APPARENCE MENU
		menu.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		menu.setAlignment(Pos.BASELINE_CENTER);
		
		// REMPLIR MENU
			//AGENDA
		Label agenda = new Label("AGENDA");
		agenda.setFont(new Font("Arial",20));
		agenda.setPadding(new Insets(20,20,20,20));
		
		Button consAgenda = new Button("Consulter");
		agendaHandler aH = new agendaHandler(this);
		consAgenda.setOnAction((EventHandler<ActionEvent>) aH);
		
		newEventHandler neH = new newEventHandler(this);
		Button addEvent = new Button("Ajouter événement");
		addEvent.setOnAction((EventHandler<ActionEvent>) neH);
		
		
			//PLANTES
		Label plante = new Label("PLANTE");
		plante.setFont(new Font("Arial",20));
		plante.setPadding(new Insets(20,20,20,20));
		Button consplante = new Button("Consulter");
		Button ajoutplante = new Button("Ajouter");
		
		HerbierHandler hh = new HerbierHandler(this);
		
		consplante.setOnAction(hh);
		
		AjoutPlanteHandler ap = new AjoutPlanteHandler(this);
		ajoutplante.setOnAction(ap);
		
		// REMPLIR LE MENU
		menu.getChildren().add(agenda);
		menu.getChildren().add(consAgenda);
		menu.getChildren().add(addEvent);
		menu.getChildren().add(plante);
		menu.getChildren().add(consplante);
		menu.getChildren().add(ajoutplante);
		
		menu.setPadding(new Insets(25,30,1000,30));
		
		
		// REMPLIR ROOT
		root.getChildren().add(box1);
		root.getChildren().add(box2);
		
		
		
		
		
		primaryStage.setScene(scene);

		
		primaryStage.show();
		
	}
	
	// cette fonction affiche la liste des plantes
	public void affichageHerbier(ArrayList<String> tab) {
		contenu.getChildren().removeAll(contenu.getChildren());
	
		// créations des widgets 
		VBox temp = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(temp);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setPrefSize((scene.getWidth()-menu.getWidth()), (scene.getHeight()-box1.getHeight()));
		
		// parcours l'ArrayList contenant tous les noms de plantes et les ajoute à la fenetre sous forme de boutons
		for(int i=0;i<tab.size();i++) {
			PlantButtonHandler pbh = new PlantButtonHandler(tab.get(i),this);// controlleur de chaque bouton
			Button l = new Button(tab.get(i));
			l.setOnAction(pbh);
			temp.getChildren().add(l);
		}
		contenu.getChildren().add(scrollPane);// on ajoute le tout à la fenêtre
		
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
	
	
	// Cette fonction remplis la VBox contenu avec toutes les informations sur une plante choisie par l'utilisateur
	// on remplis Nom, Nom scientifique, l'image, les dates sous forme de tableaux, les mesures sous forme de graphe, les notes et observations
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
		scrollPane.setPrefSize((scene.getWidth()-menu.getWidth()), (scene.getHeight()-box1.getHeight()));
		
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
        Image image1 = new Image(Herbier.class.getResourceAsStream("/imgs/"+image),250, 200,false,false);
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
        AJoutDateHandler ajdh = new AJoutDateHandler(this,popup,nomPlante);
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
		
		contentBis.getChildren().add(dates); // ajout des dates à la fenêtre
		
		//MESURES
		HBox headmesure = new HBox();
		Button bmesure = new Button("+ Mesure");
		Label lmesure = new Label("Mesures");
		headmesure.getChildren().add(bmesure);
		headmesure.getChildren().add(lmesure);
		Popup popMesure = new Popup();
		popMesure.setX(300);
        popMesure.setY(200);
		AJoutDateHandler adh2 = new AJoutDateHandler(this,popMesure,nomPlante);
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
		
		// parse the note ArrayList
		Label lnotes = new Label("Notes & Observations");
		lnotes.setFont(new Font("Arial",20));
		lnotes.setPadding(new Insets(10,0,0,10));
		lnotes.setTextFill(Color.DARKGREEN);
		contentBis.getChildren().add(lnotes);
		VBox boxNotes = new VBox();
		ScrollPane sp = new ScrollPane();
		sp.setContent(boxNotes);
		sp.setPrefSize((scene.getWidth()-menu.getWidth()), 200);
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
			        Image image2 = new Image(Herbier.class.getResourceAsStream("/imgs/"+pic[1]),150, 100,false,false);
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
	
	
	private TextField tfdate,tfdatemesure,tfvalmesure;
	private ComboBox<String> comboBox, comboMesure;
	public void affichePopup(Popup popup, String namePlante,String bName) {
		//System.out.println("COUCOU");
		if (!popup.isShowing())
            popup.show(myStage);
		
		if(bName=="Date") {
			VBox a = new VBox();
			a.setStyle( 
	                "-fx-border-style: solid inside;" + 
	                "-fx-border-width: 1;" +
	                "-fx-border-color: green;");
			a.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	        a.setAlignment(Pos.BASELINE_CENTER);
			Label titre = new Label("Ajouter une nouvelle date");
			HBox date = new HBox();
			Label ldate = new Label("Date : ");
			tfdate = new TextField();
			date.getChildren().add(ldate);
			date.getChildren().add(tfdate);
			comboBox = new ComboBox<String>();
	        comboBox.getItems().addAll("plantation","arrosage","entretien","rempotage","recolte");
	        comboBox.setPromptText("type");
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
	        HBox dateMesure = new HBox();
	        Label dadate = new Label("Date : ");
	        tfdatemesure = new TextField();
	        dateMesure.getChildren().add(dadate);
	        dateMesure.getChildren().add(tfdatemesure);
	        a.getChildren().add(dateMesure);
	        HBox valMesure = new HBox();
	        Label vaval = new Label("Valeur : ");
	        tfvalmesure = new TextField();
	        valMesure.getChildren().add(vaval);
	        valMesure.getChildren().add(tfvalmesure);
	        a.getChildren().add(valMesure);
	        comboMesure = new ComboBox<String>();
	        comboMesure.getItems().addAll("cm","kg","pH");
	        comboMesure.setPromptText("Unité de mesure");
	        a.getChildren().add(comboMesure);
	        Button bmesure = new Button("Valider");
	        a.getChildren().add(bmesure);
	        ValidateHandler vh = new ValidateHandler(this,popup,namePlante,bName);
	        bmesure.setOnAction(vh);
	        popup.getContent().add(a);
		}
		
		
	}
	
	public void getSentence(Popup p,String name,String bName) {
		p.hide();
		String phrase = new String();
		String Newligne=System.getProperty("line.separator");
		if(bName=="Date") {
			String date = tfdate.getText();
			String type = comboBox.getValue();
			String newSentence = type+":"+date;
			System.out.println("GET SENTENCE");
			int inddate;
			try {
				Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(date);
				inddate=1;
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				inddate = 0;
			}
			
			if(type!=null && inddate!=0 && date.length()==10) {
				if (date.length()==10) {					
					phrase = newSentence+Newligne;
		        	
				}
			}
		}
		if(bName=="Mesure") {
			String date = tfdatemesure.getText();
			String value = tfvalmesure.getText();
			String unit = comboMesure.getValue();
			int val;
			int inddate;
			try {
	    	    val = Integer.parseInt((String) value);
	    	  } catch (NumberFormatException nfe) {
	    	    val = 0; // or null if that is your preference
	    	  }
	    	try {
				Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(date);
				inddate = 1;
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				inddate = 0;
			}
	    	if(val!=0  && inddate!=0 && unit!=null) {
	        	phrase = "mesures:"+date+";"+val+" "+unit+Newligne;
	    	}
		}
		
		Ecriture e = new Ecriture();
		e.ecrire(name+".txt", phrase);
		
	}
	
	
	public void afficheAjoutPlante() {
		contenu.getChildren().removeAll(contenu.getChildren());
		VBox contentBis = new VBox();
		Label titre = new Label("Ajouter une plante");
		titre.setFont(new Font("Arial",40));
		titre.setTextFill(Color.DARKGREEN);
		titre.setPadding(new Insets(30,30,10,0));
		contentBis.getChildren().add(titre);
		HBox nomplante = new HBox();
		nomplante.setPadding(new Insets(30,30,10,0));
		Label lnom = new Label ("Nom vulgaire : ");
		TextField tfnom = new TextField();
		nomplante.getChildren().add(lnom);
		nomplante.getChildren().add(tfnom);
		contentBis.getChildren().add(nomplante);
		HBox nomsci = new HBox();
		nomsci.setPadding(new Insets(10,30,10,0));
		Label lnomsci = new Label("Nom scientifique : ");
		TextField tfnomsci = new TextField();
		nomsci.getChildren().add(lnomsci);
		nomsci.getChildren().add(tfnomsci);
		contentBis.getChildren().add(nomsci);
		Button valider = new Button("Enregistrer");
		savePlanteHandler sph = new savePlanteHandler(tfnom,tfnomsci);
		valider.setOnAction(sph);
		contentBis.getChildren().add(valider);
		contentBis.setPadding(new Insets(30,30,10,30));
		contenu.getChildren().add(contentBis);
	}

	
	/**
	 * @return the contenu
	 */
	public VBox getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(VBox contenu) {
		this.contenu = contenu;
	}
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}