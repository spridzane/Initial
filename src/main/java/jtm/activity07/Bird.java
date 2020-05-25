package jtm.activity07;

public class Bird extends Animal {

	private boolean canFly;

	public Bird() {
		super();
	}

	public Bird(int age) {
		super(age);
	}

	public boolean getCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

}
