package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "playing_xi")
@Table(name = "playing_xi")
public class PlayingXI {
    @Id
    @Column(name = "playing_xi_id")
    Integer playingXiId;

    @Column(name = "match_id")
    Integer matchId;
    @Column(name = "team_id")
    Integer teamId;
    @Column(name = "person_id")
    Integer personId;
}
