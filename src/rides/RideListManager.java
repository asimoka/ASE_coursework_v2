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
import java.util.Scanner;
import java.util.Set;

public class RideListManager {

	private RideList rideList;
	private String	 taxiRegNumber;
	private String 	 destination;
	private Double 	 travelDistance;
	private int 	 numPassengers;
	private Double 	 fare;
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
	        
	        	RidesBuff = new BufferedReader(new FileReader("journeyDetailsInput.csv"));
		    	String inputJourneyLine = RidesBuff.readLine();  //read first line
		    	while(inputJourneyLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineJourney(inputJourneyLine);
		            //read next line
		            inputJourneyLine = RidesBuff.readLine();
		        }
		    	
		    	destBuff = new BufferedReader(new FileReader("destinationDetailsInput.csv"));
		    	String inputDestLine = destBuff.readLine();  //read first line
		    	while(inputDestLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineDestination(inputDestLine);
		            //read next line
		            inputDestLine = destBuff.readLine();
		        }
		    	
		    	taxiBuff = new BufferedReader(new FileReader("taxiDetailsInput.csv"));
		    	String inputTaxiLine = taxiBuff.readLine();  //read first line
		    	while(inputTaxiLine != null)
		    	{  
		    		//stores details from this line in RideList class
		    		processLineTaxi(inputTaxiLine);
		            //read next line
		            inputTaxiLine = taxiBuff.readLine();
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
			travelDistance = Double.parseDouble(inline[2]);
			numPassengers = Integer.parseInt(inline[3]);
			double halfPriceDestination=0;
			if (travelDestination>10)
			{halfPriceDestination=travelDestination-10;
			 travelDestination=10;
			fare=(destination().travelDistance()*2+halfPriceDestination);}
		 	else
			{fare=(destination().travelDistance()*2);}
			if (fare<3){fare=3;}
			

			Journey j = new Journey(taxiRegNumber, destination, travelDistance, numPassengers, fare);
			rideList.addJourney(j);
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

	public void processLineTaxi(String line) {
		try {
			String [] inline = line.split(",");
			taxiRegNumber = inline[0];
			driverName = inline[1];
			carType = inline[2];

			Taxi j = new Taxi(taxiRegNumber, driverName, carType);
			rideList.addTaxi(j);
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
	
	public String outputJourneysByYear()
	{
		String output="";
		
		ArrayList<String> journeysThisYear  = rideList.getDestinationsThisYear();
		ArrayList<String> journeysLastYear  = rideList.getDestinationsLastYear();
		ArrayList<String> journeysBothYears = rideList.getDestinationsBothYears();
		
		output += journeysThisYear.size() + " NEW PLACES IN 2017.";
		
		for (String j : journeysThisYear) {
			output += j + "\n";
		}

		output += journeysLastYear.size() + " PLACES VISITED IN 2016 ONLY.";
		
		for (String j : journeysLastYear) {
			output += j + "\n";
		}

		output += journeysBothYears.size() + " PLACES VISITED IN BOTH 2016 AND 2017.";
		
		for (String j : journeysBothYears) {
			output += j + "\n";
		}

		
		return output;
	}
	
	public static void main (String arg[]) 
    {
       	//creates demo object which sets up the interface
    	//then just waits for user interaction
    	RideListManager rideListMan = new RideListManager();   	
    	System.out.print(rideListMan.rideList.outputTreeByDrivers());
    	//System.out.print(rideListMan.RideList.getDestination(1));
    }        

}
