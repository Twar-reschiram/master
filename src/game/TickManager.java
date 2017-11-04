package game;

import java.util.Timer;
import java.util.TimerTask;

public class TickManager {
	
	private static int TICK_DURATION = 1000/60;

	public TickManager(GameManager gM){
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				gM.tick();
			}
		};
		timer.schedule(task, TICK_DURATION, TICK_DURATION);
	}

}
