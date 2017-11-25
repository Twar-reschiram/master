package game.menu;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import Data.Events.Action;
import Data.Image.Image;
import Engine.Engine;
import game.tick.TickManager;

public class Menu {
	
	private static int OPENSPEED = 15;
	
	private HashMap<Image, Action> buttons = new HashMap<>();
	private HashMap<Image, Dimension> buttonSize = new HashMap<>();
	private Image background;
	private Dimension size;
	private boolean visible = false;
	private int layer;
	
	private int changeSize = -1;	
	private double grow = 0.0;
	
	public Menu(Image background, int layer){
		this.layer = layer;
		this.size = new Dimension(background.Hitbox.getWidth(), background.Hitbox.getX());
		background.disabled = true;
		this.background = background;
		Engine.getEngine(this, this.getClass()).addImage(background, layer);
	}
	
	public void addButton(Image I, Action a){
		buttons.put(I, a);
		buttonSize.put(I, new Dimension(I.Hitbox.getWidth(), I.Hitbox.getX()));
		I.disabled = true;
		Engine.getEngine(this, this.getClass()).addImage(I, layer);
	}
	
	public void show(){
		if(!visible){
			changeSize = 0;
			visible = true;
			background.disabled = false;
			background.Hitbox.setDimension(0,background.Hitbox.getHeigth());
//			for(Image button: this.buttons.keySet()){
//				button.disabled = false;
//				button.Hitbox.setDimension(0,button.Hitbox.getHeigth());
//			}
		}
	}
	
	public void hide(){
		if(visible){
			changeSize = 1;	
			for(Image button: buttonSize.keySet())button.disabled = true;
		}
	}
	
	public void tick(){
		if(changeSize == 0){
			if(background.Hitbox.getWidth()!=size.width){
				grow += Menu.OPENSPEED*TickManager.getDeltaTime();
				if(grow>=size.width){
					grow = size.width;
					changeSize = -1;
					for(Image button: buttonSize.keySet())button.disabled = false;
				}
				background.Hitbox.setDimension((int) grow, background.Hitbox.getHeigth());
				background.Hitbox.setLocation((int) (size.getHeight()-grow), background.Hitbox.getY());
//				for(Image button: buttonSize.keySet()){
//					int width = (int) ((grow/size.width)*buttonSize.get(button).getWidth());
//					button.Hitbox.setDimension(width, button.Hitbox.getHeigth());
//					button.Hitbox.setLocation((int) (buttonSize.get(button).getHeight()-width), button.Hitbox.getY());
//				}
				Engine.getEngine(this, this.getClass()).update();
			}
		}else if(changeSize == 1){
			if(background.Hitbox.getWidth()!=0){
				grow -= Menu.OPENSPEED*TickManager.getDeltaTime();
				if(grow<=0){
					grow = 0;
					background.disabled = true;
					visible = false;
				}
				background.Hitbox.setDimension((int) grow, background.Hitbox.getHeigth());
				background.Hitbox.setLocation((int) (size.getHeight()-grow), background.Hitbox.getY());
//				for(Image button: buttonSize.keySet()){
//					if(grow<=0){
//						changeSize = -1;
//						button.disabled = true;
//						button.Hitbox.setDimension(0, button.Hitbox.getHeigth());
//						button.Hitbox.setLocation((int) (buttonSize.get(button).getHeight()), button.Hitbox.getY());
//					}else {
//						int width = (int) ((grow/size.width)*buttonSize.get(button).getWidth());
//						button.Hitbox.setDimension(width, button.Hitbox.getHeigth());
//						button.Hitbox.setLocation((int) (buttonSize.get(button).getHeight()-width), button.Hitbox.getY());
//					}
//				}
				Engine.getEngine(this, this.getClass()).update();
			}
		}else if(changeSize == -1){
			if(visible&&Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON1)){
				for(Image button: buttons.keySet()){
					if(button.Hitbox.contains(Engine.getInputManager().MousePosition())){
						buttons.get(button).act(this);
					}
				}
			}
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isOpen() {
		return changeSize==-1;
	}

}
