package com.in28minutes.junit.helper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ QuickBeforeAfterTest.class, StringHelperTest.class })
public class TestSuiteExample {
	
	
	/*
	 * This is a JUnit test suite - created with New > JUnit > Test Suite > Select classes to group together
	 * 
	 * It is a way of grouping tests together or to test a selection of classes at a time in a "Suite".
	 * 
	 * JUnit will only run the tests pertaining to the selected classes - see @SuiteClasses annotation for selected classes under test
	 */

}
