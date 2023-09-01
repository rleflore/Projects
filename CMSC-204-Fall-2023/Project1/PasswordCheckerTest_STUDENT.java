
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwordsArray; 
	String password = "short";

	
	@Before
	public void setUp() throws Exception {
		String[] p = {"23SCHOOL", "2Cool4school", "Hello@123"};
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(p));
		
	}

	@After
	public void tearDown() throws Exception {
		passwordsArray = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		//Test with a password more than 6 characters
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("sixteen"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
		
		//Test with a password less than 6 characters
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(password);
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
		
	}
	
	/**
	 * Test if the password matches
	 * This test should throw a UnmatchedException for second case.
	 */
	@Test
	public void testIsValidPasswordComparePasswords()
	{
		Throwable exception = assertThrows(UnmatchedException.class, new Executable() {			
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords(password, "tallest");				
			}
		});
		
		assertEquals("Passwords do not match", exception.getMessage());
	}
	
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		//Test with password with NO uppercase
		Throwable exception = Assertions.assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha("eggnog");
			}			
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
		
		//Test with password WITH uppercase
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("eggNog"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		//Test with password with NO lowercase
		Throwable exception = Assertions.assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha​("23SCHOOL");
			}			
		});
		assertEquals("The password must contain at least one lowercase alphabetic character", exception.getMessage());
		
		//Test with password WITH lowercase
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha​("EGGNoG"));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test if the password is weak
	 * This test should throw a WeakPasswordException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		//Test with password that is week 
		Throwable exception = Assertions.assertThrows(WeakPasswordException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isWeakPassword("roseLF@32");
			}			
		});
		assertEquals("The password is OK but weak - it contains fewer than 10 characters", exception.getMessage());
		
	}
	
	/**
	 * Test if the password has a special character
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordSpecialCharacter()
	{
		//Test with password that does not have a special character
		Throwable exception = Assertions.assertThrows(NoSpecialCharacterException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSpecialChar​("AAAChoo1");
			}			
		});
		assertEquals("The password must contain at least one special character", exception.getMessage());
		
		//Test with special character
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha​("AACho@1"));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		//Test with password that has more than 2 same character
		Throwable exception = Assertions.assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.NoSameCharInSequence​("AAAChoo!1");
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
		
		
		//Test with password that doesn't have 2 or more characters in sequence
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha​("AAlb24"));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		//Test with password that doesn't have a digit
		Throwable exception = Assertions.assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit​("nicely");
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
		
		
		//Test with password that does have a digit
		try {
			assertTrue(PasswordCheckerUtility.hasDigit​("n1cely"));
		} catch (NoDigitException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	
	@Test
	public void testIsValidPasswordSuccessful() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Hello@123"));
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoSpecialCharacterException
				| InvalidSequenceException | NoDigitException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(results.size(),2);
		assertEquals(results.get(0), "23SCHOOL The password must contain at least one lowercase alphabetic character");
		assertEquals(results.get(1), "2Cool4school The password must contain at least one special character");
	}
	
}
