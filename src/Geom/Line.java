package Geom;

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
		return  (y-getN())/getM();
	}
	
	public double dis(double x1, double y1, int x2, int y2) {
		return Math.sqrt(Math.pow(y1-y2, 2)+Math.pow(x1-x2, 2));
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
