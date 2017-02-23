package rides;

  /*exception class that throws an error 
   *if the given plate number in a journey object doesn't 
   *exist in the given taxi list*/
public class noMatchingDriverException extends Exception {
	
	public  noMatchingDriverException (String noMatch){
		//message to be displayed in case of mismatch
		super("There is no taxi with this plate Number = " + noMatch);
	}
}
