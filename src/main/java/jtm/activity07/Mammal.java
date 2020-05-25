package jtm.activity07;

public class Mammal extends Animal {
	private boolean isDomestic;

	public Mammal() {
		super();
	}

	public Mammal(boolean isDomestic) {
		super();
		this.isDomestic = isDomestic;
	}

	public Mammal(int age) {
		super(age);

	}

	public boolean getIsDomestic() {
		return isDomestic;
	}

	public void setIsDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}

}
