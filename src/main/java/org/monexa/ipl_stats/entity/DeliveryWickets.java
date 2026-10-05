package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity(name = "delivery_wickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DeliveryWickets {

    @Id
    @Column(name = "wicket_id")
    private Integer wicketId;

    @ManyToOne
    @JoinColumn(name = "player_out_id")
    private Person playerOut;

    @Column(name = "dismissal_kind")
    private String dismissalKind;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Deliveries delivery;


    @OneToMany(
            mappedBy = "wicket",
            fetch = FetchType.LAZY
    )
    private Set<DeliveryWicketsFielders> fielders;
}