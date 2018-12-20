package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

public class PackmanMetaData implements Meta_data{
	private int id;
	private int alt;
	private int speed;
	private int radius;
	private int grade=0;
	private ArrayList<Fruit> eat= new ArrayList<Fruit>();
	private double time;
	private double timeNext;

	public PackmanMetaData(int id) {
		setId(id);
		setAlt(0);
		setSpeed(1);
		setRadius(1);

	}



	public PackmanMetaData(String [] head, String[] line) {
		int hasId=serch(head,"id");
		int hasSpeed= serch(head,"Speed");
		int hasRadius= serch(head,"Radius");
		int hasAlt= serch(head, "Alt");
		if(hasId==head.length) {
			throw new RuntimeException("EROR: the packman has no id");
		}
		else {
			setId(Integer.parseInt(line[hasId]));
			
			if(hasSpeed== head.length) {
				setSpeed(0);
			}
			else {
				setSpeed( Integer.parseInt(line[hasSpeed]));
			}
			
			if(hasRadius== head.length) {
				setRadius(0);
			}
			else {
				setRadius( Integer.parseInt(line[hasRadius]));
			}
			if(hasAlt== head.length) {
				setAlt(0);
			}
			else {
				setAlt( Integer.parseInt(line[hasAlt]));
			}
			
		}
	}
	
	public PackmanMetaData(PackmanMetaData data) {
		setAlt(data.getAlt());
		setId(data.getId());
		setRadius(data.getRadius());
		setSpeed(data.getSpeed());
		setGrade(data.grade);
		Iterator<Fruit> it=data.getEat().iterator();
		while(it.hasNext()) {
			getEat().add(it.next());
		}
		setTime(data.getTime());
		setTimeNext(data.getTimeNext());
	}

	

	public String toString() {
		String ans="";
		Iterator <Fruit> it= getEat().iterator();
		while(it.hasNext()) {
			ans= ans+it.next().getDataF().getId()+",";
		}
		return "Alt: "+getAlt()+", Weight/Grade: "+getGrade()+", Id: "+ getId()+", Time: "+getTimeNext()/60+", Start: "+getTime()/60+";"+
				", Speed: "+ getSpeed()+", Radius: "+ getRadius()+", ate: "+ans;
	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return (long) (getTimeNext()*1000);
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	private int serch(String[] head,String s) {
		int index=head.length;
		for (int i=0; i<head.length; i++) {
			if(s.equals(head[i])||head[i].contains(s)) {
				index= i;
				return index;
			}
		}


		return index;
	}
	
	public boolean equals(Meta_data e) {
		return (getUTC()==e.getUTC())&&(toString().substring(toString().indexOf("Id: ")+4, toString().indexOf(", Time:")))
				.equals(e.toString().substring(e.toString().indexOf("Id: ")+4, e.toString().indexOf(", Time:")));
	}

	public int getId() {
		return id;
	}

	public int getAlt() {
		return alt;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRadius() {
		return radius;
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setAlt(int alt) {
		this.alt = alt;
	}

	private void setSpeed(int speed) {
		this.speed = speed;
	}

	private void setRadius(int radius) {
		this.radius = radius;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGrade() {
		return grade;
	}
	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public ArrayList<Fruit> getEat() {
		return eat;
	}


	public double getTimeNext() {
		return timeNext;
	}



	public void setTimeNext(double timeNext) {
		this.timeNext = timeNext;
	}
	

}
