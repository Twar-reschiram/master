package game;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Engine.Engine;
import anim.AnimationType;
import data.Mouse;
import data.Resources;
import files.Files;
import game.dev.mapEditor.MapEditor;
import game.map.Map;
import game.map.MapLoader;
import game.tick.TickManager;
import sprites.Sprites;

public class GameManager {
	
	MapEditor mapEditor;
	Player p;
	
	public GameManager(){
		Sprites.create();
		AnimationType.create();
		Resources.create();
		new Engine(1920, 1080, new Dimension(1920,1080));
		this.setKillAble();
		new TickManager(this);
		mapEditor = new MapEditor(new MapLoader(Files.MAP_TEST.getFile()));
		//Map map = new MapLoader(Files.MAP_TEST.getFile()).getMap();
		Engine.getEngine(this, this.getClass()).addLayer(false, false, false, 6);
		new Mouse(6);
		p = new Player(mapEditor.getMap());
	}
	
	double lasttime = System.currentTimeMillis();
	public void tick(){
		if(mapEditor!=null)mapEditor.tick();
		if(Mouse.getMouse()!=null)Mouse.getMouse().tick();
		if(p!=null)p.tick();
//		if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_E)){
//			double time = System.currentTimeMillis()-lasttime;
//			if(time>=500){
//				lasttime = System.currentTimeMillis();
//				Resources explosionRes = Resources.Explosion;
//				explosionRes.getAnimationType().newAnimation(true, 4, new Image(new Location(Engine.getInputManager().MousePosition()), new Dimension(40, 40), "", explosionRes.getSprites().getSpriteSheet(), null), explosionRes).start();
//			}
//		}
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
