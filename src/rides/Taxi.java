package rides;

public class Taxi {
	private String plateNumber;
	private String driverName;
	private String carType;
	
	public Taxi(String plateNo, String carType, String driverName){
		if (validPlateNumber(plateNumber)) {
			this.plateNumber=plateNo;
		}
		else {
			throw new InputMismatchException("Licence Plate number is in the wrong format!");
		}
		this.carType=carType;
		this.driverName=driverName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		if (validPlateNumber(plateNumber)) {
			this.plateNumber = plateNumber;
		}
		else {
			throw new InputMismatchException("Licence Plate number is in the wrong format!");
		}
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	
		//check that license plate number is in the correct format
	public bool validPlateNumber(String plateNumber) {
		//check license plate has the correct length
		if (plateNumber.length != 7) {
			return false;
		}
		//check that licence plate has format letter, letter, digit, digit, letter, letter, letter
		if (plateNumber.charAt(0).isLetter() && plateNumber.charAt(2).isLetter()
				&& plateNumber.charAt(3).isDigit() && && plateNumber.charAt(4).isDigit()
				&& plateNumber.charAt(5).isLetter() && plateNumber.charAt(6).isLetter()
				&& plateNumber.charAt(7).isLetter()) {
			return true;
		}
		else {
			return false;
		}
	}

}
