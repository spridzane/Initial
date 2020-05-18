package jtm.activity04;

public class TrafficManagementSystem {
	static Transport[] transports;
	static Road[] roads;

	/**
	 * This method is called to set up TransportManagementSystem
	 * 
	 * @param roads
	 * @param transports
	 */
	public static void initSystem(int roads, int transports) {
		addRoads(roads);
		addTransport(transports);
	}

	public static Transport[] getTransports() {
		// TODO return required value

		return transports;
	}

	public static void addTransport(int i) {
		// TODO create new array of transports in size of passed value
		transports = new Transport[i];
	}

	public static void setVehicle(Transport transport, int i) {
		// TODO set passed transport into transports array cell of passed index
		
		for (int j = 0; j<transports.length; j++){
			
			if (j == i) {
				transports[j] = transport;
		}
	}
	}
	public static void addRoads(int i) {
		// TODO create new array of roads in size of passed value
		roads = new Road[i];
	}

	public static Road[] getRoads() {
		// TODO return required value
		return roads;
	}

	public static void setRoad(Road road, int i) {
		// TODO set passed road into passed cell of roads array
		for (int j = 0; j < roads.length; j++) {
			if(j==i) {
				roads[j] = road;
			}
		}

	}


		return null;
	}

	public static void addTransport(int i) {
		// TODO create new array of transports in size of passed value
	}

	public static void setVehicle(Transport transport, int i) {
		// TODO set passed transport into transports array cell of passed index
	}

	public static void addRoads(int i) {
		// TODO create new array of roads in size of passed value
	}

	public static Road[] getRoads() {
		// TODO return required value
		return null;
	}

	public static void setRoad(Road road, int i) {
		// TODO set passed road into passed cell of roads array
	}


}
