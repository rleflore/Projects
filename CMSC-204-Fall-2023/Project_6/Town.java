/*
 * Rose LeFlore
 * Project 6
 * Professor Thai
 */
import java.util.ArrayList;

public class Town implements Comparable<Town>{
	private String name;
	private ArrayList<Town> adjacant;
	
	public Town(String name) {
		this.name = name;
		adjacant = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Town> getAdjacants(){
		return adjacant;
	}
	
	public void setAdjacant(ArrayList<Town> adj) {
		adjacant = adj;
	}
	
	public void addAdjacant(Town town) {
	    if (!adjacant.contains(town)) {
	        adjacant.add(town);
	    }
	}
	
	public void removeAdjacant(Town town) {
		adjacant.remove(town);
	}
	
	public boolean adjExists(Town town) {
		return adjacant.contains(town);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Town town = (Town) obj;
		
		
		if (name.equals(town.getName())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	// Same if town's name are the same
	@Override
	public int compareTo(Town o) {
		if (o == null) {
			return -1;
		}
		
		if (this == o) {
			return 0;
		}
		
		return name.compareTo(o.getName());
	}
	

}
