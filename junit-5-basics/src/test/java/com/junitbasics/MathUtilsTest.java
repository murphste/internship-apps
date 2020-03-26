package com.junitbasics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MathUtilsTest {
	
	// Create instance of class under test
	MathUtils mathUtils;
	
	/*
	 * -- Next is our setup code --
	 */
	
	/*
	 * @BeforeAll:
	 * This is the JUnit hook that runs before anything else in this testing class. A note here - this beforeAllInit() method needs to be made
	 * static, because static methods do not have a dependency on an instance. ie: As we are aiming to use the mathUtils class, 
	 * mathUtils is not yet initialised until our @BeforeEach which runs AFTER our @BeforeAll. Without static on beforeAllInit() we will see an error, as beforeAllInit() would be
	 * trying to action beforeAllInit() on a class that has not yet been instantiated.
	 */
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	
	
	/*
	 * @BeforeEach:
	 * This is the JUnit hook that lets us set up / initialise any objects that will be common to all of our tests
	 * so that we do not repeatedly have to manually create/initialise it each time. Everytime an individual test
	 * is run, JUnit will call the init() method and create a fresh instance of the mathUtils class each time.
	 */
	
	@BeforeEach
	void init() {
		mathUtils = new MathUtils();
	}
	
	
	/*
	 * @AfterEach:
	 * This is the JUnit hook that will run after each individual test - resetting any values we have adjusted to their
	 * default. 
	 */
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up...");
	}

	
	
	
	
	
	
	
	
	
	/*
	 * ---- OUR ACTUAL TESTS! ----
	 */
	
	@Test
	@DisplayName("Testing add method") // Just a visual aid when tests are listed in JUnit console
	void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		// Always include a message describing the test outcome. It appears in JUnit log & makes debugging a lot easier 
		assertEquals(expected,actual, "Add method should add two nums & equal 2");
	}
	
	@Test
	void testDivide() {
		/*
		 * The assertThrows method checks to ensure a method throws an expected error as it should do.
		 * The method takes in an expected exception (ArithmeticException here),
		 * an "executable" ie: behaviour (lamdba here), ie: the execution of divide method with 1,0 should throw error,
		 * and a message to describe the test outcome.
		 */
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0), "Divide by zero should throw");
	}
	
	
	@Test
	void testComputeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return correct circle area");
	}
	
	
	@Test
	@DisplayName("Work in progress test. Should not run")
	@Disabled
	void testDisabled() {
		fail("This test should be disabled. Eg: It is a work in progress... and we want it ignored for now.");
	}

}
