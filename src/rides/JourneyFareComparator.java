package rides;
import java.util.Comparator;


public class JourneyFareComparator implements Comparator<Journey>{

	//defines an ordering on competitors objects on the name
	public int compare(Journey j1, Journey j2) 
	{
		return j1.getFare().compareTo(j2.getFare());
	}
}


