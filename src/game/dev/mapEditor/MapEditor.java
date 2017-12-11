package game.dev.mapEditor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import Data.Location;
import Data.Events.Action;
import Data.Image.Image;
import Engine.Engine;
import data.Mouse;
import data.MapResource;
import game.map.Map;
import game.map.MapLoader;
import game.menu.Grid;
import game.menu.Hotbar;
import game.menu.Menu;
import sprites.Sprites;

public class MapEditor {

	private MapLoader mapLoader;
	private Map map;
	private Menu menu;
	private Hotbar hotbar;
	private Grid grid;
	
	private MapResource selectedRes;
	
	private boolean build = false;
	
	public MapEditor(MapLoader mapLoader){
		this.mapLoader = mapLoader;
		this.map = this.mapLoader.getMap();
		Engine.getEngine(this, this.getClass()).addLayer(false, false, false, 4);
		Engine.getEngine(this, this.getClass()).addLayer(false, true , false, 5,6);
		this.grid = new Grid(4, 1920/Map.DEFAULT_SQUARESIZE, 1080/Map.DEFAULT_SQUARESIZE, new Location(0,0), true);
		createMenu();
		createHotbar();
	}

	private void createHotbar() {
		hotbar = new Hotbar(9, new Location((1920-60*9)/2,1000), 5);
		hotbar.show();
	}

	private void createMenu() {
		menu = new Menu(new Image(new Location(1920,0), new Dimension(300, 1049), "", Sprites.Sidebar.getSpriteSheet(), null), 5);
		Image ground = new Image(new Location(1705,20), new Dimension(123, 50), "", Sprites.Buttons.getSpriteSheet(), null);
		menu.addButton(0, ground, new Action() {
//			int index = 0;
//			double lastTime = System.currentTimeMillis();
			@Override
			public void act(Object caller, Object... data) {
//				if(System.currentTimeMillis()-lastTime<500)return;
//				lastTime = System.currentTimeMillis();
//				if(Sprites.Buttons.getSpriteSheet().getSprite(index)==null)index = 0;
//				ground.setSpriteState(index);
//				if(index == 0){
//					menu.enableButtons(2);
//					menu.disableButtons(3);
//					hotbar.clear();
//					selectedRes = null;
//				}else{
//					menu.disableButtons(2);
//					menu.disableButtons(3);
//					hotbar.clear();
//					selectedRes = null;
//				}
//				index++;
			}
		});
		for(int x = 0; x< 5; x++){
			for(int y = 0; y<18; y++){
				menu.addButton(1, new Image(new Location(1920-x*50-70,y*50+90), new Dimension(40, 40), "", Sprites.Slot.getSpriteSheet(), null), new Action() {
					@Override
					public void act(Object caller, Object... data) {	
					}
				});	
			}
		}
		HashMap<Integer, Point> Locs = new HashMap<>();
		for(MapResource resource: MapResource.getMapResource()){
			int key = 2;
			if(resource.getLayerUp()==1)key++;
			if(!Locs.containsKey(key))Locs.put(key, new Point(0,0));
			Point p = Locs.get(key);
			Image I = new Image(new Location(1920-p.x*50-65,p.y*50+95), new Dimension(30, 30), "", resource.getSprites().getSpriteSheet(), null);
			I.setSpriteState(resource.getSpriteIDs()[0]);
			menu.addButton(key, I, new Action() {
				MapResource res = resource;
				@Override
				public void act(Object caller, Object... data) {		
					if(res==null) Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(), 0);
					else Mouse.getMouse().setImage(res.getSprites().getSpriteSheet(), res.getSpriteIDs()[0]);
					selectedRes = res;		
				}
			});	
			p.setLocation(p.x+1, p.y);
			if(p.x>=5)p.setLocation(0, p.y+1);
		}
		Image b = new Image(new Location(1830, 990), new Dimension(30,30), "", Sprites.Arrows.getSpriteSheet(), null);
		b.setSpriteState(1);
		menu.addButton(0,b, new Action() {
			int index = 1;
			double time = System.currentTimeMillis();
			@Override
			public void act(Object caller, Object... data) {
				if(System.currentTimeMillis()-time<500)return;
				time = System.currentTimeMillis();
				if(Sprites.Arrows.getSpriteSheet().getSprite(index)==null) index = 0;
				b.setSpriteState(index);
				int ground = 0;
				if(build)ground=2;
				if(index == 0){
					menu.disableButtons(2+ground);
					menu.enableButtons(3+ground);
					hotbar.clear();
					selectedRes = null;
				}else{
					menu.enableButtons(2+ground);
					menu.disableButtons(3+ground);
					hotbar.clear();
					selectedRes = null;
				}
				Engine.getEngine(this, this.getClass()).update();
				index++;
			}
		});
		menu.disableButtons(3);
	}

//	private void createGrid() {
//		Dimension d = new Dimension(Map.DEFAULT_SQUARESIZE, Map.DEFAULT_SQUARESIZE);
//		BufferedImage bi = Image.createEmptyImage(d.width, d.height);
//		Graphics2D g = bi.createGraphics();
//		g.setColor(new Color(50,50,50,50));
//		g.fillRect(0, 0, d.width, d.height);
//		g.setBackground(new Color(0,0,0,0));
//		g.clearRect(1, 1, d.width-2, d.height-2);
//		SpriteSheet s = new SpriteSheet(bi);
//		for(int x = 0; x<1920/Map.DEFAULT_SQUARESIZE; x++){
//			for(int y = 0; y<1080/Map.DEFAULT_SQUARESIZE; y++){
//				Engine.getEngine(this, this.getClass()).addImage(new Image(new Location(x*Map.DEFAULT_SQUARESIZE, y*Map.DEFAULT_SQUARESIZE), d, "", s, null), 4);
//			}
//		}
// 	}

	private MapResource res = null;
	public void tick() {
		grid.tick();
		if(menu.isVisible()){
			menu.tick();
			if(menu.isOpen() && Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_Q)){
				menu.hide();
				selectedRes = null;
				Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
			}else{
				hotbar.tick();
				if(hotbar.getState()!=-1 && selectedRes !=null){
					hotbar.setRecource(selectedRes, hotbar.getState());
					Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
					selectedRes = null;
				}
			}
		}else{
			menu.tick();
			if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_Q)){
				menu.show();
				Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
				hotbar.setSelected(-1);
			}
			if(hotbar!=null){
				hotbar.tick();
				if(hotbar.getSlected()==null && res!=null){
					Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
				}
				if(hotbar.getSlected()!=null && !hotbar.getSlected().equals(res)){
					Mouse.getMouse().setImage(hotbar.getSlected().getSprites().getSpriteSheet(), hotbar.getSlected().getSpriteIDs()[0]);
				}
				res = (MapResource) hotbar.getSlected();
				if(!hotbar.contains(Engine.getInputManager().MousePosition())){
					int x = Mouse.getMouse().getPosition().x/Map.DEFAULT_SQUARESIZE;
					int y = Mouse.getMouse().getPosition().y/Map.DEFAULT_SQUARESIZE; 
					if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON1)){
						if(res!=null){
							map.addToGround(res.getID(), x, y);
						}
					}else if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON3)){
						map.addToGround(0, x, y);
					}
				}
			}
			if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_S)){
				System.out.println("save");
				mapLoader.saveMap();
			}
		}
	}

	public Map getMap() {
		return map;
	}
}
