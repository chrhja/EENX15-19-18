package simulator;

import java.util.*;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class SpheriCar extends Particle {
	
	boolean up, down, right, left;
	ArrayList<TrackSensor> sensors = new ArrayList<>();
	
	public SpheriCar(Scene scene) {
		createShape();
		setMaxVelocity(0.5);
		
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) up = true;
			else if (e.getCode() == KeyCode.DOWN) down = true;
			else if (e.getCode() == KeyCode.RIGHT) right = true;
			else if (e.getCode() == KeyCode.LEFT) left = true;
		});
		scene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.UP) up = false;
			else if (e.getCode() == KeyCode.DOWN) down = false;
			else if (e.getCode() == KeyCode.RIGHT) right = false;
			else if (e.getCode() == KeyCode.LEFT) left = false;
		});
	}
	
	@Override
	protected void update(long elapsedTime, ArrayList<Particle> particles) {
		super.update(elapsedTime, particles);

		double acc = 0.002;
		setAccelerationX((right ? acc : 0) + (left ? -acc : 0));
		setAccelerationY((up ? -acc : 0) + (down ? acc : 0));
		
		for (TrackSensor sensor : sensors) {
			sensor.probe(particles);
		}
	}
	
	
	
	private void createShape() {
		double bodyLength = 150;
		double bodyWidth = 75;
		
		double wheelInnerRadius = 16;
		double wheelOuterRadius = 20;
		
		double wheelLengthOffset = 45;
		double wheelWidthOffset = 30;
		
		double bodyCenterX = bodyLength / 2;
		double bodyCenterY = bodyWidth / 2;
		
		/*
		 * Do not modify below this line.
		 * 
		 * Constructing the car.
		 */
		
		Shape body = new Rectangle(bodyLength, bodyWidth);
		Circle wheel = new Circle(wheelOuterRadius);
		
		for (int i = 0; i < 2; i++) {

			wheel.setTranslateX(bodyCenterX + Math.pow(-1, i) * wheelLengthOffset);
			
			for (int j = 0; j < 2; j++) {

				wheel.setRadius(wheelOuterRadius);
				wheel.setTranslateY(bodyCenterY + Math.pow(-1, j) * wheelWidthOffset);
				body = Shape.subtract(body, wheel);

				wheel.setRadius(wheelInnerRadius);
				body = Shape.union(body, wheel);
			}
		}
		
		body.setFill(new Color(0, 0.5, 0, 0.5));
		this.nodes.add(body);
		
		// Sensor(s)
		double sensitivity = 5;
		double spacing = 10;
		
		TrackSensor frontSensor = new TrackSensor(bodyLength / 2 + (spacing + sensitivity), bodyWidth / 2, sensitivity);
		sensors.add(frontSensor);
		this.nodes.add(frontSensor.getNode());

		frontSensor.setOnRisingEdge(e -> right = true);
		frontSensor.setOnFallingEdge(e -> right = false);
		

		TrackSensor backSensor = new TrackSensor(bodyLength / 2 - (spacing + sensitivity), bodyWidth / 2, sensitivity);
		sensors.add(backSensor);
		this.nodes.add(backSensor.getNode());
		
		backSensor.setOnRisingEdge(e -> left = true);
		backSensor.setOnFallingEdge(e -> left = false);
		

		TrackSensor rightSensor = new TrackSensor(bodyLength / 2, bodyWidth / 2 + (spacing + sensitivity), sensitivity);
		sensors.add(rightSensor);
		this.nodes.add(rightSensor.getNode());
		
		rightSensor.setOnRisingEdge(e -> down = true);
		rightSensor.setOnFallingEdge(e -> down = false);
		
		

		TrackSensor leftSensor = new TrackSensor(bodyLength / 2, bodyWidth / 2 - (spacing + sensitivity), sensitivity);
		sensors.add(leftSensor);
		this.nodes.add(leftSensor.getNode());
		

		leftSensor.setOnRisingEdge(e -> up = true);
		leftSensor.setOnFallingEdge(e -> up = false);
		
	}
	
}
