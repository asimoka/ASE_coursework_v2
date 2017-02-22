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
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RideListManager {

	private RideList rideList;
	private String	 taxiRegNumber;
	private String 	 destination;
	private Double 	 travelDistance;
	private int 	 numPassengers;
	private String 	 driverName;
	private String 	 carType;

	public RideListManager()
	{
		//Initialize empty list of Competitors
    	rideList = new RideList();
        addRides(); 
        
	}
	
	 private void addRides() 
	    {
	        //load staff data from file
	        BufferedReader RidesBuff = null;
	        BufferedReader destBuff = null;
	        BufferedReader taxiBuff = null;
	        
	        try 
	        {
	        
	        	RidesBuff = new BufferedReader(new FileReader(".//src/journeyDetailsInput.csv"));
		    	String inputJourneyLine = RidesBuff.readLine();  //read first line
		    	while(inputJourneyLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineJourney(inputJourneyLine);
		            //read next line
		            inputJourneyLine = RidesBuff.readLine();
		        }
		    	
		    	destBuff = new BufferedReader(new FileReader(".//src/destinationDetailsInput.csv"));
		    	String inputDestLine = destBuff.readLine();  //read first line
		    	while(inputDestLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineDestination(inputDestLine);
		            //read next line
		            inputDestLine = destBuff.readLine();
		        }
		    	
		    	taxiBuff = new BufferedReader(new FileReader(".//src/taxiDetailsInput.csv"));
		    	String inputTaxiLine = taxiBuff.readLine();  //read first line
		    	while(inputTaxiLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineTaxi(inputTaxiLine);
		            //read next line
		            inputTaxiLine = taxiBuff.readLine();
		        }
		    	taxiBuff = new BufferedReader(new FileReader(".//src/destination2016.csv"));
		    	String inputLastYearLine = taxiBuff.readLine();  //read first line
		    	while(inputLastYearLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineLastYear(inputLastYearLine);
		            //read next line
		            inputLastYearLine = taxiBuff.readLine();
		        }
	        }
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
	        		RidesBuff.close();
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
			String [] inline = line.split(",");
			taxiRegNumber = inline[0];
			destination = inline[1];
			travelDistance = Double.parseDouble(inline[3]);
			numPassengers = Integer.parseInt(inline[2]);
	
			Journey j = new Journey(taxiRegNumber, destination, travelDistance, numPassengers);
			rideList.addJourney(j);
		} catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "' - " + nfe.getMessage();
			System.out.println(error);
		}
		catch (InputMismatchException ime) {
			String error = "Input mismatch error in '"
					+ line + "' - " + ime.getMessage();
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

	public void processLineTaxi(String line) {
		try {
			String [] inline = line.split(",");
			taxiRegNumber = inline[1];
			driverName = inline[0];
			carType = inline[2];

			Taxi j = new Taxi(taxiRegNumber, driverName, carType);
			rideList.addTaxi(j);
		} catch (NumberFormatException nfe) {
			String error = "Number conversion error in '"
					+ line + "' - " + nfe.getMessage();
			System.out.println(error);
		}
		catch (InputMismatchException ime) {
			String error = "Input mismatch error in '"
					+ line + "' - " + ime.getMessage();
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
			String [] inline = line.split(",");
			destination = inline[0];
			travelDistance = Double.parseDouble(inline[1]);

			Destination j = new Destination(destination, travelDistance);
			rideList.addDestination(j);
		} catch (NumberFormatException nfe) {
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
	
	public void processLineLastYear(String line) {
		try {
			
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
		
		Set<String> journeysThisYear  = rideList.getDestinationsThisYear();
		Set<String> journeysLastYear  = rideList.getDestinationsLastYear();
		Set<String> journeysBothYears = rideList.getDestinationsBothYears();
		
		output += journeysThisYear.size() + " NEW PLACES IN 2017. \n";
		
		for (String j : journeysThisYear) {
			output += j + "\n";
		}

		output += journeysLastYear.size() + " PLACES VISITED IN 2016 ONLY. \n";
		
		for (String j : journeysLastYear) {
			output += j + "\n";
		}

		output += journeysBothYears.size() + " PLACES VISITED IN BOTH 2016 AND 2017. \n";
		
		for (String j : journeysBothYears) {
			output += j + "\n";
		}

		
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
