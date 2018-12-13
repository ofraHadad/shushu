package GIS;

import Geom.Point3D;

public class FruitMetaData implements Meta_data{

	private int alt;
	private int weight;
	private int id;


	public FruitMetaData(int id) {
		setAlt(0);
		setWeight(1);
		setId(id);
	}

	public FruitMetaData(String[] head, String [] line) {

		int hasWeight= serch(head,"Weight");
		int hasAlt= serch(head,"Alt");
		int hasId= serch(head,"id");

		if(hasId==head.length) {
			throw new RuntimeException("EROR: the fruit has no id");
		}
		else {
			setId(Integer.parseInt(line[hasId]));
			if(hasWeight== head.length) {
				setWeight(0);
			}
			else {
				setWeight(Integer.parseInt(line[hasWeight]));
			}
			if(hasAlt == head.length) {
				setAlt(0);
			}
			else {
				setAlt(Integer.parseInt(line[hasAlt]));
			} 
		}

	}

	public FruitMetaData(FruitMetaData data) {
		setAlt(data.getAlt());
		setId(data.getId());
		setWeight(data.getWeight());
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


	public String toString() {
		return "Alt: "+getAlt()+ ", Weight: "+getWeight()+ ", Id: "+ getId();

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

	private void setAlt(int alt) {
		this.alt = alt;
	}

	private void setWeight(int weight) {
		this.weight = weight;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getAlt() {
		return alt;
	}

	public int getWeight() {
		return weight;
	}

	public int getId() {
		return id;
	}

}
