
public class trips {
	private Taxi taxi;
	private Destination destination;
	private int passengerQuantity;
	private int tripYear;
	private double tripTime;//should be given in minutes
	
	public trips (Taxi taxi, Destination destination, int passQuant, int tripYear, double tripTime){
		this.taxi=taxi;
		this.destination=destination;
		this.passengerQuantity=passQuant;
		this.tripYear=tripYear;
		this.tripTime=tripTime;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public int getPassengerQuantity() {
		return passengerQuantity;
	}

	public void setPassengerQuantity(int passengerQuantity) {
		this.passengerQuantity = passengerQuantity;
	}

	public void setTripYear(int tripYear) {
		this.tripYear = tripYear;
	}
	
	public int getTripYear(){
		return this.tripYear;
	}
	
	
	
	public double getTripTime() {
		return tripTime;
	}

	public void setTripTime(double tripTime) {
		this.tripTime = tripTime;
	}

	public double fareCalculation(){
		int minTime=5;
		double fare=(getDestination().getDistance()*2)+(getTripTime()*minTime*0.15);
		if (fare<3){return 3;}
		return fare;
	}


}