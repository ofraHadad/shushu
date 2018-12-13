package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import File_format.CSV_handling;
import game.Map;

public class Game implements GIS_layer {

	public ArrayList<Fruit> fruits=new ArrayList<>();
	public ArrayList<Packman> packmans=new ArrayList<>();
	private Map map;


	public Game(Map map) {
		this.map= map; 
	}
	public Game(String csvFile, Map map) {
		this.map= map;
		CSV_handling c= new CSV_handling();
		c.read(csvFile, 0, map, this);
	}

	public String toString() {
		String ans="";
		Iterator itFruits= getFruits().iterator();
		Iterator itPackmans= getPackmans().iterator();
		while(itPackmans.hasNext()) {
			ans= ans+"Packman: "+itPackmans.next().toString()+"\n";
		}
		while(itFruits.hasNext()) {
			ans= ans+"Fruit: "+itFruits.next().toString()+"\n";
		}
		return ans;
	}



	public ArrayList<Fruit> getFruits() {
		return fruits;
	}
	public ArrayList<Packman> getPackmans() {
		return packmans;
	}
	


	public Map getMap() {
		return map;
	}

	@Override
	public boolean add(GIS_element e) {
if (e.whatAmI()==1) {
	getFruits().add((Fruit) e);
}
if(e.whatAmI()==2) {
	getPackmans().add((Packman) e);
}
		
	return false;
	}


	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int whatAmI() {
		// TODO Auto-generated method stub
		return 4;
	}
}
