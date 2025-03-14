package view;

import model.Point;
import model.RoadLine;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.Timer;

import application.CarSimulationApplication;
import controller.RoadController;
import model.AutoCar;
import model.Car;
import model.Line;

// this is the panel where road are drawn and the car moves
@SuppressWarnings("serial")
public class SimulationPanel extends JPanel implements MouseListener {

	// fields
	private boolean selected = false;
	private Point p1; // first point of drawn road lines
	private Point p2; // second point of drawn road lines
	public static ArrayList<Line> lineList = new ArrayList<Line>();
	Car car = new AutoCar(10, Math.PI / 2, new Point(100, 100));
	Timer timer = new Timer(10, new ActionListener() {

		// this method moves the self-driven car
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// downcast
			AutoCar selfCar = (AutoCar) car;
			selfCar.steer();

			selfCar.move();
			selfCar.updateRays();
			updateCar();
			repaint();

			if (selfCar.collided()) {

				timer.stop();
			}
			
		}
		
	});
	
	

	public JLabel carLabel = new JLabel() {

		// this method rotates the car label according to the car model's angle
		@Override
		public void paintComponent(Graphics g) {

			try {
				BufferedImage carImage = ImageIO.read(new File("car.png"));

				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D) g;

				g2.rotate(car.getAngle(), car.WIDTH/2, car.LENGTH/2);

				g2.drawImage(carImage, 0, 0, 100, 100, null, null);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	// draws all line graphics
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// draw road lines
		for (Line road : lineList) {

			g.drawLine(road.getP1().getX(), road.getP1().getY(), road.getP2().getX(), road.getP2().getY());
		}

		// draw ray lines
		g.setColor(Color.yellow);
		if (CarSimulationApplication.frame.controlPanel.autoMode) {

			for (Line line : ((AutoCar) car).rays) {

				g.drawLine(line.getP1().getX(), line.getP1().getY(), line.getP2().getX(), line.getP2().getY());
			}

		}

	}

	// constructor
	public SimulationPanel() {

		addMouseListener(this);

		setBackground(Color.white);

		setLayout(null);

		carLabel.setBounds((int) car.getCoordinate().getX(), (int) car.getCoordinate().getY(), 100, 100);

		add(carLabel);

	}

	// this method updates the location of the car
	public void updateCar() {

		carLabel.setBounds(car.getCoordinate().getX(), car.getCoordinate().getY(), 100, 100);

	}

	// this method initializes the array list of lines
	public void initializeBorder() {

		// get corners of the panel
		Point topLeft = new Point(0, 0);
		Point topRight = new Point(getWidth(), 0);
		Point bottomLeft = new Point(0, getHeight());
		Point bottomRight = new Point(getWidth(), getHeight());

		// add border lines to line list
		lineList.add(new Line(topLeft, topRight));
		lineList.add(new Line(topRight, bottomRight));
		lineList.add(new Line(bottomLeft, bottomRight));
		lineList.add(new Line(topLeft, bottomLeft));

	}

	// this method is used to draw and erase road lines from user's clicks
	@Override
	public void mouseClicked(MouseEvent e) {

		// check if user wants to draw road
		if (CarSimulationApplication.frame.controlPanel.drawMode) {

			// start of line
			if (!selected) {

				p1 = new Point(e.getX(), e.getY());
				selected = true;

			}

			// end of line
			else {

				p2 = new Point(e.getX(), e.getY());
				selected = false;

				// add line to array list
				lineList.add(new RoadLine(p1, p2));

				// update and draw road line
				repaint();
			}

		}

		// check if user wants to erase a road
		else if (CarSimulationApplication.frame.controlPanel.eraseMode) {

			// erase road lines within a 25 pixel square of clicked point
			RoadController.erase(new Point(e.getX(), e.getY()));

			// update road lines
			repaint();

		}
		
		// if user is in manual mode, bring focus back to arrow keys
		if (CarSimulationApplication.frame.controlPanel.autoMode) {
			CarSimulationApplication.frame.requestFocusInWindow();
			}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
