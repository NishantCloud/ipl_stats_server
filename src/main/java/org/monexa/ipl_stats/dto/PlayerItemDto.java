package org.monexa.ipl_stats.dto;

public record PlayerItemDto(
        int personId,
        String name,
        String fullName,
        String imageUrl,
        String countryCode,
        String countryName,
        String teamName,
        String teamShortName,
        int teamId,
        String playingRole,
        boolean isBowler,
        int formScore,
        int runsScored,
        int wicketsTaken
) {}
