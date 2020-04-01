package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class StringHelperParameterizedTest1 {
	
	
	/*
	 * This version of the earlier StringHelperTest.java class uses Parameterized tests for the testTruncateAInFirst2Positions() method
	 * Parameterized tests allow us to prepare a list of values to test & run the same test over and over again using different values.
	 * 1. Note the @RunWith(....) annotation on class
	 * 2. Set up a Collection to prepare the test data/conditions in a collection so we can pass into unit tests
	 * 3. Create a public constructor that takes in what is equivalent to one "row" of test data.
	 * 4. Create an instance variable for each "column" of test data.
	 * 5. Create your test case(s) using the instance variables as the source of the test data.
	 */
	
	// Test input => expected output: 		AACD => CD 		ACD => CD		CDEF => CDEF 		CDAA => CDAA
	
	StringHelper helper = new StringHelper();
	
	
	private String input;
	private String expectedOutput;
	
	
	
	
	public StringHelperParameterizedTest1(String input, String expectedOutput) {
		super();
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Parameters
	public static Collection <String[]> testConditions() {
		// Could have a long list of test data here prepared. Will be effective once the test logic to be applied is consistent for all data
		// For each array index, first value is the input value - second is the expected output value
		String expectedOutputs[][] = {{"AACD","CD"},{"ACD", "CD"}}; 
		return Arrays.asList(expectedOutputs);
	}

	@Test
	public void testTruncateAInFirst2Positions() {
		assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
		// ^ arguments: (expected, actual) as before
		//... this time passing the variables we populate above in testConditions() method
	}
	
	

}
