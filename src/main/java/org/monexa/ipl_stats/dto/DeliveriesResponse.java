package org.monexa.ipl_stats.dto;

import java.util.List;

public record DeliveriesResponse(
        List<InningsDto> innings,
        List<DeliveriesDto> deliveries
) {
}
