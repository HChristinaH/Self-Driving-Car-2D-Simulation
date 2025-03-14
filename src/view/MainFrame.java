package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

// this frame holds all the GUI components and deals with key events
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener {

	// panels
	public ControlPanel controlPanel = new ControlPanel();
	public SimulationPanel simulationPanel = new SimulationPanel();

	// constructor
	public MainFrame() {

		setSize(1920, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Christina Huang - Self Driving Car Simulation");
		setFocusable(true);
		addKeyListener(this);
		setLayout(null);
		setVisible(true);

		controlPanel.setBounds(0, 0, 1920, 300);
		add(controlPanel);

		simulationPanel.setBounds(0, 300, 1920, 780);
		simulationPanel.initializeBorder();
		add(simulationPanel);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		// check if simulation is in auto mode
		if (!controlPanel.autoMode) {

			// left
			if (key == 37) {

				// turn left
				simulationPanel.car.setAngle(simulationPanel.car.getAngle() - 0.05);
				simulationPanel.repaint();
			}

			// right
			else if (key == 39) {
				simulationPanel.car.setAngle(simulationPanel.car.getAngle() + 0.05);
				simulationPanel.repaint();

			}

			// up
			if (key == 38) {

				if (!simulationPanel.car.collided())
					animateCar(1);
				
			}

			// down
			else if (key == 40) {

				if (!simulationPanel.car.collided())
					animateCar(-1);

			}

		}

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// this method moves and animates the car in specified direction (1 for forward, -1 for backward)
	public void animateCar(int direction) {

		simulationPanel.car.setSpeed(10 * direction);
		simulationPanel.car.move();
		simulationPanel.repaint();
		simulationPanel.updateCar();
	}

}
