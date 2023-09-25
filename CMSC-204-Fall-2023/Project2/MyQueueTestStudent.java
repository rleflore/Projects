import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyQueueTestStudent {

	public MyQueue<Character> test;
	
	@BeforeEach
	void setUp() throws Exception {
		test = new MyQueue<>();
		System.out.println(test.isFull());
	}
	

	@AfterEach
	void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testEnqueue() {
		
		try {
			assertEquals(true, test.enqueue('a'));
			assertEquals(true, test.enqueue('b'));
			assertEquals(true, test.enqueue('c'));
			System.out.println(test.toString());
		}
		catch (QueueOverflowException e){
			assertTrue(false, "This should have caused an QueueOverflowException");
		}
	}

}
