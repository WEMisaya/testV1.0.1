package thread;

import java.util.LinkedList;
import java.util.List;

public class testThread implements Runnable{
	private int count = 0;
	private boolean isRunning = true;
	private volatile LinkedList<String> list = null;
	private volatile LinkedList<String> result = null;
	
	public testThread (LinkedList<String> list,int count){
		this.list = list;
		this.count = count;
		System.out.println(" creat thread .......");
		this.result = new LinkedList<>();
	}
	
	public void setcount(int count){
		this.count = count;
		
	}
	
	public void print(){
		if (result!=null && result.size()>0) 
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
	}
	
	@Override
	public void run() {

		while (isRunning) {
			
			String name = null;
			synchronized (list) {
				if (list==null || list.size()==0) {
					this.isRunning = false;
					System.out.println("thread stop");
					break;
				}else
					name = list.remove(0);
			}
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
			System.out.println(count+" thread is run "+name);
			
			synchronized (result) {
				if (result==null) {
					result = new LinkedList<>();
				}else
					result.add(name +" out ");
			}
		}
		
		
	}

	public boolean isok() {

		return isRunning;
	}

}
