package org.monexa.ipl_stats.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity(name = "tournaments")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tournaments {

    @Id
    @Column(name =  "tournament_id")
    private Integer tournamentId;

    @Column(name =  "tournament_name")
    private String tournamentName;

    @Column(name =  "short_name")
    private String shortName;

    @Column(name =  "country")
    private String country;
}
