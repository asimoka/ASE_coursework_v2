package rides;

//exception is thrown when a plate number has invalid format
public class incorrectLicensePlateException extends Exception {
	
	public  incorrectLicensePlateException (String incorrectPlate){
		super("Plate number has invalid format = " + incorrectPlate);
	}
	
}
