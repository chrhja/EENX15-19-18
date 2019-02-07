package simulator;

import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class TrackSensor {
	
	private boolean high;
	private Node node;
	
	private EventHandler<BinaryEvent> risingEdge, fallingEdge;
	
	TrackSensor(double x, double y, double sensitivity) {
		node = new Rectangle(x - sensitivity / 2, y - sensitivity / 2, sensitivity, sensitivity);
	}
	
	public void probe(ArrayList<Particle> particles) {
		Shape s = (Shape) node;
		
		for (Particle p : particles) {
			if (p instanceof Track) {
				for (Node n : p.getNodes()) {
					if (Shape.intersect((Shape) n, s).getBoundsInLocal().getWidth() != -1) {
						high();
						return;
					}
				}
			}
		}
		
		low();
	}
	
	public boolean isHigh() {
		return high;
	}
	
	private void high() {
		if (!isHigh()) {
			
			high = true;
			((Shape) node).setFill(Paint.valueOf("red"));
			if (risingEdge != null) risingEdge.handle(null);
			
		}
	}
	
	public void setOnRisingEdge(EventHandler risingEdge) {
		this.risingEdge = risingEdge;
	}
	

	public void setOnFallingEdge(EventHandler fallingEdge) {
		this.fallingEdge = fallingEdge;
	}
	
	private void low() {
		if (isHigh()) {
			
			high = false;
			((Shape) node).setFill(Paint.valueOf("black"));
			if (fallingEdge != null) fallingEdge.handle(null);
			
		}
	}
	
	public Node getNode() {
		return node;
	}
	
}
