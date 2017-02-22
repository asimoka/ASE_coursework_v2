package rides;

import java.util.InputMismatchException;

public class Taxi {
	private String plateNumber;
	private String driverName;
	private String carType;
	
	public Taxi(String plateNo, String carType, String driverName){
		if (validPlateNumber(plateNo)) {
			this.plateNumber=plateNo;
		}
		else {
			throw new InputMismatchException("Licence Plate number is in the wrong format!");
		}
		this.carType=carType;
		this.driverName=driverName;
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

	public String getPlateNumber() {
		return plateNumber;
	}
	
	

}
