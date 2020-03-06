package com.myapps.coronavirustracker.controllers;

import com.myapps.coronavirustracker.services.CoronavirusDataService;
import java.util.List;
import com.myapps.coronavirustracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronavirusDataService.getAllStats();
        //These two lines, ie: how sum and diff are calculated use a stream() lambda - like a for:each loop
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        
        
        
        /*
         * Below we assign the model with the attributes from above. With model populated, we feed
         * to Thymeleaf for UI (home.html)
         */
        
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}