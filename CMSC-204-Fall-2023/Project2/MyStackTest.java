import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	// STUDENT: add variables as needed for your student tests
	
	@BeforeEach
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
		doubleS = new MyStack<Double>(5);
		doubleS.push(3.9);
		doubleS.push(1.2);
		doubleS.push(10.3);
		doubleS.push(12.1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() throws StackUnderflowException {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		stringS.pop();
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push(d);
		stringS.push(e);
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
		catch (StackUnderflowException e){
			assertTrue(true, "This should have caused an StackUnderflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
	}

	@Test
	public void testPopStudent() {
		try {
			assertEquals(12.1, doubleS.pop());
			assertEquals(10.3, doubleS.pop());
			assertEquals(1.2, doubleS.pop());
			assertEquals(3.9, doubleS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			doubleS.pop();
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
		catch (StackUnderflowException e){
			assertTrue(true, "This should have caused an StackUnderflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an StackUnderflowException");
		}
	}

	@Test
	public void testTop() throws StackUnderflowException, StackOverflowException {
		assertEquals(c, stringS.top());
		stringS.push(d);
		assertEquals(d, stringS.top());
		stringS.pop();
		stringS.pop();
		assertEquals(b, stringS.top());		
	}

	@Test
	public void testSize() throws StackOverflowException, StackUnderflowException {
		assertEquals(3, stringS.size());
		stringS.push(d);
		assertEquals(4, stringS.size());
		stringS.pop();
		stringS.pop();
		assertEquals(2, stringS.size());
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue(false, "This should have caused an StackOverflowException");
		}
		catch (StackOverflowException e){
			assertTrue(true, "This should have caused an StackOverflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an StackOverflowException");
		}
	}

	@Test
	public void testPushStudent() {
		try {
			assertEquals(4, doubleS.size());
			assertEquals(true, doubleS.push(1.2));
			assertEquals(5, doubleS.size());
			assertEquals(true, doubleS.push(2.4));
			assertEquals(5, doubleS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			doubleS.push(5.3);
			assertTrue(false, "This should have caused an StackOverflowException");
		}
		catch (StackOverflowException e){
			assertTrue(true, "This should have caused an StackOverflowException");
		}
		catch (Exception e){
			assertTrue(false, "This should have caused an StackOverflowException");
		}
	}
	
	@Test
	public void testToString() throws StackOverflowException {
		assertEquals("abc", stringS.toString());
		stringS.push(d);
		assertEquals("abcd", stringS.toString());
		stringS.push(e);
		assertEquals("abcde", stringS.toString());
	}

	@Test
	public void testToStringStudent() {
		assertEquals("3.91.210.312.1", doubleS.toString());
		doubleS.push(23.3);
		assertEquals("3.91.210.312.123.3", doubleS.toString());
	}
	
	@Test
	public void testToStringDelimiter() throws StackOverflowException {
		assertEquals("a%b%c", stringS.toString("%"));
		stringS.push(d);
		assertEquals("a&b&c&d", stringS.toString("&"));
		stringS.push(e);
		assertEquals("a/b/c/d/e", stringS.toString("/"));
	}

	@Test
	public void testFill() throws StackOverflowException, StackUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new MyStack<String>(5);
		//fill with an ArrayList
		stringS.fill(fill);
		assertEquals(3,stringS.size());
		assertEquals("carrot", stringS.pop());
		assertEquals("banana", stringS.pop());
		assertEquals("apple", stringS.pop());		
	}

}
