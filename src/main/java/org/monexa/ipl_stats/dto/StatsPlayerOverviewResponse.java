package org.monexa.ipl_stats.dto;

import java.util.List;

public record StatsPlayerOverviewResponse(

        List<PlayerSeasonStatsOverviewDto> mostRuns,
        List<PlayerSeasonStatsOverviewDto> mostWickets,
        List<PlayerSeasonStatsOverviewDto> mostSixes,
        List<PlayerSeasonStatsOverviewDto> mostFours,
        List<PlayerSeasonStatsOverviewDto> mostHundreds,
        List<PlayerSeasonStatsOverviewDto> mostFifties,
        List<PlayerSeasonStatsOverviewDto> mostCatches,
        List<PlayerSeasonStatsOverviewDto> mostStumpings
) {
}
