package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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


    @Column(name = "season_id")
    int Season;

    @Column(name = "tournament_id")
    int tournamentId;

    @Column(name = "match_number")
    Integer matchNumber;

    @Column(name = "match_date")
    Date date;

    @Column(name = "venue_id")
    Integer venueId;

    @Column(name = "match_type")
    Integer matchType;

    @Column(name = "overs_limit")
    Integer oversLimit;

    @Column(name = "balls_per_over")
    Integer ballsPerOver;

    @Column(name = "team1_id")
    Integer team1Id;

    @Column(name = "team2_id")
    Integer team2Id;

    @Column(name = "toss_winner_id")
    Integer tossWinnerId;

    @Column(name = "toss_decision")
    String tossDecision;

    @Column(name = "result_type")
    String resultType;

    @Column(name = "winner_team_id")
    Integer winnerTeamId;

    @Column(name = "win_by_runs")
    Integer winByRuns;

    @Column(name = "win_by_wickets")
    Integer winByWickets;

    @Column(name = "eliminator_team_id")
    Integer eliminatorTeamId;

    @Column(name = "method")
    String method;

    @Column(name = "player_of_match_id")
    Integer playerOfMatchId;

    @Column(name = "match_stage")
    String matchStage;

    @Column(name = "match_group")
    String matchGroup;


}
