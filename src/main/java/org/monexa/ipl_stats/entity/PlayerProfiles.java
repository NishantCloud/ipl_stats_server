package org.monexa.ipl_stats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "player_profiles")
@Entity
public class PlayerProfiles {
    @Id
    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "name")
    private String name;

    @Column(name = "official_full_name")
    private String fullName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "playing_role")
    private String playingRole;

    @Column(name = "playing_role_code")
    private String playingRoleCode;

    @ManyToOne
    @JoinColumn(name = "current_team_id")
    private Teams team;


    @Column(name = "batting_style_short")
    private String battingStyleShort;

    @Column(name = "bowling_style_short")
    private String bowlingStyleShort;

    @Column(name = "bowling_style")
    private String bowlingStyle;


}
