import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	private static MorseCodeTree<String> tree = new MorseCodeTree<String>();
	
	public static String printTree() {

		ArrayList<String> elements = tree.toArrayList();
		
		String data = "";
		// loop to list all data elements
		for (String element : elements) {
			data += element + " ";
		}

		return data.trim();

	}
	
	
	public static String convertToEnglish(String code) {
		// if empty string return empty string
		if (code.equals("")) {
			return "";
		}
		
		 //split the code into seperate words
		String[] words = code.split(" / ");
		String translation = "";
				
		for (String word : words) {
			// split the words into letter (finding each letter)
			String[] letters = word.split(" ");
			
			for (String letter : letters) {
				String data = (String) tree.fetch(letter);				
				translation += data;
			}
			translation+=" ";
		}

		return translation.trim();
	}
	
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		String translation = "";
		String str = "";
		try {
			Scanner scanner = new Scanner(codeFile);
			// adding content from file to string to call other method
			while(scanner.hasNextLine()) {
				str += scanner.nextLine();
				if (scanner.hasNextLine()) {
					str += " ";
				}
			}
			scanner.close();
			translation = convertToEnglish(str);
		//file not found
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			//returning empty string if file isn't found
			return "";
		}
		return translation;
		
	}


}
