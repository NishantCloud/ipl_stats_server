package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity(name = "innings")
public class Innings {

    @Id
    @Column(name = "innings_id")
    Integer inningsId;

    @Column(name = "innings_number")
    Integer inningsNumber;
    @Column(name = "match_id")
    Integer matchId;
    @Column(name = "batting_team_id")
    Integer battingTeamId;
    @Column(name = "is_super_over")
    boolean isSuperOver;
    @Column(name = "target_runs")
    Integer targetRuns;
    @Column(name = "target_overs")
    Float targetOvers;
}
