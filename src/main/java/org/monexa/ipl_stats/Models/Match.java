package org.monexa.ipl_stats.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.monexa.ipl_stats.entity.Teams;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "matches")
public class Match {

    @Id
    @Column(name = "match_id")
    private int matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team1_id")
    private Teams team1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team2_id")
    private Teams team2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_team_id")
    private Teams winnerTeam;

    @Column(name = "season_id")
    private int seasonId;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_stage")
    private String matchStage;



}
