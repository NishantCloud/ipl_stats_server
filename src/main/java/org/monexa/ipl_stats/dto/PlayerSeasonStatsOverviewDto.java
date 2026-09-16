package org.monexa.ipl_stats.dto;

public record PlayerSeasonStatsOverviewDto(
        int playerId,
        int teamId,
        String playerName,
        int seasonId,
        String seasonName,
        Number value
) {
}
