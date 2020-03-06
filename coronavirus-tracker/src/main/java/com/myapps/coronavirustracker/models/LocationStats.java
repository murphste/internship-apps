package com.myapps.coronavirustracker.models;


public class LocationStats {
	
	private String state;
	private String country;
	private int latestTotalCases;
	private int prevDayTotalCases;
	private int diffFromPrevDay;
	private int globalDayDiff;
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}

	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

	
	@Override
	public String toString() {
		return "Location Stats [State = " + state + ", Country = " + country + ", Latest total cases = " + latestTotalCases
				+ "]";
	}

	public int getPrevDayTotalCases() {
		return prevDayTotalCases;
	}

	public void setPrevDayTotalCases(int prevDayTotalCases) {
		this.prevDayTotalCases = prevDayTotalCases;
	}

	public int getGlobalDayDiff() {
		return globalDayDiff;
	}

	public void setGlobalDayDiff(int globalDayDiff) {
		this.globalDayDiff = globalDayDiff;
	}

	
}

