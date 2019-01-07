package game;

import Geom.Gps_Point;
import Geom.Pixel;

public class Box {
	
	private int id;
	private Gps_Point LocationGPS;
	private int hetigh;
	private int width;
	private Map map;
	private Gps_Point maxGPS;
	
	
	public Box(String[] line, String[] head,Map map) {
		setId(Integer.parseInt(line[1]));
		this.map= map;

		Gps_Point min=new Gps_Point(head,line);

		String[] newHead= {"null","null","null","null","null","Lat","Lon","Alt"};
		Gps_Point max=new Gps_Point(newHead,line);
		setLocationGPS(new Gps_Point(min.get_x(),max.get_y(),0));
		setMaxGPS(new Gps_Point(max.get_x(),min.get_y(),0));
		Pixel minP= getMap().converteGpsToPixel(min);
		Pixel maxP= getMap().converteGpsToPixel(max);
		setHetigh(minP.getY()-maxP.getY());
		setWidth(maxP.getX()-minP.getX());
	}
	public String toString() {
		return "ID:";//+getId()+", min:"+getMin()+",max: "+getMax();
	}
	
	public boolean onTheBoxY(double x) {
		return(getLocation().getX()<x && getMax().getX()>x);
	}
	public boolean onTheBoxX(double y) {
		return(getLocation().getY()<y && getMax().getY()>y);
	}
	public Pixel getMax() {
		return new Pixel(getLocation().getX()+getWidth(), getLocation().getY()+getHetigh());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Gps_Point getLocationGPS() {
		return LocationGPS;
	}
	public void setLocationGPS(Gps_Point locationGPS) {
		LocationGPS = locationGPS;
	}
	public int getHetigh() {
		return hetigh;
	}
	public void setHetigh(int hetigh) {
		this.hetigh = hetigh;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Pixel getLocation() {
		return getMap().converteGpsToPixel(getLocationGPS());
	}
	public Gps_Point getMaxGPS() {
		return maxGPS;
	}
	public void setMaxGPS(Gps_Point maxGPS) {
		this.maxGPS = maxGPS;
	}

}
