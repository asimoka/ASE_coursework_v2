package rides;


public class Main {

	
	
	public static void main (String arg[]) 
    {
		RideListManager manager = new RideListManager();
       	//creates demo object which sets up the interface
    	//then just waits for user interaction  	
    	manager.writeToFile("task2report.txt", manager.outputTreeByDrivers());
    	manager.writeToFile("task3report.txt", manager.outputJourneysByYear());
    	manager.writeToFile("task1report.txt", manager.getFiveExpensiveCheapestJourney());
    }   
	
	
}
