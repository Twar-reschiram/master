package game;

import java.awt.event.KeyEvent;

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
				Engine.getEngine(this, this.getClass()).move(0, -1, 0,1);
				ym--;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_DOWN)){
			ym-=speed*TickManager.getDeltaTime();
			while(ym<-1){
				Engine.getEngine(this, this.getClass()).move(0, 1, 0,1);
				ym++;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_RIGHT)){
			xm+=speed*TickManager.getDeltaTime();
			System.out.println(xm);
			while(xm>1){
				Engine.getEngine(this, this.getClass()).move(-1, 0, 0,1);
				xm--;
			}
		}else if(Engine.getInputManager().getKeyDown().contains(KeyEvent.VK_LEFT)){
			xm-=speed*TickManager.getDeltaTime();
			while(xm<-1){
				Engine.getEngine(this, this.getClass()).move(1, 0, 0,1);
				xm++;
			}
		}
	}

}
