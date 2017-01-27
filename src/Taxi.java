
public class Taxi {
	private String plateNumber;
	private String driverName;
	
	public Taxi(String plateNo, String driverName){
		this.plateNumber=plateNo;
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
	
	

}
