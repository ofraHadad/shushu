package game;

import java.util.ArrayList;

import java.util.Iterator;

//import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import File_format.CSV_handling;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Packman;
import Geom.Gps_Point;
import Geom.Pixel;

public class Game /*implements GIS_layer */{

	private ArrayList<Fruit> fruits=new ArrayList<>();
	private ArrayList<Packman> packmans=new ArrayList<>();
	private Map map;



	public Game(Map map) {
		this.map= map; 
	}
	public Game(String csvFile, Map map) {
		this.map= map;
		CSV_handling c= new CSV_handling();
		c.read(csvFile, 0, map, this);
	}

	public String toString() {
		String ans="";
		Iterator itFruits= getFruits().iterator();
		Iterator itPackmans= getPackmans().iterator();
		while(itPackmans.hasNext()) {
			ans= ans+"Packman: "+itPackmans.next().toString()+"\n";
		}
		while(itFruits.hasNext()) {
			ans= ans+"Fruit: "+itFruits.next().toString()+"\n";
		}
		return ans;
	}

	public void synchronization(int w, int h) {

		Gps_Point start= getMap().convertePixelToGps(new Pixel(0,0));
		Gps_Point end= getMap().convertePixelToGps(new Pixel(getMap().getMyImage().getWidth(), getMap().getMyImage().getHeight()));

	}

	public boolean add(GIS_element e) {
		if (e.whatAmI()==1) {
			getFruits().add((Fruit) e);
		}
		if(e.whatAmI()==2) {
			getPackmans().add((Packman) e);
		}

		return false;
	}
	
	
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}
	public ArrayList<Packman> getPackmans() {
		return packmans;
	}



	public Map getMap() {
		return map;
	}
	
	


}
