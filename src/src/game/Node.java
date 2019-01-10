package game;

import Geom.Gps_Point;

public class Node {
	private Gps_Point key;
	private Node left;
	private Node right;
	private Node parent;

public Node (Node n, Gps_Point p,double length) {
	setKey(p);
	setParent(n);
	setLangth(length+parent.langth);
}
public Node ( Gps_Point p) {
	setKey(p);
	
}


	public Gps_Point getKey() {
		return key;
	}
	public void setKey(Gps_Point key) {
		this.key = key;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public double getLangth() {
		return langth;
	}
	public void setLangth(double langth) {
		this.langth = langth;
	}
	double langth;
}
