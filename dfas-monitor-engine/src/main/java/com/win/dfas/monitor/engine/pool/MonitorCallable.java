package com.win.dfas.monitor.engine.pool;

import java.util.concurrent.Callable;

/**
 * 有返回值的线程接口
 *
 */
public interface MonitorCallable<V> extends Callable<V> {
	
	/**
	 * 线程名
	 * @return
	 */
	public String getThreadName();
	
}
