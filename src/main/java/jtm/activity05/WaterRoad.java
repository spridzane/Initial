package jtm.activity05;

import jtm.activity04.Road;


public class WaterRoad extends Road{

	public WaterRoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WaterRoad(String from, String to, int distance) {
		super(from, to, distance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName() + super.toString();
	}
	
}
