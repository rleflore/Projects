/*
 * Class: CMSC203 CRN: 33115
 * Instructor: Professor Grinberg
 * Description: Asks questions if connection to the wifi is successful. Ends if it is, continues if it isn't
 * Due: 2/13/2023
 * Platform/compiler: Windows
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Rose LeFlore
*/

import java.util.Scanner;

public class WifiDiagnosis {
	
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);		
		String question = "Did that fix the problem?";
		String invalid = "Invalid answer; try again";
		
		System.out.println("If you have a problem with internet connectivity, this WiFi Diagnosis might work");		
		System.out.println("\nReboot the computer and try to connect");
		System.out.println(question);
		
		String answer = keyboard.nextLine();
		
		//first
		if (answer.equals("no") || answer.equals("No")) {
			System.out.println("Reboot the router and try to connect");
			System.out.println(question);
			answer = keyboard.nextLine();
			
			//second
			if (answer.equals("no") || answer.equals("No")) {
				System.out.println("Make sure the cables connecting the router are firmly plugged in and power is getting to the router");
				System.out.println(question);
				answer = keyboard.nextLine();
				
				//third
				if (answer.equals("no") || answer.equals("No")) {
					System.out.println("Move the computer closer to the router and try to connect");
					System.out.println(question);
					answer = keyboard.nextLine();
					
					//forth
					if (answer.equals("no") || answer.equals("No")) {
						System.out.println("Contact your ISP");
						System.out.println("Done");
					}
					else {
						System.out.println("Done");
					}
				}
				
				//third if statement
				else if(answer.equals("Yes") || answer.equals("yes")) {
					System.out.println("Done");
				}
				//third if statement
				else {
					System.out.println(invalid);
				}
			}
			//second if statement 
			else if (answer.equals("Yes") || answer.equals("yes")) {
				System.out.println("Done");
			}
			//second if statement
			else {
				System.out.println(invalid);
			}
			
			
		}
		//fist if statement
		else if (answer.equals("Yes") || answer.equals("yes")) {
			System.out.println("Done");
			
		}
		//first if statement
		else {
			System.out.println(invalid);
		}
		
		
	}
	
	
	

}
