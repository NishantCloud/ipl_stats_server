package org.monexa.ipl_stats.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "teams")
public class Team {

    @Id
    @Column(name = "team_id")
    private int teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "short_name")
    private String shortName;
}
