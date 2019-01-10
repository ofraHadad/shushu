package game;

import GIS.FruitMetaData;
import Geom.Gps_Point;
import Geom.Pixel;

public class Ghost {
	private int id;
	private double speed;
	private double radius;
	private Gps_Point locationGPS;
	private Map map;

	public Ghost (String[] line, String[] head,Map map) {

		setLocationGPS(new Gps_Point(head,line));
		this.map= map;

		setId(Integer.parseInt(line[1]));
		setSpeed(Double.parseDouble(line[5]));
		setRadius(Double.parseDouble(line[6]));

	}
	
	public String toString() {
		return "ID:"+getId()+", Speed:"+getSpeed()+",location: "+getLocationGPS();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Gps_Point getLocationGPS() {
		return locationGPS;
	}

	public void setLocationGPS(Gps_Point locationGPS) {
		this.locationGPS = locationGPS;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	public Pixel getLocation() {
		return map.converteGpsToPixel(getLocationGPS());
	}

}
