package thread;

/**
 * 工作线程
 * 
 * @author Administrator
 * 
 */
public class WorkThread extends Thread {

	// 空闲的线程
	private static final int WorKThread_IDLE = 0;
	// 繁忙的线程
	private static final int WorKThread_BUSY = 1;
	// 判断线程是否运行
	private boolean running;
	// 判断是否有线程分发到该线程
	private boolean assigned;
	// 线程池管理器对象
	private ThreadPoolManager poolManager;
	// 任务对象
	private Task task;
	// 线程状态
	private int state;

	public WorkThread(ThreadPoolManager poolManager, String name, int i) {
		super(name + " " + i);
		this.poolManager = poolManager;

		// 初始化线程状态
		assigned = false;
		running = false;
		state = WorKThread_IDLE;
	}

	@Override
	public synchronized void run() {
		running = true;
		System.out.println("工作线程：" + this.getName() + "开始运行");
		synchronized (poolManager) {
			// 唤醒线程池
			poolManager.notify();
		}
		while (running) {
			if (assigned) {
				state = WorKThread_BUSY;
				task.execute();
				synchronized (poolManager) {
					assigned = false;
					state = WorKThread_IDLE;
					task = null;
					// 唤醒待分配的任务
					poolManager.notify();
					notify();
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
	 * 判断是否可分配任务至该线程
	 */
	public boolean isAvailable() {
		return (!assigned) && running;
	}

	/**
	 * 分配一个新的任务给该线程，并告知线程不可再接受新任务
	 */
	public synchronized void assign(Task task) {
		if (!running) {
			throw new RuntimeException("该线程没有运行!!!");
		}

		if (assigned) {
			throw new RuntimeException("该线程已经分配任务!!!");
		}

		this.task = task;
		assigned = true;
		// 唤醒该线程
		notify();
	}

	/**
	 * 关闭线程， 调用该方法后线程不一定立即关闭 只有线程处于运行状态并且执行完当前任务后才关闭
	 */
	public synchronized void shutdown() {
		if (!running) {
			throw new RuntimeException("该线程没有运行");
		}
		if (assigned) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		running = false;
		notify();
	}

	public int getStates() {
		return state;
	}

}
