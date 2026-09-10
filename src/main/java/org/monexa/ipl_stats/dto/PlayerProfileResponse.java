package org.monexa.ipl_stats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerProfileResponse {

    PlayerProfileDto playerProfile;
    PlayerCareerStatsDto playerCareerStats;
    List<PlayerSeasonStatsDto> playerSeasonStatsList;


}
