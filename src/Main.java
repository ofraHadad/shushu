import java.util.ArrayList;

import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Vector;
import Robot.Play;
import game.Game;
import game.Graph;
import game.Map;
import game.MyFrame;



public class Main {

	public static void main(String[] args) {
	Map map= new Map();
		Play p= new Play("data/Ex4_OOP_example5.csv");
		Game game= new Game(p.getBoard(), new Map());
		Graph g= new Graph(game);
		//g.bestPath(0,3);
	for(int i=0 ;i<g.getGraph().get(3).getConnected().size();i++) {
		System.out.println(g.getGraph().get(3).getConnected().get(i).getId());
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
//		MyFrame frame = new MyFrame(new Map());
	}

}