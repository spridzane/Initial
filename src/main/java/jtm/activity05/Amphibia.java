package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Amphibia extends Transport{
	
	private byte sails;
	private int wheels;

	public Amphibia(String id, float consumption, int tankSize, byte sails, int wheels) {
		super(id, consumption, tankSize);
		this.sails = sails;
		this.wheels = wheels;
	}


	@Override
	public String move(Road road) {
		
		if(road instanceof WaterRoad) {
	
				return super.getType() + " is sailing on " +  road.toString() + " with " + sails + " sails";
				
		}else{
			super.move(road);
				return super.getType() + " is driving on " + road.toString() + " with "
						+ wheels + " wheels";
			}	
		}
}



