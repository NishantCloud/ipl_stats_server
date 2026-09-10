package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "player_career_stats")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerCareerStats {

    @EmbeddedId
    private PlayerCareerStatsId id;

    //Career Batting stats
    @Column(name = "matches_played")
    private Integer matchesPlayed;
    @Column(name = "innings_batted")
    private Integer inningsBatted;
    @Column(name = "runs_scored")
    private Integer runsScored;
    @Column(name = "balls_faced")
    private Integer ballsFaced;
    @Column(name = "fours")
    private Integer fours;
    @Column(name = "sixes")
    private Integer sixes;
    @Column(name = "fifties")
    private Integer fifties;
    @Column(name = "hundreds")
    private Integer hundreds;
    @Column(name = "highest_score")
    private Integer highestScore;
    @Column(name = "times_out")
    private Integer timesOut;

    //Career Bowling Stats
    @Column(name = "innings_bowled")
    private Integer inningsBowled;
    @Column(name = "balls_bowled")
    private Integer ballsBowled;
    @Column(name = "runs_conceded")
    private Integer runsConceded;
    @Column(name = "wickets_taken")
    private Integer wicketsTaken;
    @Column(name = "best_bowling_wickets")
    private Integer bestBowlingWickets;
    @Column(name = "best_bowling_runs")
    private Integer bestBowlingRuns;

    //field stats

    @Column(name = "catches")
    private Integer catches;
    @Column(name = "stumpings")
    private Integer stumpings;
}
