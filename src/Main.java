


import game.Map;
import game.MyFrame;


public class Main {

	public static void main(String[] args) {
		MyFrame frame = new MyFrame(new Map());
		frame.setBounds(0, 0, frame.getGame().getMap().getMyImage().getWidth(), frame.getGame().getMap().getMyImage().getHeight());
		frame.createGui();
		frame.setVisible(true);
//
//		frame.setResizable(true);


	}

}
