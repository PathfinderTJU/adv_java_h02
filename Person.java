package cn.edu.tju.h02;

public class Person implements IPerson {
	public String gender;
	private int height;
	private double weight;
	public String name;
	public int age;

	@Override
	public int AddAge(int age) {
		System.out.println("Invoke AddAge(int)");
		return age + 1;
	}

}
