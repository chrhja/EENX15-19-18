package simulator;

import java.util.*;

import javafx.scene.*;
import javafx.scene.shape.*;

public abstract class Particle {
	

	private double accelerationX, accelerationY;
	private double velocityX, velocityY;
	private double maxAcceleration, maxVelocity;
	
	protected ArrayList<Node> nodes = new ArrayList<>();
	
	public Particle() {
		maxAcceleration = maxVelocity = Double.MAX_VALUE;
	}
	
	
	protected void update(long elapsedTime, ArrayList<Particle> particles) {
		velocityX = Math.max(Math.min(velocityX + accelerationX, maxVelocity), -maxVelocity);
		velocityY = Math.max(Math.min(velocityY + accelerationY, maxVelocity), -maxVelocity);
		
		for (Node n : nodes) {
			n.setTranslateX(n.getTranslateX() + elapsedTime/1000/1000 * velocityX);
			n.setTranslateY(n.getTranslateY() + elapsedTime/1000/1000 * velocityY);
		}
		
	}
	
	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}
	
	public double getAccelerationX() {
		return accelerationX;
	}
	
	public double getAccelerationY() {
		return accelerationY;
	}
	
	public void setAccelerationX(double acceleration) {
		this.accelerationX = acceleration;
	}
	
	public void setAccelerationY(double acceleration) {
		this.accelerationY = acceleration;
	}
	
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	

}
