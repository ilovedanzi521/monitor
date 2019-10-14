package com.win.dfas.monitor.common.util.id;

public class IDUtils {

	static NUIDGenerator nuid = new NUIDGenerator();

	public static String nextId() {
		return nuid.nextId();
	}
}
