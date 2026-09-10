package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerCareerStatsId implements Serializable {

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "tournament_id")
    private Integer tournamentId;
}
