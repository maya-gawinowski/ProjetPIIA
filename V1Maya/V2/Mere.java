package Plants;


import javafx.application.Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.stage.Popup;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;


public class Mere extends Application {
	private TextField textField;
	private TextField textField2;
	private ComboBox<String> comboBox;
	 
    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        
        
        VBox a = new VBox();
        Button b = new Button("pop up");
        a.getChildren().add(b);
        
        Label label = new Label("Ajouter une mesure");
        textField = new TextField("");
        Label labelvaleur = new Label("Valeur : ");
        Label labeldate = new Label("Date : ");
        textField2 = new TextField("");
        comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
        		"cm",
        		"Kg",
        		"pH"

        		);
        comboBox.setPromptText("Unité de mesure");
       
        Button bvalidate = new Button("Valider");
       
        //ValidateHandler vh = new ValidateHandler(this);
        //bvalidate.setOnAction(vh);
        
        
        Popup popup = new Popup();
       
        VBox boxpop = new VBox();
        boxpop.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1;" +
                "-fx-border-color: green;");
        boxpop.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        boxpop.setAlignment(Pos.BASELINE_CENTER);
        HBox boxvaleur = new HBox();
        //label.setStyle(" -fx-background-color: white;");
        HBox boxdate = new HBox();
        
        boxpop.getChildren().add(label);
        
        boxvaleur.getChildren().add(labelvaleur);
        boxvaleur.getChildren().add(textField);
        boxvaleur.setPadding(new Insets(10,10,10,10));
        boxdate.getChildren().add(labeldate);
        boxdate.getChildren().add(textField2);
        boxdate.setPadding(new Insets(10,10,10,10));
        boxpop.getChildren().add(boxvaleur);
        boxpop.getChildren().add(boxdate);
        boxpop.getChildren().add(comboBox);
        boxpop.getChildren().add(bvalidate);
        popup.getContent().add(boxpop);

        
        //label.setMinWidth(20);
        //label.setMinHeight(50);
        popup.setAutoHide(true);
        
        EventHandler<ActionEvent> event = 
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        if (!popup.isShowing())
                            popup.show(stage);
                    }
                };
                
        b.setOnAction(event);
                
        Scene scene  = new Scene(a,800,600);
        
       
        stage.setScene(scene);
        stage.show();
    }
    
    public void getSentence() {
    	System.out.println("getSentence");
    	String a = textField.getText(); //value 
    	String c = textField2.getText(); // date
    	String b = comboBox.getValue(); // unity
    	int number;
    	int date;
    	try {
    	    number = Integer.parseInt((String) a);
    	  } catch (NumberFormatException nfe) {
    	    number = 0; // or null if that is your preference
    	  }
    	try {
			Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(c);
			System.out .println(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			date = 0;
		}
    	System.out.println(number);
    	if(number!=0  && b!=null) {
    		String Newligne=System.getProperty("line.separator");
        	String newSentence = "mesures:"+c+";"+a+" "+b+Newligne;
        	Ecriture e = new Ecriture();
        	e.ecrire("test.txt", newSentence);
    	}
    	else {
    		System.out.println("ERREUR FORMAT");
    	}
    	
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}