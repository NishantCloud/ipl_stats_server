package org.monexa.ipl_stats.dto;

public record WicketDto(
        Integer wicketId,
        Integer playerOutId,
        String playerOutName,
        Integer fielderId,
        String fielderName,
        String wicketType

) {
}
