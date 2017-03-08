package rides;
  /* Class containing all data structures used to store input details
   * and methods which populate these data structures and process the
   * information for output
   *  
   *  */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class RideList {
	//instance variables
	private ArrayList<Taxi> taxiList;
	private ArrayList<Destination> destinationList;
	private ArrayList<Journey> journeyList;
	private ArrayList<String> journeyLastYearList;

	
	//constructor
	public RideList() {
		taxiList = new ArrayList<Taxi> ();
		destinationList = new ArrayList<Destination>();
		journeyList = new ArrayList<Journey>();
		journeyLastYearList = new ArrayList<String>();
	}
	//add a taxi to the list of valid taxi details
	public void addTaxi(Taxi t) {
		taxiList.add (t);
	}
	//add a destination to the list of valid destination details
	public void addDestination(Destination d) {
		destinationList.add(d);
	}
	//add a journey to the list of journeys from this year
	public void addJourney(Journey j) throws noMatchingDestinationException, noMatchingDriverException {
		//check reg number and destination are valid and add
		if (validDestination(j.getDestination()) && validTaxi(j.getTaxiRegNumber())) {
			//find the travel distance  from the list of valid destinations
			j.setTravelDistance(getDistanceFromDestination(j.getDestination()));
			j.setFare(j.calculateFare());
			//add to list of journeys
			journeyList.add(j);
		}
		else if (validTaxi(j.getDestination())) {
			//if taxi is valid but destination is not
			throw new noMatchingDestinationException(j.getDestination());
		}
		else {
			//if taxi is not valid
			throw new noMatchingDriverException(j.getTaxiRegNumber());
		}
	}
	//add a journey to the list of journeys from last year
	public void addJourneyLastYear(String string) {
		journeyLastYearList.add(string);
	}
	//returns a destination at position i in the list of valid destinations
	public Destination getDestination(int i){
		return destinationList.get(i);
	}
	//returns a journey at position i in the list of journeys from this year
	public Journey getJourney(int i){
		return journeyList.get(i);
	}
	//returns a journey at position i in the list of journeys from last year
	public String getJourneyLastYear(int i){
		return journeyLastYearList.get(i);
	}
	
	//Sort JourneyList by Fare Price
	public ArrayList<Journey> sortByFarePrice() {              
     Collections.sort(journeyList, new JourneyFareComparator());
     return journeyList;
    }
	
	//returns a set of every destination visited this year but not last
	public Set<String> getDestinationsThisYear() {
		//initialise hashset
		Set<String> journeys = new HashSet<String>();
		//search through this year's journey details
		for (Journey j : journeyList) {
			//if the detination was not visited last year, add to hashset
			//the hash set will only add a new element if it is unique
			if (!journeyLastYearList.contains(j)) {
				journeys.add(j.getDestination());
			}
		}
		//return the complete hashset
		return journeys;
		
	}
	//returns a set of every destination visited last year but not this year
	public Set<String> getDestinationsLastYear() {
		//initialize hashset and boolean to check if destination was reached this year
		Set<String> journeys = new HashSet<String>();
		boolean thisYear;
		//search through last year’s journey details
		for (String j : journeyLastYearList) {
			thisYear = false;
			//check list of this year’s journeys if destination was visited this year
			for (Journey k : journeyList) {
				if(k.getDestination().equals(j)) {
					thisYear = true;
		        }
			}
			//if destination was not visited this year, add to last year only hash set
			if (!thisYear) {
				//the hash set will only add a new element if it is unique
				journeys.add(j);
			}
		}
		return journeys;
		
	}

	//returns a set of all destinations visited in both years
	public Set<String> getDestinationsBothYears() {
		
		Set<String> journeys = new HashSet<String>();
		//search through this year's journey details
		for (Journey j : journeyList) {
			//if the destination was visited last year too, add it to the hashset
			if (journeyLastYearList.contains(j.getDestination())) {
				//the hash set will only add a new element if it is unique
				journeys.add(j.getDestination());
			}
		}
		//return the hash set
		return journeys;
		
	}
	
	//return a treemap containing destinations visited by each driver
	public TreeMap< String,ArrayList<String>> treeByDrivers()
	{
		//initializing new tree map to hold the details of the destinations driven by each driver
		TreeMap< String,ArrayList<String>> destinationsList = new TreeMap< String,ArrayList<String>>();
		//adds each new driver as a key and new destination as a value into a list
		for (Journey j :journeyList){
			String plateNum=j.getTaxiRegNumber();
			String driverName;
			try {
				driverName = driverNamebyRegNumb(plateNum);
			} catch (noMatchingDriverException e) {
				// if no matching driver exists, block out name
				driverName="--"+e;
			}
			//if the treemap already contains this driver, add the destination to this driver's list
			if (destinationsList.containsKey(driverName)) 
			{
				destinationsList.get(driverName).add(j.getDestination());
			}
			else
				//if driver does not already exist in treemap, add them
			{
				ArrayList<String> al=new ArrayList<String>();
				al.add(j.getDestination());
				destinationsList.put(driverName, al);
			}
		}
		return destinationsList;
	}
	
	//method for getting drivers names by taxi registration number
	public String driverNamebyRegNumb(String regNumber) throws noMatchingDriverException
	{
		//search through list of valid taxi details
		for (int i=0;i<taxiList.size();i++)
		{
			//when the input license plate number is found, return the corresponding driver name
			if (taxiList.get(i).getPlateNumber().equals(regNumber))
			{ //returns driver name
				return taxiList.get(i).getCarType();
			}
		}
		throw new noMatchingDriverException(regNumber);
	}
	
	//returns the distance of a destination from it's name
	public Double getDistanceFromDestination(String destn) {
		//search through list of valid destinations
		for (Destination d : destinationList) {
			if (d.getDestinationName().equals(destn)) {
				//return the distance of the destination corresponding to input name
				return d.getDistance();
			}
		}
		//if no such valid destination, return zero
		return 0.0;
	}
	
	//check whether a destination is included in the list of valid destinations
	public boolean validDestination(String destn) {
		for (Destination d : destinationList) {
			if (d.getDestinationName().equals(destn)) {
				return true;
			}
		}
		return false;
	}
	//check whether a taxi license plate number is included in the list of valid taxis
	public boolean validTaxi(String regNum) {
		for (Taxi t : taxiList) {
			if (t.getPlateNumber().equals(regNum)) {
				return true;
			}
		}
		return false;
	}

	

}
