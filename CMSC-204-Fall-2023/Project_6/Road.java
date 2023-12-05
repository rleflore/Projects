/*
 * Rose LeFlore
 * Project 6
 * Professor Thai
 */
public class Road implements Comparable<Road>{

	Town endpoint1;
	Town endpoint2;
	String name;
	int distance;
	
	public Road(Town town1, Town town2, int distance, String name) {
		endpoint1 = town1;
		endpoint2 = town2;
		this.distance = distance;
		this.name = name;
	}
	
	public Town getTown1() {
		return endpoint1;
	}
	
	public Town getTown2() {
		return endpoint2;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		
		Road other = (Road)o;
		//comparing same sides
		return (endpoint1.equals(other.getTown1()) && endpoint2.equals(other.getTown2())) ||
	               (endpoint1.equals(other.getTown2()) && endpoint2.equals(other.getTown1()));
	}

	@Override
	public int compareTo(Road o) {
		if (o == null) {
			return -1;
		}
		
		if(this == o) {
			return 0;
		}	
		
		//first case - compare respective endpoints to each other
		if (endpoint1.compareTo(o.getTown1()) == 0) {
			return endpoint2.compareTo(o.getTown2());
		}		
		
		//second case - compare opposite endpoints to each other
		if (endpoint1.compareTo(o.getTown2()) == 0) {
			return endpoint2.compareTo(o.getTown1());
		}
		
		return endpoint1.compareTo(o.getTown1());
	}	

}
