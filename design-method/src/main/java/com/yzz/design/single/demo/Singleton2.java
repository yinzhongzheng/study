package com.yzz.design.single.demo;

//?????????.????????
public class Singleton2 {
	//1????????????????????
	private Singleton2() {}
	//2???????????????????????????????
	private static Singleton2 single=null;
	//3???????????????????????????????
	//??????????????????????????????????????synchronized
	//????  synchronized ???????????????????????
	//????synchronized???????????getInstance()????????????????????????
	//????????????????????????????????????
	public static synchronized Singleton2 getInstance() {
		if (single == null) {
			single = new Singleton2();
		}
		return single;  
	}
}
