
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_Student_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	CreatureCompator creatureCompator;
	BasicDoubleLinkedList<MythicalCreature> creatures;

	public MythicalCreature creature1 = new MythicalCreature("Unicorn", 1000.0);
	public MythicalCreature creature2 = new MythicalCreature("Pegasus", 500.0);
	public MythicalCreature creature3 = new MythicalCreature("Dragon", 2000.0);


	@Before
	public void setUp() throws Exception {
		creatureCompator = new CreatureCompator();
		linkedString = new BasicDoubleLinkedList<>();
		linkedDouble = new BasicDoubleLinkedList<>();
		creatures = new BasicDoubleLinkedList<>();
		
		linkedString.addToEnd("Pineapples");
		linkedString.addToEnd("Peaches");
		linkedString.addToFront("Strawberrys");
		linkedString.addToFront("Lychee");

		linkedDouble.addToEnd(4.0);
		linkedDouble.addToEnd(6.0);
		linkedDouble.addToFront(10.0);
		linkedDouble.addToFront(16.0);
		
		creatures.addToFront(creature1);
		creatures.addToFront(creature2);
		creatures.addToEnd(creature3);

		
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		creatureCompator = null;
		creatures = null;
	}
	
	
	@Test 
	public void testAddToEnd() {
		assertEquals("Peaches", linkedString.getLast());
		linkedString.addToEnd("Apples");
		assertEquals("Apples", linkedString.getLast());
		
		assertEquals(6.0, linkedDouble.getLast(), .0001);
		linkedDouble.addToEnd(7.0);
		assertEquals(7.0, linkedDouble.getLast(), .0001);
	}

	@Test
	public void testGetSize() {
		assertEquals(4,linkedString.getSize());
		assertEquals(4,linkedDouble.getSize());

	}	
	
	@Test
	public void testaddToFront() {
		assertEquals("Lychee", linkedString.getFirst());
		linkedString.addToFront("Apples");
		assertEquals("Apples", linkedString.getFirst());
		
		assertEquals(16.0, linkedDouble.getFirst(), .0001);
		linkedDouble.addToFront(7.0);
		assertEquals(7.0, linkedDouble.getFirst(), .0001);
	}
	
	
	@Test 
	public void testGetFrist() {
		assertEquals("Lychee", linkedString.getFirst());
		assertEquals(16.0, linkedDouble.getFirst(), .0001);
	}
	
	
	@Test
	public void testGetLast() {
		assertEquals("Peaches", linkedString.getLast());
		assertEquals(6.0, linkedDouble.getLast(), .0001);

	}
	
	
	@Test
	public void testToArrayList() {
		ArrayList<String> strArray = new ArrayList<>();
		strArray = linkedString.toArrayList();
		assertEquals("Lychee", strArray.get(0));
		assertEquals("Strawberrys", strArray.get(1));
		assertEquals("Pineapples", strArray.get(2));
		assertEquals("Peaches", strArray.get(3));

		ArrayList<Double> doubleArray = new ArrayList<>();
		doubleArray = linkedDouble.toArrayList();
		assertEquals(16.0, doubleArray.get(0), .0001);
		assertEquals(10.0, doubleArray.get(1), .0001);
		assertEquals(4.0, doubleArray.get(2), .0001);
		assertEquals(6.0, doubleArray.get(3), .0001);

	}
	
	
	@Test 
	public void testIteratorSuccessfulNext() {
		// Testing on String list
		ListIterator<String> iteratorS = linkedString.iterator();
		assertEquals(true, iteratorS.hasNext());
		assertEquals("Lychee", iteratorS.next());
		assertEquals("Strawberrys", iteratorS.next());
		assertEquals("Pineapples", iteratorS.next());
		assertEquals(true, iteratorS.hasNext());
		assertEquals("Peaches", iteratorS.next());
		
		
		// Testing on double list
		ListIterator<Double> iteratorD = linkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(16.0, iteratorD.next(), .0001);
		assertEquals(10.0, iteratorD.next(), .0001);
		assertEquals(4.0, iteratorD.next(), .0001);
		assertEquals(true, iteratorD.hasNext());
		assertEquals(6.0, iteratorD.next(), .0001);
	}
	
	
	@Test 
	public void testIteratorSuccessfulPrevious() {
		// Testing on String list
		ListIterator<String> iteratorS = linkedString.iterator();
		assertEquals(true, iteratorS.hasNext());
		assertEquals("Lychee", iteratorS.next());
		assertEquals("Strawberrys", iteratorS.next());
		assertEquals("Pineapples", iteratorS.next());
		assertEquals(true, iteratorS.hasPrevious());
		
		
		// Testing on double list
		ListIterator<Double> iteratorD = linkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(16.0, iteratorD.next(), .0001);
		assertEquals(10.0, iteratorD.next(), .0001);
		assertEquals(4.0, iteratorD.next(), .0001);
		assertEquals(6.0, iteratorD.next(), .0001);
		assertEquals(true, iteratorD.hasPrevious());
	}
	
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		// Testing on String list
		ListIterator<String> iteratorS = linkedString.iterator();
		assertEquals(true, iteratorS.hasNext());
		assertEquals("Lychee", iteratorS.next());
		assertEquals("Strawberrys", iteratorS.next());
		assertEquals("Pineapples", iteratorS.next());
		assertEquals(true, iteratorS.hasNext());
		assertEquals("Peaches", iteratorS.next());
		try{
			//no more elements in list
			iteratorS.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
		
		// Testing on double list
		ListIterator<Double> iteratorD = linkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(16.0, iteratorD.next(), .0001);
		assertEquals(10.0, iteratorD.next(), .0001);
		assertEquals(4.0, iteratorD.next(), .0001);
		assertEquals(true, iteratorD.hasNext());
		assertEquals(6.0, iteratorD.next(), .0001);
		try{
			//no more elements in list
			iteratorD.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		// Testing on String list
		ListIterator<String> iteratorS = linkedString.iterator();
		assertEquals(false, iteratorS.hasPrevious());
		assertEquals("Lychee", iteratorS.next());
		assertEquals("Strawberrys", iteratorS.next());
		assertEquals(true, iteratorS.hasPrevious());
		assertEquals("Strawberrys", iteratorS.previous());
		assertEquals("Lychee", iteratorS.previous());
		try{
			//no more elements in list
			iteratorS.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
		
		
		// Testing on double list
		ListIterator<Double> iteratorD = linkedDouble.iterator();
		assertEquals(false, iteratorD.hasPrevious());
		assertEquals(16.0, iteratorD.next(), .0001);
		assertEquals(10.0, iteratorD.next(), .0001);
		assertEquals(true, iteratorD.hasPrevious());
		assertEquals(10.0, iteratorD.previous(), .0001);
		assertEquals(16.0, iteratorD.previous(), .0001);
		try{
			//no more elements in list
			iteratorD.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		// Testing on String list
		ListIterator<String> iteratorS = linkedString.iterator();
		assertEquals(true, iteratorS.hasNext());
		assertEquals("Lychee", iteratorS.next());
		assertEquals("Strawberrys", iteratorS.next());
		assertEquals("Pineapples", iteratorS.next());
		assertEquals("Peaches", iteratorS.next());
		try{
			iteratorS.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
		// Testing on double list
		ListIterator<Double> iteratorD = linkedDouble.iterator();
		assertEquals(true, iteratorD.hasNext());
		assertEquals(16.0, iteratorD.next(), .0001);
		assertEquals(10.0, iteratorD.next(), .0001);
		assertEquals(4.0, iteratorD.next(), .0001);
		assertEquals(6.0, iteratorD.next(), .0001);
		try{
			iteratorD.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	
	
	@Test
	public void testRemove() {
		// Testing on creature list
		
		// Removing first
		MythicalCreature creature4 = new MythicalCreature("Fairy", 400.0);
		assertEquals(creature2, creatures.getFirst());
		assertEquals(creature3, creatures.getLast());
		creatures.addToFront(creature4);
		creatures.remove(creature4, creatureCompator);
		assertEquals(creature2, creatures.getFirst());
		
		
		// Removing middle
		creatures.addToFront(creature4);
		creatures.remove(creature2, creatureCompator);
		assertEquals(creature4, creatures.getFirst());
		assertEquals(creature3, creatures.getLast());
		
		
		// Removing end of list
		creatures.addToFront(creature2);
		creatures.remove(creature3, creatureCompator);
		assertEquals(creature1, creatures.getLast());
	}
	
	
	@Test
	public void testRetrieveFirstElement() {
		// Testing on String List
		assertEquals("Lychee", linkedString.getFirst());
		linkedString.addToFront("Orange");
		assertEquals("Orange", linkedString.getFirst());
		assertEquals("Orange", linkedString.retrieveFirstElement());
		assertEquals("Lychee",linkedString.getFirst());
		assertEquals("Lychee", linkedString.retrieveFirstElement());
		assertEquals("Strawberrys",linkedString.getFirst());
		
		// Testing on double list
		assertEquals(16.0, linkedDouble.getFirst(), .0001);
		linkedDouble.addToFront(42.1);
		assertEquals(42.1, linkedDouble.getFirst(), .0001);
		assertEquals(42.1, linkedDouble.retrieveFirstElement(), .0001);
		assertEquals(16.0,linkedDouble.getFirst(), .0001);
		assertEquals(16.0, linkedDouble.retrieveFirstElement(), .0001);
		assertEquals(10.0,linkedDouble.getFirst(), .0001);
		
	}
	
	
	@Test
	public void testRetrieveLastElement() {
		// Testing on String list
		assertEquals("Peaches", linkedString.getLast());
		linkedString.addToEnd("Orange");
		assertEquals("Orange", linkedString.getLast());
		assertEquals("Orange", linkedString.retrieveLastElement());
		assertEquals("Peaches",linkedString.getLast());
		
		
		// Testing on Double list
		assertEquals(6.0, linkedDouble.getLast(), .0001);
		linkedDouble.addToEnd(45.3);
		assertEquals(45.3, linkedDouble.getLast(), .0001);
		assertEquals(45.3, linkedDouble.retrieveLastElement(), .0001);
		assertEquals(6.0,linkedDouble.getLast(), .0001);
	}
	
	
	
	
	private class CreatureCompator implements Comparator<MythicalCreature>{

		@Override
		public int compare(MythicalCreature o1, MythicalCreature o2) {
			// compares based off of power
			Double power1 = o1.getPower();
			Double power2 = o2.getPower();
			return power1.compareTo(power2);
		}
		
	}
	
	
	private class MythicalCreature{
		String name;
		Double power;
		
		public MythicalCreature(String name, Double power) {
			this.name = name;
			this.power = power;
		}
		
		public String getName() {
			return name;
		}
		
		public Double getPower() {
			return power;
		}
		
		public String toString() {
			return (getName() + " " + getPower());
					 
		}
	}
	
	
	
}
