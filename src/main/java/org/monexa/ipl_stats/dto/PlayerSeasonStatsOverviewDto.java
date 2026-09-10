package org.monexa.ipl_stats.dto;

public record PlayerSeasonStatsOverviewDto(
        int playerId,
        String playerName,
        int seasonId,
        String seasonName,
        Number value
) {
}
