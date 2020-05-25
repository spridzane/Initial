package jtm.extra04;

public class Car {

	private String model;
	private Integer year;
	private String color;
	private Float price;

	// TODO #1 generate public constructor which takes all properties of an
	// object as parameters
	public Car(String model, Integer year, String color, Float price) {
		super();
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}

	// TODO #2 generate public getters of all object properties

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
