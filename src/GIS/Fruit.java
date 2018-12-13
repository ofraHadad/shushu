package GIS;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;
import game.Map;

public class Fruit implements GIS_element{
	private Pixel fruit;
	private Map map;
	private FruitMetaData dataF;
	private double whenEaten;

	public Fruit(int x, int y,Map map,int id) {
		this.map= map;
		fruit=new Pixel(x, y);
		this.dataF= new FruitMetaData(id);

	}



	public Fruit(String[] line, String[] head,Map map) {

		Gps_Point gps= new Gps_Point(head,line);

		this.map= map;
		fruit=	map.converteGpsToPixel(gps);

		this.dataF= new FruitMetaData(head,line);



	}

	public Fruit(Fruit f) {
		this.map= f.map;
		fruit= new Pixel(f.getFruit());
		this.dataF= new FruitMetaData(f.dataF);
		setWhenEaten(f.getWhenEaten());
	}



	public String toString() {
		return "Location: " + fruit +", "+dataF+" ,Time: "+getWhenEaten();
	}



	public Pixel getFruit() {
		return fruit;
	}

	public void setFruit(Pixel fruit) {
		this.fruit = fruit;
	}

	public Map getMap() {
		return map;
	}
	public FruitMetaData getDataF() {
		return this.dataF;
	}
	

	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return fruit;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return dataF;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}
	@Override
	public int whatAmI() {
		return 1;
	}



	public double getWhenEaten() {
		return whenEaten;
	}



	public void setWhenEaten(double whenEaten) {
		this.whenEaten = whenEaten;
	}



	
	


}