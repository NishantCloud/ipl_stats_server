package org.monexa.ipl_stats.Controllers;

import org.monexa.ipl_stats.Services.StatsServices;
import org.monexa.ipl_stats.dto.PlayerSeasonStatsDto;
import org.monexa.ipl_stats.dto.StatsPlayerOverviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    StatsServices statsServices;
    @GetMapping("/player/overview")
    private ResponseEntity<StatsPlayerOverviewResponse> getStatsSeasonPlayerOverview(
            @RequestParam(defaultValue = "-1") int season,
            @RequestParam(defaultValue = "1") int page
    ){

        return ResponseEntity.ok(statsServices.getStatsPlayerOverview(season, page));
    };
}
