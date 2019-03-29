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
	 * 打印全部属性
	 * 首先打印当前类的所有属性，getDeclaredFields()能获得当前类的全部属性（包括私有）
	 * 在判断这个类有没有非Object父类，如果有，打印父类的所有属性并重复以上操作
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
	 * 打印全部构造方法
	 * getDeclaredConstructors()可以获得当前类的全部构造方法（包括私有的）
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
	 * 打印父类，getSuperClass()方法获得父类名
	 */
	public static void printParent(String className) throws Exception{
		System.out.println("Father:");
		Class<?> clazz = Class.forName(className);
		System.out.println(clazz.getSuperclass());
		System.out.println("-------------");
	}
	
	/*
	 * 打印实现的接口，getInterface()方法获得接口名
	 * 一个类可以实现多个接口
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
	 * 打印方法名并调用全部方法
	 * 先打印当前类的方法
	 * 再检查是否有非object父类，如果有，打印他们的方法
	 * 调用全部方法
	 */
	public static void printAndInvokeMethod() throws Throwable{
		System.out.println("Methods:");
		Class<?> clazz = Teacher.class;
		Teacher t = (Teacher)clazz.newInstance();
		Method methods[] = clazz.getDeclaredMethods(); //采用getDeclaredMethods()获取当前类声明的所有方法
													   //不使用getMethods()：无法显示非公有方法，且会打印出当前类继承的父类（包括Object类）的方法
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
		
		m1.invoke(t, "苹果");
		m2.invoke(t, "橘子", 5);
		m3.invoke(t);
		m4.invoke(t, 1);
		
		System.out.println("-------------");
	}
	
}
