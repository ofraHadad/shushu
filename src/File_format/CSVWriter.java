package File_format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Fruit;
import GIS.Packman;
import Geom.Gps_Point;
import game.Game;

public class CSVWriter 
{
	
	public  void gameToCSV(String fileName,Game game) {
		FileWriter writer;
		try {
			writer = new FileWriter(fileName+".csv");
			BufferedWriter bw = new BufferedWriter(writer);
			StringBuilder sb = new StringBuilder();
			
			sb.append("Type,id,Lat,Lon,Alt,Speed/Weight,Raius,"+game.getPackmans().size()+","+game.getFruits().size()+"\n");
			Iterator<Packman> packmans= game.getPackmans().iterator();
			Iterator<Fruit> fruits= game.getFruits().iterator();
			while(packmans.hasNext()) {
				Packman p= packmans.next();
				Gps_Point location= game.getMap().convertePixelToGps(p.getLocation());
				sb.append("P,"+p.getDataP().getId()+","+location.get_y()+","+location.get_x()+
						","+ p.getDataP().getAlt()+","+p.getDataP().getSpeed()+","+p.getDataP().getRadius()+"\n");
			}
			while(fruits.hasNext()) {
				Fruit f= fruits.next();
				Gps_Point location= game.getMap().convertePixelToGps(f.getLocation());
				sb.append("F,"+f.getDataF().getId()+","+location.get_y()+","+location.get_x()+
						","+ f.getDataF().getAlt()+","+f.getDataF().getWeight()+"\n");
			}
		

			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}




