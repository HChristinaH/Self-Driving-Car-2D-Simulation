package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.CarSimulationApplication;
import controller.RoadController;
import model.AutoCar;
import model.Point;

// this panel lets the user control the simulation
@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	// buttons
	private JButton runButton = new JButton("Run");
	private JButton stopButton = new JButton("Stop");
	private JButton drawButton = new JButton("Draw Mode");
	private JButton eraseButton = new JButton("Erase Mode");
	private JButton autoButton = new JButton("Self Driving Car");
	private JButton manualButton = new JButton("Manual Car");
	private JButton restartButton = new JButton("Restart Car");
	private JButton clearRoadButton = new JButton("Clear Road");

	// instructions
	private String drawInstruction = "Draw road lines by clicking two points.";
	private String eraseInstruction = "Click to erase roads.";
	private JLabel roadInstructionLabel = new JLabel(drawInstruction);
	private String manualInstruction = "Drive car using arrow keys.";
	private String autoInstruction = "Click run and stop to control the car.";
	private JLabel driveInstructionLabel = new JLabel(manualInstruction);
	private JLabel mapInstructionLabel = new JLabel("Load a pre-made road map.");

	// modes
	public boolean autoMode = false;
	public boolean drawMode = true;
	public boolean eraseMode = false;

	// maps
	private JButton map1Button = new JButton("Square Road Map");

	// constructor
	public ControlPanel() {

		setLayout(null);
		setBackground(new Color(211, 222, 227));

		// instruction labels
		roadInstructionLabel.setBounds(1050, 150, 500, 30);
		add(roadInstructionLabel);

		driveInstructionLabel.setBounds(500, 150, 500, 30);
		add(driveInstructionLabel);

		mapInstructionLabel.setBounds(270, 250, 200, 30);
		add(mapInstructionLabel);

		// load map button
		map1Button.setBounds(50, 250, 200, 30);
		map1Button.setBackground(new Color(112, 190, 230));
		map1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RoadController.loadMap(1);

			}
		});

		add(map1Button);

		// stop button
		stopButton.setBounds(150, 50, 100, 30);
		stopButton.setBackground(Color.red);
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (autoMode) {

					CarSimulationApplication.frame.simulationPanel.timer.stop();

					CarSimulationApplication.frame.requestFocusInWindow();

				}

			}
		});

		add(stopButton);

		// run button
		runButton.setBounds(50, 50, 100, 30);
		runButton.setBackground(Color.green);
		runButton.setEnabled(false);
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (runButton.getText().equals("Run")) {

					if (autoMode && !CarSimulationApplication.frame.simulationPanel.car.collided()) {

						CarSimulationApplication.frame.simulationPanel.timer.start();
						CarSimulationApplication.frame.simulationPanel.car.setSpeed(10);

					}

				}

			}

		});

		add(runButton);

		// draw mode button
		drawButton.setBounds(1050, 50, 150, 30);
		drawButton.setBackground(Color.yellow);
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drawMode = true;
				drawButton.setBackground(Color.yellow);
				roadInstructionLabel.setText(drawInstruction);
				eraseMode = false;
				eraseButton.setBackground(Color.gray);

			}
		});

		add(drawButton);

		// erase mode button
		eraseButton.setBounds(1200, 50, 150, 30);
		eraseButton.setBackground(Color.gray);
		eraseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drawMode = false;
				drawButton.setBackground(Color.gray);
				eraseMode = true;
				eraseButton.setBackground(Color.yellow);
				roadInstructionLabel.setText(eraseInstruction);

			}
		});

		add(eraseButton);

		// manually driven car button
		manualButton.setBounds(500, 50, 150, 30);
		manualButton.setBackground(Color.yellow);
		manualButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				autoMode = false;
				runButton.setEnabled(false);
				stopButton.setEnabled(false);
				driveInstructionLabel.setText(manualInstruction);
				manualButton.setBackground(Color.yellow);
				autoButton.setBackground(Color.gray);
				CarSimulationApplication.frame.requestFocusInWindow();

			}
		});

		add(manualButton);

		// self driving car button
		autoButton.setBounds(650, 50, 150, 30);
		autoButton.setBackground(Color.gray);
		autoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				autoMode = true;
				runButton.setEnabled(true);
				stopButton.setEnabled(true);
				driveInstructionLabel.setText(autoInstruction);
				manualButton.setBackground(Color.gray);
				autoButton.setBackground(Color.yellow);

			}
		});

		add(autoButton);

		// restart car button
		restartButton.setBounds(50, 150, 100, 30);
		restartButton.setBackground(Color.orange);
		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset car's position
				CarSimulationApplication.frame.simulationPanel.car.setCoordinate(new Point(100, 100));
				CarSimulationApplication.frame.simulationPanel.car.setAngle(Math.PI / 2);

				if (autoMode) {
					((AutoCar) CarSimulationApplication.frame.simulationPanel.car).updateRays();
				}

				CarSimulationApplication.frame.simulationPanel.updateCar();
				CarSimulationApplication.frame.simulationPanel.repaint();

				// if manual car, enable keys
				if (!autoMode) {
					CarSimulationApplication.frame.requestFocusInWindow();
				}

				// if self driving, stop car
				else {
					CarSimulationApplication.frame.simulationPanel.timer.stop();
				}

			}
		});

		add(restartButton);

		// clear road button
		clearRoadButton.setBounds(150, 150, 100, 30);
		clearRoadButton.setBackground(Color.pink);
		clearRoadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RoadController.clearRoads();

			}
		});

		add(clearRoadButton);
	}

}
