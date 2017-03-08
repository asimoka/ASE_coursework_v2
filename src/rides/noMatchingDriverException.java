package rides;

public class noMatchingDriverException extends Exception {
	
	public  noMatchingDriverException (String noMatch){
		super("There is no taxi with this plate Number = " + noMatch);
	}
}
