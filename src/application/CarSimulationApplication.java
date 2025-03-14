package application;

import view.MainFrame;

/**
 * Name: Christina Huang
 * Date: June 14, 2023
 * Course Code: ICS4U1
 * Title: Self Driving Car Simulation
 * Description: This program produces a GUI frame where user can test the success of a self driven car
 * 				on customized or pre-made road maps. The car uses rays to sense its surroundings and avoid collisions.
 * Features: The car can be manually driven by arrow keys. Road lines can be drawn, erased, and cleared all at once. 
 * 			 Instructions are given for each mode (auto/manual, draw/erase) and change depending on the selected mode.
 * 			 The square road map button loads a square-shaped road on which the self driving car can drive continously.
 * 			 When the car collides with the edge of the panel or road line in either manual or self-driving mode, it will stop.
 * 			 Run and stop buttons are disabled in manual mode to make simulation more logical and intuitive to use.
 * Major skills: File importing and reading (loading road maps), inheritance (Autocar -> Car, RoadLine -> Line), 
 * 				 polymorphic processing (while erasing road lines and keeping border lines), object oriented programming, modularized methods,
 * 				 swing graphics (drawing lines, rotating images) and timer, interfaces (KeyListener, MouseListener, ActionListener)
 * 				 arrays and array lists
 * Areas of concern: Since the car adjusts its angle based on the difference between the side ray lengths, the car does not do well in 
 * 					 situations where it approaches a wall while both rays are equal. For roads where it possible for the manual car to
 * 					 drive on, the self driving car may not always be able to drive without colliding. Although the self driving car can
 * 					 follow straight and slightly bent roads fairly well, it may struggle with sharp turns or wide areas. When drawing roads,
 * 					 try to avoid sharp edges and gaps.
 */

// this class runs the application
public class CarSimulationApplication {

	// fields
	public static MainFrame frame; 
	
	// main method
	public static void main(String[] args) {

		frame = new MainFrame();
		
	}

}
