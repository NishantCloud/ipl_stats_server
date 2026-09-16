package org.monexa.ipl_stats.dto;

import jakarta.persistence.Column;

public record DeliveriesDto(
        Integer inningsId,
        Integer overNumber,
        Integer ballSequence,
        Integer legalBallNumber,
        Integer batterId,
        Integer bowlerId,
        Integer nonStrikerId,
        Integer runsBatter,
        Integer runsExtras,
        Integer runsTotal,
        String extraType,

        boolean isWicket,
        WicketDto wicketInfo

) {


}
