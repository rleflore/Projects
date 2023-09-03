import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradebookTester {
	GradeBook g1;

	@BeforeEach
	public void setUp() throws Exception {
		g1 = new GradeBook(5);
		g1.addScore(50);
		g1.addScore(90);
		g1.addScore(45);
	}

	@AfterEach
	public void tearDown() throws Exception {
		g1 = null;
	}

	@Test
	public void testAddScore() {
		assertEquals(3, g1.getScoreSize(), .0001);
		assertTrue(g1.toString().equals("50.0 90.0 45.0 "));
	}
	
	@Test
	public void testSum() {
		assertEquals(185, g1.sum(), .0001);
	}
	
	@Test
	public void testMinimum() {
		assertEquals(45, g1.minimum(), .0001);
	}
	
	@Test
	public void testFinalScore() {
		assertEquals(140, g1.finalScore(), .0001);
	}

}
