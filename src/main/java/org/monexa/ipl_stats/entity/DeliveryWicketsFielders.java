package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "delivery_wicket_fielders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DeliveryWicketsFielders {



    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wicket_id")
    private DeliveryWickets wicket;
}