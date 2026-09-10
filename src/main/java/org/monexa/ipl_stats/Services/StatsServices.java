package org.monexa.ipl_stats.Services;

import org.monexa.ipl_stats.Repositories.PlayerCareerStatsRepository;
import org.monexa.ipl_stats.Repositories.PlayerSeasonStatsRepository;
import org.monexa.ipl_stats.dto.PlayerSeasonStatsOverviewDto;
import org.monexa.ipl_stats.dto.StatsPlayerOverviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;

@Service
public class StatsServices {

    @Autowired
    PlayerCareerStatsRepository careerStatsRepository;

    @Autowired
    PlayerSeasonStatsRepository seasonStatsRepository;

    public StatsPlayerOverviewResponse getStatsPlayerOverview(int seasonId, int page) {
        Pageable pages = PageRequest.of(0,page);
        StatsPlayerOverviewResponse statsPlayerOverviewResponse=null;
        if(seasonId<=0){

            statsPlayerOverviewResponse = new StatsPlayerOverviewResponse(
                    careerStatsRepository.findTopRuns(pages) ,
                    careerStatsRepository.findTopWickets(pages) ,
                    careerStatsRepository.findTopSixes(pages) ,
                    careerStatsRepository.findTopFours(pages) ,
                    careerStatsRepository.findTopHundreds(pages) ,
                    careerStatsRepository.findTopFifties(pages) ,
                    careerStatsRepository.findTopCatches(pages) ,
                    careerStatsRepository.findTopStumpings(pages)

                    );
        }else{
            statsPlayerOverviewResponse = new StatsPlayerOverviewResponse(
                    seasonStatsRepository.findTopRuns(seasonId,pages),
                    seasonStatsRepository.findTopWickets(seasonId,pages),
                    seasonStatsRepository.findTopSixes(seasonId,pages),
                    seasonStatsRepository.findTopFours(seasonId,pages),
                    seasonStatsRepository.findTopHundreds(seasonId,pages),
                    seasonStatsRepository.findTopFifties(seasonId,pages),
                    seasonStatsRepository.findTopCatches(seasonId,pages),
                    seasonStatsRepository.findTopStumpings(seasonId,pages)
            );
        }

        return statsPlayerOverviewResponse;
    }
}
