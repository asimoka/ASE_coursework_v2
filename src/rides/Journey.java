package rides;
/* Represents a single journey in a taxi
 * A journey has a taxi license plate number,
 * destination, distance, number of passengers
 * and a fare.
 * Includes Methods to get and set variables as well as
 * calculate the fare for the journey,
 * check that license plate number is valid
 * and represent a journey as a string
 * in a meaningful format.
 */

public class Journey {
	//instance variables
	private String taxiRegNumber;
	private String destination;
	private Double travelDistance;
	private int numPassengers;
	private Double fare;

	//constructor
	public Journey(String tRegNo, String dest, int numPass) { 
		
		this.taxiRegNumber=tRegNo;
		this.destination=dest;
		this.travelDistance=0.0;
		this.numPassengers=numPass;
		this.fare=calculateFare();
	}
	
	//calculate the fare for this journey
	public Double calculateFare() {
		double taxiFare = 0;
		double halfPriceDestination=0;
		double fullPriceDestination=0;
		//if a journey has distance greater than 10 miles, any mile after 10 is half price
		if (travelDistance>10) {
			halfPriceDestination=travelDistance-10;
			fullPriceDestination=10.0;
			taxiFare=(fullPriceDestination*2+halfPriceDestination);
			}
	 	else {
	 		//if a journey is less than 10 miles, it is full price. Fare = 2* distance in miles
	 		taxiFare=(travelDistance*2);
	 		}
		//if a journey is less than 1.5 miles, charge £3
		if (taxiFare<3){
			taxiFare=3.0;
			}
		return taxiFare;
	}
	
	
	//getter and setter for taxiRegNumber
	public String getTaxiRegNumber() {
		return taxiRegNumber;
	}
	
	//set taxi license plate number. If plate number is in the wrong format, throw an exception
	public void setTaxiRegNumber(String taxiRegNumber) throws incorrectLicensePlateException {
		if (validPlateNumber(taxiRegNumber)) {
			this.taxiRegNumber = taxiRegNumber;
		}
		else {
			throw new incorrectLicensePlateException(taxiRegNumber);
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
	
	//returns a formatted string describing the journey
	public String toString() {
		return "" + String.format("%-10s", this.taxiRegNumber) + 
			String.format("%-28s", this.destination) +
			String.format("%4s", this.travelDistance)  +
			" miles, "+String.format("%-2d", this.numPassengers)+ " people, " +
			" Cost:" + String.format("%4s", this.fare);
	}
	
	//check that license plate number is in the correct format
	public boolean validPlateNumber(String plateNumber) {
		//check license plate has the correct length
		if (plateNumber.trim().length() != 7) {
			return false;
		}
		//check that licence plate has format letter, letter, digit, digit, letter, letter, letter
		if (Character.isLetter(plateNumber.charAt(0)) && Character.isLetter(plateNumber.charAt(1))
				&& Character.isDigit(plateNumber.charAt(2)) && Character.isDigit(plateNumber.charAt(3))
				&& Character.isLetter(plateNumber.charAt(4)) && Character.isLetter(plateNumber.charAt(5))
				&& Character.isLetter(plateNumber.charAt(6))) {
			return true;
		}
		else {
			return false;
		}
	}

}


