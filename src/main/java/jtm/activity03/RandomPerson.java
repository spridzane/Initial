package jtm.activity03;

public class RandomPerson {

	// TODO Create _private_ structure of random person to store values:
	// name as String,
	private String name;
	// age as int,
	private int age;
	// weight as float,
	private float weight;
	// isFemale as boolean;
	private boolean isFemale;
	// smile as char
	private char smile;
	// HINT: use Alt+Shift+A to switch to Block selection (multi-line cursor)
	// to edit list of all properties at once
	
	// TODO Select menu "Source — Generate Getters and Setters..." then select
	
	
	// all properties and generate _public_ getters and setters for all of them
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public float getWeight() {
		return weight;
	}
	public boolean isFemale() {
		return isFemale;
	}
	public char getSmile() {
		return smile;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setFemale(boolean isFemale) {
		this.isFemale = isFemale;
	}
	public void setSmile(char smile) {
		this.smile = smile;
	}


}
