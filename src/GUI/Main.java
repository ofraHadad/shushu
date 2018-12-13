package GUI;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(window.getGame().getMap().getMyImage().getWidth(),window.getGame().getMap().getMyImage().getHeight());

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
