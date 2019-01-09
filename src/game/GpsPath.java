package game;

import java.util.ArrayList;

import Geom.Gps_Point;

public class GpsPath {
	private ArrayList<Integer> path=new ArrayList();
	private double dis;

	public ArrayList<Integer> getPath() {
		return path;
	}



	public double getDis() {
		return dis;
	}

	public void setDis(double dis) {
		this.dis = dis;
	}

}
