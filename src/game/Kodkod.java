package game;

import java.io.File;
import java.util.ArrayList;

import Geom.Gps_Point;
import Geom.Pixel;


public class Kodkod {
	private Gps_Point locationGps;
	private ArrayList<Kodkod> connected=new ArrayList();
	private double distance;
	private boolean isFruit;
	private Map map;
	
	
	private int id;
	private boolean isVisited;
	
	
	public Kodkod( Gps_Point p,boolean isFruit,Map map,int id) {
		setLocationGps(p);
		setFruit(isFruit);
		this.map= map;
		setId(id);
	}
	

	public Gps_Point getLocationGps() {
		return locationGps;
	}

	public void setLocationGps(Gps_Point locationGps) {
		this.locationGps = locationGps;
	}

	public ArrayList<Kodkod> getConnected() {
		return connected;
	}

	public void setConnected(ArrayList<Kodkod> connected) {
		this.connected = connected;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public boolean isFruit() {
		return isFruit;
	}

	public void setFruit(boolean isFruit) {
		this.isFruit = isFruit;
	}
	
	public Pixel getLocation() {
		return map.converteGpsToPixel(getLocationGps());
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public boolean isVisited() {
		return isVisited;
	}


	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}




}
