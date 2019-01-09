import java.util.ArrayList;

import Coords.MyCoords;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Vector;
import Robot.Play;
import game.Algo;
import game.Game;
import game.GpsPath;
import game.Graph;
import game.Map;
import game.MyFrame;
import game.Player;



public class Main {

	public static void main(String[] args) {
		Play play1= new Play("data/Ex4_OOP_example5.csv");	
		Game game= new Game(play1.getBoard(),new Map());
		
		//System.out.println(play1.getBoard().get(0));
		game.setMe( new Player(1,4,new Map()));
		play1.setInitLocation( game.getMe().getLocationGPS().get_y(),game.getMe().getLocationGPS().get_x());

		
				Graph g= new Graph(game);
				GpsPath  a= new GpsPath();
		System.out.println(g.bestPath(7,4,a).getPath());
		//		System.out.println(g.getGraph().get(0).getLocationGps());
		//		System.out.println(g.getGraph().get(2).getLocationGps());
		//		System.out.println(g.getGraph().get(6).getLocationGps());
		//		MyCoords c= new MyCoords();
		//		
		//		System.out.println(c.distance3d(g.getGraph().get(6).getLocationGps(),g.getGraph().get(2).getLocationGps()));
		//		
		for(int i=0; i<g.getGraph().size();i++) {
			System.out.println(g.getGraph().get(i).getId()+"ggg"+g.getGraph().get(i).getWhoAmI());
		}
				for(int i=0 ;i<g.getGraph().get(7).getConnected().size();i++) {
					System.out.println(g.getGraph().get(7).getConnected().get(i).getId());
				}
		//		System.out.println(g.getGame().getBoxes().get(1).getLocation());
		//		System.out.println(g.getGame().getBoxes().get(1).getMax());
		//
		//		for (int i=0; i<8; i++) {
		//			System.out.println(i);

		//System.out.println(g.getGraph().get(0).getLocation()+","+ g.getGraph().get(i).getLocation());
		//	System.out.println(g.isLegal(g.getGraph().get(3).getLocation(), g.getGraph().get(7).getLocation()));
		//	}
		//		System.out.println(game.getBoxes().get(0).getLocation());
		//		System.out.println(game.getBoxes().get(0).getMax());
		//	   System.out.println(	g.isLegal(new Pixel(766,98),new Pixel(813,98)));
		//		//System.out.println(game.getGhosts().get(0).getLocation());
		//		//System.out.println(game.getBoxes().get(0).getLocation());
//		Play play1= new Play("data/Ex4_OOP_example5.csv");	
//		Game game= new Game(play1.getBoard(),new Map());
//		
//		//System.out.println(play1.getBoard().get(0));
//		game.setMe( new Player(3, 5,new Map()));
//		play1.setInitLocation( game.getMe().getLocationGPS().get_y(),game.getMe().getLocationGPS().get_x());
//
//		game.readArrayList(play1.getBoard());
//		Algo a= new Algo(play1,game);
//		a.algo();
		MyFrame frame = new MyFrame(new Map());
	}

}