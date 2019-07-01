/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array

		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 1, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)2, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		// upper bound test
		try {
			list1.remove(10);
			fail("index out of range");
		}
		catch (IndexOutOfBoundsException e){
			
		}
		
		// lower bound test
				try {
					list1.remove(-1);
					fail("Check out of bounds");
				}
				catch (IndexOutOfBoundsException e) {
				
				}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		list1.add(99);
		assertEquals((Integer)1, list1.get(0));
		assertEquals((Integer)99, list1.get(list1.size()-1));
		
		try{
			list1.add(null);
		}
		catch(NullPointerException e) {
			
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("shortList: size a is correct ", 2, shortList.size());
		assertEquals("emptyList: size a is correct ", 0, emptyList.size());
		assertEquals("longerList: size a is correct ", 10, longerList.size());
		assertEquals("list1: size a is correct ", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		list1.add(1, 99);
		assertEquals((Integer)1, list1.get(0));
		assertEquals((Integer)2, list1.get(2));
		assertEquals(4, list1.size());
		
		// upper bound test
		try {
			list1.add(10,10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// lower bound test
		try {
			list1.add(-1,10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		// add null element
				try {
					list1.add(1,null);
					fail("Check out of bounds");
				}
				catch (NullPointerException e) {
					
				}
		
		
		shortList.add(0, "H");
		assertEquals((String)"H", shortList.get(0));
		assertEquals((String)"A", shortList.get(1));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int a = list1.set(0, 99);
		assertEquals((Integer)99, list1.get(0));
		assertEquals(1, a);
		assertEquals(3, list1.size());
		
		try {
			list1.set(10,10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		
		try {
			list1.set(0, null);
		}
		catch(NullPointerException e) {
			
		}
	}
	
	
	// TODO: Optionally add more test methods.
}
