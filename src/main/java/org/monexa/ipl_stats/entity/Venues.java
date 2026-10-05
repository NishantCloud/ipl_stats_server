package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "venues")
@Entity(name = "venues")
public class Venues {

    @Id
    @Column(name = "venue_id")
    Integer venueId;

    @Column(name = "venue_name")
    String venueName;

    @Column(name = "city")
    String city;

    @Column(name = "country")
    String country;

}
