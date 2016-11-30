package timerTask;

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class taskJob extends TimerTask {
	private static int count = 0;
	Toolkit toolKit;
	
	public taskJob() {
		toolKit = Toolkit.getDefaultToolkit();
	}
	
	@Override
	public void run() {
		System.out.println(" timetask is running "+count++);
		toolKit.beep();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 11);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.add(Calendar.DATE, +1);
		Date date = cal.getTime();
		
		taskJob job = new taskJob();
		Timer time = new Timer();
//		time.scheduleAtFixedRate(job, date,5 * 60 * 1000);
		time.schedule(job, date, 5 * 60 * 1000);
		
	}

}
