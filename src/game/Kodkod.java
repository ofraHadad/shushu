package game;

import java.io.File;
import java.util.ArrayList;

import Geom.Gps_Point;
import Geom.Pixel;


public class Kodkod {
	private Gps_Point locationGps;
	private ArrayList<Kodkod> connected=new ArrayList();
	
	private int whoAmI;//1-box, 2- fruit, 3- me, 4- packman
	private Map map;

	private int id;
	private boolean isVisited;
	
	
	public Kodkod( Gps_Point p,int whoAmI,Map map,int id) {
		setLocationGps(p);
		setWhoAmI(whoAmI);
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

	

	
	public int getWhoAmI() {
		return whoAmI;
	}


	public void setWhoAmI(int whoAmI) {
		this.whoAmI = whoAmI;
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
