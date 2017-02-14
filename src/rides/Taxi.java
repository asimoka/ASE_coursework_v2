package rides;

public class Taxi {
	private String plateNumber;
	private String driverName;
	private String carType;
	
	public Taxi(String plateNo, String carType, String driverName){
		this.plateNumber=plateNo;
		this.carType=carType;
		this.driverName=driverName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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
	
	public boolean validPlateNumber(String plateNumber){
		if (plateNumber.length()!=8){return false;}
		String letters=plateNumber.substring(0, 1).concat(plateNumber.substring(5, 7));
		for (int i=0;i<=letters.length();i++){
			if (!(letters.charAt(i) >= 'A' && letters.charAt(i) <= 'Z')){return false;}
		}
		if (!(letters.charAt(2) >= '0' && letters.charAt(2) <= '9')){return false;}
		if (!(letters.charAt(3) >= '0' && letters.charAt(4) <= '9')){return false;}
		if (letters.charAt(4) != ' '){return false;}
		return true;
	}

}