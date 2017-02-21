package rides;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class RideList {
	private ArrayList<Taxi> taxiList;
	private ArrayList<Destination> destinationList;
	private ArrayList<Journey> journeyList;
	private ArrayList<Journey> journeyLastYearList;

	
	
	public RideList() {
		taxiList = new ArrayList<Taxi> ();
		destinationList = new ArrayList<Destination>();
		journeyList = new ArrayList<Journey>();
		journeyLastYearList = new ArrayList<Journey>();
	}
	
	public void addTaxi(Taxi t) {
		taxiList.add (t);
	}
	public void addDestination(Destination d) {
		destinationList.add(d);
	}
	public void addJourney(Journey j) {
		journeyList.add(j);
	}
	public void addJourneyLastYear(Journey q) {
		journeyLastYearList.add(q);
	}
	
	public Destination getDestination(int i){
		return destinationList.get(i);
	}
	public Journey getJourney(int i){
		return journeyList.get(i);
	}
	public Journey getJourneyLastYear(int i){
		return journeyLastYearList.get(i);
	}
	//method to Sort JourneyList by highest Fare Price
	public ArrayList<Journey> sortByFarePrice() {              
     Collections.sort(journeyList, new JourneyFareComparator());
     return journeyList;
    }
	
	public String getFiveExpensiveJourney() {
		String fiveExpJourney="";
		
		for (int i=0;i<5;i++)
		{
			fiveExpJourney += sortByFarePrice().get(i).toString();
		}
		
		return fiveExpJourney;
	}
	
	public String getFiveCheapJourney() {
		String fiveCheapJourney="";
		int l = journeyList.size();
		
		for (int i=l-5;i<l;i++)
		{
			fiveCheapJourney += sortByFarePrice().get(i).toString();
		}
		return fiveCheapJourney;
		
	}
	
	

}
