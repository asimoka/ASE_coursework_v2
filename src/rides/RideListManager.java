package rides;
import java.io.*;
import java.util.Scanner;

public class RideListManager {

	private RideList RideList;
	private String taxiRegNumber;
	private String destination;
	private Double travelDistance;
	private int numPassengers;
	private Double fare;
	private String driverName;
	private String carType;

	public void readerJourney() {
		try{
			File inFile = new File("journeyDetailsInput.csv");

			Scanner input = new Scanner(inFile);
			String journeys = input.nextLine();

			if (journeys.length() != 0) 
				processLineJourney(journeys);
			input.close();

		} catch (FileNotFoundException ex) {
			System.out.printf("ERROR: File Not Found", ex);
		}
	}
	public void readerDestination() {
		try{
			File inFile = new File("destinationDetailsInput.csv");

			Scanner input = new Scanner(inFile);
			String destination = input.nextLine();

			if (destination.length() != 0) 
				processLineDestination(destination);
			input.close();

		} catch (FileNotFoundException ex) {
			System.out.printf("ERROR: File Not Found", ex);
		}
	}
	public void readerTaxi() {
		try{
			File inFile = new File("taxiDetailsInput.csv");

			Scanner input = new Scanner(inFile);
			String taxi = input.nextLine();

			if (taxi.length() != 0) 
				processLineTaxi(taxi);
			input.close();

		} catch (FileNotFoundException ex) {
			System.out.printf("ERROR: File Not Found", ex);
		}
	}
	public void processLineJourney(String line) {
		try {
			String [] inline = line.split(",");
			taxiRegNumber = inline[0];
			destination = inline[1];
			travelDistance = Double.parseDouble(inline[2]);
			numPassengers = Integer.parseInt(inline[3]);
			fare = Double.parseDouble(inline[4]);

			Journey j = new Journey(taxiRegNumber, destination, travelDistance, numPassengers, fare);
			RideList.addJourney(j);
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
			RideList.addTaxi(j);
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
			RideList.addDestination(j);
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

}

