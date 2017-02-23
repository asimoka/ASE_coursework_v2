package rides;

  /*exception class that throws an error 
   *if the given plate number of wrong format (not corresponding
   to the given format)*/
public class noMatchingDriverException extends Exception {
	
	public  noMatchingDriverException (String noMatch){
		//message to be displayed in case of mismatch
		super("There is no taxi with this plate Number = " + noMatch);
	}
}
