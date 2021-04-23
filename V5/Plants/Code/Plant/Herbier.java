package Plant;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.control.TextField;

public class Herbier extends Application {
	private VBox contenu = new VBox();
	private Scene scene;
	private VBox menu = new VBox();
	private HBox box1  = new HBox();
	private CalendarView agenda = new CalendarView();
	
	private ArrayList<ArrayList<String>> tableauCM = new ArrayList<ArrayList<String>>(); 
	private ArrayList<ArrayList<String>> tableauKG = new ArrayList<ArrayList<String>>(); 
	private ArrayList<ArrayList<String>> tableauPH = new ArrayList<ArrayList<String>>(); 

	private Stage myStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// BOITE PRINCIPALE
		VBox root = new VBox();
		// SET SCENE
		scene = new Scene(root,1800,900,Color.LIGHTGREEN);
		
		// boite qui contient le contenu changeant à droite
		contenu = new VBox(); 
		
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
		AccueilHandler accH = new AccueilHandler(this,contenu);
		accueilButton.setOnAction(accH);
		
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

		Button addEvent = new Button("Ajouter événement");
		NewEventHandler neH = new NewEventHandler(this);
		addEvent.setOnAction(neH);
		
		Button consAgenda = new Button("Consulter");
		AgendaHandler aH = new AgendaHandler(this);
		consAgenda.setOnAction(aH);
		
		
			//PLANTES
		Label plante = new Label("PLANTE");
		plante.setFont(new Font("Arial",20));
		plante.setPadding(new Insets(20,20,20,20));
		
		Button consplante = new Button("Consulter");
		HerbierHandler hh = new HerbierHandler(this,contenu, scene, menu, box1);
		consplante.setOnAction(hh);
		
		Button ajoutplante = new Button("Ajouter");
		AjoutPlanteHandler ap = new AjoutPlanteHandler(this,contenu);
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
	public void setAgenda(CalendarView a) {
		this.agenda=a;
	}
	
	public CalendarView getAgenda() {
		return this.agenda;
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
	
	public VBox getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(VBox c) {
		this.contenu = c;
		Label l = new Label ("YO");
		this.contenu.getChildren().add(l);
		System.out.println("HE");
	}
	
	public void emptyContenu() {
		contenu.getChildren().removeAll(contenu.getChildren());
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}