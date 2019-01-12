package com.yzz.design.single.demo;

//����ʽ����̬�ڲ��ࣩ
//����д�����������ȫ���⣬�ֽ������������
//������룬û���˷�һ����
public class Singleton4 {
	//1��������һ����̬�ڲ���
	//private ˽�еı�֤���˲����޸�
	//static ��֤ȫ��Ψһ
	private static class LazyHolder {
		//final Ϊ�˷�ֹ�ڲ������������ģʽ��GgLib�Ĵ���ģʽ
		private static final Singleton4 INSTANCE = new Singleton4();
	}
	//2����Ĭ�Ϲ��췽��˽�л�
	private Singleton4 (){}
	//�൱����һ��Ĭ�ϵ�public���޲εĹ��췽��������ζ���ڴ�������ʱ������new����
		
	//3��ͬ���ṩ��̬������ȡʵ��
	//final ȷ�����˲��ܸ���
	public static final Singleton4 getInstance() {  
		
		//�����е��߼�����Ҫ���û����õ�ʱ��ſ�ʼִ�е�
		//������ʵ���߼���Ҫ�����ڴ棬Ҳ�ǵ���ʱ�ŷ����
		return LazyHolder.INSTANCE;
    }
	
//	static int studyio = 1;
//	//���ܸ�class��û��ʵ������static��̬���ܻ���classLoaderִ�����Ժ󣬾ͼ������
//	static{
//		//��̬���е����ݣ�ֻ�ܷ��ʾ�̬���Ժ;�̬����
//		//ֻҪ�Ǿ�̬�����������ԣ�ֱ�ӿ�����Class�����־��ܵ����
//		Singleton4.studyio = 2;
//		//JVM �ڴ��еľ�̬������һ��������ǹ����� 
//	}
}

//������д�����еĴ��룬��java�ķ��������ǰ�������㱼��
//��������ǿ����õ�private���ε����ݵ�
//���ǿ������ɼ�ʹ����privateҲ�����ף���������·���ƣ�ò�ƿ��ԣ�


//��װ�ص�JVM�й���
//1����������(����������ǰ��ʹ���ں�)
//�����ԡ��󷽷�
//�Ⱦ�̬����̬
