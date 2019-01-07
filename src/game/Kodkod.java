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
	
	public Kodkod( Gps_Point p,boolean isFruit,Map map) {
		setLocationGps(p);
		setFruit(isFruit);
		this.map= map;
	}
	
//	public double bestPath(double dis , Gps_Point f,Kodkod k) {
//		
////		for(int i=0; i<k.getConnected().size(); i++){
////			if(!k.getConnected().get(i).isFruit){
////				read(f.getAbsolutePath(),type,startWith);
////			}
////			String filePath = f.getAbsolutePath();
////			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
////
////			if(type.equals(fileExtenstion) &&filePath.contains(startWith)){
////				//add to a list or array
////
////				s.add(filePath);
////			}
////
////		} 
////
////		return s;
//	}

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
}
