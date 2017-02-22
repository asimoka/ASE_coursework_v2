package rides;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.Attributes.Name;

public class RideList {
	private ArrayList<Taxi> taxiList;
	private ArrayList<Destination> destinationList;
	private ArrayList<Journey> journeyList;
	private ArrayList<String> journeyLastYearList;

	
	
	public RideList() {
		taxiList = new ArrayList<Taxi> ();
		destinationList = new ArrayList<Destination>();
		journeyList = new ArrayList<Journey>();
		journeyLastYearList = new ArrayList<String>();
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
	public void addJourneyLastYear(String string) {
		journeyLastYearList.add(string);
	}
	
	public Destination getDestination(int i){
		return destinationList.get(i);
	}
	public Journey getJourney(int i){
		return journeyList.get(i);
	}
	public String getJourneyLastYear(int i){
		return journeyLastYearList.get(i);
	}
	//method to Sort JourneyList by highest Fare Price
	public ArrayList<Journey> sortByFarePrice() {              
     Collections.sort(journeyList, new JourneyFareComparator());
     return journeyList;
    }
	
	//returns a report of the 5 most expensive journeys
	public String getFiveExpensiveCheapestJourney() {
		String fiveExpJourney="CHARGES FOR THE TOP 5 LEAST EXPENSIVE JOURNEYS \n";
		
		for (int i=0;i<5;i++)
		{
			fiveExpJourney += sortByFarePrice().get(i).toString()+"\n";
		}
		
		fiveExpJourney +="\n\nCHARGES FOR THE TOP 5 MOST EXPENSIVE JOURNEYS \n";
		int l = journeyList.size();
		
		for (int i=l-5;i<l;i++)
		{
			fiveExpJourney += sortByFarePrice().get(i).toString()+"\n";
		}
		return fiveExpJourney;
	}
	
	
	
	public Set<String> getDestinationsThisYear() {
		
		Set<String> journeys = new HashSet<String>();
		
		for (Journey j : journeyList) {
			if (!journeyLastYearList.contains(j)) {
				journeys.add(j.getDestination());
			}
		}
		
		return journeys;
		
	}
	
	public Set<String> getDestinationsLastYear() {
		
		Set<String> journeys = new HashSet<String>();
		boolean thisYear;
		
		for (String j : journeyLastYearList) {
			thisYear = false;
			for (Journey k : journeyList) {
				if(k.getDestination().equals(j)) {
					thisYear = true;
		        }
			}
			if (!thisYear) {
				journeys.add(j);
			}
		}
		
		return journeys;
		
	}

	public Set<String> getDestinationsBothYears() {
		
		Set<String> journeys = new HashSet<String>();
		
		for (Journey j : journeyList) {
			if (journeyLastYearList.contains(j.getDestination())) {
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
			String driverName;
			try {
				driverName = driverNamebyRegNumb(plateNum);
			} catch (noMatchingDriverException e) {
				// TODO Auto-generated catch block
				driverName="--"+e;
			}
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
	public String driverNamebyRegNumb(String regNumber) throws noMatchingDriverException
	{
		for (int i=0;i<taxiList.size();i++)
		{
			if (taxiList.get(i).getPlateNumber().equals(regNumber))
			{ 
				
				return taxiList.get(i).getCarType();
			}
			
		}
		throw new noMatchingDriverException(regNumber);
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
