package Geom;

import game.Map;

public class Pixel implements Geom_element{
	
	private int x;
	private int y;
	
	
	public Pixel(int x, int y) {
		setX(x);
		setY(y);
	}
	
	
	public Pixel(Pixel p) {

		setX(p.getX());
		setY(p.getY());
	}


	public String toString() {
		return "("+getX()+","+getY()+")";
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public double distance(Pixel p) {
		double x= Math.pow(getX()-p.getX(), 2);
		double y= Math.pow(getY()-p.getY(), 2);
		return Math.sqrt(x+y);
	}

	@Override
	public double distance3D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distance2D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}

}
