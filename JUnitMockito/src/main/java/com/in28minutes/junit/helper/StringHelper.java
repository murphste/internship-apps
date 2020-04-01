package com.in28minutes.junit.helper;

public class StringHelper {

	/*
	 * Removes first two instances of AA if they are both at pos 1 & 2 in the String eg:
	 * AACD => CD 	ACD => CD	CDEF => CDEF 	CDAA => CDAA
	 */
	public String truncateAInFirst2Positions(String str) {
		if (str.length() <= 2)
			return str.replaceAll("A", "");

		String first2Chars = str.substring(0, 2);
		String stringMinusFirst2Chars = str.substring(2);

		return first2Chars.replaceAll("A", "") 
				+ stringMinusFirst2Chars;
	}

	
	//ABCD => FALSE (should return Boolean false) , AB => TRUE, A => FALSE
	public boolean areFirstAndLastTwoCharactersTheSame(String str) {

		if (str.length() <= 1)
			return false;
		if (str.length() == 2)
			return true;

		String first2Chars = str.substring(0, 2);

		String last2Chars = str.substring(str.length() - 2);

		return first2Chars.equals(last2Chars);
	}

}
