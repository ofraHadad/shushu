package game;

import java.util.Iterator;

import GIS.Fruit;

import GIS.MyGisLayer;
import GIS.Packman;
import GIS.Path;


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

	public void pathalgo() {

		Iterator<Fruit> it= getGame().getFruits().iterator();

		int size=getGame().getPackmans().size();
		double close[][]= new double[4][size];//0-min time, 1- fruit, 2- time, 3- grade
		close[0][0]=-1;
		boolean in=true;

		while(it.hasNext()) {
			for(int i=0; i<size; i++) {
				Packman p= getGame().getPackmans().get(i);
				if(in||close[0][i]==-2) {
					for(int j=0; j<getGame().getFruits().size();j++) {
						Fruit f= getGame().getFruits().get(j);
						double dis=(p.getLocationGPS().distance3d(f.getLocationGPS())-p.getDataP().getRadius());

						double time= (dis)/p.getDataP().getSpeed();
						if(time<0) {//when in the same place (distance3d=0 - raduis) 
							time=0;
						}
						if(close[0][i]<0) {
							close[0][i]=time;
							close[1][i]=j;
						}
						if(time<close[0][i]) {
							close[0][i]=time;
							close[1][i]=j;
						}
					}
					if(i==size-1) {
						in=false;
					}
				}
			}
			double min= close[0][0];
			int f=(int) close[1][0];
			int p=0;
			for(int i=1; i<size; i++) {
				
				if(min>close[0][i] ){
					min=close[0][i];
					f=(int) close[1][i];
					p=i;
				}
			
				if(f==(int)(close[1][i])) {
					
					if(close[2][i]<close[2][p]) {
						close[0][p]=-2;
						p=i;
					}
					else {
						close[0][i]=-2;
					}
				}
			}
			for(int i=0; i<size; i++) {

				if(i==p) {
					if(i!=size-1) {
						i++;
					}
					else {
						break;
					}
				}

			
				if((int)(close[1][i])>f) {
					close[1][i]--;
				}
			}
			close[2][p]=close[2][p]+min;
			Fruit now=getGame().getFruits().get(f);
			close[3][p]=close[3][p]+now.getDataF().getWeight();
			now.getDataF().setWhenEaten(close[2][p]);
			getGame().getPackmans().get(p).getDataP().getEat().add(now);
			
			getGame().getFruits().remove(f);
			close[0][p]=-2;
		}

	}


	public Path forGPS() {
		pathalgo();
		Iterator<Packman> it1=getGame().getPackmans().iterator();
		Path path= new Path();
		while(it1.hasNext()) {
			double t=0;
			Packman pack=it1.next();
			MyGisLayer layer= new MyGisLayer();
			layer.add(pack);
			
			Iterator<Fruit> it=pack.getDataP().getEat().iterator();
			Iterator<Fruit> it2=pack.getDataP().getEat().iterator();

			Fruit fNext=it2.next();
			pack.getDataP().setTimeNext(fNext.getDataF().getWhenEaten());
			int grade=0;
			while(it.hasNext()) {

				Fruit f= new Fruit(it.next());
				
				Packman pNext= new Packman(pack);
				

				pNext.getDataP().setTime(f.getDataF().getWhenEaten());
				layer.add(f);
				layer.add(pNext);

				grade=grade+f.getDataF().getWeight();
				pNext.getDataP().setGrade(grade);
				pNext.setLocationGPS(f.getLocationGPS());
				if(it2.hasNext()) {
					pNext.getDataP().setTimeNext(it2.next().getDataF().getWhenEaten());


				}
				else {
					
					t=pNext.getDataP().getTime()+10;
					if(t>path.getData().getTime()) {
						path.getData().setTime(t);
					}
					pNext.getDataP().setTimeNext(path.getData().getTime());
				}

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
