package Plant;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage; 

public class PictureHandler implements
EventHandler<ActionEvent> {
	public void handle(ActionEvent event) {
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	String filePath = file.getAbsolutePath();
        	String fileName = file.getName();
        	System.out.println(fileName);	
        	setPicName(fileName);
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
	public PictureHandler(FileChooser f, Stage s) {
		fileChooser = f;
		stage = s;
		//ajout = aph;
	}
	
	
}