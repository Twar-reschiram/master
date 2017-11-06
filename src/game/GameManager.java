package game;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import Data.Components.CTextInput;
import Engine.Engine;
import Data.Image.Image;

public class GameManager {
	
	ArrayList<Image> Images = new ArrayList<>();
	CTextInput t;
	
	public GameManager(){
		new Engine(800, 680, new Dimension(800,800));
		this.setKillAble();
		new TickManager(this);
	}

	public void tick() {
	}
	
	private void setKillAble(){
		Engine.getInputManager().addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		});
	}

}
