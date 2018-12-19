package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
import game.Map;

public class Packman implements GIS_element{

	private Pixel location;	
	private Gps_Point locationGPS;
	private Map map;
	private PackmanMetaData dataP;


	public Packman(int x,int y, Map map,int id) {
		this.map= map;
		this.location= new Pixel(x, y);
		this.dataP= new PackmanMetaData(id);
		setLocationGPS(new Gps_Point(map.convertePixelToGps(getLocation())));
		

	}

	public Packman(String[] line, String[] head,Map map) {
		Gps_Point gps= new Gps_Point(head,line);
		setLocationGPS(gps);
		this.map=map;
		location=map.converteGpsToPixel(gps);

		this.dataP= new PackmanMetaData(head, line);
		
	}
	
	public Packman(Packman p) {
		this.map= p.map;
		this.location= new Pixel (p.getLocation());
		this.dataP= new PackmanMetaData(p.getDataP());
		setLocationGPS(p.getLocationGPS());
		
	}
	
	
	
	
	public String toString() {
		
		return "Location: " + location + ", "+ this.dataP;
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
		return getLocationGPS();
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

	
	
	public boolean equals(GIS_element e) {
		return getGeom().equals(e.getGeom())&&getData().equals(e.getData())&&(whatAmI()==e.whatAmI());
		
	}

	public Gps_Point getLocationGPS() {
		return locationGPS;
	}

	public void setLocationGPS(Gps_Point locationGPS) {
		
		this.locationGPS = new Gps_Point(locationGPS);
	}
	public void setLocation(Pixel location) {
		this.location = location;
	}

	private void setMap(Map map) {
		this.map = map;
	}

	private void setDataP(PackmanMetaData dataP) {
		this.dataP = dataP;
	}

	
}
