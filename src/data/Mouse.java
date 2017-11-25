package data;

import java.awt.Dimension;
import java.awt.Point;

import Data.Location;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;

public class Mouse {
	
	public static int XOff = 0;
	public static int YOff = 0;
	
	private Point p = new Point(0,0);
	private Image img;
	private int layer;
	
	private static Mouse mouse;
	public static Mouse getMouse(){
		return Mouse.mouse;
	}
	
	public Mouse(int layer){
		this.layer = layer;
		img = new Image(new Location(p), new Dimension(40,40), "", new SpriteSheet(Image.createEmptyImage(1, 1)), null);
		Engine.getEngine(this, this.getClass()).addImage(img, layer);
		Mouse.mouse = this;
	}
	
	public void tick(){
		Point ep = Engine.getInputManager().MousePosition();
		if(ep.x != img.Hitbox.getX() || ep.y != img.Hitbox.getY() || ep.x+XOff != img.Hitbox.getX() || ep.y+YOff != img.Hitbox.getY()){
			Location l = img.Hitbox.getLocation().clone();
			img.Hitbox.setLocation(ep.x+XOff, ep.y+YOff);
			Engine.getEngine(this, this.getClass()).updateImage(layer, img, l);
		}
	}
	
	public void setImage(SpriteSheet s, int id){
		img.setSpriteSheet(s);
		img.setSpriteState(id);
		Engine.getEngine(this, this.getClass()).update();
	}

	public Location getPosition() {
		return img.Hitbox.getLocation();
	}
}
