package org.monexa.ipl_stats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.StyleContext;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "seasons")
public class Seasons {

    @Id
    @Column(name = "season_id")
    private Integer seasonId;

    @Column(name = "tournament_id")
    private Integer tournamentId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "season_name")
    private String seasonName;
}
