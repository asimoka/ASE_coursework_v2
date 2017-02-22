package tests;

import static org.junit.Assert.*;

import org.junit.*;

import rides.Journey;

public class JourneyFareTest {


	Journey testJourney;

	//@SuppressWarnings("deprecation")
	@Before
	public void fareShouldBeCalculated() {
		
		testJourney = new Journey("AB12CDE", "Glasgow", 0.0, 3);

	}
	
	@Test
	public void testLongJourney() {
		testJourney.setTravelDistance(60.0);
		assertTrue(testJourney.calculateFare() == 70.0);
	}
	
	@Test
	public void testJustBelowMinJourney() {
		testJourney.setTravelDistance(1.4);
		assertTrue(testJourney.calculateFare() == 3.0);
	}
	
	@Test
	public void testJustaboveMinJourney() {
		testJourney.setTravelDistance(1.6);
		assertTrue(testJourney.calculateFare() == 3.2);
	}
	
	@Test
	public void testMinJourney() {
		testJourney.setTravelDistance(1.5);
		assertTrue(testJourney.calculateFare() == 3.0);
	}

	@Test
	public void testJustAboveFullPriceJourney() {
		testJourney.setTravelDistance(11.0);
		assertTrue(testJourney.calculateFare() == 21.0);
	}
	
	@Test
	public void testJustBelowFullPriceJourney() {
		testJourney.setTravelDistance(9.0);
		assertTrue(testJourney.calculateFare() == 18.0);
	}

	@Test
	public void testMaxFullPriceJourney() {
		testJourney.setTravelDistance(10.0);
		assertTrue(testJourney.calculateFare() == 20.0);
	}

	@Test
	public void testNoJourney() {
		testJourney.setTravelDistance(0.0);
		assertTrue(testJourney.calculateFare() == 3.0);
	}
	
	
	

}
