
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.management.loading.PrivateClassLoader;
import javax.swing.*;

import File_format.CSVWriter;
import File_format.CsvToKml;
import File_format.DirectoryToKml;
import GIS.Fruit;
import GIS.GIS_layer;
import GIS.Game;
import GIS.MyGisLayer;
import GIS.MyGisProject;
import GIS.Packman;
import GIS.Path;
import GIS.ProjectMetaData;
import game.Map;
import game.ShortestPathAlgo;


/**
 *
 * @author annaf
 */
public class MyFrame extends JFrame implements MouseListener {

	public Map map=new Map();
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int isGamer;
	private Game game;
	private int id=1;
	private boolean stop;
	private int gameNum=1;
	private Path path;
	private int pathNum=1;
	private boolean isRun;

	public MyFrame(){
		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		this.setBounds(0,0,1433,700); //setSize(1433,700);        // "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		game=new Game(map);

	}


	public void createGui(){ 

		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		MenuBar menuBar;   // the menu-bar
		Menu menu, data, menu2;         // each menu in the menu-bar
		MenuItem fruit,run, packman,save,saveKML; // an item in a menu
		menuBar = new MenuBar();
		data=new Menu("data");
		menuBar.add(data);
		Iterator<String> it=read("data").iterator();
		while(it.hasNext()) {
			String s= it.next();
			MenuItem m= new MenuItem(s);
			data.add(m);
			m.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();

					game= new Game(s,map);
					isGamer=4;
					//stop=true;

				}
			});
		}
		
		
		// First Menu
		menu = new Menu("Play");
		//menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu
		
		
		menu2= new Menu("Run");
		menuBar.add(menu2);
		run= new MenuItem("Run");
		menu2.add(run);
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				isGamer=3;
				isRun=true;

			}
		});

		saveKML= new MenuItem("Save path");
		menu2.add(saveKML);
		saveKML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				isGamer=6;
				DirectoryToKml d= new DirectoryToKml();
				if(isRun==true) {
				d.multyKmlFile(getPath(), "Path_"+pathNum++);
				}
				else {
					System.out.println("please enter Run befor");
				}
			}
		});
		fruit = new MenuItem("fruit");
		menu.add(fruit); // the menu adds this item
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 1;
			}
		});
		packman = new MenuItem("packman");
		menu.add(packman); // the menu adds this item
		packman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 2;
			}
		}); 
		
		save= new MenuItem("Save game");
		menu.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	
				isGamer=5;

				CSVWriter c= new CSVWriter();
				c.gameToCSV("game"+gameNum++,game);
				
			}
		});
		

		setMenuBar(menuBar);  // "this" JFrame sets its menu-bar
		JLabel labelMap = new JLabel();
		//labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);

		map= new Map();
	}

	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object

		_paper = _panel.getGraphics();
		if(isGamer!=3) {
			Iterator<Fruit> fruits= game.getFruits().iterator();
			Iterator<Packman> packmans= game.getPackmans().iterator();
			while(packmans.hasNext()) {
				_paper.setColor(Color.yellow);
				Packman p=packmans.next();
				_paper.fillOval(p.getLocation().getX()-10,p.getLocation().getY()-10,20,20);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
				_paper.drawString("("+Integer.toString(p.getLocation().getX())+", "+Integer.toString(p.getLocation().getY())+")"
						,p.getLocation().getX(),p.getLocation().getY()-10);
			}
			while(fruits.hasNext()) {
				_paper.setColor(Color.RED);
				Fruit f=fruits.next();
				_paper.fillOval(f.getFruit().getX()-5,f.getFruit().getY()-5,10,10);
				_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
				_paper.drawString("("+Integer.toString(f.getFruit().getX())+", "+Integer.toString(f.getFruit().getY())+")"
						,f.getFruit().getX(),f.getFruit().getY()-10);
			}
		}
		if(isGamer==3) {
			ShortestPathAlgo algo= new ShortestPathAlgo(game);
			algo.bestPathCalculation();
			path= algo.forGPS();
			Iterator<Packman> packmans= game.getPackmans().iterator();
			while(packmans.hasNext()) {
				_paper.setColor(Color.yellow);
				Packman p= new Packman(packmans.next());
			//	Thread t= new Thread(p);
			//	t.run();
				_paper.fillOval(p.getLocation().getX()-10,p.getLocation().getY()-10,20,20);

				Iterator<Fruit> fruits= p.getEat().iterator();
				while(fruits.hasNext()) {
					_paper.setColor(Color.RED);
					Fruit f=fruits.next();
					_paper.fillOval(f.getFruit().getX()-5,f.getFruit().getY()-5,10,10);

					if(fruits.hasNext()) {
						_paper.setColor(Color.BLACK);
						_paper.drawLine(p.getLocation().getX(), p.getLocation().getY(), f.getFruit().getX(), f.getFruit().getY());
					}
				}
			}
		}

	}


	public void paint(Graphics g) {
		g.clearRect(0, 0, map.getMyImage().getWidth(), map.getMyImage().getHeight());

		g.drawImage(map.getMyImage(), 0, 0, this);
		paintElement();
	}


	@Override
	public void mousePressed(MouseEvent event) {
		if(isGamer==1&&!stop ) {
			Fruit f= new Fruit(event.getX(), event.getY(),map,id++);
			game.getFruits().add(f);
		}
		if(isGamer==2&&!stop) {
			Packman p= new Packman(event.getX(),event.getY(),map,id++);
			game.getPackmans().add(p);
		}

		repaint();


	}
	// Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}

	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Container getWindow() {
		return window;
	}
	public void setWindow(Container window) {
		this.window = window;
	}
	public JPanel get_panel() {
		return _panel;
	}
	public void set_panel(JPanel _panel) {
		this._panel = _panel;
	}
	public Graphics get_paper() {
		return _paper;
	}
	public void set_paper(Graphics _paper) {
		this._paper = _paper;
	}

	public int getIsGamer() {
		return isGamer;
	}
	public void setIsGamer(int isGamer) {
		this.isGamer = isGamer;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	private  ArrayList<String> read(String parentDirectory){
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		ArrayList<String> s= new ArrayList<String>();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				read(f.getAbsolutePath());
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());
			//String fileStart= filePath.substring(0,4);

			if("csv".equals(fileExtenstion) &&filePath.contains("game")){
				//add to a list or array

				s.add(filePath);
			}

		} 

		return s;
	}


	public int getId() {
		return id;
	}


	public boolean isStop() {
		return stop;
	}


	public int getGameNum() {
		return gameNum;
	}


	public Path getPath() {
		return path;
	}


	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setBounds(0, 0, frame.getGame().getMap().getMyImage().getWidth(), frame.getGame().getMap().getMyImage().getHeight());
		frame.createGui();

		frame.setVisible(true);



	}
}
