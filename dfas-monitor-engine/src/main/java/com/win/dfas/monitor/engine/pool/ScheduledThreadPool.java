package com.win.dfas.monitor.engine.pool;

import java.util.concurrent.*;

/**
 * 定时任务线程池
 *
 */
public class ScheduledThreadPool {

	/**
	 * 	构造一个线程池
	 */
	private static ScheduledExecutorService service = null;

	/**
	 * 禁止实例化
	 */
	private ScheduledThreadPool(){
	};

	/**
	 * 功能说明：初始化线程池
	 * @param poolSize 池大小
	 */
	public static synchronized void init(int poolSize) {
		if(service!=null){
			stop();
		}
		MonitorThreadFactory threadFactory = new MonitorThreadFactory();
		threadFactory.setNamePrefix("IBT-ScheduledThreadPool-thread-");
		service = new ScheduledThreadPoolExecutor(poolSize, threadFactory) ;
	}

	public static  void init() {
		 init( Runtime.getRuntime().availableProcessors());
	}

	/**
	 * 停止线程池
	 */
	public static synchronized void stop(){
		if(service!=null) {
			service.shutdown();
			service = null;
		}		
	}


	/**
	 * 创建并执行一个在给定初始延迟后首次启用的一次性操作，
	 * @param initialDelay 延迟时间
	 * @param unit 单位
	 * @return
	 */
	public static ScheduledFuture<?> schedule(
			Runnable command, long initialDelay, TimeUnit unit) {
		return service.schedule(command, initialDelay, unit);
	}

	/**
	 * 创建并执行首次启用的一次性操作
	 * @return
	 */
	public static ScheduledFuture<?> schedule(
			Runnable command) {
		return service.schedule(command, 0, TimeUnit.MILLISECONDS);
	}


	/**
	 * 创建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，
	 * 然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
	 * 如果任务的任何一个执行遇到异常，则后续执行都会被取消。否则，只能通过执行程序的取消或终止方法来终止该任务。如果此任务的任何一个执行要花费比其周期更长的时间，则将推迟后续执行，但不会同时执行。
	 * @param command 任务
	 * @param initialDelay 初始执行时间
	 * @param period 简隔时间
	 * @return
	 */
	public static ScheduledFuture<?> scheduleAtFixedRate(
			Runnable command, long initialDelay, long period) {
		return service.scheduleAtFixedRate(command, initialDelay, period,TimeUnit.MILLISECONDS);
	}

	/**
	 * 创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
	 * 如果任务的任一执行遇到异常，就会取消后续执行。否则，只能通过执行程序的取消或终止方法来终止该任务。
	 * @param command 任务
	 * @param initialDelay 初始执行时间
	 * @param delay 简隔时间
	 * @return
	 */
	public static ScheduledFuture<?> scheduleWithFixedDelay(
			Runnable command,long initialDelay,long delay){
		return service.scheduleWithFixedDelay(command, initialDelay, delay, TimeUnit.MILLISECONDS);
	}

}
