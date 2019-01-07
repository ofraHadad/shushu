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
	ArrayList<Kodkod> graph= new ArrayList();
	MyCoords c= new MyCoords();

	public Algo(Game game) {
		this.game= game;
		g=new Graph(game.getMe().getLocationGPS());
	}

	private void insertGraph() {
		Iterator<Box> boxes= getGame().getBoxes().iterator();
		while (boxes.hasNext()) {
			Box b= boxes.next();
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS()),false,getGame().getMap()));
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS().get_x(),b.getMaxGPS().get_y(),0),false,getGame().getMap()));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS().get_x(),b.getLocationGPS().get_y(),0),false,getGame().getMap()));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS()),false,getGame().getMap()));

		}
		Iterator<Kodkod> boxesI=graph.iterator();
		int count=0;
		Iterator<Fruit> fruits= getGame().getFruits().iterator();
		while(boxesI.hasNext()) {
			Kodkod i= boxesI.next();
			
			Iterator<Kodkod> boxesJ= graph.iterator();
			for(int k=0 ; k<count; k++) {
				boxesJ.next();
			}
			while(boxesJ.hasNext()) {
				Kodkod j= boxesJ.next();
				if(isLegal(i.getLocation(), j.getLocation())) {
					j.setDistance(c.distance3d(i.getLocationGps(), j.getLocationGps()));
					i.setDistance(c.distance3d(i.getLocationGps(), j.getLocationGps()));

					i.getConnected().add(j);
					j.getConnected().add(i);
				}
				
			}
			while(fruits.hasNext()) {
				Fruit f= fruits.next();
				if(isLegal(i.getLocation(), f.getLocation())) {
					Kodkod k= new Kodkod(f.getLocationGPS(),true,getGame().getMap());
					k.setDistance(c.distance3d(i.getLocationGps(), f.getLocationGPS()));
					i.getConnected().add(k);
				}
			}
			count++;
		}
	}

	private void boxesHandling() {
		ArrayList<Kodkod> possible= new ArrayList();
		Iterator<Kodkod> kodkods= graph.iterator();
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