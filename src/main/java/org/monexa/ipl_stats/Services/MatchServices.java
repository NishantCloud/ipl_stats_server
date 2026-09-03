package org.monexa.ipl_stats.Services;

import org.monexa.ipl_stats.Models.Match;
import org.monexa.ipl_stats.Models.MatchItem;
import org.monexa.ipl_stats.Repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
public class MatchServices {

    @Autowired
    MatchRepository matchRepo;

     public List<MatchItem> getMatchesList(){
         List<MatchItem> list = new ArrayList<>();
        for(Match match: matchRepo.findAllByOrderByMatchDateDesc()){
            if (match!=null)
            {
                String team1 = match.getTeam1() != null
                        ? match.getTeam1().getTeamName()
                        : "Unknown";

                String team2 = match.getTeam2() != null
                        ? match.getTeam2().getTeamName()
                        : "Unknown";

                String winner = match.getWinnerTeam() != null
                        ? match.getWinnerTeam().getTeamName()
                        : "Not Known";

                list.add(new MatchItem(
                        team1,
                        team2,
                        winner,
                        match.getMatchDate(),
                        match.getMatchStage()
                ));
            }
        }
        return list;
    }

    public List<MatchItem> getMatchesListOfSeason(Long seasonId) {
        List<MatchItem> list = new ArrayList<>();
        for(Match match: matchRepo.findBySeasonIdOrderByMatchDateDesc(seasonId)){
            if (match!=null)
            {
                String team1 = match.getTeam1() != null
                        ? match.getTeam1().getTeamName()
                        : "Unknown";

                String team2 = match.getTeam2() != null
                        ? match.getTeam2().getTeamName()
                        : "Unknown";

                String winner = match.getWinnerTeam() != null
                        ? match.getWinnerTeam().getTeamName()
                        : "Not Known";

                list.add(new MatchItem(
                        team1,
                        team2,
                        winner,
                        match.getMatchDate(),
                        match.getMatchStage()
                ));
            }
        }
        return list;
    }
}
