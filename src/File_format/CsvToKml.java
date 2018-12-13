package File_format;
import java.util.Iterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import GIS.ElementMetaData;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Game;
import GIS.GameGPS;
import GIS.LayerMetaData;
import GIS.Meta_data;
import GIS.MyGisElement;
import GIS.MyGisLayer;
import GIS.Packman;

/**
 * this class read a csv file of gps points 
 * and generate it to one kml file
 * @author ofra&shira
 *
 */
public class CsvToKml {


/**
 * read the file and update a layer= array list of elments
 * ,whit the data in the file;
 * @param csvFile
 * @return
 */
	public MyGisLayer read(String csvFile) {

		String firstLine="";
		String line = "";
		String cvsSplitBy = ",";
		MyGisLayer layer= new MyGisLayer();
		String[] head= new String [11];



		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			int count =0;
			
			while ((line = br.readLine()) != null) 
			{	
				
				if(count== 1) {
					head = line.split(cvsSplitBy);

				}


				if(count>1) {
					String[] userInfo = line.split(cvsSplitBy);
					layer.add(new MyGisElement(userInfo,head));
				}
				count++;
			}

			Iterator<GIS_element> it= layer.iterator();
			layer.setMetaData(new LayerMetaData((MyGisElement)it.next()));


		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return layer;
	}

	/**
	 * This function takes a csv file, reads it and transform it into a kml file
	 * @param csvfile1 which is the csv first file address
	 * @param csvfile2 which is the csv second file address
	 */
	public  void KmlWriter(GIS_layer layer,String name)
	{
		try 
		{

			FileWriter writer = new FileWriter(name+".kml");
			BufferedWriter bw = new BufferedWriter(writer);
			StringBuilder sb = new StringBuilder();
			CsvToKml r= new CsvToKml();
		//	GIS_layer layer=r.read(csvfile);
			//create kml file	



			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"blue\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
					+ "</Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");

			sb.append(content(layer));


			sb.append("\n</Folder>\n");
			sb.append("</Document></kml>");
			
			bw.write(sb.toString());
			bw.close();



		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public String content(GIS_layer layer) {
		Iterator<GIS_element> itLayer= layer.iterator();
		String ans="";
		while(itLayer.hasNext()) {
			MyGisElement e= (MyGisElement) itLayer.next();
			ans=ans+("<Placemark>\n");
		//	ans=ans+(" <TimeStamp> \n <when> "+ e.getMetaData().getFirstSeen()+" </when \n"+"</TimeStamp> \n");
			ans=ans+("<name>"+"<![CDATA["+e.getMetaData().getSSID()+"]]>"  +"</name>\n");
			ans=ans+("<description>"+"<![CDATA[BSSID: <b>"+e.getMetaData().getMAC()+"</b><br/>Capabilities: <b>"
					+e.getMetaData().getAuthMode()+"</b><br/>Channel: <b>"+e.getMetaData().getChannel()+
					"</b><br/>RSSI: <b>"+e.getMetaData().getRSSI()+"</b><br/>AccuracyMeters: <b>"
					+e.getMetaData().getAccuracyMeters()+"</b><br/>Type: <b>"+e.getMetaData().getType()+
					"</b><br/>Date: <b>"+e.getMetaData().getFirstSeen()+"</b>]]>"+"</description><styleUrl>#blue</styleUrl>\n");
			ans=ans+("<Point>\n");
			ans=ans+("<coordinates>"+e.getGps().get_x()+","+e.getGps().get_y()+","+e.getGps().get_z()+"</coordinates></Point>\n");
			ans=ans+("</Placemark>\n");

		}
		return ans;


	}
	
	public String contentPath(GIS_layer layer) {
		Iterator<GIS_element> itLayer= layer.iterator();
		String ans="";
		int count=0;
		while(itLayer.hasNext()) {
		
			GameGPS e= new GameGPS( (GameGPS)itLayer.next()) ;

			ans=ans+("<Placemark>\n");
		//	ans=ans+(" <TimeStamp> \n <when> "+ e.getMetaData().getFirstSeen()+" </when \n"+"</TimeStamp> \n");
			ans=ans+("<name>"+"<![CDATA["+e.getDataP().getId()+"]]>"  +"</name>\n");
			ans=ans+("<description>"+"<![CDATA[Grade: <b>"+e.getDataP().getGrade()+"</b><br/>Timestamp: <when>"+
					e.getUTC()+"</when>"+"<br/>Date: <b>2017-12-01 10:44:05</b>]]></description><styleUrl>#yellow</styleUrl>\n");
			ans=ans+("<Point>\n");
			ans=ans+("<coordinates>"+e.getLocation().get_x()+","+e.getLocation().get_y()+"</coordinates></Point>\n");
			ans=ans+("</Placemark>\n");
			

		}
		return ans;
	}

}
