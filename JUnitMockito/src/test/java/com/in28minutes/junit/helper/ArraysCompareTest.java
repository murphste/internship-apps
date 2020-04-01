package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArraysCompareTest {

	
	/*
	 * This class is a template guide to testing values in arrays
	 */
	
	//Testing Arrays.sort method
	@Test
	public void testArraySort_RandomArray() {
		int[] numbers = {12,3,4,1};
		int[] expected = {1,3,4,12};
		Arrays.sort(numbers);
		// This assert method will check both arrays and compare values to ensure the output is equal to what we expect
		assertArrayEquals(expected, numbers);
	}
	
	/* 
	 * Here testing that an exception is thrown as expected
	 * If our array is sorted as null it will throw the expected exception and test will pass
	 * If, for example, we pass an empty array (different to null) eg: numbers={}, test will FAIL as expected exception will not be thrown
	 */
	@Test(expected=NullPointerException.class)
	public void testArraySort_NullArray() {
		int[] numbers = null;
			Arrays.sort(numbers);
	}
	
	
	/*
	 * Here we are testing the PERFORMANCE of the array sort by adding a time limit (timeout=100ms)
	 * Useful if we had a performance benchmark or were limited somehow
	 */
	@Test(timeout=100)
	public void testSort_Performance() {
		int array[] = {12,23,4};
		for(int i=1; i<=100000; i++) {
			array[0] = i;
			Arrays.sort(array);
		}
	}
}
