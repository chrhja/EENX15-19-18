package simulator;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class Simulator extends AnimationTimer {
	
	Pane root;
	
	long previousFrame = -1;
	
	ArrayList<Particle> particles = new ArrayList<>();
	
	public Simulator() {
		root = new Pane();
		root.setPrefSize(800, 500);
	}
	
	public void add(Particle p) {
		particles.add(p);
		
		for (Node n : p.getNodes()) {
			n.setCache(true);
			root.getChildren().add(n);
		}
	}
	
	
	public Node getNode() {
		return root;
	}
	
	@Override
	public void stop() {
		super.stop();
		previousFrame = -1;
	}
	
	
	@Override
	public void handle(long now) {
		long elapsedTime = (previousFrame > 0) ? now - previousFrame : 0;
		previousFrame = now;
		
		for (Particle p : particles) {
			p.update(elapsedTime, particles);
		}
	}
	
	
	
}
