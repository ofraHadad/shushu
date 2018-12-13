package game;

import javax.swing.JFrame;


public class Main 
{
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
		window.setSize(window.map.getMyImage().getWidth(),window.map.getMyImage().getHeight());

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
