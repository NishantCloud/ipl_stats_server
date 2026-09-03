package org.monexa.ipl_stats.Models;

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
@Entity(name = "player_profiles")
public class Player {
    @Id
    @Column(name = "person_id")
    private int personId;


    @Column(name = "display_name")
    private String displayName;


    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "team_id")
    private int teamId;


}
