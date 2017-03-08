package rides;


public class Main {

	public static void main (String arg[]) 
    {
		//input files
		String taxiInput = ".//src/taxiDetailsInput.csv";
		String destinationsInput = ".//src/destinationDetailsInput.csv";
		String journeysInput = ".//src/journeyDetailsInput.csv";
		String lastYearInput = ".//src/destination2016.csv";
		
		
		RideListManager manager = new RideListManager(taxiInput, destinationsInput, journeysInput, lastYearInput);
       	//creates demo object which sets up the interface
    	//then just waits for user interaction  	
    	manager.writeToFile("task2report.txt", manager.outputTreeByDrivers());
    	manager.writeToFile("task3report.txt", manager.outputJourneysByYear());
    	manager.writeToFile("task1report.txt", manager.getFiveExpensiveCheapestJourney());
    }   
	
	
}
