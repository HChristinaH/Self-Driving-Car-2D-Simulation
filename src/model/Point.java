package model;

// this class defines a point on the GUI frame grid
public class Point {

	// fields
	private int x;
	private int y;

	// constructor
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	// getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// string display
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	// returns the distance from current point to another point
	public double distanceTo(Point p2) {

		return Math.sqrt(Math.pow(x - p2.x, 2) + Math.pow(y - p2.y, 2));

	}

	// this method returns the position of a point on a circle given a specified centre point, raidus, and angle
	//(using cartesion grid coordinates and angles)
	public static Point rotate(double angle, double radius, Point centre) {

		return new Point((int) (centre.getX() + radius * Math.cos(angle)),
				centre.getY() - (int) (radius * Math.sin(angle)));
	}

}
