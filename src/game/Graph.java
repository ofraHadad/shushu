package game;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Gps_Point;
import Geom.Line;
import Geom.Pixel;

public class Graph {
	private ArrayList<Kodkod> graph= new ArrayList();
	private MyCoords c= new MyCoords();
	private Game game;
	private int id=0;

	public Graph(Game game) {
		this.game=game;
		insertGraph();
	}
	private void insertGraph() {
		Iterator<Box> boxes= getGame().getBoxes().iterator();
		while (boxes.hasNext()) {
			Box b= boxes.next();
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS()),false,getGame().getMap(),id++));
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS().get_x(),b.getMaxGPS().get_y(),0),false,getGame().getMap(),id++));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS().get_x(),b.getLocationGPS().get_y(),0),false,getGame().getMap(),id++));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS()),false,getGame().getMap(),id++));
		}
		for(Fruit i : game.getFruits()) {
			graph.add(new Kodkod(i.getLocationGPS(),true,game.getMap(),id++));
		}

		Iterator<Kodkod> boxesI=graph.iterator();
		int count=0;
		while(boxesI.hasNext()) {
			Kodkod i= boxesI.next();
			if(i.isFruit()) {
				return;
			}
			Iterator<Kodkod> boxesJ= graph.iterator();
			for(int k=-1 ; k<count; k++) {
				boxesJ.next();
			}
			while(boxesJ.hasNext()) {
				Kodkod j= boxesJ.next();

				if(isLegal(i.getLocation(), j.getLocation())) {
					j.setDistance(c.distance3d(i.getLocationGps(), j.getLocationGps()));
					i.setDistance(c.distance3d(i.getLocationGps(), j.getLocationGps()));

					i.getConnected().add(j);
					if(!j.isFruit()) {
						j.getConnected().add(i);
					}
				}

			}
			
			count++;
		}
	}

	public void bestPath(int s, int d) 
	{ 
		//boolean[] isVisited = new boolean[getGraph().size()]; 
		ArrayList<Integer> pathList = new ArrayList<>(); 

		//add source to path[] 
		pathList.add(s); 

		//Call recursive utility 
		bestPathUtil(s, d,pathList); 
	} 


	private void bestPathUtil(Integer s, int d,  ArrayList<Integer> localPathList) { 

		// Mark the current node 
		graph.get(s).setVisited(true);

		if (s.equals(d)) 
		{ 
			System.out.println(localPathList); 
			// if match found then no need to traverse more till depth 
			graph.get(s).setVisited(false);
			return ; 
		} 

		// Recur for all the vertices 
		// adjacent to current vertex 
		//	for (Integer i=0; i< graph.get(s).getConnected().size();i++) 
		for(Kodkod k: graph.get(s).getConnected())
		{ 
			System.out.println(k.getId()+"g"+s);
			if (!k.isVisited() ) 
			{ 
				// store current node 
				// in path[] 
				System.out.println(k.getId());

				localPathList.add(k.getId()); 
				bestPathUtil(k.getId(), d,localPathList); 

				// remove current node 
				// in path[] 
				localPathList.remove((Object)k.getId()); 
			} 
		} 

		// Mark the current node 
		graph.get(s).setVisited(true); 
	} 


	public boolean isLegal(Pixel p1, Pixel p2) {
		Iterator <Box> boxes= game.getBoxes().iterator();
		
		Line l= new Line(p1,p2);
		
		while(boxes.hasNext()) {
			Box b= boxes.next();
		System.out.println("ggg"+(l.onTheKeta(l.cutX(b.getMax().getX())) && b.onTheBoxX(l.cutX(b.getMax().getX()))));
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

	public ArrayList<Kodkod> getGraph() {
		return graph;
	}
	public void setGraph(ArrayList<Kodkod> graph) {
		this.graph = graph;
	}
	public MyCoords getC() {
		return c;
	}
	public void setC(MyCoords c) {
		this.c = c;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

}

