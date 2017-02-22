package tests;

import static org.junit.Assert.*;

import org.junit.*;

import rides.Taxi;
import rides.incorrectLicensePlateException;

public class LicensePlateTest {


	Taxi testTaxi;

	//@SuppressWarnings("deprecation")
	@Before
	public void plateShouldBeAccepted(){
		
		
	}
	
	@Test 
	public void testStandardPlate() throws incorrectLicensePlateException  {
			testTaxi = new Taxi("AB12CDE", "car type", "driver name");

	}
	
	@Test
	public void testLowerCase() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab12cde", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testLongPlate() throws incorrectLicensePlateException {
		testTaxi = new Taxi("AB12CDEF", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testShortPlate() throws incorrectLicensePlateException {
		testTaxi = new Taxi("AB12CD", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testNumberat1() throws incorrectLicensePlateException {
		testTaxi = new Taxi("8B12CDE", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testNumberat2() throws incorrectLicensePlateException {
		testTaxi = new Taxi("A812CDE", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testNumberat5() throws incorrectLicensePlateException {
		testTaxi = new Taxi("AB128DE", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testNumberat6() throws incorrectLicensePlateException {
		testTaxi = new Taxi("AB12C8E", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testNumberat7() throws incorrectLicensePlateException {
		testTaxi = new Taxi("AB12CD8", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testLetterat3() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ABG2CDE", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testLetterat4() throws incorrectLicensePlateException {
		testTaxi = new Taxi("A8G2CDE", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt1() throws incorrectLicensePlateException {
		testTaxi = new Taxi(" b12cde", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt2() throws incorrectLicensePlateException {
		testTaxi = new Taxi("a 12cde", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt3() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab 2cd ", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt4() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab1 cde", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt5() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab12 de", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt6() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab12cd ", "car type", "driver name");
	}
	
	@Test (expected=incorrectLicensePlateException.class)
	public void testSpaceAt7() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab12cd ", "car type", "driver name");
	}
	
	@Test //(expected=incorrectLicensePlateException.class)
	public void testSpaceAtStart() throws incorrectLicensePlateException {
		testTaxi = new Taxi(" ab12cde", "car type", "driver name");
	}
	
	@Test //(expected=incorrectLicensePlateException.class)
	public void testSpaceAtEnd() throws incorrectLicensePlateException {
		testTaxi = new Taxi("ab12cde ", "car type", "driver name");
	}
	
	

}
