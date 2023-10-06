/*
 * Rose LeFlore
 * Project 3
 * Professor Thai
 */


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_Student_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<MythicalCreature> creaturesSorted;
	StringComparator comparator;
	DoubleComparator comparator2;
	CreatureCompator creatureCompare;
	

	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		comparator2 = new DoubleComparator();
		creatureCompare = new CreatureCompator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparator2);
		creaturesSorted = new SortedDoubleLinkedList<>(creatureCompare);
	}

	@After
	public void tearDown() throws Exception {
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		creaturesSorted = null;
		comparator = null;
		comparator2 = null;
		
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals(e.getMessage(), "Invalid operation for sorted list");
		}
	}
	
	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToEnd("GoodBye");
		}
		catch (UnsupportedOperationException e)
		{
			assertEquals(e.getMessage(), "Invalid operation for sorted list");
		}
	}
	
	@Test
	public void testAdd() {		
		//String
        // Adding data to the list
        sortedLinkedString.add("Pear");
        sortedLinkedString.add("Apple");
        sortedLinkedString.add("Mango");

        // Converting the list to an ArrayList
        ArrayList<String> list = sortedLinkedString.toArrayList();

        // Asserting that the list is sorted as expected
        assertEquals("Apple", list.get(0));
        assertEquals("Mango", list.get(1));
        assertEquals("Pear", list.get(2));
        
        
        //Double
        sortedLinkedDouble.add(10.0);
        sortedLinkedDouble.add(50.0);
        sortedLinkedDouble.add(3.0);

        // Converting the list to an ArrayList
        ArrayList<Double> list2 = sortedLinkedDouble.toArrayList();


        // Asserting that the list is sorted as expected
        assertEquals(3.0, list2.get(0), .0001);
        assertEquals(10.0, list2.get(1), .0001);
        assertEquals(50.0, list2.get(2), .0001);
        
        
        
        // Creature
        //Adding data to list
    	MythicalCreature creature1 = new MythicalCreature("Dragon", 2000.0);
    	MythicalCreature creature3 = new MythicalCreature("Pegasus", 500.0);
    	MythicalCreature creature2 = new MythicalCreature("Unicorn", 1000.0);
    	creaturesSorted.add(creature1);
    	creaturesSorted.add(creature2);
    	creaturesSorted.add(creature3);
    	
        ArrayList<MythicalCreature> list3 = creaturesSorted.toArrayList();
        assertEquals(creature3, list3.get(0));
        assertEquals(creature2, list3.get(1));
        assertEquals(creature1, list3.get(2));


    	
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
	}
	
	private class DoubleComparator implements Comparator<Double>
	{
		@Override
		public int compare(Double arg0, Double arg1) {
			return arg0.compareTo(arg1);
		}
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
