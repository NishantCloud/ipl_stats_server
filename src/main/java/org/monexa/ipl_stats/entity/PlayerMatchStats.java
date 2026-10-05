package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "player_match_stats")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerMatchStats {

    @EmbeddedId
    private PlayerMatchStatsId id;

    // Batting stats
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "runs_scored")
    private Integer runsScored;
    @Column(name = "balls_faced")
    private Integer ballsFaced;
    @Column(name = "fours")
    private Integer fours;
    @Column(name = "sixes")
    private Integer sixes;
    @Column(name = "is_out")
    private boolean isOut;

    // Bowling Stats
    @Column(name = "balls_bowled")
    private Integer ballsBowled;
    @Column(name = "runs_conceded")
    private Integer runsConceded;
    @Column(name = "wickets_taken")
    private Integer wicketsTaken;

    //field stats

    @Column(name = "catches")
    private Integer catches;
    @Column(name = "stumpings")
    private Integer stumpings;
    @Column(name = "run_outs")
    private Integer runOuts;
    @Column(name = "batting_position")
    private Integer battingPosition;
    @Column(name = "maidens")
    private Integer maidens;
}
