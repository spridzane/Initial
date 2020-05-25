package jtm.activity07;

public class Animal {

	private int age;

	public Animal() {
		super();
	}

	public Animal(int age) {
		super();
		this.age = age;
	}

	public void setAge(int age) {
		if (age < 0) {
			this.age = 0;
		} else {
			this.age = age;
		}
	}

	public int getAge() {
		return age;
	}

}
