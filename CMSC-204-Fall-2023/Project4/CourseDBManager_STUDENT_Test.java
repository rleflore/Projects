

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the Good Faith Attempt test file for the CourseDBManager
 *
 */
public class CourseDBManager_STUDENT_Test {
	private CourseDBManager manager = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = new CourseDBManager();
		manager.add("CMSC100", 28461, 3, "SC312", "Squidward");
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		manager = null;
	}

	/**
	 * Test for the createConcordanceArray method
	 */
	@Test
	public void testAddToDB() {
		try {
			manager.add("CMSC110", 21561, 3, "SC451", "BillyO");
			manager.add("CMSC110", 21561, 3, "SC451", "BillyO");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/*
	 * Test the get method
	 */
	@Test
	public void testGet() {
		try {
			manager.get(28461);
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/*
	 * test readFile method
	 * Reads the given file and inputs the lines to the table
	 */
	@Test 
	public void testReadFile() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 30504 4 SC450 Joey Bag-O-Donuts");
			inFile.print("CMSC204 30503 4 SC450 Jill B. Who-Dunit");
			
			inFile.close();
			manager.readFile(inputFile);
			assertEquals("CMSC203",manager.get(30504).getID());
			assertEquals("CMSC204",manager.get(30503).getID());
			assertEquals("SC450",manager.get(30503).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	
	}
	/*
	 * Test the showAll method (converting the table to an array list)
	 */
	@Test
	public void testShowAll() {
		manager.add("MATH180", 34123, 3, "SC384", "Muffan Man");
		manager.add("MATH143", 35452, 3, "SC354", "Gingerbread man");
		manager.add("MATH180", 34123, 3, "SC384", "Blueberry Man"); //same crn as the first one, should not replace
		ArrayList<String> arr = manager.showAll();
		System.out.println(arr.get(0));
		System.out.println(arr.get(1));
		System.out.println(arr.get(2));

		
		assertEquals(arr.get(0),"Course:MATH143 CRN:35452 Credits:3 Instructor:Gingerbread man Room:SC354");
		assertEquals(arr.get(1),"Course:CMSC100 CRN:28461 Credits:3 Instructor:Squidward Room:SC312");
		assertEquals(arr.get(2),"Course:MATH180 CRN:34123 Credits:3 Instructor:Muffan Man Room:SC384"); //updated
		
	}
}
