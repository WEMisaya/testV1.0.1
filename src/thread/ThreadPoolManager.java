package thread;

import java.util.Vector;

/**
 * 线程池管理器
 * 
 * @author Administrator
 * 
 */
public class ThreadPoolManager {
	/**
	 * 最大线程数
	 */
	private int maxNum;
	/**
	 * 线程池默认连接数
	 */
	private static final int DEFAULT_SIZE = 20;
	/**
	 * 工作线程队列
	 */
	private Vector<WorkThread> vector;

	/**
	 * 线程池管理器对象
	 */
	private static ThreadPoolManager poolManager = null;

	private ThreadPoolManager() {

	}

	/**
	 * 获取线程池管理器对象 采用单例模式
	 * 
	 * @return
	 */
	public synchronized static ThreadPoolManager getInstance() {
		if (poolManager == null) {
			poolManager = new ThreadPoolManager();
		}
		return poolManager;
	}

	/**
	 * 初始化线程池
	 * 
	 * @param maxNum
	 *            工作线程最大连接数
	 */
	public void init(int maxNum) {
		if (maxNum <= 0) {
			this.maxNum = DEFAULT_SIZE;
		} else {
			this.maxNum = maxNum;
		}
		vector = new Vector<WorkThread>();
	}

	/**
	 * 启动线程池，开始处理任务
	 */
	public void startup(String threadName) {
		System.out.println("启动工作线程...");
		for (int i = 0; i < maxNum; i++) {
			WorkThread work = new WorkThread(this, threadName, i);
			vector.add(work);
			synchronized (this) {
				try {
					// 启动线程
					work.start();
					// 线程池等待
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	/**
	 * 分配任务至工作线程 通过while循环不断监测是否有空闲的线程 如果没有，则休眠；直到有空闲线程时唤醒
	 */
	public synchronized void assigned(Task task) {
		while (true) {
			for (int i = 0; i < vector.size(); i++) {
				WorkThread work = vector.get(i);
				if (work.isAvailable()) {
					work.assign(task);
					return;
				}
			}
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * 关闭线程池
	 */
	public void stop() {
		System.out.println("停止工作线程...");
		for (int i = 0; i < vector.size(); i++) {
			WorkThread work = vector.get(i);
			work.shutdown();
		}
	}

	/**
	 * @return the maxNum
	 */
	public synchronized int getMaxNum() {
		return maxNum;
	}
}
