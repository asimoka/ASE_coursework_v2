package rides;


public class Journey {
	private String taxiRegNumber;
	private String destination;
	private Double travelDistance;
	private int numPassengers;
	private Double fare;

	public Journey(String tRegNo, String dest, int numPass) { 
		
		this.taxiRegNumber=tRegNo;
		this.destination=dest;
		this.travelDistance=0.0;
		this.numPassengers=numPass;
		this.fare=calculateFare();
	}
	
	
	public Double calculateFare() {
		double taxiFare = 0;
		double halfPriceDestination=0;
		double fullPriceDestination=0;
		
		if (travelDistance>10) {
			halfPriceDestination=travelDistance-10;
			fullPriceDestination=10.0;
			taxiFare=(fullPriceDestination*2+halfPriceDestination);
			}
	 	else {
	 		taxiFare=(travelDistance*2);
	 		}
		if (taxiFare<3){
			taxiFare=3.0;
			}
		return taxiFare;
	}
	
	
	//getter and setter for taxiRegNumber
	public String getTaxiRegNumber() {
		return taxiRegNumber;
	}
	
	public void setTaxiRegNumber(String taxiRegNumber) throws incorrectLicensePlateException {
		//if (validPlateNumber(taxiRegNumber)) {
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
	
	public String toString() {
		return "" + String.format("%-10s", this.taxiRegNumber) + 
			String.format("%-25s", this.destination) +
			String.format("%4s", this.travelDistance)  +
			" miles, "+String.format("%-2d", this.numPassengers)+ " people, " +
			" Cost:" + String.format("%4s", this.fare);
/*		String outputLine=""+String.format("%-10s", this.taxiRegNumber); 
		outputLine+=String.format("%-25s", this.destination) ;
		outputLine+=String.format("%4s", this.travelDistance)  ;
		outputLine+= " miles, "+String.format("%-2d", this.numPassengers)+ " people, " ;
		outputLine+=" Cost:" + String.format("%4s", this.fare);
		 return outputLine;*/
	}

}


