package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {
	
	
	/*
	 * This is using JUnit4.
	 * Note the test method naming conventions: "test" followed by method under test, plus "_businessScenarioUnderTest"
	 * Best practice is to create a single unit test for each scenario we want to test.
	 */
	
	// Test input => expected output: 		AACD => CD 		ACD => CD		CDEF => CDEF 		CDAA => CDAA
	
	StringHelper helper;
	
	// @Before = Do this before EACH test
	@Before
	public void setup() {
		helper = new StringHelper();
	}
	
	@After
	public void tearDown() {
		System.out.println("Do this - After each test");
		// Logic here might involve disconnecting from a database connection ie: freeing up a port
	}

	
	@Test
	public void testTruncateAInFirst2Positions_Ainfirst2Positions() {
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
		// ^ arguments: (expected, actual)
	}
	
	@Test
	public void testTruncateAInFirst2Positions2_AinFirstPosition() {
		assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
	}
	
	@Test
	public void testTruncateAInFirst2Positions3_WithoutA() {
		assertEquals("CDEF", helper.truncateAInFirst2Positions("CDEF"));
	}
	
	@Test
	public void testTruncateAInFirst2Positions4_AAbeyondFirstTwoPositions () {
		assertEquals("CDAA", helper.truncateAInFirst2Positions("CDAA"));
	}
	
	
	
	//ABCD => FALSE (should return Boolean false), ABAB => TRUE, AB => TRUE, A => FALSE
	// Basic ABCD input - should result in false. Test will pass if false.
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicFalseScenario() {
		assertFalse("Should result in false. Test will pass if false.", helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
		// ^ arguments: (expected, actual)
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicTrueScenario() {
		assertTrue("Should result in true. Test will pass if true.", helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
		// ^ arguments: (expected, actual)
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicTrueScenario2() {
		assertTrue("Should result in true. Test will pass if true.", helper.areFirstAndLastTwoCharactersTheSame("AB"));
		// ^ arguments: (expected, actual)
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicFalseScenario2() {
		assertFalse("Should result in false. Test will pass if false.", helper.areFirstAndLastTwoCharactersTheSame("A"));
		// ^ arguments: (expected, actual)
	}
	
	
	
	

}
