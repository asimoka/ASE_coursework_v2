package rides;

public class noMatchingDestinationException extends Exception {

	public  noMatchingDestinationException (String noMatch){
		super("There is no such valid destination = " + noMatch);
	}
}
