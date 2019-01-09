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
import Robot.Play;


public class Algo {

	private Play play;
	private Game game;
	Graph graph;
	MyCoords c= new MyCoords();

	public Algo(Play p,Game game) {
		this.play= p;		
		this.game= game;		
		graph= new Graph(this.game);
		play.start();
	}

	public void algo() {
		//System.out.println(game.getFruits());
		ArrayList<Integer> path= computPath().getPath();
		System.out.println(computPath().getPath());
		for(int i=0; i<path.size()-1; i++) {
			Kodkod kodkod=getGraph().search(path.get(i));
			while(getGraph().search(path.get(path.size()-1))!=null &&
					! game.getMe().getLocationGPS().equals(kodkod.getLocationGps())){

				play.rotate((360-c.azimuth( game.getMe().getLocationGPS(),
						kodkod.getLocationGps())-90)%360);
				game.readArrayList(play.getBoard());
				graph= new Graph(game);
			}
			if(getGraph().search(path.get(path.size()-1))==null) {
				algo();
			}
		}
	}



	private GpsPath computPath() {
		double min=-1;
		GpsPath path= new GpsPath();
		GpsPath minPath= new GpsPath();
		for( Kodkod k :getGraph().getGraph() ) {
			if(k.getWhoAmI()==2) {
				path=bestWay(k.getId());
				if(path.getDis()<min || min==-1) {
					min=path.getDis();
					minPath.getPath().clear();
					for(Integer i : path.getPath()) {
						minPath.getPath().add(i);
					}
					minPath.setDis(path.getDis());
				}
			}
		}
	return minPath;
	}
	
//		GpsPath p= new GpsPath();
//		Iterator<Kodkod> fruits= getGraph().getGraph().iterator();
//		while(fruits.hasNext()) {
//			System.out.println(p.getPath());
//			Kodkod k= fruits.next();
//			Kodkod f=k;
//			if(k.getWhoAmI()==2) {
//				f= k;
//
//			}
//			else {
//
//				while(fruits.hasNext()) {
//					if(k.getWhoAmI()!=2) {
//						f=fruits.next();
//					}
//					else {
//						break;
//					}
//				}
//
//			}
//			if(min==-1) {
//				p=bestWay(f.getId());
//				min= p.getDis();
//			}
//			if(min>bestWay(f.getId()).getDis()) {
//				p=bestWay(f.getId());
//				min= p.getDis();
//			}
//
//		}
//		return p;
//	}

	private GpsPath bestWay(int id) {
		GpsPath path= new GpsPath();
		System.out.println(getGraph().bestPath(1, id, path).getPath());
		return getGraph().bestPath(1, id, path);
	}

	private boolean isLegal(Pixel p1, Pixel p2) {
		Iterator <Box> boxes= game.getBoxes().iterator();
		Line l= new Line(p1,p2);
		while(boxes.hasNext()) {
			Box b= boxes.next();
			if(	(l.onTheKeta(l.cutX(b.getLocation().getX())) && b.onTheBoxX(l.cutX(b.getLocation().getX())))
					|| (l.onTheKeta(l.cutX(b.getMax().getX())) && b.onTheBoxX(l.cutX(b.getMax().getX())))
					|| (l.onTheKeta(l.cutY(b.getLocation().getY())) && b.onTheBoxY(l.cutY(b.getLocation().getY())))
					||(l.onTheKeta(l.cutY(b.getMax().getY())) && b.onTheBoxY(l.cutY(b.getMax().getY()))))
			{
				return false;
			}

		}
		return true;
	}


	public Game getGame() {
		return game;
	}


	public Graph getGraph() {
		return graph;
	}


	public void setGraph(Graph graph) {
		this.graph = graph;
	}


	public void setGame(Game game) {
		this.game = game;
	}



	//	private Pixel newTarget(Pixel target) {
	//		if(isLegal(target)) {
	//			return target;
	//		}
	//		else {
	//			
	//		}
}