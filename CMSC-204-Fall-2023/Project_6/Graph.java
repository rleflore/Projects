import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/*
 * Rose LeFlore
 * Graph Class 
 * Prof: Gary Thai
 */

public class Graph implements GraphInterface<Town, Road>{
	//implementing with adjacancy list
	private Map<Town, List<Road>> adj_list;
		
	//dijkstraShortestPath
	private Map<Town, Integer> distances;
	private Map<Town, Town> order;
	
	public Graph() {
		adj_list = new HashMap<>();
	}
		

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		// checks if there is an edge from the towns
		if (!containsEdge(sourceVertex, destinationVertex) ) {
			return null;
		}
		
		List<Road> roads = adj_list.get(sourceVertex);
		if (roads != null) {
			for (Road road : roads) {
				if (road.getTown2().equals(destinationVertex)|| road.getTown1().equals(destinationVertex)) {	
					return road;
				}
			}
		}		
		//returns null if no edge
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		//null check for both towns
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		
		//checks if both exist in graph
		if (!adj_list.containsKey(sourceVertex) || !adj_list.containsKey(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		
		//adding a new adjacant town
		sourceVertex.addAdjacant(destinationVertex);
		destinationVertex.addAdjacant(sourceVertex);	
				
		
		// Check for existing edge
	    for (Road road : adj_list.get(sourceVertex)) {
	        if ((road.getTown1().equals(sourceVertex) && road.getTown2().equals(destinationVertex)) ||
	            (road.getTown1().equals(destinationVertex) && road.getTown2().equals(sourceVertex))) {
	            // Edge already exists, return null
	            return null;
	        }
	    }
	    
		//creating connection
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		adj_list.get(sourceVertex).add(newRoad);
	
		adj_list.get(destinationVertex).add(newRoad);
		return newRoad;	
	}

	@Override
	public boolean addVertex(Town v) {
		// if the town is null or if it alr exists in the graph
		if (v == null || containsVertex(v)) {
			return false;
		}
		adj_list.put(v, new ArrayList<>());
		return true;	
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null || !containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			return false;
		}
		
		List<Road> roads = adj_list.get(sourceVertex);
		if (roads != null) {
			for (Road road : roads) {
				if (road.getTown2().equals(destinationVertex)) {
					//edge found
					return true;
				}
			}
		}
		
		//because graph is undirected check for the edge in the opposite way
		List<Road> reverseRoads = adj_list.get(destinationVertex);
		if (reverseRoads != null) {
			for (Road road : reverseRoads) {
				if (road.getTown2().equals(sourceVertex)) {
					return true; // edge found in opposite direction
				}
			}
		}
		
		//no edges found
		return false;
		
	}

	@Override
	public boolean containsVertex(Town v) {
		if (v == null) {
			return false;
		}		
		Set<Town> towns = vertexSet();
		//iterating through all the towns
		for (Town town : towns) {
			if (town.equals(v)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		//using tree set so it can use the compareTo
		Set<Road> edges = new TreeSet<>();
		for (List<Road> roadlist : adj_list.values()) {
			edges.addAll(roadlist);
		}
				
		return edges;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		// if the vertex is null
		if (vertex == null) {
			throw new NullPointerException();
		}
		
		// if vertex does not exist
		if (!adj_list.containsKey(vertex)) {
			throw new IllegalArgumentException();
		}
		
		return new HashSet<>(adj_list.get(vertex));
		
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		List<Road> edges = adj_list.get(sourceVertex);
		Road roadToRemove = null;
		// road does not exist
		if (!containsEdge(sourceVertex, destinationVertex)) {
			return null;
		}
		
		// if weight is less than -1 or the description is null it doesn't check
		if (weight < -1 || description.equals(null)) {
			return null;
		}
				
		// retrieving the edge
		for (Road edge : edges) {
			if (edge.getDistance() == weight && edge.getName().equals(description)) {
				roadToRemove = edge;
				break;
			}
		}
		
		// removing the road
		if (roadToRemove != null) {
			edges.remove(roadToRemove);
			
			//because it's undirected remove edge from other town
			List<Road> reverseEdges = adj_list.get(destinationVertex);
			reverseEdges.remove(roadToRemove);
			
			return roadToRemove;
		}
		
		// road not found
		return null;
	
	}

	@Override
	public boolean removeVertex(Town v) {
		// town is null
		if (v == null || !adj_list.containsKey(v)) {
			return false;
		}
		
		// removing the adjacant towns / raods
		ArrayList<Town> adjacants = v.getAdjacants();
		
		//removing the town from the adjacancy list from the other towns
		for (Town town : adjacants) {
			town.removeAdjacant(v);
			
			// removing the road from the adjacant town
			List<Road> roads = adj_list.get(town);
			Road road = getEdge(v, town);
			if (road != null) {
				roads.remove(getEdge(v, town));
				
				// opposite direction bc unidrected graph
				List<Road> reverseRoads = adj_list.get(v);
				reverseRoads.remove(road);
			}
		}

		adj_list.remove(v);
		
		return true;
	}

	@Override
	public Set<Town> vertexSet() {
		return adj_list.keySet();
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		
		ArrayList<String> path = new ArrayList<>();		
		
		// start from destination and trace back to the source
		Town current = destinationVertex;
		while(current != null && !current.equals(sourceVertex)) { 
			Town previous = order.get(current);
			Road road = getEdge(previous, current);	
			//if no connecting raod is found
			if (road == null || previous == null) {
				break;
			}
			String step = previous.getName() + " via " + road.getName() + " to " + current.getName() + " " + road.getDistance() + " mi";
			//adding string to beginning of list
			path.add(0, step);
			current = previous;
		}
		
		//checks if the path is valid
		if (!path.isEmpty() && current.equals(sourceVertex)) {
			return path;
		}
		//returns empty list
		return new ArrayList<>();
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		//Map to hold shortest distances from source to each vertex
		distances = new HashMap<>();
		//Map to hold the path the towns using shortest path
		order = new HashMap<>();
		//tracks the towns visited
		Set<Town> visited = new HashSet<>();
		//queue to choose the closest town
		PriorityQueue<Town> queue = new PriorityQueue<>(new Comparator<Town>() {
		@Override
		public int compare(Town o1, Town o2) {
			return Integer.compare(distances.get(o1), distances.get(o2));
		}
		
	});
		
		//the vertexes, setting the distance
		for (Town town : vertexSet()) {
			distances.put(town, Integer.MAX_VALUE);
			order.put(town, null);
		}
		
		//setting the source vertex
		distances.put(sourceVertex, 0);
		//adds the source as the starting point
		queue.add(sourceVertex);
		
		//process towns until the queue is empty
		while(!queue.isEmpty()) {
			Town current = queue.poll();
			visited.add(current);
			//adding the adjacant town distances to 'distances'			
			for (Town town : current.getAdjacants()) {
				//skip if already visited
				if (visited.contains(town)) {
					continue;
				}
				Road adjRoad = getEdge(current, town);
				if (adjRoad != null) {
					int currentDistance = distances.get(current);
					// calculate the new distance from adjacant town through current town
					int distance = currentDistance == Integer.MAX_VALUE ? Integer.MAX_VALUE : currentDistance + adjRoad.getDistance();
					//if distance is shorter, update the distance
					if (distance < distances.get(town)) {
						distances.put(town, distance);
						order.put(town, current);
						//only add if town not in queue
						if (!queue.contains(town)) {
							queue.add(town);
						}
					}
				}
			}
								
		}	
	
	}

}
