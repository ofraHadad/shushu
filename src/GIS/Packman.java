package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
import game.Map;

public class Packman implements GIS_element/*, Runnable*/{

	private Pixel location;	
	private Map map;
	private PackmanMetaData dataP;
	private ArrayList<Fruit> eat= new ArrayList<Fruit>();
	private double time;




	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public ArrayList<Fruit> getEat() {
		return eat;
	}

	public void setEat(ArrayList<Fruit> eat) {
		this.eat = eat;
	}

	public void setLocation(Pixel location) {
		this.location = location;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setDataP(PackmanMetaData dataP) {
		this.dataP = dataP;
	}

	public Packman(int x,int y, Map map,int id) {
		this.map= map;
		this.location= new Pixel(x, y);
		this.dataP= new PackmanMetaData(id);
		

	}

	public Packman(String[] line, String[] head,Map map) {
		Gps_Point gps= new Gps_Point(head,line);

		this.map=map;
		location=map.converteGpsToPixel(gps);

		this.dataP= new PackmanMetaData(head, line);
		
	}
	
	public Packman(Packman p) {
		this.map= p.map;
		this.location= new Pixel (p.getLocation());
		this.dataP= new PackmanMetaData(p.getDataP());
		Iterator<Fruit> it= p.getEat().iterator();
		while(it.hasNext()) {
			getEat().add(it.next());
		}
	}
	
	
	
	
	public String toString() {
		String ans="";
		Iterator <Fruit> it= getEat().iterator();
		while(it.hasNext()) {
			ans= ans+it.next().getWhenEaten()+",";
		}
		return "Location: " + location + ", "+ this.dataP+", this time: "+getTime()+", time: "+ans;
	}





	public Map getMap() {
		return map;
	}

	public PackmanMetaData getDataP() {
		return dataP;
	}

	public Pixel getLocation() {
		return location;
	}
	



	@Override
	public Geom_element getGeom() {
		return this.location;
	}

	@Override
	public Meta_data getData() {
		return this.dataP;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}
	@Override
	public int whatAmI() {
		return 2;
	}

//	@Override
//	public void run() {
//
//		for(double i=0; i<=getTime(); i++) {
//			System.out.println(getTime());
//			Iterator<Fruit> it= getEat().iterator();
//			while(it.hasNext()) {
//				Fruit f= it.next();
//				if(f.getWhenEaten()<=i) {
//					setLocation(f.getFruit());
//					it.remove();
//				}
//			}
//			
//		}
//	}
}
