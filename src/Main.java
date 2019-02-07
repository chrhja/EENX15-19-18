import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.*;

import simulator.*;

public class Main extends Application {
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Simulator sim = new Simulator();
		Scene scene = new Scene((Parent) sim.getNode());
		
		Track track = new Track(new Point2D(50, 50), new Point2D(500, 50));
		sim.add(track);
		

		track = new Track(new Point2D(490, 50), new Point2D(500, 500));
		sim.add(track);
		
		SpheriCar car = new SpheriCar(scene);
		sim.add(car);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		sim.start();
	}
	
	
	
}
