package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Data.Location;
import Data.Components.CTextInput;
import Engine.Engine;
import files.Files;
import sprites.Sprites;
import Data.Image.Image;
import Data.Image.SpriteSheet;

public class GameManager {
	
	ArrayList<Image> Images = new ArrayList<>();
	CTextInput t;
	
	public GameManager(){
		new Engine(800, 680, new Dimension(800,800));
		this.setKillAble();
		this.createImages();
		this.addMovement();
		new TickManager(this);
		System.out.println(Files.TEST.getFile().getSubkey("")[0]);
	}

	private void addMovement() {
		Engine.getInputManager().addKeyLister(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}		
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A){
					Engine.getEngine(this, this.getClass()).move(-10, 0, 1);
				} else if(e.getKeyCode()==KeyEvent.VK_D){
					Engine.getEngine(this, this.getClass()).move(10, 0, 1);
				} else if(e.getKeyCode()==KeyEvent.VK_W){
					Engine.getEngine(this, this.getClass()).move(0, -10, 1);
				} else if(e.getKeyCode()==KeyEvent.VK_S){
					Engine.getEngine(this, this.getClass()).move(0, 10, 1);
				}
			}
		});
	}

	String input = "";
	public void tick() {
		for(int a = 0; a<Images.size(); a++){
			Image i = Images.get((int) (Math.random()*Images.size()));
			Location l = i.Hitbox.getLocation().clone();
			int x = (int) Math.round((Math.random()*2.0)-1.0);
			int y = (int) Math.round((Math.random()*2.0)-1.0);
			if(i.Hitbox.getX()+x*10<0)x = 1;
			if(i.Hitbox.getY()+y*10<0)y = 1;
			i.Hitbox.setLocation(l.getX()+x*10, l.getY()+y*10);		
			Engine.getEngine(this, this.getClass()).updateImage(1, i, l);
		}
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
	
	public void createImages(){
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 0);
		BufferedImage b = Image.createEmptyImage(800,680);
		Graphics2D g = b.createGraphics();
		g.setPaint(new Color(255,255,255));
		g.fillRect(0, 0, b.getWidth(), b.getHeight());
		Engine.getEngine(this, this.getClass()).addImage(new Image(new Location(0,0), new Dimension(b.getWidth(), b.getHeight()), "Background", new SpriteSheet(b), null), 0);
		Engine.getEngine(this, this.getClass()).addLayer(true, false, false, 1);
		BufferedImage bi = Image.createEmptyImage(40, 40);
		g = bi.createGraphics();
		g.setPaint(Color.black);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g.setColor(new Color(100,100,100));
		g.fillRect(5, 5, bi.getWidth()-10, bi.getHeight()-10);
		Sprites s = new Sprites("Test", bi);
		for(int x = 0; x < 800; x+= 30){
			for(int y = 0; y < 680; y+= 30){
				Image i = new Image(new Location(x, y), new Dimension(bi.getWidth(),bi.getHeight()), "", s.getSpriteSheet(), s.getShadowSpriteSheet());
				Images.add(i);
				Engine.getEngine(this, this.getClass()).addImage(i, 1);
			}
		}
	}

}
