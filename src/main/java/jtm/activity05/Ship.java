package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Ship extends Transport {

	protected byte sails;

	public Ship(String id, byte sails) {
		super(id);
		this.sails = sails;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String move(Road road) {
		
		if(road instanceof WaterRoad) {
		// TODO Auto-generated method stub
			
		return super.getType() + " is sailing on " +  road.toString() + " with " + sails + " sails";
	}else {
		return "Cannot sail on " + road.toString();
	}
		
	}
	
	
}
