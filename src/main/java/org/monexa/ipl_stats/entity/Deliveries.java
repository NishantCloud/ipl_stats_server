package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Deliveries {

    @Id
    @Column(name = "delivery_id")
    Integer deliveryId;

    @Column(name = "innings_id")
    Integer inningsId;

    @Column(name = "over_number")
    Integer overNumber;

    @Column(name = "ball_sequence")
    Integer ballSequence;

    @Column(name = "legal_ball_number")
    Integer legalBallNumber;

    @ManyToOne
    @JoinColumn(name = "batter_id")
    Person batter;

    @ManyToOne
    @JoinColumn(name = "bowler_id")
    Person bowler;

    @ManyToOne
    @JoinColumn(name = "non_striker_id")
    Person nonStriker;

    @Column(name = "runs_batter")
    Integer runsBatter;

    @Column(name = "runs_extras")
    Integer runsExtras;

    @Column(name = "runs_total")
    Integer runsTotal;

    @Column(name = "extra_type")
    String extraType;

    @Column(name = "is_wicket")
    boolean isWicket;


    // NEW
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Set<DeliveryWickets> wickets;
}