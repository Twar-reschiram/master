package game;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Data.Location;
import Data.Components.CTextInput;
import Engine.Engine;
import files.Files;
import game.dev.mapEditor.MapEditor;
import game.map.MapLoader;
import sprites.Sprites;
import Data.Image.Image;

public class GameManager {
	
	ArrayList<Image> Images = new ArrayList<>();
	CTextInput t;
	
	public GameManager(){
		new Engine(1920, 1080, new Dimension(1000,1000));
		this.setKillAble();
		new TickManager(this);
		new MapEditor(new MapLoader(200, 200, Files.MAP_TEST.getFile()));
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
