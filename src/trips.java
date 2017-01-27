
public class trips {
	private Taxi taxi;
	private Destination destination;
	private int passengerQuantity;
	private String date;
	
	public trips (Taxi taxi, Destination destination, int passQuant, String date){
		this.taxi=taxi;
		this.destination=destination;
		this.passengerQuantity=passQuant;
		this.date=date;
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

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getYear(){
		return this.date.substring(6, 10);
	}


}
