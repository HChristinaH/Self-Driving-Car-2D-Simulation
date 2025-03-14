package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import application.CarSimulationApplication;
import model.Line;
import model.Point;
import model.RoadLine;
import view.SimulationPanel;

// this class is responsible for erasing roads and loading road maps
public class RoadController {

	// constructor
	public RoadController() {

	}

	// this method erases all road lines
	public static void clearRoads() {

		// erase all lines that intersect the diagonal lines
		int x = 0;

		while (x < SimulationPanel.lineList.size()) {

			Line currentLine = SimulationPanel.lineList.get(x);

			if (currentLine instanceof RoadLine) {

				SimulationPanel.lineList.remove(x);
				
				x--;

			}

			x++;
		}
		
		CarSimulationApplication.frame.simulationPanel.repaint();

	}

	// this method erases roadline within a 25 pixel square around a point
	public static void erase(Point p) {

		// draw two small diagonal lines from the clicked point
		Line diagonal1 = new Line(new Point(p.getX() - 25, p.getY() - 25), new Point(p.getX() + 25, p.getY() + 25));
		Line diagonal2 = new Line(new Point(p.getX() + 25, p.getY() - 25), new Point(p.getX() - 25, p.getY() + 25));

		// erase all lines that intersect the diagonal lines
		int x = 0;

		while (x < SimulationPanel.lineList.size()) {

			Line currentLine = SimulationPanel.lineList.get(x);

			if (currentLine instanceof RoadLine && (currentLine.getIntersection(diagonal1) != null
					|| currentLine.getIntersection(diagonal2) != null)) {

				SimulationPanel.lineList.remove(x);

			}

			x++;
		}
	}

	// this method loads a road map by reading a text file
	public static void loadMap(int number) {

		// clear previous road lines
		clearRoads();
		
		File mapFile = new File(String.format("data/RoadMap%d.txt", number));

		Scanner fileReader;
		try {
			fileReader = new Scanner(mapFile);
			fileReader.useDelimiter(",|\\R");

			while (fileReader.hasNextLine()) {
				RoadLine newRoad = new RoadLine(new Point(fileReader.nextInt(), fileReader.nextInt()),
						new Point(fileReader.nextInt(), fileReader.nextInt()));
				SimulationPanel.lineList.add(newRoad);
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		CarSimulationApplication.frame.simulationPanel.repaint();

	}

}
