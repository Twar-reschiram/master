package game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import data.Mouse;
import Engine.Engine;
import game.map.Map;
import game.tick.TickManager;

public class Player {
	
	private double xm = 0;
	private double ym = 0;
	private int speed = 3;
	private Map map;	
	
	public Player(Map map){
		this.map = map;
	}
	
	
	private double lastTime = System.currentTimeMillis();
	public void tick(){
		if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_UP)){
			ym+=speed*TickManager.getDeltaTime();
			while(ym>1){
				Engine.getEngine(this, this.getClass()).move(0, -1, 0,1,2,3,4);
				ym--;
				Mouse.YOff--;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_DOWN)){
			ym-=speed*TickManager.getDeltaTime();
			while(ym<-1){
				Engine.getEngine(this, this.getClass()).move(0, 1, 0,1,2,3,4);
				ym++;
				Mouse.YOff++;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_RIGHT)){
			xm+=speed*TickManager.getDeltaTime();
			while(xm>1){
				Engine.getEngine(this, this.getClass()).move(-1, 0, 0,1,2,3,4);
				xm--;
				Mouse.XOff--;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_LEFT)){
			xm-=speed*TickManager.getDeltaTime();
			while(xm<-1){
				Engine.getEngine(this, this.getClass()).move(1, 0, 0,1,2,3,4);
				xm++;
				Mouse.XOff++;
			}
		}else if(Engine.getInputManager().getMouseButton().contains(MouseEvent.BUTTON1)){
			Point pos = new Point(Mouse.getMouse().getPosition().x/Map.DEFAULT_SQUARESIZE,Mouse.getMouse().getPosition().x/Map.DEFAULT_SQUARESIZE);
			if(map.getBuild()[pos.x][pos.y][0]!=0){
				
			}
		}
	}

}
