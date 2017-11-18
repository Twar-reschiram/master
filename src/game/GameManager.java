package game;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Engine.Engine;
import data.Mouse;
import files.Files;
import game.dev.mapEditor.MapEditor;
import game.map.MapLoader;
import game.tick.TickManager;

public class GameManager {
	
	MapEditor mapEditor;
	Player p;
	
	public GameManager(){
		new Engine(1920, 1080, new Dimension(1000,1000));
		this.setKillAble();
		new TickManager(this);
		mapEditor = new MapEditor(new MapLoader(200, 200, Files.MAP_TEST.getFile()));
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 3);
		new Mouse(3);
		p = new Player();
	}
	
	public void tick(){
		if(mapEditor!=null)mapEditor.tick();
		if(Mouse.getMouse()!=null)Mouse.getMouse().tick();
		if(p!=null)p.tick();
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
