package model;

// this method defines a line on the GUI frame grid using two points
public class Line {

	// fields
	Point p1;
	Point p2;

	// constructor
	public Line(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	// getters and setters
	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	// this point returns the intersection of two line segments or null if there is
	// none
	public Point getIntersection(Line line2) {

		// x coordinate of intersection
		double pX;
		double pY;

		// represent each line segment as a linear equation in quadrant 3
		boolean verticalLine1 = false;
		boolean verticalLine2 = false;

		// line 1
		double a1X = getP1().getX();
		double a1Y = -getP1().getY();

		double b1X = getP2().getX();
		double b1Y = -getP2().getY();

		double m1 = (b1Y - a1Y) / (b1X - a1X);
		double b1 = a1Y - a1X * m1;

		// line 2
		double a2X = line2.getP1().getX();
		double a2Y = -line2.getP1().getY();

		double b2X = line2.getP2().getX();
		double b2Y = -line2.getP2().getY();

		double m2 = (b2Y - a2Y) / (b2X - a2X);
		double b2 = a2Y - a2X * m2;

		// check if slopes are parallel
		if (m1 == m2) {
			return null;
		}

		// check for vertical line
		if (a1X == b1X) {
			verticalLine1 = true;
		}

		if (a2X == b2X) {
			verticalLine2 = true;
		}

		// if both lines are vertical
		if (verticalLine1 && verticalLine2) {
			if (a1X != a2X) {
				return null;
			}

			else
				pX = a1X;
		}

		// if line 1 is vertical
		else if (verticalLine1) {
			
			pX = a1X;
		}

		// if line 2 is vertical
		else if (verticalLine2) {
			pX = a2X;
		}

		else {
			pX = (b2 - b1) / (m1 - m2);

		}

		// check if intersection's x point is on both line segments
		if (!(pX >= Math.min(a1X, b1X)) || !(pX <= Math.max(a1X, b1X)) || !(pX >= Math.min(a2X, b2X)) || !(pX <= Math.max(a2X, b2X))){
			
			return null;
		}

		// if both lines are vertical, check if there is at least one y value that
		// exists on both lines
		if (verticalLine1 && verticalLine2) {

			// line 1
			int lowerBound = (int) Math.min(a1Y, b1Y);
			int upperBound = (int)Math.max(a1Y, b1Y);
			
			// check if a point on line 2 lies on line 1
			for (int y = lowerBound; y <= upperBound; y++) {

				if ((y >= a2Y && y <= b2Y) || (y >= b2Y && y <= a2Y)) {
					pY = y;
					break;
				}

			}

		}

		// if one line is vertical, solve for the y coordinate of the non-vertical line and
		// check if the y value exists on the vertical line
		else if (verticalLine1 || verticalLine2) {

			
			if (verticalLine1) {
				pY = m2 * a2X + b2;
			}

			else {
				pY = m1 * a1X + b1;
			}

			// check that the y coordinate is on both lines
			if (!(pY >= Math.min(a1Y, b1Y)) || !(pY <= Math.max(a1Y, b1Y)) || !(pY >= Math.min(a2Y, b2Y)) || !(pY <= Math.max(a2Y, b2Y))){
				
				return null;
			}
		}

		// otherwise, get intersection point
		pY = m1 * pX + b1;

		Point intersection = new Point((int) pX, (int) -pY); // negative to convert from Q3 to GUI frame grid

		return intersection;
	}

	// string display
	@Override
	public String toString() {
		return "Line [p1=" + p1 + ", p2=" + p2 + "]";
	}

}
