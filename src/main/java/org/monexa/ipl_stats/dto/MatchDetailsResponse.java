package org.monexa.ipl_stats.dto;

import java.util.List;

public record MatchDetailsResponse(
        Integer matchId,
        String stageLabel,
        String venue,
        String dateLabel,

        String team1ShortName,
        String team1FullName,
        String team1Score,

        String team2ShortName,
        String team2FullName,
        String team2Score,

        String resultText,

        String tossWinnerShortName,
        String tossDecision,

        PlayerOfMatch playerOfMatch,

        List<Innings> innings,

        List<Squad> team1Squad,
        List<Squad> team2Squad
) {

    public record PlayerOfMatch(
            Integer playerId,
            String playerName,
            String teamShortName,
            String summary
    ) {
    }

    public record Squad(
            Integer playerId,
            String playerName,
            String playerRoleCode,
            boolean isCaptain,
            boolean isWicketKeeper,
            boolean isImpactPlayer
    ) {
    }

    public record Innings(
            String battingTeamShortName,
            String totalScore,
            String totalOvers,
            String extras,
            List<Batting> batting,
            List<Bowling> bowling
    ) {
    }

    public record Batting(
            Integer playerId,
            String playerName,
            String dismissal,
            Integer runs,
            Integer balls,
            Integer fours,
            Integer sixes,
            Double strikeRate,
            boolean isOut
    ) {
    }

    public record Bowling(
            Integer playerId,
            String playerName,
            String overs,
            Integer maidens,
            Integer wickets,
            Double economy
    ) {
    }
}