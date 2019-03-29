package cn.edu.tju.h02;

import java.lang.reflect.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) {
		try {
			printField("cn.edu.tju.h02.Teacher");
			printConstructor("cn.edu.tju.h02.Teacher");
			printParent("cn.edu.tju.h02.Teacher");
			printImp("cn.edu.tju.h02.Person");
			printAndInvokeMethod();
		} catch (Throwable e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	 * ��ӡȫ������
	 * ���ȴ�ӡ��ǰ����������ԣ�getDeclaredFields()�ܻ�õ�ǰ���ȫ�����ԣ�����˽�У�
	 * ���ж��������û�з�Object���࣬����У���ӡ������������Բ��ظ����ϲ���
	 */
	public static void printField(String className) throws Exception{
		System.out.println("All fields:");
		Class<?> clazz = Class.forName(className);
		Field fields[] = clazz.getDeclaredFields();
		for (int i = 0 ; i < fields.length ; i++) {
			System.out.println(fields[i]);
		}
		
		while (clazz.getSuperclass()!= null && clazz.getSuperclass().getName() != "java.lang.Object") {
			clazz = clazz.getSuperclass();
			fields = clazz.getDeclaredFields();
			for (int i = 0 ; i < fields.length ; i++) {
				System.out.println(fields[i]);
			}
		}
		
		System.out.println("-------------");
	}
	
	/*
	 * ��ӡȫ�����췽��
	 * getDeclaredConstructors()���Ի�õ�ǰ���ȫ�����췽��������˽�еģ�
	 */
	public static void printConstructor(String className) throws Exception{
		System.out.println("All constructors:");
		Class<?> clazz = Class.forName(className);
		Constructor<?> cons[] = clazz.getDeclaredConstructors();
		for (int i = 0 ; i < cons.length ; i++) {
			System.out.println(cons[i]);
		}
		System.out.println("-------------");
	}
	
	/*
	 * ��ӡ���࣬getSuperClass()������ø�����
	 */
	public static void printParent(String className) throws Exception{
		System.out.println("Father:");
		Class<?> clazz = Class.forName(className);
		System.out.println(clazz.getSuperclass());
		System.out.println("-------------");
	}
	
	/*
	 * ��ӡʵ�ֵĽӿڣ�getInterface()������ýӿ���
	 * һ�������ʵ�ֶ���ӿ�
	 */
	public static void printImp(String className) throws Exception{
		System.out.println("Interfaces:");
		Class<?> clazz = Class.forName(className);
		Class<?> imp[] = clazz.getInterfaces();
		for (int i = 0 ; i < imp.length ; i++) {
			System.out.println(imp[i]);
		}
		System.out.println("-------------");
	}
	
	/*
	 * ��ӡ������������ȫ������
	 * �ȴ�ӡ��ǰ��ķ���
	 * �ټ���Ƿ��з�object���࣬����У���ӡ���ǵķ���
	 * ����ȫ������
	 */
	public static void printAndInvokeMethod() throws Throwable{
		System.out.println("Methods:");
		Class<?> clazz = Teacher.class;
		Teacher t = (Teacher)clazz.newInstance();
		Method methods[] = clazz.getDeclaredMethods(); //����getDeclaredMethods()��ȡ��ǰ�����������з���
													   //��ʹ��getMethods()���޷���ʾ�ǹ��з������һ��ӡ����ǰ��̳еĸ��ࣨ����Object�ࣩ�ķ���
		for (int i = 0 ; i < methods.length ; i++) {
			System.out.println(methods[i]);
		}

		while(clazz.getSuperclass() != null && clazz.getSuperclass().getName() != "java.lang.Object") {
			clazz = clazz.getSuperclass();
			methods = clazz.getDeclaredMethods();
			for (int i = 0 ; i < methods.length ; i++) {
				System.out.println(methods[i]);
			}
		}
		
		clazz = Teacher.class;
		System.out.println("Invoke:");
		Method m1 = clazz.getMethod("eat", String.class);
		Method m2 = clazz.getMethod("eat", String.class, int.class);
		Method m3 = clazz.getMethod("toString");
		Method m4 = clazz.getMethod("AddAge", int.class);
		
		m1.invoke(t, "ƻ��");
		m2.invoke(t, "����", 5);
		m3.invoke(t);
		m4.invoke(t, 1);
		
		System.out.println("-------------");
	}
	
}
