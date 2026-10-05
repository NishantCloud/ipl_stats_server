package org.monexa.ipl_stats.Controllers;


import org.monexa.ipl_stats.Services.MatchServices;
import org.monexa.ipl_stats.dto.DeliveriesResponse;
import org.monexa.ipl_stats.dto.MatchDetailsResponse;
import org.monexa.ipl_stats.dto.MatchItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    MatchServices matchServices;

//    @GetMapping("/all")
//    public List<MatchItem> getMatchItems(){
//        return matchServices.getMatchesList();
//    }
//
//    @GetMapping("/all/{seasonId}")
//    public List<MatchItem> getMatchesOfSeason(@PathVariable Long seasonId){
//        return matchServices.getMatchesListOfSeason(seasonId);
//    }

    @GetMapping("/{matchId}/deliveries")
    public ResponseEntity<DeliveriesResponse> getDeliveriesOfMatch(@PathVariable int matchId) {

        System.out.println("---------------------------------------------MATCH ID  : " + matchId);
        return ResponseEntity.ok(matchServices.getDeliveriesOfMatch(matchId));
    }

    @GetMapping("/{matchId}/details")
    public ResponseEntity<MatchDetailsResponse> getMatchDetails(@PathVariable int matchId){
        if (matchServices.getMatchDetails(matchId)==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(matchServices.getMatchDetails(matchId));
        }
    }

    @GetMapping("/all/{seasonId}")
    public ResponseEntity<List<MatchItemResponse>> getListOfMatches(@PathVariable int seasonId) {

        return ResponseEntity.ok(matchServices.getMatchList(seasonId));
    }
}
