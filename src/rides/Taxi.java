package rides;
/* Class representing a Taxi belonging to the company
 * a taxi has a license plate number, a driver name and a car type
 * contains methods to check inputted license plate number,
 * getters and setters
 */

public class Taxi {
	private String plateNumber;
	private String driverName;
	private String carType;
	
	public Taxi(String plateNo, String carType, String driverName) throws incorrectLicensePlateException{
		if (validPlateNumber(plateNo)) {
			this.plateNumber=plateNo;
		}
		else {
			throw new incorrectLicensePlateException(plateNo);
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

	//getters and setters
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
