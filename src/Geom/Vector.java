package Geom;
import Coords.MyCoords;

public class Vector {
	private double x;
	private double y;
	MyCoords c= new MyCoords();


	public Vector(Gps_Point start, Gps_Point end) {
		Point3D p= c.vector3D(start, end);
		setX(p.x());
		setY(p.y());
	
	}
	

	
	public Vector(String s) {
		String [] arr= s.split(",");
	}



	public Gps_Point goTo(Gps_Point start,double dis) {
		normal();
		
		Point3D p=new Point3D(getX()*dis,getY()*dis,0);
		return  new Gps_Point(c.add(start, p));
	}
	
	public double angel(Vector next) {
		double normaA= Math.sqrt(Math.pow(getX(), 2)+Math.pow(getY(), 2));
		double normaB= Math.sqrt(Math.pow(next.getX(), 2)+Math.pow(next.getY(), 2));
		double mid=( getX()*next.getX()+getY()*next.getY())/(normaA*normaB);
		return Math.acos(mid);
	}
	
	private void normal() {
		double norma= Math.sqrt(Math.pow(getX(), 2)+Math.pow(getY(), 2));
		
		setX((getX()/norma));
		setY(getY()/norma);
	}
	public String toString() {
		String s="("+getX()+","+getY()+")";
		return s;
		
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}
	
}