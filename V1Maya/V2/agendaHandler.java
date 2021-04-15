package Plants;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class agendaHandler implements EventHandler<ActionEvent>{

	Herbier herbier;
	VBox contenu;
    
	
	public agendaHandler(Herbier H) {
		herbier = H;
		contenu = H.getContenu();
	}
	

	@Override
	public void handle(ActionEvent arg0) {
		contenu.getChildren().removeAll(contenu.getChildren());
		contenu.setPrefSize(1000, 900);
		contenu.getChildren().add(herbier.agenda);
		herbier.setContenu(contenu);
	}

}
