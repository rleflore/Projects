/*
 * Rose LeFlore
 * Project 6
 * Professor Thai
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{
	
	private GraphInterface<Town,Road> graph;
	
	public TownGraphManager() {
		graph = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		//return false if either towns are null
		if (t1 == null || t2 == null) {
			return false;
		}		
		Road addedRoad = graph.addEdge(t1, t2, weight, roadName);
		//return T/F depending if the road is null
		return addedRoad != null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		
		Road road = graph.getEdge(t1, t2);
		if (road != null) {
			return road.getName();
		}
		//return empty string if road doesn't exist
		return "";
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {		
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		Town town = null;
		
		while (iterator.hasNext()) {
			Town current = iterator.next();
			if (current.getName().equals(name)) {
				town = current;
				return town;
			}
		}
		//couldn't find the town
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(getTown(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		
		if (t1 == null || t2 == null) {
			return false;
		}
		
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> allRoads = new ArrayList<String>();
		for (Road road : roads) {
			allRoads.add(road.getName());
		}
		Collections.sort(allRoads);
		return allRoads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		//if the graph doesn't contain either towns
		if (!(graph.containsVertex(t1) || graph.containsVertex(t2))) {
			return false;
		}
		Road connection = graph.getEdge(t1, t2);
		//checking if there is a road connection
		if(connection == null) {
			return false;
		}
		
		Road remove = graph.removeEdge(t1, t2, connection.getDistance(), road);
		if (remove == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> allTowns = new ArrayList<String>();
		for (Town town : towns) {
			allTowns.add(town.getName());
		}
		Collections.sort(allTowns);
		return allTowns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		
		if (t1 != null && t2 != null) {
			return graph.shortestPath(t1, t2);
		}
		
		return new ArrayList<String>();
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException{
		Scanner scan = new Scanner(selectedFile);
		while(scan.hasNextLine()) {
			String str = scan.nextLine();
			//Getting the indiviual info thats split by ';'
			String[] info = str.split(";");
			//Getting the road info
			String[] road = info[0].split(",");
			
			String town1 = info[1];
			String town2 = info[2];
			String roadName = road[0];
			int weight = Integer.valueOf(road[1]);
			
			//populating graph with towns
			addTown(town1);
			addTown(town2);
			//adding road
			addRoad(town1, town2, weight, roadName);
		}
	}

}
