package rides;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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
		if (destinationList.contains(j.getDestination()) ) {
			journeyList.add(j);
		}
		else {
			throw new InputMismatchException("Destination specified for journey is not a valid destination!");
		}
	}
	public void addJourneyLastYear(Journey q) {
		if (destinationList.contains(q.getDestination()) ) {
			journeyLastYearList.add(q);
		}
		else {
			throw new InputMismatchException("Destination specified for journey is not a valid destination!");
		}
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
	
	//returns a report of the 5 most expensive journeys
	public String getFiveExpensiveCheapestJourney() {
		String fiveExpJourney="CHARGES FOR THE TOP 5 MOST EXPENSIVE JOURNEYS \n";
		
		for (int i=0;i<5;i++)
		{
			fiveExpJourney += sortByFarePrice().get(i).toString();
		}
		
		fiveExpJourney +="\n\nCHARGES FOR THE TOP 5 LEAST EXPENSIVE JOURNEYS \n";
		int l = journeyList.size();
		
		for (int i=l-5;i<l;i++)
		{
			fiveExpJourney += sortByFarePrice().get(i).toString();
		}
		return fiveExpJourney;
	}
	
	public ArrayList<String> getDestinationsThisYear() {
		
		ArrayList<String> journeys = new ArrayList<String>();
		
		for (Journey j : journeyList) {
			if (!journeyLastYearList.contains(j)) {
				journeys.add(j.getDestination());
			}
		}
		
		return journeys;
		
	}
	
	public ArrayList<String> getDestinationsLastYear() {
		
		ArrayList<String> journeys = new ArrayList<String>();
		
		for (Journey j : journeyLastYearList) {
			if (!journeyList.contains(j)) {
				journeys.add(j.getDestination());
			}
		}
		
		return journeys;
		
	}

	public ArrayList<String> getDestinationsBothYears() {
		
ArrayList<String> journeys = new ArrayList<String>();
		
		for (Journey j : journeyList) {
			if (journeyLastYearList.contains(j)) {
				journeys.add(j.getDestination());
			}
		}
		
		return journeys;
		
	}
	
	public TreeMap< String,ArrayList<String>> treeByDrivers()
	{
		//initializing new tree map to hold the details of the destinations driven by each driver
		TreeMap< String,ArrayList<String>> destinationsList = new TreeMap< String,ArrayList<String>>();
		//adds each new driver as a key and new destination as a value into a list
		for (Journey j :journeyList){
			String plateNum=j.getTaxiRegNumber();
			String driverName=driverNamebyRegNumb(plateNum);
			if (destinationsList.containsKey(driverName)) 
			{
				destinationsList.get(driverName).add(j.getDestination());
			}
			else
			{
				ArrayList<String> al=new ArrayList<String>();
				al.add(j.getDestination());
				destinationsList.put(driverName, al);
			}
		}
		return destinationsList;
	}
	
	//method for getting drivers names by taxi registration number
	public String driverNamebyRegNumb(String regNumber)
	{
		
		for (int i=0;i<taxiList.size();i++)
		{
			if (taxiList.get(i).getPlateNumber().equals(regNumber))
			{ 
				
				return taxiList.get(i).getCarType();
			}
			
		}
		return "--Driver not found--";
	
	}
	
	//method that creates a string version of the treemap values ready for output
	public String outputTreeByDrivers()
	{
		String output="";
		Set< Map.Entry< String,ArrayList<String>> > treeSetView = treeByDrivers().entrySet();
		for (Map.Entry< String,ArrayList<String>> byDrivers:treeSetView)
		{
			output+=byDrivers.getKey()+":\n";
			for (String destinations:byDrivers.getValue())
			{
				output+="     "+destinations+"\n";
			}
		}
		return output;
	}
	
	
	

}
