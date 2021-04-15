package Plants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class DessineGraph extends Application {
	 
    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data("2020/03/02", 23));
        series.getData().add(new XYChart.Data("2020/03/03", 14));
        series.getData().add(new XYChart.Data("2020/03/04", 15));
        series.getData().add(new XYChart.Data("2020/03/05", 24));
        series.getData().add(new XYChart.Data("2020/03/06", 34));
        series.getData().add(new XYChart.Data("2020/03/07", 36));
        series.getData().add(new XYChart.Data("2020/03/08", 22));
        series.getData().add(new XYChart.Data("2020/03/09", 45));
        series.getData().add(new XYChart.Data("2020/03/10", 43));
        series.getData().add(new XYChart.Data("2020/03/11", 17));
        series.getData().add(new XYChart.Data("2020/03/12", 29));
        series.getData().add(new XYChart.Data("2020/03/13", 25));
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}