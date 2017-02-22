package rides;

public class Journey {
	private String taxiRegNumber;
	private String destination;
	private Double travelDistance;
	private int numPassengers;
	private Double fare;

	public Journey(String tRegNo, String dest, Double tDist, int numPass, Double fare) {
		if (validPlateNumber(tRegNo)) {
			this.taxiRegNumber=tRegNo;
		}
		else {
			throw new InputMismatchException("Licence Plate number is in the wrong format!");
		}
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
		if (validPlateNumber(taxiRegNumber)) {
			this.taxiRegNumber = taxiRegNumber;
		}
		else {
			throw new InputMismatchException("Licence Plate number is in the wrong format!");
		}
	}
	//check that license plate number is in the correct format
	public boolean validPlateNumber(String plateNumber) {
		//check license plate has the correct length
		if (plateNumber.length() != 7) {
			return false;
		}
		//check that licence plate has format letter, letter, digit, digit, letter, letter, letter
		if (Character.isLetter(plateNumber.charAt(0)) && Character.isLetter(plateNumber.charAt(2))
				&& Character.isDigit(plateNumber.charAt(3)) && Character.isDigit(plateNumber.charAt(4))
				&& Character.isLetter(plateNumber.charAt(5)) && Character.isLetter(plateNumber.charAt(6))
				&& Character.isLetter(plateNumber.charAt(7))) {
			return true;
		}
		else {
			return false;
		}
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
	
	public String toString() {
		String outputLine="\n"+String.format("%-10s", this.taxiRegNumber); 
		outputLine+=String.format("%-25s", this.destination) ;
		outputLine+=String.format("%4s", this.travelDistance)  ;
		outputLine+= " miles, "+String.format("%-2d", this.numPassengers)+ " people, " ;
		outputLine+=" Cost:" + String.format("%4s", this.fare);
		 return outputLine;
	}
}


