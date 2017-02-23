package rides;
/*
 * This class deals with all of the I/O between the program
 * and the input/output text files. 
 * reads input files, processes each line of the files then splits them into instance variables for each class
 * performs various checks to make sure data entered follows common format for names, places, registration numbers, distance, car types, and number of passengers
 * 
 * code authors Kirsty Duncan and Assem Yerkinova
 * 
 */
import java.io.*;
import java.util.Set;

public class RideListManager {

	private RideList rideList;
	private String	 taxiRegNumber;
	private String 	 destination;
	private Double 	 travelDistance;
	private int 	 numPassengers;
	private String 	 driverName;
	private String 	 carType;

	//constructor for RideListManager
	public RideListManager() {
		//Initialize empty list of Competitors
		rideList = new RideList();
		addRides();
		}
		//load data from files destinationDetailsInput.csv journeyDetailsInput.csv and taxiDetailsInput.csv
		 private void addRides() {
			//construct null buffered readers for input
			BufferedReader RidesBuff = null;
			BufferedReader destBuff = null;
			BufferedReader taxiBuff = null;
	        
			try {
				//create buffer for journeys file and read each line
				RidesBuff = new BufferedReader(new FileReader(".//src/journeyDetailsInput.csv"));
				String inputJourneyLine = RidesBuff.readLine();  //read first line
				while(inputJourneyLine != null) {  
					//stores details from this line in RideList class
					processLineJourney(inputJourneyLine);
				    //read next line
				    inputJourneyLine = RidesBuff.readLine();
				}
				//create buffer for destination file and read each line
				destBuff = new BufferedReader(new FileReader(".//src/destinationDetailsInput.csv"));
				String inputDestLine = destBuff.readLine();  //read first line
				while(inputDestLine != null) {  
					//stores details from this line in RideList class
					processLineDestination(inputDestLine);
					//read next line
					inputDestLine = destBuff.readLine();
				}
				//create buffer for taxi file and read each line
				taxiBuff = new BufferedReader(new FileReader(".//src/taxiDetailsInput.csv"));
				String inputTaxiLine = taxiBuff.readLine();  //read first line
				while(inputTaxiLine != null) {  
					//stores details from this line in RideList class
					processLineTaxi(inputTaxiLine);
					//read next line
					inputTaxiLine = taxiBuff.readLine();
				}
				taxiBuff = new BufferedReader(new FileReader(".//src/destination2016.csv"));
				String inputLastYearLine = taxiBuff.readLine();  //read first line
				while(inputLastYearLine != null) {  
					//stores details from this line in RideList class
					processLineLastYear(inputLastYearLine);
					//read next line
					inputLastYearLine = taxiBuff.readLine();
				}
			}
			//catches if no file was found
			catch(FileNotFoundException e) 
			{
				System.out.println(e.getMessage());
				System.exit(1);
			}
			//catches if there is a problem with data in the file
			catch (IOException e) 
			{
				e.printStackTrace();
				System.exit(1);        	
			}
			finally  
			{
			try
			//catches if theres a problem closing RidesBuff
			{
				RidesBuff.close();
			}
			catch (IOException ioe) 
			{
			//don't do anything
			}
		}   	
	}
	//method to process lines from input and convert from one String to various instance variables in the Journey class
	public void processLineJourney(String line) {
		try {
			//split up the information in this line by commas into license plate number, destination, distance and number of passengers
			String [] inline = line.split(",");
			taxiRegNumber = inline[0];
			destination = inline[1];
			//convert these values to the correct format
			travelDistance = Double.parseDouble(inline[3]);
			numPassengers = Integer.parseInt(inline[2]);
			//create a new object of type journey and add it to the list of journeys
			Journey j = new Journey(taxiRegNumber, destination, travelDistance, numPassengers);
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

	}
	//method to process lines from input and convert from one String to various instance variables in the Taxi class
	public void processLineTaxi(String line) {
		try {
			String [] inline = line.split(",");
			taxiRegNumber = inline[1];
			driverName = inline[0];
			carType = inline[2];

			Taxi j = new Taxi(taxiRegNumber, driverName, carType);
			rideList.addTaxi(j);
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
	//method to process lines from input and convert from one String to various instance variables in the Destination class
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
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) {
			String error = "Not enough items in : '" + line
					+ "' index position : " + air.getMessage();
			System.out.println(error);
		}
	}
	//method to process lines from destinations2016.csv
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
	//method to output to text file journeys organize by year
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
		return rideList.outputTreeByDrivers();
	}
	
	//returns a report of the 5 most expensive journeys
	public String getFiveExpensiveCheapestJourney() {
		return rideList.getFiveExpensiveCheapestJourney();
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
