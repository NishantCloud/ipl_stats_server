package org.monexa.ipl_stats.dto;

public record PlayerCareerStatsDto(
        Integer tournamentId,
        //Career Batting Stats
        Integer matchesPlayed,
        Integer inningsBatted,
        Integer runsScored,
        Integer ballsFaced,
        Integer fours,
        Integer sixes,
        Integer fifties,
        Integer hundreds,
        Integer highestScore,
        Integer timesOut,

        //Career Bowling Stats
        Integer inningsBowled,
        Integer ballsBowled,
        Integer runsConceded,
        Integer wicketsTaken,
        Integer bestBowlingWickets,
        Integer bestBowlingRuns,

        //field stats
        Integer catches,
        Integer stumpings
) {
}
