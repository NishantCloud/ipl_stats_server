package org.monexa.ipl_stats.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public record InningsDto(
        Integer inningsId,
        Integer inningsNumber,
        Integer matchId,
        Integer battingTeamId,
        boolean isSuperOver,
        Integer targetRuns,
        Float targetOvers
){
        }
