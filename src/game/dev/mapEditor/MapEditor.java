package game.dev.mapEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Data.Location;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import data.Mouse;
import data.Resources;
import game.map.Map;
import game.map.MapLoader;
import game.menu.Hotbar;
import sprites.Sprites;

public class MapEditor {

	private MapLoader mapLoader;
	private Map map;
	private Hotbar hotbar;
	
	public MapEditor(MapLoader mapLoader){
		this.mapLoader = mapLoader;
		this.map = this.mapLoader.getMap();
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 2);
		createGrid();
		hotbar = new Hotbar(9, new Location((1920-60*9)/2,1000), 2);
		hotbar.addRecources(Resources.Grass);
		hotbar.show();
	}

	private void createGrid() {
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 2);
		Dimension d = new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE);
		BufferedImage bi = Image.createEmptyImage(d.width, d.height);
		Graphics2D g = bi.createGraphics();
		g.setColor(new Color(50,50,50,50));
		g.fillRect(0, 0, d.width, d.height);
		g.setBackground(new Color(0,0,0,0));
		g.clearRect(1, 1, d.width-2, d.height-2);
		SpriteSheet s = new SpriteSheet(bi);
		for(int x = 0; x<1920/Map.DEFAULT_SQUARESIZE; x++){
			for(int y = 0; y<1080/Map.DEFAULT_SQUARESIZE; y++){
				Engine.getEngine(this, this.getClass()).addImage(new Image(new Location(x*Map.DEFAULT_SQUARESIZE, y*Map.DEFAULT_SQUARESIZE), d, "", s, null), 2);
			}
		}
 	}

	private Resources res = null;
	public void tick() {
		if(hotbar!=null){
			if(!hotbar.contains(Engine.getInputManager().MousePosition())){
				int x = Engine.getInputManager().MousePosition().x/Map.DEFAULT_SQUARESIZE;
				int y = Engine.getInputManager().MousePosition().y/Map.DEFAULT_SQUARESIZE; 
				if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON1)){
					if(res!=null){
						map.addToGround(res.getID(), x, y);
					}
				}else if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON3)){
					map.addToGround(0, x, y);
				}
			}else {
				hotbar.tick();
				if(hotbar.getSlected()==null && res!=null){
					Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet());
				}
				if(hotbar.getSlected()!=null && !hotbar.getSlected().equals(res)){
					Mouse.getMouse().setImage(hotbar.getSlected().getSprites().getSpriteSheet());
				}
				res = hotbar.getSlected();
			}
		}
	}
}
