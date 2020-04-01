package com.in28minutes.junit.helper;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuickBeforeAfterTest {
	
	
	/*
	 * This example is doing a setup at the beginning of the class
	 * This means that all tests can use the items initialized once this has run
	 */
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Before class setup....");
	}
	

	/*
	 * These are examples of doing a setup and teardown before & after EACH test:
	 */
	
	@Before
	public void setup() {
		System.out.println("Before test");
	}
	
	@Test
	public void test1() {
		System.out.println("Test 1 executed");
	}
	
	@Test
	public void test2() {
		System.out.println("Test 2 executed");
	}
	
	public void tearDown() {
		System.out.println("After test");
	}
	
	
	/*
	 * This example is of a class wide teardown at the end of the class
	 * This de-constructs any items initialised in the beforeClass setup method
	 */
	
	@AfterClass
	public static void afterClass() {
		System.out.println("After class");
	}
}
