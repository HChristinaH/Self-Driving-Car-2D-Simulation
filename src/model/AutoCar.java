package model;

import view.SimulationPanel;

// this class creates a car with methods for self-driving
public class AutoCar extends Car {

	// fields
	public Line[] rays = new Line[3];
	private final int rayLength = 200;

	// constructor
	public AutoCar(double speed, double angle, Point coordinate) {
		
		super(speed, angle, coordinate);

		updateRays();

	}

	// this method decides which angle to turn the car
	public void steer() {

		// assume that rays do not initially intersect
		double upperLeftDistance = 201;
		double upperRightDistance = 201;
		double frontDistance = 201;

		// check if rays intersect any lines
		for (int x = 0; x < rays.length; x++) {

			for (Line roadLine : SimulationPanel.lineList) {

				// for multiple road lines, intersection is the shortest distance
				Point intersection = rays[x].getIntersection(roadLine);
				if (intersection != null) {

					// front ray
					if (x == 0) {

						double distance = getFront().distanceTo(intersection);
						if (distance < frontDistance) {
							frontDistance = distance;
						}

					}

					// upper left ray
					else if (x == 1) {

						double distance = getFront().distanceTo(intersection);

						if (distance < upperLeftDistance) {
							upperLeftDistance = distance;
						}

					}

					// upper right ray
					else if (x == 2) {

						double distance = getFront().distanceTo(intersection);
						if (distance < upperRightDistance) {
							upperRightDistance = distance;
						}

					}

				}
			}

		}

		// balance car in the middle of the road
		if (upperLeftDistance > upperRightDistance && Math.abs(upperLeftDistance - upperRightDistance) >= 1) {

			angle -= 0.02 + Math.abs(upperLeftDistance - upperRightDistance) / rayLength / Math.PI / 2;
		}

		else if (upperLeftDistance < upperRightDistance && Math.abs(upperLeftDistance - upperRightDistance) >= 1) {

			angle += 0.02 + Math.abs(upperLeftDistance - upperRightDistance) / rayLength / Math.PI / 2;

		}

	}

	// this method updates the position of rays based on the car's position
	public void updateRays() {

		Point front = getFront();

		angle = simplifyAngle(angle);

		rays[0] = new Line(front, new Point((int) (front.getX() + rayLength * Math.sin(angle)),
				(int) (front.getY() - rayLength * Math.cos(angle))));
		rays[1] = new Line(front, new Point((int) (front.getX() + rayLength * Math.sin(angle - Math.PI / 2.1)),
				(int) (front.getY() - rayLength * Math.cos(angle - Math.PI / 2.1))));
		rays[2] = new Line(front, new Point((int) (front.getX() + rayLength * Math.sin(angle + Math.PI / 2.1)),
				(int) (front.getY() - rayLength * Math.cos(angle + Math.PI / 2.1))));


	}

}
