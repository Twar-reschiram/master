package game.vehicle.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Data.Hitbox;
import Data.Location;
import Data.Events.Action;
import Data.Image.Image;
import Data.Image.SpriteSheet;
import Engine.Engine;
import data.Mouse;
import data.VehicleResource;
import game.map.Map;
import game.menu.Grid;
import game.menu.Hotbar;
import game.menu.Menu;
import game.vehicle.Vehicle;
import sprites.Sprites;

public class VehicleEditor {
	
	private Hotbar hotbar;
	private Menu menu;
	private Grid grid;
	private int baseLayer;
	private Dimension size;
	private Hitbox hitbox;
	
	private int workingState = 0;
	private VehicleResource currentResource;
	
	private Vehicle vehicle;
	
	public VehicleEditor(int Layer, Dimension size){
		this.baseLayer = Layer;
		this.size = size;
		Engine.getEngine(this, this.getClass()).addLayer(false, true, false, this.baseLayer,this.baseLayer+1,this.baseLayer+2);
		
		BufferedImage bi = Image.createEmptyImage(size.width*Map.DEFAULT_SQUARESIZE, size.height*Map.DEFAULT_SQUARESIZE);
		Graphics2D g = bi.createGraphics();
		g.setColor(new Color(50, 50, 50, 100));
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		
		this.hitbox = new Hitbox(((int)Engine.getWindowSize().getWidth()-bi.getWidth())/2, ((int)Engine.getWindowSize().getHeight()-bi.getHeight())/2, bi.getWidth(), bi.getHeight());
		Engine.getEngine(this, this.getClass()).addImage(new Image(this.hitbox.getLocation(), new Dimension(bi.getWidth(), bi.getHeight()), "", new SpriteSheet(bi), null), this.baseLayer);
		
		this.grid = new Grid(this.baseLayer, size.width, size.height, this.hitbox.getLocation(), false);
		
		this.hotbar = new Hotbar(9, new Location((1920-60*9)/2,1000), this.baseLayer+2);
		this.hotbar.show();
		
		createMenu();
		
		this.vehicle = new Vehicle(size.width, size.height, this.hitbox.getLocation(), baseLayer+1);
	}

	private void createMenu() {
		this.menu = new Menu(new Image(new Location(Engine.getWindowSize().width,0), new Dimension(300, 1049), "", Sprites.Sidebar.getSpriteSheet(), null), this.baseLayer+1);
		Image modi = new Image(new Location((int) (Engine.getWindowSize().width-((300-123)*1.2)),20), new Dimension(123, 50), "", Sprites.Buttons.getSpriteSheet(), null);
		this.menu.addButton(0, modi, new Action() {
			int index = 0;
			double lastTime = System.currentTimeMillis();
			@Override
			public void act(Object caller, Object... data) {
				if(System.currentTimeMillis()-lastTime<500)return;
				lastTime = System.currentTimeMillis();
				if(index == 0){
					if(workingState == 0){
						index++;
					}
				}else if(index == 1){
					if(workingState == 1){
						index = 0;
					}
				}
				modi.setSpriteState(index);
			}
		});
		for(int x = 0; x< 5; x++){
			for(int y = 0; y<18; y++){
				menu.addButton(1, new Image(new Location((int) (Engine.getWindowSize().getWidth()-x*50-70),y*50+90), new Dimension(40, 40), "", Sprites.Slot.getSpriteSheet(), null), new Action() {
					@Override
					public void act(Object caller, Object... data) {	
						Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(), 0);
						currentResource = null;
					}
				});	
			}
		}
		HashMap<Integer, Location> Locs = new HashMap<>();
		for(VehicleResource res: VehicleResource.getVehicleResources()){
			int typeID = res.getVehiclePartType().getTypeID();
			if(!Locs.containsKey(typeID))Locs.put(typeID, new Location(0,0));
			else{
				Location loc = Locs.get(typeID);
				loc.setLocation(loc.x+1, loc.y);
				if(loc.x>=5){
					loc.setLocation(0, loc.y+1);
				}
			}
			Image I = new Image(new Location(new Location((int) (Engine.getWindowSize().getWidth()-Locs.get(typeID).x*50-65),Locs.get(typeID).y*50+95)), new Dimension(30, 30), "", res.getSprites().getSpriteSheet(), null);
			I.setSpriteState(res.getSpriteIDs()[0]);
			menu.addButton(typeID, I, new Action() {				
				@Override
				public void act(Object caller, Object... data) {
					Mouse.getMouse().setImage(res.getSprites().getSpriteSheet(), res.getSpriteIDs()[0]);
					currentResource = res;
				}
			});
		}
	}

	public void tick() {
		if(menu.isVisible()){
			menu.tick();
			if(menu.isOpen()&&Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_Q)){
				menu.hide();
				currentResource = null;
			}
			if(currentResource!=null){
				if(hotbar.contains(Engine.getInputManager().MousePosition())){
					hotbar.tick();
					if(hotbar.getState()!=-1 && currentResource !=null){
						hotbar.setRecource(currentResource, hotbar.getState());
						Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
						currentResource = null;
					}
				}
			}
		}else{
			if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_Q)){
				menu.show();
				currentResource = null;
			}else{
				hotbar.tick();
				if(currentResource != hotbar.getSlected()){
					currentResource = (VehicleResource) hotbar.getSlected();
					if(currentResource == null)Mouse.getMouse().setImage(Sprites.Mouse.getSpriteSheet(),0);
					else Mouse.getMouse().setImage(currentResource.getSprites().getSpriteSheet(), currentResource.getSpriteIDs()[0]);
				}else if(!hotbar.contains(Engine.getInputManager().MousePosition())&&this.hitbox.contains(Engine.getInputManager().MousePosition())){
					if(vehicle!=null){
						int x = (Engine.getInputManager().MousePosition().x-this.hitbox.getLocation().x)/Map.DEFAULT_SQUARESIZE;
						int y = (Engine.getInputManager().MousePosition().y-this.hitbox.getLocation().y)/Map.DEFAULT_SQUARESIZE;
						if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON1)){
							if(currentResource!=null)vehicle.setVehiclePart(x, y, currentResource.getID());
							else vehicle.setVehiclePart(x, y, VehicleResource.getNONID());
						}else if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON3)){
							vehicle.setVehiclePart(x, y, VehicleResource.getNONID());
						}
					}
				}
			}
		}
	}
}
