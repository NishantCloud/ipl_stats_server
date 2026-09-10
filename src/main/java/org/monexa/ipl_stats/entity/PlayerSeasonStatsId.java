package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PlayerSeasonStatsId implements Serializable {
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "season_id")
    private Integer seasonId;
}
