package simulator;

import javafx.geometry.*;
import javafx.scene.shape.*;

public class Track extends Particle {
	
	double thickness = 20;
	
	public Track(Point2D start, Point2D end) {
		double length = end.distance(start);
		double angle = end.subtract(start).angle(1, 0);
		
		Rectangle rect = new Rectangle(length, thickness);
		rect.setRotate(angle);
		
		Point2D midpoint = start.midpoint(end);
		rect.setTranslateX(midpoint.getX() - length / 2);
		rect.setTranslateY(midpoint.getY() - thickness / 2);
		
		
		this.nodes.add(rect);
	}

}
