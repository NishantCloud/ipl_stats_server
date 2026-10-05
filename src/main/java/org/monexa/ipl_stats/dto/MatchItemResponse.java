package org.monexa.ipl_stats.dto;

import java.util.Date;

public record MatchItemResponse(
        Integer matchId,
        String team1Name,
        String team1Score,
        String team2Name,
        String team2Score,
        String winnerTeamName,
        Date matchDate,
        String winBy,
        Integer venueId,
        String venueName,
        String seasonName,
        String groupStage
) {}
