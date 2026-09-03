package org.monexa.ipl_stats.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchItem {
    private String team1Name;
    private String team2Name;
    private String winnerTeamName;
    private LocalDate matchDate;
    private String matchStage;
}
