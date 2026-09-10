package org.monexa.ipl_stats.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.monexa.ipl_stats.entity.PlayerCareerStats;

public record PlayerProfileDto(
        Integer personId,
        String name,
        String fullName,
        String countryName,
        String imageUrl,
        String countryCode,
        Integer teamId,
        Boolean isBowler,
        Integer formScore,
        String formLabel,
        String playingRole,
        String playingRoleCode,
        String teamName,
        String teamShortName,
        String battingStyleShort,
        String bowlingStyle,
        String bowlingStyleShort
) {
}
