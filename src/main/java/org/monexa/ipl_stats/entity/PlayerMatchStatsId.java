package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerMatchStatsId implements Serializable {


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "match_id")
    private Integer matchId;
}