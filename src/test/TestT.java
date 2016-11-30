package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import thread.testThread;

public class TestT {
	
	private static boolean isRunning = false;
	
	
	
	public static boolean isRunning() {
		return isRunning;
	}



	public static void setRunning(boolean isRunning) {
		TestT.isRunning = isRunning;
	}


	public static void main(String[] args) {
		new TestT().setRunning(true);
		System.out.println(TestT.isRunning);
		new TestT().setRunning(false);
		
		System.out.println(TestT.isRunning());
	}
	

	public static void main1(String[] args) throws InterruptedException {
		
		LinkedList<String> list = new LinkedList<>();
		for (int i = 0; i < 50; i++) {
			list.add("object "+i);
			
		}
		
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
		testThread run = new testThread(list, 0);
			for (int i = 0; i < 6; i++) {
				fixedThreadPool.execute(run);
				
			}
			fixedThreadPool.shutdown();
			fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			System.out.println("all thread complete");  
			
	}

}
