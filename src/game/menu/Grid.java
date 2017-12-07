package game.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Data.Location;
import data.Mouse;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import game.map.Map;

public class Grid {
	
	private int layer;	
	private Image[][] images;
	private Point lastPosition = new Point(Mouse.XOff, Mouse.YOff);
	private boolean movable = false;
	
	public Grid(int layer, int width, int heigth, boolean movable){
		this.layer = layer;
		this.images = new Image[width+2][heigth+2];
		this.movable = movable;
		create();
	}

	private void create() {
		Dimension d = new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE);
		BufferedImage bi = Image.createEmptyImage(d.width, d.height);
		Graphics2D g = bi.createGraphics();
		g.setColor(new Color(50,50,50,50));
		g.fillRect(0, 0, d.width, d.height);
		g.setBackground(new Color(0,0,0,0));
		g.clearRect(1, 1, d.width-2, d.height-2);
		SpriteSheet s = new SpriteSheet(bi);
		for(int x = 0; x<images.length; x++){
			for(int y = 0; y<images[x].length; y++){
				this.images[x][y] = new Image(new Location((x-1)*Map.DEFAULT_SQUARESIZE, (y-1)*Map.DEFAULT_SQUARESIZE), d, "", s, null);
				Engine.getEngine(this, this.getClass()).addImage(this.images[x][y], layer);
//				System.out.println(this.images[x][y].disabled);
			}
		}
	}
	
	public void tick(){
		if(this.movable){
			int x = (Mouse.XOff/Map.DEFAULT_SQUARESIZE) - (this.lastPosition.x/Map.DEFAULT_SQUARESIZE);
			if(x!=0){
//				System.out.print(x+"|");
				if(x>0)x=-1;
				else x=1;
				this.lastPosition.setLocation(this.lastPosition.x-(x*Map.DEFAULT_SQUARESIZE), this.lastPosition.y);
//				else this.lastPosition.setLocation(this.lastPosition.x+(x*Map.DEFAULT_SQUARESIZE), this.lastPosition.y);
			}
			int y = (Mouse.YOff/Map.DEFAULT_SQUARESIZE) - (this.lastPosition.y/Map.DEFAULT_SQUARESIZE);
			if(y!=0){
//				System.out.print(y+"|");
				if(y>0)y=-1;
				else y=1;
				this.lastPosition.setLocation(this.lastPosition.x, this.lastPosition.y-(y*Map.DEFAULT_SQUARESIZE));
			}
//			if(x!=0||y!=0)System.out.println(x+"|"+y+"|"+lastPosition.x+"|"+lastPosition.y+"|"+Mouse.XOff+"|"+Mouse.YOff);
			Engine.getEngine(this, this.getClass()).move(x*Map.DEFAULT_SQUARESIZE, y*Map.DEFAULT_SQUARESIZE, this.layer);
		}
	}

}
