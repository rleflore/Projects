/*
 * Rose LeFlore
 * CMSC 204 - Project 4
 * Professor Thai
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class CourseDBStructure  implements CourseDBStructureInterface {
	private LinkedList<CourseDBElement>[] hashTable;
	private int size;
	
	// main constructor
	public CourseDBStructure(int n) {
		int num = (int) (n/1.5);
		//increment num until mod 4 equals 3 and is prime
		while(!(num%4 == 3 && isPrime(num))) {
			num++;		
		}
		size = num;
		hashTable = new LinkedList[size];
	}
	
	//testing constructor
	public CourseDBStructure(String str, int n) {
		size = n;
		hashTable = new LinkedList[size];
	}
	
	
	//adding the course to the table based of hashcode
	@Override
	public void add(CourseDBElement element) {
		int index = hashCode(element.getCRN());
		//checks if element is valid, output invalud
		if (!element.getValid()) {
			System.out.println(element.toString());
			return;
		}
				
		// if the bucket is empty create a new linked list
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}	
				
		// checks if element already exists based off the CRN
		for (CourseDBElement ele : hashTable[index]) {
			// if crn is the same, exits and prints the duplicate course
			if(element.compareTo(ele) == 0) {
				System.out.println(element.toString());
				return;
			}	
		}
		
		hashTable[index].add(element);
		
	}
	

	//finds course based off the crn
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int index = hashCode(crn);
		
		// if linked exists at the index, iterate to find element
		if(hashTable[index] != null) {
			for (CourseDBElement element : hashTable[index]) {
				if (element.getCRN() == crn) {
					return element;
				}
			}
		}
		// does not exist
		throw new IOException();
	}

	// Produce an array list of the courses
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courses = new ArrayList<>();
		
		// Iterate over each linked list in the table
		for (LinkedList<CourseDBElement> list : hashTable) {
			if (list != null) {
				// Iterating over each element in the list
				for (CourseDBElement element : list) {				
					courses.add(element.toString());
				}
			}
		}

		return courses;
	}

	@Override
	public int getTableSize() {
		return size;
	}
	
	//returns true or false depending if the number is prime
	private boolean isPrime(int n) {
		
		for(int i=2;i <=n/2; i++) {
			//number is divisiable by a number
			if (n % i == 0) {
				return false;
			}
		}
		//number is prime
		return true;
	}
	
	// Calculating hashcode based off of crn 
	private int hashCode(int crn){
		String crnStr = String.valueOf(crn);
		int hashCode = crnStr.hashCode();
		return Math.abs(hashCode) % size;
	}
	
	
	public int constructorSize(int n) {
		int num = (int) (n/1.5);
		//increment num until mod 4 equals 3 and is prime
		while(!(num%4 == 3 && isPrime(num))) {
			num++;		
		}
		return num;
	}


}
