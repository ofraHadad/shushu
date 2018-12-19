package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Geom_element;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Point3D;

public class Map {

	private BufferedImage myImage;
	private String imageName;
	private Gps_Point gpsPoint1;
	private Pixel pixelPoint1;
	private Gps_Point gpsPoint2;
	private Pixel pixelPoint2;

	/**
	 * the default constructor.
	 * builds the map at Ariel University. 
	 */
	public Map() {
		imageName="Ariel1.jpg";
		setMyImage(getImageName());
		this.gpsPoint1= new Gps_Point(35.20691,32.10535,0);
		this.pixelPoint1= new Pixel(651,61);
		this.gpsPoint2= new Gps_Point(35.20703,32.10293,0);
		this.pixelPoint2= new Pixel(668,466);

	}

	/**
	 * 
	 * @param jpgName
	 * @param gpsPoint1
	 * @param pixelPoint1
	 * @param gpsPoint2
	 * @param pixelPoint2
	 */
	public Map(String ImageName,  Gps_Point gpsPoint1, Pixel pixelPoint1, Gps_Point gpsPoint2, Pixel pixelPoint2) {
		this.imageName= ImageName;
		setMyImage(ImageName);
		this.gpsPoint1= new Gps_Point(gpsPoint1);
		this.pixelPoint1= new Pixel(pixelPoint1);
		this.gpsPoint2= new Gps_Point(gpsPoint2);
		this.pixelPoint2= new Pixel(pixelPoint2);
	}

	public Map(Map map) {
		this.imageName= map.getImageName();
		setMyImage(map.getImageName());
		this.gpsPoint1= new Gps_Point(map.getGpsPoint1());
		this.pixelPoint1= new Pixel(map.getPixelPoint1());
		this.gpsPoint2= new Gps_Point(map.getGpsPoint2());
		this.pixelPoint2= new Pixel(map.getPixelPoint2());
	}

	public Pixel  converteGpsToPixel(Gps_Point gps) {
		double gpsDisX= Math.abs(getGpsPoint1().get_x()-gpsPoint2.get_x());
		double pixelDisX= Math.abs(getPixelPoint1().getX()-getPixelPoint2().getX());
		double gpsDisY= Math.abs(getGpsPoint1().get_y()-gpsPoint2.get_y());		
		double pixelDisY= Math.abs(getPixelPoint1().getY()-getPixelPoint2().getY());
		double currentJumpX= gps.get_x()-getGpsPoint1().get_x();
		double currentJumpY= gps.get_y()-getGpsPoint1().get_y();

		int width=getMyImage().getWidth();
		int height = getMyImage().getHeight();

		Pixel pixel= new Pixel((int) (getPixelPoint1().getX()+((currentJumpX/gpsDisX)*pixelDisX))//חייב להיות שלם?
				,(int)(getPixelPoint1().getY()-((currentJumpY/gpsDisY)*pixelDisY)));
		if(pixel.getX()<0|| pixel.getX()>width || pixel.getY()<0 || pixel.getY()>height) {
			throw new RuntimeException("the point is not in the image");
		}
		return pixel;
	}

	public Gps_Point  convertePixelToGps(Pixel pixel) {
		double gpsDisX= Math.abs(getGpsPoint1().get_x()-gpsPoint2.get_x());
		double pixelDisX= Math.abs(getPixelPoint1().getX()-getPixelPoint2().getX());
		double gpsDisY= Math.abs(getGpsPoint1().get_y()-gpsPoint2.get_y());		
		double pixelDisY= Math.abs(getPixelPoint1().getY()-getPixelPoint2().getY());
		double currentJumpX= pixel.getX()-getPixelPoint1().getX();
		double currentJumpY= pixel.getY()-getPixelPoint1().getY();

		Gps_Point gps= new Gps_Point(((currentJumpX/pixelDisX)*gpsDisX)+getGpsPoint1().get_x(),
				getGpsPoint1().get_y()-((currentJumpY/pixelDisY)*gpsDisY),0);

		return gps;
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	private void setMyImage(String jpgName) {
		try {
			this.myImage = ImageIO.read(new File(jpgName));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public Gps_Point getGpsPoint1() {
		return gpsPoint1;
	}

	public Pixel getPixelPoint1() {
		return pixelPoint1;
	}

	public Gps_Point getGpsPoint2() {
		return gpsPoint2;
	}


	public Pixel getPixelPoint2() {
		return pixelPoint2;
	}

	public String getImageName() {
		return imageName;
	}

}
