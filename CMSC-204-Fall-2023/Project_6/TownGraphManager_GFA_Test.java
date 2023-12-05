/*
 * Rose LeFlore
 * Project 6
 * Professor Thai
 */


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_GFA_Test {
	private TownGraphManagerInterface graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[9];
		  
		  for (int i = 1; i < 9; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  graph.addRoad(town[1], town[2], 2, "Road_1");
		  graph.addRoad(town[1], town[4], 4, "Road_2");
		  graph.addRoad(town[1], town[7], 6, "Road_3");
		  graph.addRoad(town[4], town[3], 3, "Road_4");
		  graph.addRoad(town[3], town[2], 2, "Road_5");
		  graph.addRoad(town[2], town[7], 7, "Road_6");
		  graph.addRoad(town[3], town[6], 7, "Road_7");
		  graph.addRoad(town[6], town[5], 3, "Road_8");
		  graph.addRoad(town[5], town[7], 5, "Road_9");
		  graph.addRoad(town[7], town[8], 3, "Road_10");
		  

	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_2", roads.get(2));
		graph.addRoad(town[1], town[3], 5, "Road_11");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_2", roads.get(3));
	}
	
	@Test
	public void testGetRoad() {
		assertEquals("Road_10", graph.getRoad(town[7], town[8]));
		assertEquals("Road_8", graph.getRoad(town[6], town[5]));
	}
	
	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		assertEquals(true, graph.containsTown("Town_12"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_11"));
		graph.addTown("Town_11");
		ArrayList<String> path = graph.getPath(town[1],"Town_11");
		assertFalse(path.size() > 0);
	}
	
	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_11"));
	}
	
	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[7]));
		//opposite way
		assertEquals(true, graph.containsRoadConnection(town[7], town[2]));
		assertEquals(false, graph.containsRoadConnection(town[3], town[5]));
	}
	
	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_2", roads.get(2));
		assertEquals("Road_3", roads.get(3));
		assertEquals("Road_4", roads.get(4));
	}
	
	
	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[7]));
		graph.deleteRoadConnection(town[2], town[7], "Road_6");
		assertEquals(false, graph.containsRoadConnection(town[2], town[7]));
	}
	
	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> towns = graph.allTowns();
		assertEquals("Town_1", towns.get(0));
		assertEquals("Town_2", towns.get(1));
		assertEquals("Town_3", towns.get(2));
		assertEquals("Town_4", towns.get(3));
	}
	
	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[8]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_3 to Town_7 6 mi",path.get(0).trim());
		  assertEquals("Town_7 via Road_10 to Town_8 3 mi",path.get(1).trim());
	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[2],town[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_2 via Road_5 to Town_3 2 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_7 to Town_6 7 mi",path.get(1).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[1],town[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_1 via Road_1 to Town_2 2 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_5 to Town_3 2 mi",path.get(1).trim());
	}
	
}
