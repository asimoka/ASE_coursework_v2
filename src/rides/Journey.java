package rides;

public class Journey {
	private String taxiRegNumber;
	private String destination;
	private Double travelDistance;
	private int numPassengers;
	private Double fare;

	public Journey(String tRegNo, String dest, Double tDist, int numPass, Double fare) {
		this.taxiRegNumber=tRegNo;
		this.destination=dest;
		this.travelDistance=tDist;
		this.numPassengers=numPass;
		this.fare=fare;
	}
	
	//getter and setter for taxiRegNumber
	public String getTaxiRegNumber() {
		return taxiRegNumber;
	}
	public void setTaxiRegNumber(String taxiRegNumber) {
		this.taxiRegNumber = taxiRegNumber;
	}
	
	//getter and setter for destination
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	//getter and setter for travelDistance
	public Double getTravelDistance() {
		return travelDistance;
	}
	public void setTravelDistance(Double travelDistance) {
		this.travelDistance = travelDistance;
	}
	
	//getter and setter for numPassengers
	public int getnumPassengers() {
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers) {
		this.numPassengers = numPassengers;
	}
	
	//getter and setter for fare
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	
	public String toString(Journey j) {
		return "The trip to " + getDestination() + " cost " + Double.toString(getFare()) + ".";
	}

}


