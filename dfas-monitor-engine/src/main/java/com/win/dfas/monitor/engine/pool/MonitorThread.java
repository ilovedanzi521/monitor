package com.win.dfas.monitor.engine.pool;


import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * SOFA线程
 * 实现SOFACallable接口
 */
public abstract class MonitorThread<V> implements MonitorCallable<V> {
	
	protected static Logger log = LoggerFactory.getLogger(MonitorThread.class);

	/** 线程名 */
	protected String threadName = null;


	public MonitorThread(String threadName){
		this.threadName = threadName;
	}
	
	public MonitorThread(){
		
	}

	@Override
	public V call() throws Exception{
		Date date1 = new Date();
		V v = doCall();
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
		//System.out.println(name+"执行时间为"+(date1.getTime()-date2.getTime())+"毫秒");
		return v;
	}
	
	/**
	 * 子类实现线程的业务处理
	 * @return
	 * @throws Exception
	 */
	public abstract V doCall() throws Exception ;

	@Override
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
