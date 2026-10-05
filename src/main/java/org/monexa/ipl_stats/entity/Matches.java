package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.springframework.stereotype.Component;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity(name = "matches")
@Component
public class Matches {
    @Id
    int matchId;


    @ManyToOne
    @JoinColumn(name = "season_id")
    Seasons seasons;

    @Column(name = "tournament_id")
    int tournamentId;

    @Column(name = "match_number")
    Integer matchNumber;

    @Column(name = "match_date")
    Date date;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    Venues venue;

    @Column(name = "match_type")
    String matchType;

    @Column(name = "overs_limit")
    Integer oversLimit;

    @Column(name = "balls_per_over")
    Integer ballsPerOver;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    Teams team1;


    @ManyToOne
    @JoinColumn(name = "team2_id")
    Teams team2;

    @ManyToOne
    @JoinColumn(name = "toss_winner_id")
    Teams tossWinner;

    @Column(name = "toss_decision")
    String tossDecision;

    @Column(name = "result_type")
    String resultType;

    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    Teams winnerTeam;

    @Column(name = "win_by_runs")
    Integer winByRuns;

    @Column(name = "win_by_wickets")
    Integer winByWickets;

    @ManyToOne
    @JoinColumn(name = "eliminator_team_id")
    Teams eliminatorTeam;

    @Column(name = "method")
    String method;

    @ManyToOne
    @JoinColumn(name = "player_of_match_id")
    Person playerOfMatch;

    @Column(name = "match_stage")
    String matchStage;

    @Column(name = "match_group")
    String matchGroup;




}
