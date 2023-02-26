import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {
	GradeBook g1;
	GradeBook g2;

	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		
		g1.addScore(20);
		g1.addScore(40);
		g1.addScore(60);
		
		g2.addScore(50);
		g2.addScore(70);
		g2.addScore(90);
	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(g1.toString().equals("20.0 40.0 60.0 "));
		assertTrue(g2.toString().equals("50.0 70.0 90.0 "));
		
		assertEquals(3, g1.getScore(), .001);
		assertEquals(3, g2.getScore(), .001);
	}
	

	@Test
	void testSum() {
		assertEquals(120, g1.sum(), .0001);
		assertEquals(210, g2.sum(), .0001);
	}

	@Test
	void testMinimum() {
		assertEquals(20, g1.minimum(), .001);
		assertEquals(50, g2.minimum(), .001);
	}

	@Test
	void testFinalScore() {
		assertEquals(100, g1.finalScore(), .001);
		assertEquals(160, g2.finalScore(), .001);
	}

}
