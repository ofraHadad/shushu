package game;


import java.util.ArrayList;
import java.util.Iterator;
import Coords.MyCoords;
import Geom.Gps_Point;
import Geom.Line;
import Geom.Pixel;


public class Graph {
	private ArrayList<Kodkod> graph= new ArrayList<Kodkod>();
	private MyCoords c= new MyCoords();
	private Game game;
	private int id=0;

	public Graph(Game game) {
		this.game=game;
		insertGraph();
	}
	private void insertGraph() {
		Iterator<Box> boxes= getGame().getBoxes().iterator();
		graph.add(new Kodkod(game.getMe().getLocationGPS(),3,game.getMap(),id++,0));
		while (boxes.hasNext()) {
			Box b= boxes.next();
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS()),1,getGame().getMap(),id++,b.getId()));
			graph.add(new Kodkod(new Gps_Point(b.getLocationGPS().get_x(),b.getMaxGPS().get_y(),0),1,getGame().getMap(),id++,b.getId()));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS().get_x(),b.getLocationGPS().get_y(),0),1,getGame().getMap(),id++,b.getId()));
			graph.add(new Kodkod(new Gps_Point(b.getMaxGPS()),1,getGame().getMap(),id++,b.getId()));
		}
		for(Fruit i : game.getFruits()) {
			graph.add(new Kodkod(i.getLocationGPS(),2,game.getMap(),id++,i.getDataF().getId()));
		}
		for(Packman i: game.getPackmans()) {
			graph.add(new Kodkod(i.getLocationGPS(),4,game.getMap(),id++,i.getDataP().getId()));
		}

		Iterator<Kodkod> boxesI=graph.iterator();
		int count=0;
		while(boxesI.hasNext()) {
			Kodkod i= boxesI.next();
			if(i.getWhoAmI()==2) {
				return;
			}
			Iterator<Kodkod> boxesJ= graph.iterator();
			for(int k=-1 ; k<count; k++) {
				boxesJ.next();
			}
			while(boxesJ.hasNext()) {
				Kodkod j= boxesJ.next();

				if(isLegal(i.getLocation(), j.getLocation())) {
				i.getConnected().add(j);
					
						j.getConnected().add(i);
					
				}

			}

			count++;
		}
	}
/**
 * we used the code from: https://www.geeksforgeeks.org/find-paths-given-source-destination/
 * @param s
 * @param d
 * @param minPathList
 * @return
 */
	public GpsPath bestPath(int s, int d, GpsPath minPathList) 
	{ 
		GpsPath pathList= new GpsPath();
		//add source to path[] 
		pathList.getPath().add(s); 

		//Call recursive utility 
		bestPathUtil(s, d,pathList,minPathList,0); 
		
		for(Kodkod k: graph) {
			k.setVisited(false);
		}
		return minPathList;
	} 


	private void bestPathUtil(Integer s, int d,  GpsPath localPathList,GpsPath MinlocalPathList, int count) { 

		// Mark the current node 
		graph.get(s).setVisited(true);

		if (s.equals(d)) 
		{ 
			
			if(	MinlocalPathList.getPath().isEmpty()) {
				
				for(Integer i: localPathList.getPath()) {
					MinlocalPathList.getPath().add(i);
				}
			}
			localPathList.setDis(distance(localPathList.getPath()));
			MinlocalPathList.setDis(distance(MinlocalPathList.getPath()));

			if(MinlocalPathList.getDis()>localPathList.getDis()) {

				MinlocalPathList.getPath().clear();
				for(Integer i: localPathList.getPath()) {
					MinlocalPathList.getPath().add(i);
				}
			}

			graph.get(s).setVisited(false);

			return ; 
		} 

		// Recur for all the vertices 
		// adjacent to current vertex 
		for(Kodkod k: graph.get(s).getConnected())
		{ 
			//System.out.println(k.getId()+"g"+s);
			if (!k.isVisited() ) 
			{ 
				// store current node 
				// in path[] 
				//System.out.println(k.getId());
				//dis= dis+k.getDistance();
				localPathList.getPath().add(k.getId()); 
				bestPathUtil(k.getId(), d,localPathList,MinlocalPathList,count++); 

				// remove current node 
				// in path[] 
				localPathList.getPath().remove((Object)k.getId()); 
				//dis= dis-k.getDistance();

			} 
		} 

		// Mark the current node 
		graph.get(s).setVisited(true); 
	} 

	public Kodkod search(int id) {
		for(Kodkod k: graph) {
			if(k.getId()==id) {
				return k;
			}
		}
		return null;
	}


	private

	boolean isLegal(Pixel p1, Pixel p2) {
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
	private double distance( ArrayList<Integer> path) {
		double dis=0;
		for(int i=0; i<path.size()-1; i++) {

			dis=dis+c.distance3d(search(path.get(i)).getLocationGps(),search(path.get(i+1)).getLocationGps());
		}
		return dis;
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