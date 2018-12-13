package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
import game.Map;

public class GameGPS implements GIS_element{

	private	Gps_Point Location;
	private	PackmanMetaData dataP;
	private double time;

	public GameGPS(Packman e) {
		setLocation(e.getMap().convertePixelToGps((Pixel) e.getGeom()));
		setDataP( e.getDataP());
		

	}
	public GameGPS(Fruit f, PackmanMetaData data) {
		setLocation(f.getMap().convertePixelToGps(f.getFruit()));
		setDataP(data);
		setTime(f.getWhenEaten());
	}
	public GameGPS(GameGPS g) {
		setLocation(g.Location);
		setDataP(g.getDataP());
		setTime(g.time);
	}
	public long getUTC() {
		// https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
		
		
			
			long timeInMillis = (long)getTime();
			return timeInMillis;
	
	}

	public String toString() {
		return "location: "+ getLocation()+" ,meta data: "+getDataP();
	}
	public Gps_Point getLocation() {
		return Location;
	}
	public void setLocation(Gps_Point location) {
		Location = new Gps_Point(location);
	}
	public PackmanMetaData getDataP() {
		return dataP;
	}
	public void setDataP(PackmanMetaData dataP) {
		this.dataP = new PackmanMetaData(dataP);
	}
	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}
	@Override
	public int whatAmI() {
		return 3;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

}
