package rides;

/* Represents a valid destination destination.
 * a destination has destination name and distance in miles*/

public class Destination {
	//instance variables
	private String destinationName;
	private double distance;
	
	//constructor
	public Destination (String destName, double distance){
		this.destinationName=destName;
		this.distance=distance;
	}
	//getters and setters
	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	

}
