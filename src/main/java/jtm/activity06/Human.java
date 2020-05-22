package jtm.activity06;

public class Human implements Humanoid {
	
	private int weight;
	private int stomach;
	private boolean isAlive;


	public Human() {
		weight = BirthWeight;
		stomach = 0;
		isAlive = true;
	}
	
	@Override
	public void eat(Integer food) {
		// If Humanoid's stomach is empty, eat food and gain weight
		if(stomach == 0) {
			stomach += food;
			weight += food;
		}
	}
	@Override
	public Integer vomit() {
		// return eaten food from the stomach
		int vomitSize = stomach;
		if(stomach != 0) {
			weight -= stomach;
			stomach = 0;
		}
		return vomitSize;
	}

	@Override
	public String isAlive() {
		// "Alive" or "Dead" depending on his status
		if(isAlive) {
			return "Alive";
		}
		return "Dead";
	}

	@Override
	public String killHimself() {
		// "Dead" if humanoid successfully killed himself
		isAlive = false;
		return "Dead";
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		int currentWeight = BirthWeight + stomach;
		return currentWeight;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + weight + " [" + stomach + "]";
	}

}
