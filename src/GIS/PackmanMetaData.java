package GIS;

import Geom.Point3D;

public class PackmanMetaData implements Meta_data{
	private int id;
	private int alt;
	private int speed;
	private int radius;
	private int grade=0;

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
	}

	

	public String toString() {
		return "Alt: "+getAlt()+ ", Speed: "+ getSpeed()+", Radius: "+ getRadius()+", id: "+ getId()+", Grade:"+getGrade();
	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
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


}
