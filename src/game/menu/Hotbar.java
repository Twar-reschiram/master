package game.menu;

import java.awt.Dimension;

import Data.Location;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import data.Resources;
import game.map.Map;
import sprites.Sprites;

public class Hotbar {
	
	private Resources[] inv;
	private Image[] ims;
	private Image[] backs;
	
	public Hotbar(int size, Location l, int layer){
		this.inv = new Resources[size];
		this.ims = new Image[size];
		this.backs = new Image[size];
		for(int i = 0; i< size; i++){
			this.backs[i] = new Image(new Location(l.getX()+(Map.DEFAULT_SQUARESIZE+20)*i, l.getY()), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", Sprites.Slot.getSpriteSheet(), null);
			this.backs[i].disabled = true;
			Engine.getEngine(this, this.getClass()).addImage(this.backs[i], layer);
			this.ims[i] = new Image(new Location(l.getX()+(Map.DEFAULT_SQUARESIZE+20)*i, l.getY()), new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE), "", new SpriteSheet(), null);
			this.ims[i].disabled = true;
			Engine.getEngine(this, this.getClass()).addImage(this.ims[i], layer);
		}
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

}
