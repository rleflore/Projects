import java.util.Scanner;

public class SocSecProcessor {

	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		String name = "";
		String ssn = "";
		String again = "y";
		
		while(again.equals("y")) {
			
			try {
				System.out.println("Name? ");
				name = obj.nextLine();
				
				System.out.println("SSN? ");
				ssn = obj.nextLine();
				
				if (isValid(ssn)) {
					System.out.println(name + " " + ssn + " is valid");
				}
			}catch(SocSecException e){
				System.out.println(e.getMessage());
			}
			
			System.out.println("Continue? ");
			again = obj.nextLine();
		}
		
		
	}
	
	public static boolean isValid(String ssn) throws SocSecException {
		
		if (ssn.length() != 11) {
			throw new SocSecException("wrong number of characters");
		}
		
		for (int i = 0; i < ssn.length(); i++) {
			if (i == 3|| i == 6) {
				if (ssn.charAt(i) != '-') {
					throw new SocSecException("dashes are wrong");
				}
			}
			else {
				if (!Character.isDigit(ssn.charAt(i))) {
					throw new SocSecException("contains a character that is not a digit");
				}
			}
			
		}
		return true;
	}
}
