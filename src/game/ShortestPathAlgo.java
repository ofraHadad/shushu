package game;

import java.util.Iterator;

import GIS.Fruit;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Game;
import GIS.GameGPS;
import GIS.MyGisElement;
import GIS.MyGisLayer;
import GIS.Packman;
import GIS.Path;
import Geom.Gps_Point;

/**
 *  @author Shira & ofra
 * class that accepts a game and calculates the optimal route so that all the fruits are eaten in the shortest time possible
 */
////////////////////Constructor\\\\\\\\\\\\\\\\\\
public class ShortestPathAlgo {
	private Game game;
	/**
	 * Constructor that get a game
	 * @param game
	 */
	public ShortestPathAlgo(Game game) {	
		setGame(game);
	}


	////////////////////Methods\\\\\\\\\\\\\\\\\\
	public void  bestPathCalculation() {
		Iterator<Fruit> it0=game.getFruits().iterator();
		while(it0.hasNext()) {
			Iterator<Packman> it1=game.getPackmans().iterator();
			Fruit f= new Fruit(it0.next());
			double min=-1; 
			int i=0;
			int counter=-1;
			double old=0;
			while(it1.hasNext()) {
				Packman p=it1.next();
				
				counter++;
				double disXy=(p.getLocation().distance(f.getFruit())-p.getDataP().getRadius());
				double diff= p.getDataP().getAlt()-f.getDataF().getAlt();
				double time= (Math.sqrt(Math.pow(disXy, 2)+Math.pow(diff, 2)))/p.getDataP().getSpeed();
				if(time<min||min==-1) {
					min=time;
					i=counter;
					old= game.getPackmans().get(i).getTime()+min;
				}
			}
			f.setWhenEaten(old);
			game.getPackmans().get(i).setTime(old);
			game.getPackmans().get(i).getEat().add(f);
			game.getPackmans().get(i).getLocation().setX(f.getFruit().getX());
			game.getPackmans().get(i).getLocation().setY(f.getFruit().getY());

			it0.remove();

		}
		
		
	}
	
	public Path forGPS() {
		bestPathCalculation();
		Iterator<Packman> it1=game.getPackmans().iterator();
		Path path= new Path();
		while(it1.hasNext()) {
			Packman pack= new Packman(it1.next());
			MyGisLayer layer= new MyGisLayer();
			layer.add(new GameGPS(pack));
			Iterator<Fruit> it=pack.getEat().iterator();
			while(it.hasNext()) {
				Fruit f= new Fruit(it.next());
				
				int grade= pack.getDataP().getGrade()+f.getDataF().getWeight();
				pack.getDataP().setGrade(grade);
				layer.add(new GameGPS(f,pack.getDataP()));
			}
			path.add(layer);
		}
		return path;
	}
	
	


	public Game getGame() {
		return game;
	}

	private void setGame(Game game) {
		this.game = game;
	}
}
