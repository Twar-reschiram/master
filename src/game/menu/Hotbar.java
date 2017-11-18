package game.menu;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import Data.Hitbox;
import Data.Location;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import data.Resources;
import game.map.Map;
import sprites.Sprites;

public class Hotbar{
	
	private Resources[] inv;
	private Image[] ims;
	private Image[] backs;
	
	private int state = -1;
	private Hitbox hb;
	
	public Hotbar(int size, Location l, int layer){
		this.inv = new Resources[size];
		this.ims = new Image[size];
		this.backs = new Image[size];
		hb = new Hitbox(l.getX(), l.getY(), (Map.DEFAULT_SQUARESIZE+20)*9, Map.DEFAULT_SQUARESIZE);
		for(int i = 0; i< size; i++){
			this.backs[i] = new Image(new Location(l.getX()+(Map.DEFAULT_SQUARESIZE+20)*i, l.getY()), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", Sprites.Slot.getSpriteSheet(), null);
			this.backs[i].disabled = true;
			Engine.getEngine(this, this.getClass()).addImage(this.backs[i], layer);
			this.ims[i] = new Image(new Location(l.getX()+(Map.DEFAULT_SQUARESIZE+20)*i+(i+1)*5, l.getY()+5), new Dimension(Map.DEFAULT_SQUARESIZE-10, Map.DEFAULT_SQUARESIZE-10), "", new SpriteSheet(), null);
			this.ims[i].disabled = true;
			Engine.getEngine(this, this.getClass()).addImage(this.ims[i], layer);
		}
	}	
	
	public void addRecources(Resources... resources){
		for(int i = 0; i< inv.length; i++){
			if(inv[i]==null){
				for(int r=0; r<resources.length; r++){
					if(resources[r]!=null){
						inv[i] = resources[r];
						resources[r] = null;
						r=resources.length;
						ims[i].setSpriteSheet(inv[i].getSprites().getSpriteSheet());
					}
				}
			}
		}
		Engine.getEngine(this, this.getClass()).update();
	}
	
	public void show(){
		if(ims[0].disabled){
			for(int i = 0; i< ims.length; i++){
				backs[i].disabled = false;
				ims[i].disabled = false;
			}
			Engine.getEngine(this, this.getClass()).update();
		}
	}
	
	public void hide(){
		if(!ims[0].disabled){
			for(int i = 0; i< ims.length; i++){
				backs[i].disabled = true;
				ims[i].disabled = true;
			}
			Engine.getEngine(this, this.getClass()).update();
		}
	}

	public void tick() {
		if(!backs[0].disabled&&Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON1)){
			for(int i = 0; i<backs.length; i++){
				if(backs[i].Hitbox.contains(Engine.getInputManager().MousePosition())){
					state = i;
					return;
				}
			}
			state = -1;
		}
	}

	public Resources getSlected() {
		if(state==-1)return null;
		return inv[state];
	}

	public boolean contains(Point p) {
		return hb.contains(p);
	}
}
