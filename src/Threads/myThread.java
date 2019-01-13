package Threads;

import game.Game;
import game.Graph;
import game.MyFrame;

public class myThread extends Thread{

		MyFrame mf;
		Game game;
		public myThread(Game g ,MyFrame mf) {
		this.mf =mf;
		this.game=g;
		}

		@Override
		public void run() {
                System.out.println(1);
				mf.repaint();
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
