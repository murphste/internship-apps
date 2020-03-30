package com.myapps.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.myapps.coronavirustracker.models.LocationStats;


@Service
public class CoronavirusDataService {
	
	
	private String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	// List to store all latest virus data (objects are instances of LocationStats model class)
	private List<LocationStats> allStats = new ArrayList<>();
	
	// Getter for above list - needed to retrieve stats list in HomeController, in order to pass this data to the home.html.
	public List<LocationStats> getAllStats() {
		return allStats;
	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats = new ArrayList<>();
		// newStats Here for concurrency reasons - so if method is invoked while allStats is updating..
		// user will still be served the most current stats, and we'll not have an error
		
		// Define http client, request and response + what to do with response
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(VIRUS_DATA_URL))
			.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		/* Need to parse the response (https://commons.apache.org/proper/commons-csv/user-guide.html)
		 - 1. need a Reader instance, converted httpResponse String to SR object
		 - 2. create an Iterable object so we can iterate through CSV with a loop
		*/
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		
		// Iterate through record set and populate a LocationStats instance for each iteration
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
		    locationStat.setState(record.get("Province/State"));
		    locationStat.setCountry(record.get("Country/Region"));
		    /* Added complexity now setting latestTotalCases as the csv record source grows each day with updates.
		       Here are saying we want to get size of the record each day and parse this to integer
		       as it is String by default (from commons-csv)
		    */
		    
		    // Here we are finding the latest cases for each record (row in our CSV)
		    int latestCases = Integer.parseInt(record.get(record.size() - 1));
		    locationStat.setLatestTotalCases(latestCases);
		    
		    // Next we want to find the total of same for the previous day (to show difference day by day for each region)
		    int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
		    locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
    
		    newStats.add(locationStat);
		}
		
		
		
		/* Now we update the allStats (which prior to method call is the latest user deliverable records on hand) 
			with the newStats. ie: those generated by the call to our data source in the method.
	    */ 
		this.allStats = newStats;
	}
	
	
	
	

}
