package com.yzz.design.single.demo;

//����ʽ����.˫�������
public class Singleton3 {
	//1����һ���Ƚ����췽��˽�л�
	private Singleton3() {}
	//2��Ȼ������һ����̬�������浥��������
	private static Singleton3 single=null;
	//3��ͨ���ṩһ����̬��������õ���������
	//Ϊ�˱�֤���̻߳����µ���һ��ʵ�ַ�ʽ��˫�������
	//���ܣ���һ�ε�ʱ��
	public static Singleton3 getInstance() {  
	  if (single == null) {
	      synchronized (Singleton3.class) {
	          if (single == null) {    
	              single = new Singleton3();
	          }    
	      }    
	  }    
	   return single;   
	}
}
