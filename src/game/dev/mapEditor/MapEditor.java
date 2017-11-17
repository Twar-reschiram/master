package game.dev.mapEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Data.Location;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import game.map.Map;
import game.map.MapLoader;
import game.menu.Hotbar;

public class MapEditor {

	private MapLoader mapLoader;
	private Map map;
	private Hotbar hotbar;
	
	public MapEditor(MapLoader mapLoader){
		this.mapLoader = mapLoader;
		this.map = this.mapLoader.getMap();
		Engine.getEngine(this, this.getClass()).clear();
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 0);
		createGrid();
		hotbar = new Hotbar(9, new Location((1920-60*9)/2,1000), 0);
		hotbar.show();
	}

	private void createGrid() {
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 0);
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
				Engine.getEngine(this, this.getClass()).addImage(new Image(new Location(x*Map.DEFAULT_SQUARESIZE, y*Map.DEFAULT_SQUARESIZE), d, "", s, null), 0);
			}
		}
 	}
}
