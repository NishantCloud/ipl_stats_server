package org.monexa.ipl_stats.Services;

import org.monexa.ipl_stats.Repositories.SeasonRepository;
import org.monexa.ipl_stats.dto.SeasonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonServices {

    @Autowired
    SeasonRepository seasonRepository;
    public List<SeasonResponse> getAllSeasons() {

       return  seasonRepository.findAllByOrderByYearDesc().stream()
               .map(seasons ->
                       new SeasonResponse(
                               seasons.getSeasonId(),
                               seasons.getSeasonName(),seasons.getYear(),
                               seasons.getTournamentId())).toList();

    }
}
