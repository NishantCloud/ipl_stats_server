package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMatchStatsId implements Serializable {

    @Column(name = "match_id")
    private Integer matchId;

    @Column(name = "team_id")
    private Integer teamId;
}
