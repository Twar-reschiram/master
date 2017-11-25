package game;

import java.awt.event.KeyEvent;

import data.Mouse;
import Engine.Engine;
import game.tick.TickManager;

public class Player {
	
	private double xm = 0;
	private double ym = 0;
	private int speed = 3;
	
	public Player(){
		
	}
	
	public void tick(){
		if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_UP)){
			ym+=speed*TickManager.getDeltaTime();
			while(ym>1){
				Engine.getEngine(this, this.getClass()).move(0, -1, 0,1,3);
				ym--;
				Mouse.YOff--;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_DOWN)){
			ym-=speed*TickManager.getDeltaTime();
			while(ym<-1){
				Engine.getEngine(this, this.getClass()).move(0, 1, 0,1,3);
				ym++;
				Mouse.YOff++;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_RIGHT)){
			xm+=speed*TickManager.getDeltaTime();
			while(xm>1){
				Engine.getEngine(this, this.getClass()).move(-1, 0, 0,1,3);
				xm--;
				Mouse.XOff--;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_LEFT)){
			xm-=speed*TickManager.getDeltaTime();
			while(xm<-1){
				Engine.getEngine(this, this.getClass()).move(1, 0, 0,1,3);
				xm++;
				Mouse.XOff++;
			}
		}
	}

}
