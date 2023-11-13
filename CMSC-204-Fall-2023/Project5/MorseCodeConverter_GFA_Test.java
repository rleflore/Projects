
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverter_GFA_Test {
	String convert1;
	String convert2;
	MorseCodeTree tree;
	@Before
	public void setUp() throws Exception {
		convert1 = ".. / .... .- - . / -.. . -... ..- --. --. .. -. --.";
		convert2 = "... - .-. .- .-- -... . .-. .-. .. . ... / .- -. -.. / .. -.-. . / -.-. .-. . .- --";
		tree = new MorseCodeTree();
	}

	@After
	public void tearDown() throws Exception {
		convert1 = null;
		convert2 = null;
		tree = null;
	}
	
	@Test
	public void testFetch() {
		String code = "....";
		assertEquals("h", tree.fetch(code));
	}	

	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(convert1);
		assertEquals("i hate debugging",converter1);
	}
	
	@Test
	public void testPrintTree() {		
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}
	
	@Test
	public void testConvertMorseFileToEnglishString() {
		File file = new File("src/Daisy.txt"); 
		try {
			assertEquals("give me your answer do", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("An unwanted exception was caught", false);
		}
	}
	
	
	

}