package game.tick;

import java.util.Timer;
import java.util.TimerTask;

import game.GameManager;

public class TickManager {
	
	private static int TICK_DURATION = 1000/100;
	private static double time = System.currentTimeMillis();

	public TickManager(GameManager gM){
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				time = System.currentTimeMillis();
				gM.tick();
			}
		};
		timer.schedule(task, TICK_DURATION, TICK_DURATION);
	}

	public static double getDeltaTime() {
		return Math.sqrt((TICK_DURATION-(System.currentTimeMillis()-time))*(TICK_DURATION-(System.currentTimeMillis()-time)))/TICK_DURATION;
	}

}
