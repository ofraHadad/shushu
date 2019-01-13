package game;


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
import Threads.myThread;



public class Algo {

	private Play play;
	private Game game;
	Graph graph;
	MyCoords c= new MyCoords();
	ArrayList<Double> p= new ArrayList<Double>();
	MyFrame mf;

	public Algo(Play p,Game game,MyFrame mf) {
		this.play= p;		
		this.game= game;		
		graph= new Graph(this.game);
		play.start();
		this.mf=mf;
	}

	public void algo() {
		if(game.getFruits().size()==0) {
			play.rotate(0);
		}
		if(!play.isRuning()) {
			System.out.println(play.getStatistics());
			return ;

		}


		//System.out.println(game.getFruits());
		//System.out.println(graph.getGame().getMe().getLocationGPS());
		ArrayList<Integer> path= computPath().getPath();
		System.out.println(path);
		for(int i=1; i<path.size(); i++) {
			Kodkod kodkod=getGraph().search(path.get(i));
					System.out.println(kodkod.getLocation());
			//			System.out.println(game.getMe().getLocation()+"me");
			//			System.out.println(path.get(i));

			//System.out.println(kodkod.getLocation());
			//	System.out.println(path.get(i));
			while(!getGraph().search(path.get(path.size()-1)).isDead() &&
					!game.getMe().equal(kodkod)&&play.isRuning() ){
				double a= (360-c.azimuth( kodkod.getLocationGps(),game.getMe().getLocationGPS()
						)-90);
				while(a<0) {
					a=a+360;
				}
				p.add(a);
				play.rotate(a);
								System.out.println(a);
				//				System.out.println(game.getMe().getLocation()+"me");

				graph.getGame().readArrayList(play.getBoard());
				
				//System.out.println(graph.getGame().getMe().getLocation());


				graph.reinsert();
				//myThread t=new myThread(this.game, this.mf);
				//t.start();
				
				mf.repaint();
		
				//				for(Kodkod p: graph.getGraph().get(0).getConnected()) {
				//					System.out.println(p.getId()+"dsfsfs");
				//				}
			}
			
			//System.out.println(i);
			//System.out.println(graph.getGame().getMe().getLocationGPS());
			if( getGraph().search(path.get(path.size()-1)).isDead()||!play.isRuning()) {
				break;
			}
			
			
		}
		algo();
	}



	public GpsPath computPath() {
		double min=-1;

		
		GpsPath minPath= new GpsPath();
		for( Kodkod k :getGraph().getGraph() ) {
			if(k.getWhoAmI()==2 && !k.isDead()) {

				GpsPath path= new GpsPath();
				graph.bestPath(0,k.getId(), path);
				System.out.println( path.getPath()+"hhhh");
				if(path.getPath().size()>0) {
					if(path.getDis()<min || min==-1) {
						System.out.println(k.getId());
						min=path.getDis();
						minPath.getPath().clear();
						for(Integer i : path.getPath()) {
							minPath.getPath().add(i);
						}
						minPath.setDis(path.getDis());
					}
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