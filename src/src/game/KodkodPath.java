package game;

import java.util.ArrayList;

public class KodkodPath {
	
		public ArrayList<Kodkod> path=new ArrayList();
		private double dis;

		public ArrayList<Kodkod> getPath() {
			return path;
		}

		public double getDis() {
			return dis;
		}

		public void setDis(double dis) {
			this.dis = dis;
		}

	}

