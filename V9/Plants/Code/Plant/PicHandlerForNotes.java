package Plant;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage; 

public class PicHandlerForNotes implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	String filePath = file.getAbsolutePath();
        	String fileName = file.getName();
        	System.out.println(fileName);	
        	setPicName(fileName);
        	myHerbier.getSentence(pop,namePlant, bName);
        }
	 }
	
	private String name;
	public void setPicName(String n) {
		name=n;
	}
	
	public String getPicName() {
		return name;
	}
	
	private FileChooser fileChooser;
	private Stage stage;
	private AjoutPlanteHandler ajout;
	private Herbier myHerbier;
	private String namePlant, bName;
	private Popup pop;
	public PicHandlerForNotes(FileChooser f, Stage s, Herbier h, Popup p, String np, String n) {
		fileChooser = f;
		stage = s;
		myHerbier = h;		
		namePlant = np;
		bName = n;
		pop = p;
	}
	
	
}
