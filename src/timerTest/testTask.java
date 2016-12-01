package timerTest;

import java.util.TimerTask;

public class testTask extends TimerTask {

	@Override
	public void run() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100; i++) {
			System.out.println("running "+i);
			
		}

	}

}
