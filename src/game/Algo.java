package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.GIS_element;
import Geom.Gps_Point;
import Geom.Line;
import Geom.Pixel;


public class Algo {

	private Game game;
	Graph g;

	public Algo(Game game) {
		this.game= game;
	}


	private void boxesHandling() {
		ArrayList<Kodkod> possible= new ArrayList();
		Iterator<Kodkod> kodkods= g.getGraph().iterator();
		while(kodkods.hasNext()) {
			Kodkod k= kodkods.next();
			if(isLegal(getGame().getMe().getLocation(), k.getLocation())) {
				k.setDistance(c.distance3d(getGame().getMe().getLocationGPS(), k.getLocationGps()));
				possible.add(k);
			}
		}
	}
	private boolean isLegal(Pixel p1, Pixel p2) {
		Iterator <Box> boxes= game.getBoxes().iterator();
		Line l= new Line(p1,p2);

		while(boxes.hasNext()) {
			Box b= boxes.next();
			if(	b.onTheBoxX(l.cutX(b.getLocation().getX()))||b.onTheBoxX(l.cutX(b.getMax().getX()))
					|| b.onTheBoxY(l.cutY(b.getLocation().getY()))||b.onTheBoxY(l.cutY(b.getMax().getY()))){
				return false;
			}

		}
		return true;
	}

	public Game getGame() {
		return game;
	}



	//	private Pixel newTarget(Pixel target) {
	//		if(isLegal(target)) {
	//			return target;
	//		}
	//		else {
	//			
	//		}
}