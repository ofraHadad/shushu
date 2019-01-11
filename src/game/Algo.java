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
		
		System.out.println(game.getFruits());
		ArrayList<Integer> path= computPath().getPath();
		for(int i=1; i<path.size(); i++) {
			Kodkod kodkod=getGraph().search(path.get(i));
			while(!getGraph().search(path.get(path.size()-1)).isDead() &&
					! game.getMe().getLocationGPS().equals(kodkod.getLocationGps())){

				play.rotate((360-c.azimuth( game.getMe().getLocationGPS(),
						kodkod.getLocationGps())-90)%360);
			
				graph.getGame().readArrayList(play.getBoard());
			
				graph.reinsert();
				
			}
			if(getGraph().search(path.get(path.size()-1))==null) {
				algo();
			}
		}
	}



	public GpsPath computPath() {
		double min=-1;
		GpsPath path= new GpsPath();
		GpsPath minPath= new GpsPath();
		for( Kodkod k :getGraph().getGraph() ) {
			if(k.getWhoAmI()==2 && !k.isDead()) {
				
				path.getPath().clear();
				path.setDis(0);
				path.setDis(graph.bestPath(0,k.getId(), path).getDis());
				if(path.getDis()<min || min==-1) {
					System.out.println(1);
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