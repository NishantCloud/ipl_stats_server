package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "team_match_stats")
@Data
@Component
public class TeamMatchStats {

    @EmbeddedId
    private TeamMatchStatsId id;

    @Column(name = "innings_number")
    Integer inningsNumber;
    @Column(name = "total_runs")
    Integer totalRuns;
    @Column(name = "total_wickets")
    Integer totalWickets;
    @Column(name = "overs_played")
    Double oversPlayed;


}
