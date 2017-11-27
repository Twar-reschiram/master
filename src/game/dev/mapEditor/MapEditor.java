package game.dev.mapEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Data.Location;
import Data.Events.Action;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import data.Mouse;
import data.Resources;
import game.map.Map;
import game.map.MapLoader;
import game.menu.Hotbar;
import game.menu.Menu;
import sprites.Sprites;

public class MapEditor {

	private MapLoader mapLoader;
	private Map map;
	private Menu menu;
	private Hotbar hotbar;
	
	private Resources selectedRes;
	
	public MapEditor(MapLoader mapLoader){
		this.mapLoader = mapLoader;
		this.map = this.mapLoader.getMap();
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, 2,3);
		createGrid();
		createMenu();
		createHotbar();
	}

	private void createHotbar() {
		hotbar = new Hotbar(9, new Location((1920-60*9)/2,1000), 2);
		hotbar.show();
	}

	private void createMenu() {
		menu = new Menu(new Image(new Location(1920,0), new Dimension(300, 1049), "", Sprites.Sidebar.getSpriteSheet(), null), 3);	
		menu.addButton(new Image(new Location(1670,20), new Dimension(200, 50), "", Sprites.Buttons.getSpriteSheet(), null), new Action() {
			@Override
			public void act(Object caller, Object... data) {				
			}
		});
		for(int x = 0; x< 5; x++){
			for(int y = 0; y<18; y++){
				final int id = x+y*5;
				menu.addButton(new Image(new Location(1920-x*50-70,y*50+90), new Dimension(40, 40), "", Sprites.Slot.getSpriteSheet(), null), new Action() {
					Resources res = Resources.getResource(101+id);
					@Override
					public void act(Object caller, Object... data) {	
						if(res==null) Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(), 0);
						else Mouse.getMouse().setImage(res.getSprites().getSpriteSheet(), res.getSpriteIDs()[0]);
						selectedRes = res;
					}
				});	
				if(Resources.getResource(101+id)!=null){
					Image I = new Image(new Location(1920-x*50-65,y*50+95), new Dimension(30, 30), "", Resources.getResource(101+id).getSprites().getSpriteSheet(), null);
					I.setSpriteState(Resources.getResource(101+id).getSpriteIDs()[0]);
					menu.addButton(I, new Action() {
						@Override
						public void act(Object caller, Object... data) {				
						}
					});	
				}
			}
		}
	}

	private void createGrid() {
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
				}else {
					hotbar.tick();
					if(hotbar.getSlected()==null && res!=null){
						Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
					}
					if(hotbar.getSlected()!=null && !hotbar.getSlected().equals(res)){
						Mouse.getMouse().setImage(hotbar.getSlected().getSprites().getSpriteSheet(), hotbar.getSlected().getSpriteIDs()[0]);
					}
					res = hotbar.getSlected();
				}
			}
			if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_S)){
				System.out.println("save");
				mapLoader.saveMap();
			}
		}
	}
}
