package org.monexa.ipl_stats.dto;

public record SeasonResponse (
        int seasonId,
        String seasonName,
        int seasonYear,
        int tournamentId
){
}
