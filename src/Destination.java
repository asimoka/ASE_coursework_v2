
public class Destination {
	private String destinationName;
	private double distance;
	
	public Destination (String destName, double distance){
		this.destinationName=destName;
		this.distance=distance;
	}

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
