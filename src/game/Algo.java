package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import Geom.Gps_Point;
import Geom.Pixel;


public class Algo {
	
	
	private Game game;
	
	public Algo(Game game) {
		this.game= game;
	}
	
	private boolean isLegal(Gps_Point p) {
		Iterator <Box> boxes= game.getBoxes().iterator();
		while(boxes.hasNext()) {
			Box b= boxes.next();
		
		}
	}
}