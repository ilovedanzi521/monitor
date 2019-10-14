
package com.win.dfas.monitor.common.util.id;


import com.win.dfas.monitor.common.util.NetUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbsIDGenerator implements IDGenerator {


	protected static final int CURSOR_START = -1;
	private static final int MAX_MUTEX_COUNT = 5;

	protected ArrayBlockingQueue<String> cache = null;
	protected int casheSize = 1000;

	// 最小长度
	protected static final int MIN_LENGTH = 18;
	// 顺序号最大数
	protected long seqMaxVal = 999990;
	// 顺序号部分长度
	protected int seqValLen = 6;

	// 生产的随机码最大数
	protected int randomMaxVal = 90;
	protected int randomValLen = 2;
	// 用于生成随机数的种子，避免在多服务器下生成重复的号。
	// 用于生成随机数的种子，随机2位id
	static protected int APPSERVERID = 0;
	protected static AtomicInteger instanceCount = new AtomicInteger(1);
	static {
		try {
			String pid = ManagementFactory.getRuntimeMXBean().getName();
			APPSERVERID = new Random(pid.hashCode() + instanceCount.incrementAndGet()).nextInt(99);
		}
		catch (Throwable e) {
			try {
				APPSERVERID = new Random(NetUtils.getLocalHost().hashCode() + instanceCount.incrementAndGet())
						.nextInt(99);
			}
			catch (Throwable e1) {
				e1.printStackTrace();
			}
		}
	}
	// 顺序号计数器
	protected static AtomicLong globalCount = new AtomicLong(APPSERVERID);

	// 12位日期格式
	protected SimpleDateFormat dateTimeFormater = new SimpleDateFormat("yyMMddHHmmss");
	private static final int DATE_VALUE_LEN = 12;

	// protected AtomicInteger cursor = new AtomicInteger(CURSOR_START); // 线程安全

	/**
	 * 生成单个id
	 * @return
	 */
	protected abstract String generate(int order);

	public AbsIDGenerator() {
		instanceCount.incrementAndGet();
		cache = new ArrayBlockingQueue<String>(this.casheSize);
		this.batchCache();
	}

	public AbsIDGenerator(int size) {
		instanceCount.incrementAndGet();
		this.casheSize = size;

		if (size > 0) {
			this.cache = new ArrayBlockingQueue<String>(size);
			this.batchCache();
		}
	}

	public AbsIDGenerator(int size, int length) {
		instanceCount.incrementAndGet();
		if (length < MIN_LENGTH) { throw new RuntimeException("ID长度不能小于" + (MIN_LENGTH) + "位！"); }
		this.casheSize = size;

		if (length < 20) {
			randomValLen = 0;
			seqValLen = length - DATE_VALUE_LEN;
		}
		else if (length > 20) {
			randomValLen = (length - DATE_VALUE_LEN) / 2;
			if (randomValLen > 8) {
				randomValLen = 8;
			}
			seqValLen = length - DATE_VALUE_LEN - randomValLen;
		}
		else {
			randomMaxVal = 90;
			randomValLen = 2;
			seqValLen = 6;
		}

		if (randomValLen >= 2) {
			randomMaxVal = (int) Math.pow(10, randomValLen) - 9;
		}
		else {
			randomMaxVal = 0;
		}

		seqMaxVal = (long) Math.pow(10, seqValLen) - 9;
		if (size > 0) {
			this.cache = new ArrayBlockingQueue<String>(size);
			this.batchCache();
		}
	}

	/**
	 * 批量生成id
	 */
	protected synchronized void batchCache() {

		cache.clear();

		for (int i = 0; i < this.casheSize; i++) {

			String id = generate(i);
			if (!cache.offer(id)) {
				break;
			}

		}
	}

	public String nextId() {

		if (this.casheSize == 0) { return this.generate(0); }

		while (true) {
			String id = this.cache.poll();
			if (id != null) { return id; }
			this.batchCache();
		}
	}

	private int getLocalIPNumber() {

		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
