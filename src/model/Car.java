package model;

import view.SimulationPanel;

// this method creates a car object
public class Car {

	// fields
	double speed;
	double angle;
	Point coordinate; // top left corner
	Point front; // midpoint of front line of car
	public final int WIDTH = 100;
	public final int LENGTH = 100;

	// constructor
	public Car(double speed, double angle, Point coordinate) {
		super();
		this.speed = speed;
		this.angle = angle;
		this.coordinate = coordinate;
		front = getFront();
	}

	// getters and setters
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	// this method returns the midpoint of the front side of the car
	public Point getFront() {

		return Point.rotate(simplifyAngle(Math.PI / 2 - angle), LENGTH / 2,
				new Point(getCoordinate().getX() + WIDTH / 2, getCoordinate().getY() + LENGTH / 2));

	}

	// this method updates the car's coordinate after one second
	public void move() {

		coordinate.setX((int) (coordinate.getX() + speed * Math.sin(angle)));
		coordinate.setY((int) (coordinate.getY() - speed * Math.cos(angle)));

	}

	// this method checks if the car has collided with a road or border
	public boolean collided() {

		// get the centre of the car
		Point centre = new Point(getCoordinate().getX() + WIDTH / 2, getCoordinate().getY() + LENGTH / 2);

		// get each corner of the car
		Point[] corner = new Point[4];
		for (int x = 0; x < corner.length; x++) {

			double angle = simplifyAngle(Math.PI / 4 * (x + 1) - this.angle);

			corner[x] = Point.rotate(angle, LENGTH / 2, centre);

		}

		// get each side line
		Line sides[] = new Line[4];
		for (int x = 0; x < sides.length; x++) {

			if (x < 3) {
				sides[x] = new Line(corner[x], corner[x + 1]);
			} else {
				sides[x] = new Line(corner[x], corner[0]);
			}

		}

		// check if each side intersects a road line
		boolean intersects = false;

		for (Line sideLine : sides) {

			if (!intersects) {

				// road
				for (Line roadLine : SimulationPanel.lineList) {

					if (!(sideLine.getIntersection(roadLine) == null)) {
						intersects = true;
						break;
					}
				}
			}

			else {

				break;
			}

		}

		return intersects;
	}

	// this method converts the car's angle to within 0 - 2pi
	public double simplifyAngle(double angle) {

		if (angle < 0) {
			return angle + 2 * Math.PI;
		}

		else if (angle > 2 * Math.PI) {
			return angle - 2 * Math.PI;
		}

		else {
			return angle;
		}

	}

}
