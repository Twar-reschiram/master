package game.dev.mapEditor;

import Engine.Engine;
import game.map.Map;
import game.map.MapLoader;

public class MapEditor {

	private MapLoader mapLoader;
	private Map map;
	
	public MapEditor(MapLoader mapLoader){
		this.mapLoader = mapLoader;
		this.map = this.mapLoader.getMap();
		Engine.getEngine(this, this.getClass()).clear();
	}
}
