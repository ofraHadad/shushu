package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MainWindow extends JFrame implements MouseListener
{
	public Map map;

	int isfruite;


	public MainWindow() 
	{
		map= new Map();
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		JMenuBar menuBar;   // the menu-bar
		JMenu menu;         // each menu in the menu-bar
		JMenuItem fruit, packman; // an item in a menu

		menuBar = new JMenuBar();
		// First Menu
		menu = new JMenu("type");
		menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu

		fruit = new JMenuItem("fruit", KeyEvent.VK_F);
		menu.add(fruit); 
		
		

		//this.setJMenuBar(menuBar);
		fruit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isfruite=1;
			}
		});
		packman = new JMenuItem("packman", KeyEvent.VK_F);
		menu.add(packman);
		packman.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				isfruite = 2;
			}
		});
		this.setJMenuBar(menuBar);

	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{

		g.drawImage(map.getMyImage(), 0, 0, this);
		if(isfruite==1) {
			if(x!=-1 && y!=-1)
			{
				g.setColor(Color.RED);
				int r = 10;
				x = x - (r / 2);
				y = y - (r / 2);
				g.fillOval(x, y, r, r);
			}
		}
		if(isfruite==2) {
			g.setColor(Color.YELLOW);

			int r = 20;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {


	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}