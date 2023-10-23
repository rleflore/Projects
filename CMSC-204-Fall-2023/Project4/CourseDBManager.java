import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.FileChooser;

/*
 * Rose LeFlore
 * CMSC 204 - Project 4
 * Professor Thai
 */
public class CourseDBManager implements CourseDBManagerInterface{
	
	private CourseDBStructure structure;
	private int size;
	
	public CourseDBManager() {
		// size 20 is default
		size = 20;
		structure = new CourseDBStructure(size);
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);		
		structure.add(element);
		
		
		
	}

	@Override
	public CourseDBElement get(int crn) {
		CourseDBElement element = null;
		try {
			element = structure.get(crn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException { 
		boolean valid;

		// if file doesn't exist
		if (input == null) {
			throw new FileNotFoundException();
		}
		File file = input;

		// retrieves number of lines in file
		int numLines = countLines(file);
		// if there are more lines in file then table, create new table with correct
		// size
		if (numLines > structure.getTableSize()) {
			size = numLines;
			structure = new CourseDBStructure(size*3);
		}
		
		Scanner scanner = new Scanner(file);
		
		// iterates each line in the file
	    while(scanner.hasNextLine()) {
	    	// keeping track of how much data is in one line
	       int count = 0;
	       
	       String courseID = "";
	       if (scanner.hasNext()) {
	    	   courseID = scanner.next();
	    	   count++;
	       }
	       
	       int crn = 0;
	       if (scanner.hasNextInt()) {
	    	   crn = scanner.nextInt();
	    	   count++;
	       }
	       
	       int credits = 0;
	       if (scanner.hasNextInt()) {
	    	   credits = scanner.nextInt();
	    	   count++;
	       }
	       
	       String roomNum = "";
	       if (scanner.hasNext()) {
	    	   roomNum = scanner.next();
	    	   count++;
	       }
	       
	       String instructor = "";
	       if (scanner.hasNext()) {
	    	   instructor = scanner.nextLine(); // rest of the line
	    	   count++;
	       }
	       
	       CourseDBElement element = new CourseDBElement(courseID, crn, credits, roomNum, instructor);
	       // ensures that all 5 data points were in the line
	       if (count == 5 && element.getValid()) {
	    	   structure.add(element);  	   
	       }
	       // prints out the invalid courses
	       else {
	    	   System.out.println(element.toString());
	       }
	    }
	    scanner.close();
	
	}

	@Override
	public ArrayList<String> showAll() {
		return structure.showAll();
	} 

	
	private int countLines(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int count = 0;
		
		while(scanner.hasNextLine()) {
			scanner.nextLine();
			count++;
		}
		
		scanner.close();
		return count;
	}
}
