package com.win.dfas.monitor.engine.pool;

import java.util.Date;

/**
 * 可自行中断线程类
 *
 */
public abstract class MonitorTerminatableThread<V> extends MonitorThread<V> {

	/** 线程停止标志 */
	public final TerminationToken terminationToken;


	public MonitorTerminatableThread() {
		super();
		this.terminationToken = new TerminationToken();
	}

	public MonitorTerminatableThread(String threadName){
		super(threadName);
		this.terminationToken = new TerminationToken();
	}


	/**
	 * 线程执行
	 * {@inheritDoc}
	 *
	 */
	@Override
	public V call() throws Exception{
		Exception ex = null;
		Date date1 = new Date();
		try {
			while (true) {
				//在执行线程的处理逻辑前先判断线程停止的标志。
				if (terminationToken.isToShutdown()
						&& terminationToken.reservations.get()<=0) {
					System.out.println("SofaTerminatableThread.interrupt发送中断通知！！！");
					break;
				}
				return doCall();
			}

		} catch (Exception e) {
			ex = e;
			throw e;
		} finally {
			if(log.isDebugEnabled()){
    			Date date2 = new Date();
    			String name = null;
    			if(threadName !=null){
    				name = "线程["+threadName+"]";
    			}else{
    				name = "线程";
    			}
    			log.debug(name+"执行时间为"+(date1.getTime()-date2.getTime())+"毫秒");
			}
			doCleanup(ex);
		}
		return null;
	}

	/**
	 * 业务处理方法，子类必须实现
	 * @return 业务处理返回结果
	 * @throws Exception
	 */
	@Override
	public abstract V doCall() throws Exception;

	/**
	 * 留给子类实现线程停止后可能需要的一些清理动作
	 * @param cause 正常处理引出的错误
	 */
	public void doCleanup(Exception cause){
		//do nothing
		//System.out.println("SofaTerminatableThread.doCleanup正在执行！！！");
	}

	/**
	 * 中断
	 * @throws Exception 
	 */
	public void interrupt() {
		//System.out.println("SofaTerminatableThread.interrupt正在执行！！！");
		doCleanup(null);
		terminationToken.setToShutdown(true);
	}
}
