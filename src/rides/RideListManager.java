package rides;
/*
 * This class deals with all of the I/O between the program
 * and the input/output text files. 
 * 
 * 
 * 
 * 
 * 
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class RideListManager {

	private RideList rideList;
	private String	 taxiRegNumber;
	private String 	 destination;
	private Double 	 travelDistance;
	private int 	 numPassengers;
	private String 	 driverName;
	private String 	 carType;

	public RideListManager(String taxiInput, String destinationInput, String journeysInput, String lastYearInput)
	{
		//Initialize empty list of Competitors
		rideList = new RideList();
		addRides(taxiInput, destinationInput, journeysInput, lastYearInput); 

	}
	
	
	private void addRides(String taxiInput, String destinationInput, String journeysInput, String lastYearInput) 
	{
		//load data from files;
		BufferedReader taxiBuff = null;
		BufferedReader destBuff = null;
		BufferedReader journeyBuff = null;
		BufferedReader lastYearBuff = null;

		try 
		{
			//create buffer for taxi file and read each line
			taxiBuff = new BufferedReader(new FileReader(taxiInput));
			String inputTaxiLine = taxiBuff.readLine();  //read first line
			while(inputTaxiLine != null)
			{  
				//stores details from this line in RideList class
				processLineTaxi(inputTaxiLine);
				//read next line
				inputTaxiLine = taxiBuff.readLine();
			}
			//create buffer for destination file and read each line
			destBuff = new BufferedReader(new FileReader(destinationInput));
			String inputDestLine = destBuff.readLine();  //read first line
			while(inputDestLine != null)
			{  
				//stores details from this line in RideList class
				processLineDestination(inputDestLine);
				//read next line
				inputDestLine = destBuff.readLine();
			}
			//create buffer for journeys file and read each line
			journeyBuff = new BufferedReader(new FileReader(journeysInput));
			String inputJourneyLine = journeyBuff.readLine();  //read first line
			while(inputJourneyLine != null)
			{  
				//stores details from this line in RideList class
				processLineJourney(inputJourneyLine);
				//read next line
				inputJourneyLine = journeyBuff.readLine();
			}
			//create buffer for last year's destinations file and read each line
			lastYearBuff = new BufferedReader(new FileReader(lastYearInput));
			String inputLastYearLine = lastYearBuff.readLine();  //read first line
			while(inputLastYearLine != null)
			{  
				//stores details from this line in RideList class
				processLineLastYear(inputLastYearLine);
				//read next line
				inputLastYearLine = lastYearBuff.readLine();
			}
		}
		//catches if no file was found
		catch(FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(1);        	
		}
		finally  
		{
			try
			{
				taxiBuff.close();
				destBuff.close();
				journeyBuff.close();
				lastYearBuff.close();
			}
			catch (IOException ioe) 
			{
				//don't do anything
			}
		}   	
	}
	//

	public void processLineJourney(String line) {
		try {
			//split up the information in this line by commas into license plate number, destination, distance and number of passengers
			String [] inline = line.split(",");
			taxiRegNumber = inline[0];
			destination = inline[1];
			//convert numerical value to the correct format
			//travelDistance = Double.parseDouble(inline[3]);
			numPassengers = Integer.parseInt(inline[2]);

			//create a new object of type journey and add it to the list of journeys
			Journey j = new Journey(taxiRegNumber, destination, numPassengers);
			rideList.addJourney(j);
		}
		//if one of the numeric values is not in the correct format to be converted to double/integer
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "' - " + nfe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
		catch (noMatchingDestinationException nmde) {
			//just don't add this line
		}
		catch (noMatchingDriverException nmde) {
			//just don't add this line
		}

	}

	public void processLineTaxi(String line) {
		try {
			//split input line by commas
			String [] inline = line.split(",");
			//retrieve taxi details
			taxiRegNumber = inline[1];
			driverName = inline[0];
			carType = inline[2];
			//create new instance of taxi with the above details and add it to the list of valid taxis
			Taxi t = new Taxi(taxiRegNumber, driverName, carType);
			rideList.addTaxi(t);
		}
		//if license plate was not in the correct format
		catch (incorrectLicensePlateException ilpe) {
			String error = "Input mismatch error in '"
					+ line + "' - " + ilpe.getMessage();
			System.out.println(error);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}

	}

	public void processLineDestination(String line) {
		try {
			//split the line up into destination name and travel distance
			String [] inline = line.split(",");
			destination = inline[0];
			travelDistance = Double.parseDouble(inline[1]);

			//create a new ofbject of type destination using these values
			Destination j = new Destination(destination, travelDistance);
			//add to the arraylist for valid destinations
			rideList.addDestination(j);
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}

	public void processLineLastYear(String line) {
		try {
			//add this journey destination to the arraylist
			rideList.addJourneyLastYear(line.trim());
		}
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}

	}



	public String outputJourneysByYear()
	{
		String output="";
		//initialise hashsets and fill them with information on destinations for each year
		Set<String> journeysThisYear  = rideList.getDestinationsThisYear();
		Set<String> journeysLastYear  = rideList.getDestinationsLastYear();
		Set<String> journeysBothYears = rideList.getDestinationsBothYears();

		output += journeysThisYear.size() + " NEW PLACES IN 2017. \n";
		//print each journey visited this year only on a separate line
		for (String j : journeysThisYear) {
			output += j + "\n";
		}

		output += journeysLastYear.size() + " PLACES VISITED IN 2016 ONLY. \n";
		//print each journey visited last year only on a separate line
		for (String j : journeysLastYear) {
			output += j + "\n";
		}

		output += journeysBothYears.size() + " PLACES VISITED IN BOTH 2016 AND 2017. \n";
		//print each journey visited both years on a separate line
		for (String j : journeysBothYears) {
			output += j + "\n";
		}
		//return the full report as a string to be output
		return output;
	}

	//method that creates a string version of the treemap values ready for output
	public String outputTreeByDrivers()
	{
		//return rideList.outputTreeByDrivers();
		String output="";
		Set< Map.Entry< String,ArrayList<String>> > treeSetView = rideList.treeByDrivers().entrySet();
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

	//returns a report of the 5 most expensive journeys
	public String getFiveExpensiveCheapestJourney() {
		//add a formatted list of the top 5 most expensive journeys to the output string
		String fiveExpJourney="CHARGES FOR THE TOP 5 LEAST EXPENSIVE JOURNEYS \n";
		//get an ordered list of journeys by fare price
		ArrayList<Journey> sortedJourneys = rideList.sortByFarePrice();
		//add the first five of the ordered list to the output string on a new line
		for (int i=0;i<5;i++)
		{
			fiveExpJourney += sortedJourneys.get(i).toString()+"\n";
		}
		//add a formatted list of the top 5 least expensive journeys to the output string
		fiveExpJourney +="\n\nCHARGES FOR THE TOP 5 MOST EXPENSIVE JOURNEYS \n";
		int l = sortedJourneys.size();
		//add the last five of the ordered list to the output string on a new line
		for (int i=l-5;i<l;i++)
		{
			fiveExpJourney += sortedJourneys.get(i).toString()+"\n";
		}
		return fiveExpJourney;
	}



	//method to write the report into output file
	public  void writeToFile(String filename, String report) 
	{	
		FileWriter fw;
		try 
		{
			fw = new FileWriter(filename);
			fw.write(report+"\n\n");
			fw.close();
		}
		//message and stop if file not found
		catch (FileNotFoundException fnf)
		{
			System.out.println(filename + " not found ");
			System.exit(0);
		}
		//stack trace here because we don't expect to come here
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			System.exit(1);
		}


	}


}
