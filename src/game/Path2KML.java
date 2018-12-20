package game;

import java.io.File;

import File_format.DirectoryToKml;

public class Path2KML {
	
	public  Path2KML(Path path, String file) {
		DirectoryToKml d= new DirectoryToKml();
		d.multyKmlFile(path, file);

	}
}
