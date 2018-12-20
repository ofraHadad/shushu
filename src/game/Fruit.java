package game;

import GIS.FruitMetaData;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;

public class Fruit implements GIS_element{
	private Pixel location;
	private Gps_Point locationGPS;
	private Map map;
	private FruitMetaData dataF;
	

	public Fruit(int x, int y,Map map,int id) {
		this.map= map;
		setLocation(new Pixel(x, y));
		this.dataF= new FruitMetaData(id);
		setLocationGPS(new Gps_Point(map.convertePixelToGps(getLocation())));

	}



	public Fruit(String[] line, String[] head,Map map) {

		setLocationGPS(new Gps_Point(head,line));
		this.map= map;
		setLocation(map.converteGpsToPixel(getLocationGPS()));

		this.dataF= new FruitMetaData(head,line);



	}

	public Fruit(Fruit f) {
		setLocationGPS(new Gps_Point(f.getLocationGPS()));
		this.map= f.map;
		setLocation(new Pixel(f.getLocation()));
		this.dataF= new FruitMetaData(f.dataF);
	}



	public String toString() {
		return "Location: " + getLocation() +", "+dataF;
	}





	public Map getMap() {
		return map;
	}
	protected FruitMetaData getDataF() {
		return this.dataF;
	}
	

	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return locationGPS;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return dataF;
	}

	@Override
	public void translate(Point3D vec) {

		

	}
	@Override
	public int whatAmI() {
		return 1;
	}
	
	public Gps_Point getLocationGPS() {
		return locationGPS;
	}

	protected void setLocationGPS(Gps_Point locationGPS) {
		this.locationGPS = locationGPS;
	}



	public Pixel getLocation() {
		return location;
	}



	private void setLocation(Pixel location) {
		this.location = location;
	}



	@Override
	public boolean equals(GIS_element e) {
		// TODO Auto-generated method stub
		return getGeom().equals(e.getGeom()) && getData().equals(e.getData())&&(whatAmI()==e.whatAmI());
	}


}