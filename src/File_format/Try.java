package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.Packman;
import game.Map;

public class Try {

/*	public GIS_layer read(String csvFile,int firstLine, GIS_layer game) {

		String line = "";
		String cvsSplitBy = ",";
		int column=0;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			int count =firstLine;

			while (count==firstLine&&(line = br.readLine()) != null) 
			{
				String[] head= line.split(cvsSplitBy);
				column= head.length;
				count++;


			}
		}

		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] head= new String [column];

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			int count =firstLine;

			while ((line = br.readLine()) != null) 
			{	
				if(count== firstLine) {
					head = line.split(cvsSplitBy);

				}


				if(count>firstLine) {
					String[] userInfo = line.split(cvsSplitBy);
					addElements(game, head, userInfo);
					/*int isValid= serch(head,"Type");
					if(isValid>=head.length) {
						throw new RuntimeException("invalid input");
					}
					int isPackman= serch(userInfo,"P");
					if(isPackman<userInfo.length) {
						game.getPackmans().add(new Packman(userInfo,head,map));

					}
					else {
						int isFruit= serch(userInfo, "F");
						if(isFruit<userInfo.length) {
							game.getFruits().add(new Fruit(userInfo,head,map));
						}
						else {
							throw new RuntimeException("the type of the point is not in the file");
						}*/

		/*		}
			}
			count++;
		}

		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return game;

	} 



	private int serch(String[] head,String s) {
		int index=head.length;
		for (int i=0; i<head.length; i++) {
			if(s.equals(head[i])||s.contentEquals(head[i])) {
				index= i;
				return index;
			}
		}


		return index;
	}

	public void addElements(GIS_layer game, String[] head, String[] userInfo) {
		int isValid= serch(head,"Type");
		if(isValid>=head.length) {
			throw new RuntimeException("invalid input");
		}
		int isPackman= serch(userInfo,"P");
		if(isPackman<userInfo.length) {
			game.getPackmans().add(new Packman(userInfo,head,game.getMap()));

		}
		else {
			int isFruit= serch(userInfo, "F");
			if(isFruit<userInfo.length) {
				game.getFruits().add(new Fruit(userInfo,head,game.getMap()));
			}
			else {
				throw new RuntimeException("the type of the point is not in the file");
			}
		}
	}*/
}
