package org.monexa.ipl_stats.dto;

public record PlayerItemResponse(
        int playerId,
        String playerName,
        int teamId
) {
}
