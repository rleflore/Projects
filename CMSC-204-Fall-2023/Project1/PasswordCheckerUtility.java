import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	
	
	
	PasswordCheckerUtility(){
	}

	public static void comparePasswords(java.lang.String password, java.lang.String passwordConfirm) throws UnmatchedException{
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}	
	}
	
	public static boolean comparePasswordsWithReturn(java.lang.String password, java.lang.String passwordConfirm) {
		return password.equals(passwordConfirm);
	}
	
	public static boolean isValidLength(String password) throws LengthException{
		if (password.length() >= 6) {
			return true;
		}
		else {
			throw new LengthException();
		}
	}
	

	public static boolean hasUpperAlpha(java.lang.String password) throws NoUpperAlphaException{
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}	
		throw new NoUpperAlphaException();
	}
	
	public static boolean hasLowerAlpha​(java.lang.String password) throws NoLowerAlphaException{
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				return true;
			}
		}	
		throw new NoLowerAlphaException();	
	}

	public static boolean hasDigit​(java.lang.String password) throws NoDigitException{
		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				return true;
			}
		}	
		throw new NoDigitException();
	}

	public static boolean hasSpecialChar​(java.lang.String password) throws NoSpecialCharacterException{
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher mt = pattern.matcher(password);
		boolean result = mt.matches();
		if (!result) {
			return true;
		}
		throw new NoSpecialCharacterException();
	}
	
	public static boolean NoSameCharInSequence​(java.lang.String password) throws InvalidSequenceException{
		int count = 1;		
		for (int i = 1; i < password.length(); i++) {
			if (password.charAt(i-1) == password.charAt(i)) {
				count++;
			}
			else {
				count = 1;
			}
			
			if (count == 3) {
				throw new InvalidSequenceException();
			}
		}
		return false;
	}
	
	public static boolean isValidPassword(java.lang.String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		
		if (!isValidLength(password)) {
			throw new LengthException();
		}
		else if (!hasUpperAlpha(password)) {
			throw new NoUpperAlphaException();
		}
		else if (!hasLowerAlpha​(password)) {
			throw new NoLowerAlphaException();
		}
		else if (!hasDigit​(password)) {
			throw new NoDigitException();
		}
		else if (!hasSpecialChar​(password)) {
			throw new NoSpecialCharacterException();
		}
		else if (NoSameCharInSequence​(password)) {
			throw new InvalidSequenceException();
		}
		else {
			return true;
		}
		
		
	}
	
	public static boolean hasBetweenSixAndNineChars​(java.lang.String password) {
		int length = password.length();
		if (length >= 6 && length <= 9) {
			return true;
		}
		return false;
	}

	public static boolean isWeakPassword(java.lang.String password) throws WeakPasswordException{
			try {
				if (!hasBetweenSixAndNineChars​(password) && isValidPassword(password)) {
					return false;
				}
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
					| NoSpecialCharacterException | InvalidSequenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}		
		throw new WeakPasswordException();
	}
	


	public static java.util.ArrayList<java.lang.String> getInvalidPasswords(java.util.ArrayList<java.lang.String> passwords){
		ArrayList<String> invalids = new ArrayList<String>();
		String add = "";
	
		for (int i = 0; i < passwords.size(); i++) {
			
			try {
				if (!isValidPassword(passwords.get(i))) {
				}
				
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
					| NoSpecialCharacterException | InvalidSequenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				add += passwords.get(i) + " " + e.getMessage();
				invalids.add(add);
				add = "";
			}		
		}
		return invalids;
	}
	
	


}
 