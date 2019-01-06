package game;

import Geom.Pixel;

public class Line {

	private double m,n;
	
	public Line(Pixel p1, Pixel p2) {
		
		setM((p1.getY()-p2.getY())/(p1.getX()-p2.getX()));
		setN(p1.getY()-(getM()*p1.getX()));
	}
	
	public double cutX(double x) {
		return getM()*x+getN();
	}

	public double cutY(double y) {
		return new (y-getN())/getM());
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}
	
	
}
