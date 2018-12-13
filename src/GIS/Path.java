package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Path implements  GIS_project{

	ArrayList<GIS_layer> path= new ArrayList<GIS_layer>();
	@Override
	public boolean add(GIS_layer e) {
		path.add(e);
		return false;
	}
	public String toString() {
		Iterator<GIS_layer> it= path.iterator();
		String ans="";
		while(it.hasNext()) {
			ans=ans+it.next()+"\n";
		}
		return ans;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
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
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return path.iterator();
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

}
