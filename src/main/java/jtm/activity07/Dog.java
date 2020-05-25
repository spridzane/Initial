package jtm.activity07;

public class Dog extends Mammal {

	private String name;

	public Dog() {
		super();
	}

	public Dog(int age) {
		super(age);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.matches("[0-9]+") || !Character.isUpperCase(name.codePointAt(0))) {
			this.name = "";
		} else {
			this.name = name;
		}

	}

}
