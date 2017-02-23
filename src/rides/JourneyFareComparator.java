package rides;
import java.util.Comparator;


public class JourneyFareComparator implements Comparator<Journey>{

	//defines an ordering of the list according to the fare of the journey
	public int compare(Journey j1, Journey j2) 
	{
		return j1.getFare().compareTo(j2.getFare());
	}
}


