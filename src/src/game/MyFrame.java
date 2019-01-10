package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import Coords.MyCoords;
import Geom.Gps_Point;
import Geom.Pixel;
import Geom.Vector;
import Robot.GameBoard;
import Robot.Play;



/**
 *a gui that represent the game.
 * ***does not support a resize ability yet*** 
 * @author ofra and shira
 */
public class MyFrame extends JFrame implements MouseListener{

	private Map map;
	private Container window;
	private JPanel _panel;
	private Graphics _paper;
	private int isGamer;
	private Game game;
	private int id=1;
	private boolean stop;
	public GameBoard v;
	private Player me;
	private Path path;
	private Play play1;
	private boolean isRun;
	private MenuBar menuBar;
	public static Image scaledImage;
	int i=0;
	private double oldAngel;
	MyCoords c=new MyCoords();

	public MyFrame(Map map){

		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
		//	Moves and resizes this component. 
		//	The new location of the top-left corner is  specified by x and y, 
		//	and the new size is specified by width and height
		//	setBounds(x,y,width,height)
		setMap(map);

		//      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		game=new Game(map);
		this.setBounds(0,0,1300,600); //setSize(1433,700);        // "super" Frame sets its initial window size
		createGui();
		setVisible(true);
		setResizable(false);

	}


	public void createGui(){ 

		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());


		//Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(getWidth(),getHeight());
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		// the menu-bar
		Menu menu, menu2,kml,csv,clearGame;         // each menu in the menu-bar
		MenuItem fruit,run,save,clear,algo; // an item in a menu
		menuBar = new MenuBar();

		clearGame= new Menu("clear game");
		menuBar.add(clearGame);
		clear= new MenuItem("clear");
		clearGame.add(clear);
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				game= new Game(getMap());
				me=null;
			}
		});



		csv= new Menu("saved games");
		menuBar.add(csv);

		Iterator<String> it=read("data","csv","Ex4_OOP_example").iterator();
		while(it.hasNext()) {
			String s= it.next();
			MenuItem m= new MenuItem(s);
			csv.add(m);
			m.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					repaint();
					play1 = new Play(s);
					ArrayList<String> board_data = play1.getBoard();

					game= new Game(board_data,getMap());


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
		run= new MenuItem("Play yourself");
		menu2.add(run);
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				isGamer=3;
				isRun=true;

			}
		});
		algo= new MenuItem("algoRun");
		menu2.add(algo);
		algo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				isGamer=6;
				isRun=true;
				
			}
		});

		fruit = new MenuItem("player");
		menu.add(fruit); // the menu adds this item
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 1;
			}
		});
		setMenuBar(menuBar);  // "this" JFrame sets its menu-bar
		JLabel labelMap = new JLabel();
		//labelMap.setIcon(imgBck);
		_panel.add(labelMap);

		// panel (source) fires the MouseEvent.
		//panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
	}

	protected void paintElement() {
		//	The method getGraphics is called to obtain a Graphics object
		repaint();
		_paper = _panel.getGraphics();

		Iterator<Box> boxes= game.getBoxes().iterator();
		Iterator<Ghost> ghosts= game.getGhosts().iterator();
		Iterator<Fruit> fruits= game.getFruits().iterator();

		Iterator<Packman> packmans= game.getPackmans().iterator();

		while(boxes.hasNext()) {
			_paper.setColor(Color.BLACK);
			Box b=boxes.next();
			_paper.fillRect(b.getLocation().getX(), b.getLocation().getY(), b.getWidth(), b.getHetigh());
		}
		while(packmans.hasNext()) {
			_paper.setColor(Color.yellow);
			Packman p=packmans.next();
			_paper.fillOval(p.getLocation().getX()-10,p.getLocation().getY()-10,20,20);

		}
		while(fruits.hasNext()) {
			_paper.setColor(Color.RED);
			Fruit f=fruits.next();
			_paper.fillOval(f.getLocation().getX()-5,f.getLocation().getY()-5,10,10);

		}
		while(ghosts.hasNext()) {
			_paper.setColor(Color.GREEN);
			Ghost g=ghosts.next();
			_paper.fillOval(g.getLocation().getX()-13,g.getLocation().getY()-13,26,26);
		}

		if(game.getMe()!=null) {
			//			if(isGamer==1) {
			//			_paper.setColor(Color.PINK);
			//			_paper.fillOval(me.getLocation().getX()-15,me.getLocation().getY()-15,30,30);
			//			i++;
			//			}
			//			else {
			_paper.setColor(Color.PINK);
			_paper.fillOval(game.getMe().getLocation().getX()-15,game.getMe().getLocation().getY()-15,30,30);
			//}
		}
	}





	public void paint(Graphics g) {
		//		scaledImage = map.getMyImage().getScaledInstance(this.getWidth()-20,this.getHeight()-60,Image.SCALE_SMOOTH);
		//		g.drawImage(scaledImage, 10, 50, this);

		Image temp=getMap().getMyImage().getScaledInstance(this.getWidth()-5, this.getHeight()-50,getMap().getMyImage().SCALE_SMOOTH);
		g.drawImage(temp, 0, 45,null);

		//		


		paintElement();

	}


	@Override
	public void mousePressed(MouseEvent event) {
		if(isGamer==1&&!stop ) {
			game.setMe( new Player(event.getX(), event.getY(),map));
			play1.setInitLocation( game.getMe().getLocationGPS().get_y(),game.getMe().getLocationGPS().get_x());

		}
		if(isGamer==3) {
			Pixel pressed= new Pixel(event.getX(),event.getY());
			String s= play1.getBoundingBox();
			String []arr= s.split(",");
			Gps_Point up= new Gps_Point(Double.parseDouble(arr[6]),Double.parseDouble(arr[2]),0);
			Gps_Point down= new Gps_Point(Double.parseDouble(arr[3]), Double.parseDouble(arr[5]),0);


			play1.start();

			play1.rotate((360-c.azimuth( getMap().convertePixelToGps(pressed),game.getMe().getLocationGPS())-90)%360);
			ArrayList<String> board_data = play1.getBoard();
			
			game.readArrayList(board_data);



			String info = play1.getStatistics();
			System.out.println(info);
		}

		if(isGamer == 6) {
			Algo a= new Algo(play1,game);
			a.algo();
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
	private void setMap(Map map) {
		this.map = map ;
	}
	public Container getWindow() {
		return window;
	}
	private void setWindow(Container window) {
		this.window = window;
	}
	public JPanel get_panel() {
		return _panel;
	}
	private void set_panel(JPanel _panel) {
		this._panel = _panel;
	}
	public Graphics get_paper() {
		return _paper;
	}
	private void set_paper(Graphics _paper) {
		this._paper = _paper;
	}

	public int getIsGamer() {
		return isGamer;
	}
	private void setIsGamer(int isGamer) {
		this.isGamer = isGamer;
	}
	public Game getGame() {
		return game;
	}

	private  ArrayList<String> read(String parentDirectory,String type, String startWith){
		File[] filesInDirectory = new File(parentDirectory).listFiles();
		ArrayList<String> s= new ArrayList<String>();
		for(File f : filesInDirectory){
			if(f.isDirectory()){
				read(f.getAbsolutePath(),type,startWith);
			}
			String filePath = f.getAbsolutePath();
			String fileExtenstion = filePath.substring(filePath.lastIndexOf(".") + 1,filePath.length());

			if(type.equals(fileExtenstion) &&filePath.contains(startWith)){
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


	public Path getPath() {
		return path;
	}




}