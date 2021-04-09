package TP4;


import java.time.Instant;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class Exercice3 extends Application {
	Rectangle rectangle1 = new Rectangle(10,10);

	Rectangle rectangle2 = new Rectangle(10,10);

	Rectangle rectangle3 = new Rectangle(10,10);

	Circle circle1 = new Circle(10);
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fitts test");



		circle1.setCenterX(300.0f);
		circle1.setCenterY(180.0f);
		circle1.setRadius(20.0f);

		circle1.setFill(Color.ORANGE);

		circle1.setStrokeWidth(1);
		circle1.setStroke(Color.DARKORANGE);



		//Group root = new Group(circle1);

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(10,10,10,50));
		root.setMargin(rectangle1, new Insets(1,400,1,20));
		root.setMargin(rectangle2, new Insets(1,1,1,40));
		root.setMargin(rectangle3, new Insets(1,1,1,40));
		root.setMargin(circle1, new Insets(100,1,100,20));

		root.add(rectangle1, 0, 1);
		root.add(rectangle2, 1, 0);
		root.add(rectangle3, 1, 2);
		root.add(circle1, 1, 1);



		Scene scene = new Scene(root,600,300,Color.LIGHTGREEN);

		primaryStage.setScene(scene);


		primaryStage.show();

	}

	/*public void calculFitt() {
		// Loi de Fitt entre rectangle1 et rectangle2
		Double distance = (Math.sqrt(rectangle2.getY()-rectangle1.get)+Math.sqrt());

	}*/

	public static void main(String[] args) {
		Application.launch(args);
	}

}
