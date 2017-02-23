package rides;

  /*destination class holds all details of each destination
   *that includes such information as destination name
   *and distance in miles*/


public class Destination {
	private String destinationName;
	private double distance;
	
	//constructor for the Destination class
	public Destination (String destName, double distance){
		this.destinationName=destName;
		this.distance=distance;
	}

	//getters and setters for all instance variables
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
