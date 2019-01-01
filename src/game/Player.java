package game;

import Geom.Gps_Point;
import Geom.Pixel;

public class Player {

	private Gps_Point LocationGPS;
	private int speed;
	private Map map;
	private int radius;
	
	public Player(int x,int y, Map map) {
		this.map=map;
		setLocationGPS(getMap().convertePixelToGps(new Pixel(x,y)));
		setSpeed(20);//בנתיים
		setRadius(1);
	}

	
	public Pixel getLocation() {
		return getMap().converteGpsToPixel(getLocationGPS());
	}
	public Gps_Point getLocationGPS() {
		return LocationGPS;
	}

	public void setLocationGPS(Gps_Point locationGPS) {
		LocationGPS = locationGPS;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
}
